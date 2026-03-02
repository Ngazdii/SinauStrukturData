import time

class Node:
    def __init__(self, berita):
        self.berita = berita
        self.next = None
        self.prev = None


class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None
        self.count = 0

    def insert_end(self, berita):
        new_node = Node(berita)

        if self.head is None:
            new_node.next = new_node
            new_node.prev = new_node
            self.head = new_node
        else:
            tail = self.head.prev
            tail.next = new_node
            new_node.prev = tail
            new_node.next = self.head
            self.head.prev = new_node

        self.count += 1
        print("Berita berhasil ditambahkan")

    def delete_position(self, pos):
        if self.count == 0 or pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return

        current = self.head

        if self.count == 1:
            self.head = None
        else:
            for _ in range(pos - 1):
                current = current.next

            current.prev.next = current.next
            current.next.prev = current.prev

            if current == self.head:
                self.head = current.next

        self.count -= 1
        print("Berita berhasil dihapus")

    def show_forward(self):
        if self.count == 0:
            print("Tidak ada berita")
            return

        print("Tekan CTRL+C untuk berhenti...\n")

        current = self.head
        try:
            while True:
                print("Berita:", current.berita)
                time.sleep(3)
                current = current.next
        except KeyboardInterrupt:
            print("\nBerhenti menampilkan berita.")

    def show_backward(self):
        if self.count == 0:
            print("Tidak ada berita")
            return

        print("Tekan CTRL+C untuk berhenti...\n")

        current = self.head.prev
        try:
            while True:
                print("Berita:", current.berita)
                time.sleep(3)
                current = current.prev
        except KeyboardInterrupt:
            print("\nBerhenti menampilkan berita.")

    def show_specific(self, pos):
        if pos < 1 or pos > self.count:
            print("Posisi tidak valid")
            return

        current = self.head
        for _ in range(pos - 1):
            current = current.next

        print("Berita:", current.berita)


cdll = CircularDoublyLinkedList()

while True:
    print("\n=== MENU BERITA ===")
    print("1. Insert berita")
    print("2. Hapus berita")
    print("3. Tampilkan berita forward")
    print("4. Tampilkan berita backward")
    print("5. Tampil berita tertentu")
    print("6. Exit")

    pilih = int(input("Pilih menu: "))

    if pilih == 1:
        teks = input("Input berita: ")
        cdll.insert_end(teks)
    elif pilih == 2:
        pos = int(input("Hapus posisi: "))
        cdll.delete_position(pos)
    elif pilih == 3:
        cdll.show_forward()
    elif pilih == 4:
        cdll.show_backward()
    elif pilih == 5:
        pos = int(input("Nomor berita: "))
        cdll.show_specific(pos)
    elif pilih == 6:
        print("Program selesai")
        break
    else:
        print("Menu tidak tersedia")