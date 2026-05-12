import java.io.*;
import java.util.*;

// ======================================
// CLASS DATA
// ======================================
class Data {
    int id;
    String nama;

    public Data(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama;
    }
}

// ======================================
// MAIN CLASS
// ======================================
public class HeapProgram {

    // ======================================
    // MIN HEAP & MAX HEAP
    // ======================================

    static PriorityQueue<Data> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(d -> d.id));

    static PriorityQueue<Data> maxHeap =
            new PriorityQueue<>((a, b) -> b.id - a.id);

    // ======================================
    // LOAD CSV
    // ======================================
    static void loadCSV(String filename) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line;

            // skip header
            br.readLine();

            int jumlah = 0;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());
                String nama = data[1].trim();

                Data barang = new Data(id, nama);

                minHeap.add(barang);
                maxHeap.add(barang);

                jumlah++;
            }

            br.close();

            System.out.println("\n" + jumlah +
                    " data berhasil dimasukkan ke Min Heap & Max Heap!");

        } catch (FileNotFoundException e) {

            System.out.println("\nFile tidak ditemukan!");

        } catch (Exception e) {

            System.out.println("\nTerjadi error: " + e.getMessage());
        }
    }

    // ======================================
    // INSERT DATA
    // ======================================
    static void insertData(Scanner input) {

        try {

            System.out.print("Masukkan ID   : ");
            int id = Integer.parseInt(input.nextLine());

            System.out.print("Masukkan Nama : ");
            String nama = input.nextLine();

            Data barang = new Data(id, nama);

            minHeap.add(barang);
            maxHeap.add(barang);

            System.out.println("\nData berhasil ditambahkan!");

        } catch (Exception e) {

            System.out.println("\nInput tidak valid!");
        }
    }

    // ======================================
    // SHOW ASCENDING
    // ======================================
    static void showAscending() {

        if (minHeap.isEmpty()) {
            System.out.println("\nMin Heap kosong!");
            return;
        }

        PriorityQueue<Data> temp =
                new PriorityQueue<>(minHeap);

        System.out.println("\n===== DATA ASCENDING (MIN HEAP) =====");

        while (!temp.isEmpty()) {

            System.out.println(temp.poll());
        }
    }

    // ======================================
    // SHOW DESCENDING
    // ======================================
    static void showDescending() {

        if (maxHeap.isEmpty()) {
            System.out.println("\nMax Heap kosong!");
            return;
        }

        PriorityQueue<Data> temp =
                new PriorityQueue<>(maxHeap);

        System.out.println("\n===== DATA DESCENDING (MAX HEAP) =====");

        while (!temp.isEmpty()) {

            System.out.println(temp.poll());
        }
    }

    // ======================================
    // DELETE MIN HEAP
    // ======================================
    static void deleteMinHeap() {

        if (minHeap.isEmpty()) {

            System.out.println("\nMin Heap kosong!");
            return;
        }

        Data deleted = minHeap.poll();

        System.out.println("\nData berhasil dihapus dari Min Heap:");
        System.out.println(deleted);
    }

    // ======================================
    // DELETE MAX HEAP
    // ======================================
    static void deleteMaxHeap() {

        if (maxHeap.isEmpty()) {

            System.out.println("\nMax Heap kosong!");
            return;
        }

        Data deleted = maxHeap.poll();

        System.out.println("\nData berhasil dihapus dari Max Heap:");
        System.out.println(deleted);
    }

    // ======================================
    // SHOW HEAP
    // ======================================
    static void showHeap() {

        System.out.println("\n===== MIN HEAP =====");
        System.out.println(minHeap);

        System.out.println("\n===== MAX HEAP =====");
        System.out.println(maxHeap);
    }

    // ======================================
    // MAIN PROGRAM
    // ======================================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n====================================");
            System.out.println("      PROGRAM MIN & MAX HEAP");
            System.out.println("====================================");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Ascending (Min Heap)");
            System.out.println("3. Tampilkan Descending (Max Heap)");
            System.out.println("4. Hapus Root Min Heap");
            System.out.println("5. Hapus Root Max Heap");
            System.out.println("6. Load Data dari CSV");
            System.out.println("7. Show Heap");
            System.out.println("0. Exit");
            System.out.println("====================================");

            System.out.print("Pilih menu: ");
            String pilih = input.nextLine();

            // ==================================
            // INSERT
            // ==================================
            if (pilih.equals("1")) {

                insertData(input);
            }

            // ==================================
            // SHOW ASCENDING
            // ==================================
            else if (pilih.equals("2")) {

                showAscending();
            }

            // ==================================
            // SHOW DESCENDING
            // ==================================
            else if (pilih.equals("3")) {

                showDescending();
            }

            // ==================================
            // DELETE MIN
            // ==================================
            else if (pilih.equals("4")) {

                deleteMinHeap();
            }

            // ==================================
            // DELETE MAX
            // ==================================
            else if (pilih.equals("5")) {

                deleteMaxHeap();
            }

            // ==================================
            // LOAD CSV
            // ==================================
            else if (pilih.equals("6")) {

                System.out.print("Masukkan nama file CSV: ");
                String filename = input.nextLine();

                loadCSV(filename);
            }

            // ==================================
            // SHOW HEAP
            // ==================================
            else if (pilih.equals("7")) {

                showHeap();
            }

            // ==================================
            // EXIT
            // ==================================
            else if (pilih.equals("0")) {

                System.out.println("\nProgram selesai.");
                break;
            }

            // ==================================
            // INVALID
            // ==================================
            else {

                System.out.println("\nMenu tidak valid!");
            }
        }

        input.close();
    }
}