import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class HashTable {
    private int size;
    // Menggunakan ArrayList dari ArrayList untuk merepresentasikan Chaining
    private ArrayList<ArrayList<Integer>> table;
    private int count;

    // Konstruktor
    public HashTable(int size) {
        this.size = size;
        this.count = 0;
        this.table = new ArrayList<>(size);
        
        // Inisialisasi setiap slot dengan ArrayList kosong
        for (int i = 0; i < size; i++) {
            this.table.add(new ArrayList<>());
        }
    }

    // Fungsi hash sederhana (Modulo)
    private int hashFunction(int key) {
        return key % size;
    }

    // 1. INPUT DATA
    public boolean insert(int key) {
        int index = hashFunction(key);
        // Pastikan data belum ada di slot tersebut (menghindari duplikasi)
        if (!table.get(index).contains(key)) {
            table.get(index).add(key);
            count++;
            return true;
        }
        return false;
    }

    // 2. HAPUS DATA
    public boolean delete(int key) {
        int index = hashFunction(key);
        // Java membedakan remove(Object) dan remove(index). 
        // Kita bungkus ke Integer object agar menghapus berdasarkan NILAI, bukan indeks list.
        if (table.get(index).contains(key)) {
            table.get(index).remove(Integer.valueOf(key));
            count--;
            return true;
        }
        return false;
    }

    // 3. CARI DATA
    // Mengembalikan array berisi [status_ketemu (1/0), indeks_slot]
    public int[] search(int key) {
        int index = hashFunction(key);
        if (table.get(index).contains(key)) {
            return new int[]{1, index}; // 1 artinya ditemukan
        }
        return new int[]{0, -1}; // 0 artinya tidak ditemukan
    }

    // 4. MENAMPILKAN SEMUA DATA
    public void displayAll() {
        if (count == 0) {
            System.out.println("Hash Table kosong!");
            return;
        }

        System.out.println("\n--- ISI HASH TABLE (Total: " + count + " data) ---");
        for (int i = 0; i < size; i++) {
            // Hanya tampilkan slot yang memiliki isi
            if (!table.get(i).isEmpty()) {
                System.out.print(String.format("Slot [%03d]: ", i));
                for (int j = 0; j < table.get(i).size(); j++) {
                    System.out.print(table.get(i).get(j));
                    if (j < table.get(i).size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------");
    }

    // Getter untuk melihat jumlah data saat ini
    public int getCount() {
        return this.count;
    }
}

// --- Program Utama ---
public class Hashing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Inisialisasi Hash Table dengan ukuran 150 slot
        HashTable hashTable = new HashTable(150);

        // Mengisi 100 data acak unik di awal program menggunakan HashSet
        Set<Integer> angkaRandom = new HashSet<>();
        while (angkaRandom.size() < 100) {
            // Menghasilkan angka acak antara 1 sampai 1000
            angkaRandom.add(random.nextInt(1000) + 1);
        }

        // Masukkan semua data unik tersebut ke dalam Hash Table
        for (int angka : angkaRandom) {
            hashTable.insert(angka);
        }

        System.out.println("=== PROGRAM HASH TABLE (JAVA) ===");
        System.out.println("Berhasil menginput " + hashTable.getCount() + " data acak unik ke dalam Hash Table.");
        System.out.println("---------------------------------");

        // Menu Interaktif
        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Input Data");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Semua Data");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    try {
                        System.out.print("Masukkan angka yang ingin diinput: ");
                        int dataInput = Integer.parseInt(scanner.nextLine());
                        if (hashTable.insert(dataInput)) {
                            System.out.println("Berhasil! Angka " + dataInput + " telah ditambahkan.");
                        } else {
                            System.out.println("Gagal! Angka " + dataInput + " sudah ada di dalam Hash Table.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka numerik!");
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Masukkan angka yang ingin dihapus: ");
                        int dataHapus = Integer.parseInt(scanner.nextLine());
                        if (hashTable.delete(dataHapus)) {
                            System.out.println("Berhasil! Angka " + dataHapus + " telah dihapus.");
                        } else {
                            System.out.println("Gagal! Angka " + dataHapus + " tidak ditemukan.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka numerik!");
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Masukkan angka yang dicari: ");
                        int dataCari = Integer.parseInt(scanner.nextLine());
                        int[] hasilCari = hashTable.search(dataCari);
                        
                        if (hasilCari[0] == 1) {
                            System.out.println("Angka " + dataCari + " ditemukan di Hash Table pada indeks slot: " + hasilCari[1]);
                        } else {
                            System.out.println("Angka " + dataCari + " TIDAK ditemukan di Hash Table.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka numerik!");
                    }
                    break;

                case "4":
                    hashTable.displayAll();
                    break;

                case "5":
                    System.out.println("Terima kasih! Program selesai.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
}