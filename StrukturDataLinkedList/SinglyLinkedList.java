import java.util.Scanner;

class Node {
    String nim, nama;
    Node next;

    Node(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
        this.next = null;
    }
}

public class SinglyLinkedList {
    Node head;
    int count = 0;
    Scanner sc = new Scanner(System.in);

    // 1. Insert at beginning
    void insertBeginning() {
        System.out.print("Input NIM  : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        Node newNode = new Node(nim, nama);
        newNode.next = head;
        head = newNode;
        count++;
    }

    // 2. Insert at given position
    void insertPosition() {
        System.out.print("Input posisi (1 - " + (count + 1) + ") : ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 1 || pos > count + 1) {
            System.out.println("Posisi tidak valid");
            return;
        }

        System.out.print("Input NIM  : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        Node newNode = new Node(nim, nama);

        if (pos == 1) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 1; i < pos - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        count++;
    }

    // 3. Insert at end
    void insertEnd() {
        System.out.print("Input NIM  : ");
        String nim = sc.nextLine();
        System.out.print("Input Nama : ");
        String nama = sc.nextLine();

        Node newNode = new Node(nim, nama);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
        count++;
    }

    // 4. Delete from beginning
    void deleteBeginning() {
        if (head == null) {
            System.out.println("Data kosong");
            return;
        }
        head = head.next;
        count--;
    }

    // 5. Delete given position
    void deletePosition() {
        System.out.print("Input posisi (1 - " + count + ") : ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 1 || pos > count) {
            System.out.println("Posisi tidak valid");
            return;
        }

        if (pos == 1) {
            head = head.next;
        } else {
            Node temp = head;
            for (int i = 1; i < pos - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        count--;
    }

    // 6. Delete from end
    void deleteEnd() {
        if (head == null) {
            System.out.println("Data kosong");
            return;
        }

        if (head.next == null) {
            head = null;
        } else {
            Node temp = head;
            while (temp.next.next != null)
                temp = temp.next;
            temp.next = null;
        }
        count--;
    }

    // 7. Delete first occurrence
    void deleteFirstOccurrence() {
        if (head == null) {
            System.out.println("Data kosong");
            return;
        }

        System.out.print("Input NIM yang dihapus : ");
        String target = sc.nextLine();

        if (head.nim.equals(target)) {
            head = head.next;
            count--;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.nim.equals(target)) {
                temp.next = temp.next.next;
                count--;
                return;
            }
            temp = temp.next;
        }

        System.out.println("Data tidak ditemukan");
    }

    // 8. Show data
    void showData() {
        if (head == null) {
            System.out.println("Data kosong");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.println(temp.nim + " - " + temp.nama);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU SINGLY LINKED LIST (Data: " + sll.count + ") ===");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. Delete from beginning");
            System.out.println("5. Delete given position");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete first occurrence");
            System.out.println("8. Show data");
            System.out.println("9. Exit");
            System.out.print("Pilih menu : ");

            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1 -> sll.insertBeginning();
                case 2 -> sll.insertPosition();
                case 3 -> sll.insertEnd();
                case 4 -> sll.deleteBeginning();
                case 5 -> sll.deletePosition();
                case 6 -> sll.deleteEnd();
                case 7 -> sll.deleteFirstOccurrence();
                case 8 -> sll.showData();
                case 9 -> {
                    System.out.println("Program selesai");
                    return;
                }
                default -> System.out.println("Menu tidak valid");
            }
        }
    }
}
