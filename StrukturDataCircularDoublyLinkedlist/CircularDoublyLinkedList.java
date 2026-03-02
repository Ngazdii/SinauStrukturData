import java.util.Scanner;

public class CircularDoublyLinkedList {

    static class Node {
        String berita;
        Node next;
        Node prev;

        Node(String berita) {
            this.berita = berita;
        }
    }

    static Node head = null;
    static int count = 0;
    static volatile boolean running = true;

    static void insertBerita(String berita) {
        Node newNode = new Node(berita);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
        System.out.println("Berita berhasil ditambahkan.");
    }

    static void deleteBerita(int pos) {
        if (head == null || pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Node current = head;

        if (count == 1) {
            head = null;
        } else if (pos == 1) {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        } else {
            for (int i = 1; i < pos; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        count--;
        System.out.println("Berita berhasil dihapus.");
    }

    static void tampilBeritaTertentu(int pos) {
        if (head == null || pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Node current = head;
        for (int i = 1; i < pos; i++) {
            current = current.next;
        }

        System.out.println("Berita ke-" + pos + ": " + current.berita);
    }

    static void tampilAuto(boolean forward) {
        if (head == null) {
            System.out.println("Tidak ada berita.");
            return;
        }

        running = true;

        Thread displayThread = new Thread(() -> {
            Node current = forward ? head : head.prev;

            System.out.println("\nTekan 0 lalu ENTER untuk berhenti...\n");

            while (running) {
                System.out.println("Berita: " + current.berita);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    break;
                }
                current = forward ? current.next : current.prev;
            }
        });

        Thread inputThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String input = sc.nextLine();
                if (input.equals("0")) {
                    running = false;
                    break;
                }
            }
        });

        displayThread.start();
        inputThread.start();

        try {
            displayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== MENU TEKS BERITA =====");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan berita forward");
            System.out.println("4. Tampilkan berita backward");
            System.out.println("5. Tampilkan berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih menu: ");

            pilihan = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan berita: ");
                    insertBerita(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Masukkan nomor berita: ");
                    deleteBerita(sc.nextInt());
                    break;

                case 3:
                    tampilAuto(true);
                    break;

                case 4:
                    tampilAuto(false);
                    break;

                case 5:
                    System.out.print("Masukkan nomor berita: ");
                    tampilBeritaTertentu(sc.nextInt());
                    break;

                case 6:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 6);
    }
}