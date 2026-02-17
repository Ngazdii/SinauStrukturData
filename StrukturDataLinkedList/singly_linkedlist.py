class Node:
    def __init__(self, nim, nama):
        self.nim = nim
        self.nama = nama
        self.next = None


class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.count = 0

    # 1. Insert at beginning
    def insert_beginning(self):
        nim = input("Input NIM  : ")
        nama = input("Input Nama : ")

        new_node = Node(nim, nama)
        new_node.next = self.head
        self.head = new_node
        self.count += 1

    # 2. Insert at given position
    def insert_position(self):
        pos = int(input("Input posisi (1 - {}): ".format(self.count + 1)))

        if pos < 1 or pos > self.count + 1:
            print("Posisi tidak valid")
            return

        nim = input("Input NIM  : ")
        nama = input("Input Nama : ")

        new_node = Node(nim, nama)

        if pos == 1:
            new_node.next = self.head
            self.head = new_node
        else:
            temp = self.head
            for _ in range(pos - 2):
                temp = temp.next
            new_node.next = temp.next
            temp.next = new_node

        self.count += 1

    # 3. Insert at end
    def insert_end(self):
        nim = input("Input NIM  : ")
        nama = input("Input Nama : ")

        new_node = Node(nim, nama)

        if self.head is None:
            self.head = new_node
        else:
            temp = self.head
            while temp.next:
                temp = temp.next
            temp.next = new_node

        self.count += 1

    # 4. Delete from beginning
    def delete_beginning(self):
        if self.head is None:
            print("Data kosong")
            return

        self.head = self.head.next
        self.count -= 1

    # 5. Delete given position
    def delete_position(self):
        pos = int(input("Input posisi (1 - {}): ".format(self.count)))

        if pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return

        if pos == 1:
            self.head = self.head.next
        else:
            temp = self.head
            for _ in range(pos - 2):
                temp = temp.next
            temp.next = temp.next.next

        self.count -= 1

    # 6. Delete from end
    def delete_end(self):
        if self.head is None:
            print("Data kosong")
            return

        if self.head.next is None:
            self.head = None
        else:
            temp = self.head
            while temp.next.next:
                temp = temp.next
            temp.next = None

        self.count -= 1

    # 7. Delete first occurrence by NIM
    def delete_first_occurrence(self):
        if self.head is None:
            print("Data kosong")
            return

        target = input("Input NIM yang dihapus : ")

        if self.head.nim == target:
            self.head = self.head.next
            self.count -= 1
            return

        temp = self.head
        while temp.next:
            if temp.next.nim == target:
                temp.next = temp.next.next
                self.count -= 1
                return
            temp = temp.next

        print("Data tidak ditemukan")

    # 8. Show data
    def show_data(self):
        if self.head is None:
            print("Data kosong")
            return

        temp = self.head
        while temp:
            print(temp.nim, "-", temp.nama)
            temp = temp.next


# ===== MENU UTAMA =====
ll = SinglyLinkedList()

while True:
    print("\n=== MENU SINGLY LINKED LIST (Data: {}) ===".format(ll.count))
    print("1. Insert at beginning")
    print("2. Insert at given position")
    print("3. Insert at end")
    print("4. Delete from beginning")
    print("5. Delete given position")
    print("6. Delete from end")
    print("7. Delete first occurrence")
    print("8. Show data")
    print("9. Exit")

    pilih = int(input("Pilih menu : "))

    if pilih == 1:
        ll.insert_beginning()
    elif pilih == 2:
        ll.insert_position()
    elif pilih == 3:
        ll.insert_end()
    elif pilih == 4:
        ll.delete_beginning()
    elif pilih == 5:
        ll.delete_position()
    elif pilih == 6:
        ll.delete_end()
    elif pilih == 7:
        ll.delete_first_occurrence()
    elif pilih == 8:
        ll.show_data()
    elif pilih == 9:
        print("Program selesai")
        break
    else:
        print("Menu tidak valid")
