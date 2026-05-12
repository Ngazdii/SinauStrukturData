import csv


# =========================================
# MIN HEAP
# =========================================
class MinHeap:
    def __init__(self):
        self.heap = []

    def insert(self, data):
        self.heap.append(data)
        self.heapify_up(len(self.heap) - 1)

    def heapify_up(self, index):
        while index > 0:
            parent = (index - 1) // 2

            if self.heap[index][0] < self.heap[parent][0]:
                self.heap[index], self.heap[parent] = self.heap[parent], self.heap[index]
                index = parent
            else:
                break

    def heapify_down(self, index):
        size = len(self.heap)

        while True:
            smallest = index
            left = 2 * index + 1
            right = 2 * index + 2

            if left < size and self.heap[left][0] < self.heap[smallest][0]:
                smallest = left

            if right < size and self.heap[right][0] < self.heap[smallest][0]:
                smallest = right

            if smallest != index:
                self.heap[index], self.heap[smallest] = self.heap[smallest], self.heap[index]
                index = smallest
            else:
                break

    def delete_root(self):
        if not self.heap:
            return None

        root = self.heap[0]
        last = self.heap.pop()

        if self.heap:
            self.heap[0] = last
            self.heapify_down(0)

        return root

    def display_ascending(self):
        if not self.heap:
            print("\nMin Heap kosong!")
            return

        data = sorted(self.heap, key=lambda x: x[0])

        print("\n========== DATA ASCENDING ==========")
        for item in data:
            print(f"ID: {item[0]} | Nama: {item[1]}")
        print("====================================")


# =========================================
# MAX HEAP
# =========================================
class MaxHeap:
    def __init__(self):
        self.heap = []

    def insert(self, data):
        self.heap.append(data)
        self.heapify_up(len(self.heap) - 1)

    def heapify_up(self, index):
        while index > 0:
            parent = (index - 1) // 2

            if self.heap[index][0] > self.heap[parent][0]:
                self.heap[index], self.heap[parent] = self.heap[parent], self.heap[index]
                index = parent
            else:
                break

    def heapify_down(self, index):
        size = len(self.heap)

        while True:
            largest = index
            left = 2 * index + 1
            right = 2 * index + 2

            if left < size and self.heap[left][0] > self.heap[largest][0]:
                largest = left

            if right < size and self.heap[right][0] > self.heap[largest][0]:
                largest = right

            if largest != index:
                self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
                index = largest
            else:
                break

    def delete_root(self):
        if not self.heap:
            return None

        root = self.heap[0]
        last = self.heap.pop()

        if self.heap:
            self.heap[0] = last
            self.heapify_down(0)

        return root

    def display_descending(self):
        if not self.heap:
            print("\nMax Heap kosong!")
            return

        data = sorted(self.heap, key=lambda x: x[0], reverse=True)

        print("\n========== DATA DESCENDING ==========")
        for item in data:
            print(f"ID: {item[0]} | Nama: {item[1]}")
        print("=====================================")


# =========================================
# LOAD CSV
# =========================================
def load_csv(filename, min_heap, max_heap):
    try:
        with open(filename, mode='r', encoding='utf-8') as file:
            reader = csv.DictReader(file)

            jumlah = 0

            for row in reader:
                id_barang = int(row["ID"])
                nama_barang = row["Nama"]

                data = (id_barang, nama_barang)

                min_heap.insert(data)
                max_heap.insert(data)

                jumlah += 1

            print(f"\n{jumlah} data berhasil dimasukkan ke Min Heap & Max Heap!")

    except FileNotFoundError:
        print("\nFile tidak ditemukan!")

    except KeyError:
        print("\nFormat CSV salah!")
        print("Header harus: ID,Nama")

    except Exception as e:
        print("\nTerjadi error:", e)


# =========================================
# MAIN PROGRAM
# =========================================
min_heap = MinHeap()
max_heap = MaxHeap()

while True:

    print("\n====================================")
    print("       PROGRAM MIN & MAX HEAP")
    print("====================================")
    print("1. Tambah Data")
    print("2. Tampilkan Ascending (Min Heap)")
    print("3. Tampilkan Descending (Max Heap)")
    print("4. Hapus Root Min Heap")
    print("5. Hapus Root Max Heap")
    print("6. Load Data dari CSV")
    print("0. Exit")
    print("====================================")

    pilih = input("Pilih menu: ")

    # =====================================
    # TAMBAH DATA
    # =====================================
    if pilih == "1":

        try:
            id_barang = int(input("Masukkan ID: "))
            nama_barang = input("Masukkan Nama: ")

            data = (id_barang, nama_barang)

            min_heap.insert(data)
            max_heap.insert(data)

            print("\nData berhasil ditambahkan!")

        except:
            print("\nInput tidak valid!")

    # =====================================
    # TAMPIL ASCENDING
    # =====================================
    elif pilih == "2":
        min_heap.display_ascending()

    # =====================================
    # TAMPIL DESCENDING
    # =====================================
    elif pilih == "3":
        max_heap.display_descending()

    # =====================================
    # HAPUS MIN HEAP
    # =====================================
    elif pilih == "4":

        deleted = min_heap.delete_root()

        if deleted:
            print("\nData terhapus dari Min Heap:")
            print(f"ID: {deleted[0]} | Nama: {deleted[1]}")
        else:
            print("\nMin Heap kosong!")

    # =====================================
    # HAPUS MAX HEAP
    # =====================================
    elif pilih == "5":

        deleted = max_heap.delete_root()

        if deleted:
            print("\nData terhapus dari Max Heap:")
            print(f"ID: {deleted[0]} | Nama: {deleted[1]}")
        else:
            print("\nMax Heap kosong!")

    # =====================================
    # LOAD CSV
    # =====================================
    elif pilih == "6":

        filename = input("Masukkan nama file CSV: ")

        load_csv(filename, min_heap, max_heap)

    # =====================================
    # EXIT
    # =====================================
    elif pilih == "0":

        print("\nProgram selesai.")
        break

    else:
        print("\nMenu tidak valid!")