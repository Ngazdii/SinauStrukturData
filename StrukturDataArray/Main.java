import java.util.Scanner;

class Mahasiswa {
    String nim;
    String nama;

    Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }
}

public class Main {

    static Mahasiswa[] data = new Mahasiswa[10];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    // ===== TAMBAHAN: METHOD MENU =====
    static void showMenu() {
        System.out.println("\n=== MENU JAVA (Data: " + count + "/10) ===");
        System.out.println("1. Insert at Beginning");
        System.out.println("2. Insert at Position");
        System.out.println("3. Insert at End");
        System.out.println("4. Delete from Beginning");
        System.out.println("5. Delete at Position");
        System.out.println("6. Delete from End");
        System.out.println("7. Delete First Occurrence");
        System.out.println("8. Show Data");
        System.out.println("9. Exit");
        System.out.print("Pilih: ");
    }
    // ===== END TAMBAHAN =====

    static void insertBeginning() {
        if (count == 10) {
            System.out.println("Array penuh");
            return;
        }

        System.out.print("Input NIM : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        for (int i = count; i > 0; i--) {
            data[i] = data[i - 1];
        }

        data[0] = new Mahasiswa(nim, nama);
        count++;
    }

    static void insertPosition() {
        if (count == 10) {
            System.out.println("Array penuh");
            return;
        }

        System.out.print("Input posisi : ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 0 || pos > count) {
            System.out.println("Posisi tidak valid");
            return;
        }

        System.out.print("Input NIM : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        for (int i = count; i > pos; i--) {
            data[i] = data[i - 1];
        }

        data[pos] = new Mahasiswa(nim, nama);
        count++;
    }

    static void insertEnd() {
        if (count == 10) {
            System.out.println("Array penuh");
            return;
        }

        System.out.print("Input NIM : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        data[count] = new Mahasiswa(nim, nama);
        count++;
    }

    static void deleteBeginning() {
        if (count == 0) return;

        for (int i = 0; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
    }

    static void deletePosition() {
        if (count == 0) return;

        System.out.print("Input posisi : ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 0 || pos >= count) {
            System.out.println("Posisi tidak valid");
            return;
        }

        for (int i = pos; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
    }

    static void deleteEnd() {
        if (count == 0) return;
        count--;
    }

    static void deleteFirstOccurrence() {
        if (count == 0) return;

        System.out.print("Input NIM yang dihapus : ");
        String target = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (data[i].nim.equals(target)) {
                for (int j = i; j < count - 1; j++) {
                    data[j] = data[j + 1];
                }
                count--;
                return;
            }
        }
        System.out.println("Data tidak ditemukan");
    }

    static void showData() {
        if (count == 0) {
            System.out.println("Data kosong");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(data[i].nim + " - " + data[i].nama);
        }
    }

    public static void main(String[] args) {

        while (true) {

            // ===== PAKAI MENU BARU =====
            showMenu();

            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1: insertBeginning(); break;
                case 2: insertPosition(); break;
                case 3: insertEnd(); break;
                case 4: deleteBeginning(); break;
                case 5: deletePosition(); break;
                case 6: deleteEnd(); break;
                case 7: deleteFirstOccurrence(); break;
                case 8: showData(); break;
                case 9:
                    System.out.println("Program selesai");
                    return;
                default:
                    System.out.println("Menu tidak tersedia");
            }
        }
    }
}
