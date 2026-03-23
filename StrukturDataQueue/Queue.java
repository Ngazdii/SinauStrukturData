import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Queue extends JFrame {

    static class Node {
        int nomor;
        String nama;

        Node(int nomor, String nama) {
            this.nomor = nomor;
            this.nama = nama;
        }
    }

    LinkedList<Node> queue = new LinkedList<>();
    int nomor = 1;

    JLabel lblNomor, lblNama;
    JTextArea area;
    JTextField inputNama;

    public Queue() {
        setTitle("ANTRIAN BANK");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // HEADER
        JLabel header = new JLabel("ANTRIAN BANK", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 40));
        mainPanel.add(header, BorderLayout.NORTH);

        // CENTER - DISPLAY PANEL
        JPanel displayPanel = new JPanel(new BorderLayout(20, 20));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Sedang Dipanggil"));
        
        lblNomor = new JLabel("-");
        lblNomor.setFont(new Font("Segoe UI", Font.BOLD, 140));
        lblNomor.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblNama = new JLabel("Menunggu...");
        lblNama.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        lblNama.setHorizontalAlignment(SwingConstants.CENTER);
        
        displayPanel.add(lblNomor, BorderLayout.CENTER);
        displayPanel.add(lblNama, BorderLayout.SOUTH);
        
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // SOUTH - CONTROL PANEL
        JPanel controlPanel = new JPanel(new BorderLayout(15, 15));
        
        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputNama = new JTextField(25);
        inputNama.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(new JLabel("Nama:"), BorderLayout.WEST);
        inputPanel.add(inputNama, BorderLayout.CENTER);
        controlPanel.add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        JButton btnAmbil = new JButton("Ambil Antrian");
        JButton btnTampil = new JButton("Tampilkan");
        JButton btnPanggil = new JButton("Panggil");
        
        btnAmbil.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTampil.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPanggil.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnAmbil.setFocusPainted(false);
        btnTampil.setFocusPainted(false);
        btnPanggil.setFocusPainted(false);
        
        buttonPanel.add(btnAmbil);
        buttonPanel.add(btnTampil);
        buttonPanel.add(btnPanggil);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

        // Area
        area = new JTextArea(8, 40);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setEditable(false);
        area.setLineWrap(true);
        
        JScrollPane scrollArea = new JScrollPane(area);
        scrollArea.setBorder(BorderFactory.createTitledBorder("Daftar Antrian"));
        controlPanel.add(scrollArea, BorderLayout.SOUTH);
        
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // EVENT LISTENERS
        btnAmbil.addActionListener(e -> ambil());
        btnTampil.addActionListener(e -> tampil());
        btnPanggil.addActionListener(e -> panggil());
    }

    private void ambil() {
        String nama = inputNama.getText().trim();
        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        queue.add(new Node(nomor, nama));
        nomor++;
        inputNama.setText("");
        tampil();
    }

    private void tampil() {
        StringBuilder sb = new StringBuilder();
        if (queue.isEmpty()) {
            sb.append("[Antrian Kosong]");
        } else {
            int index = 1;
            for (Node n : queue) {
                sb.append(String.format("%d. No %d - %s%n", index++, n.nomor, n.nama));
            }
        }
        area.setText(sb.toString());
    }

    private void panggil() {
        if (queue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Antrian kosong!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Node n = queue.poll();
        lblNomor.setText(String.valueOf(n.nomor));
        lblNama.setText(n.nama);
        
        // Jalankan text-to-speech di thread terpisah
        new Thread(() -> {
            speakAI("Nomor " + n.nomor + ", atas nama " + n.nama + ", silakan ke loket");
        }).start();
        
        System.out.println(">>> Memanggil: No " + n.nomor + " - " + n.nama);
        
        tampil();
    }

    private void speakAI(String text) {
        new Thread(() -> {
            try {
                // Coba pakai Windows Native Speech Synthesizer
                // Dengan voice Indonesia jika tersedia
                String[] cmd = {
                    "powershell.exe",
                    "-Command",
                    "Add-Type -AssemblyName System.Speech; " +
                    "$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                    "$speak.Rate = -1; " +  // Speed setting
                    "$speak.Volume = 100; " +
                    "$speak.Speak('" + text.replace("'", "''") + "')"
                };
                
                Process p = Runtime.getRuntime().exec(cmd);
                p.waitFor();
                
                System.out.println("TTS executed: " + text);
                
            } catch (Exception e) {
                System.err.println("Error speaking: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            FlatDarkLaf.install();
        } catch (Exception e) {
            System.err.println("FlatLaf setup failed: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new Queue().setVisible(true);
        });
    }
}
