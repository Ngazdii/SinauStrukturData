import random

class HashTable:
    def __init__(self, size=150):
        self.size = size
        # Membuat hash table dengan slot kosong berbentuk list (Chaining)
        self.table = [[] for _ in range(self.size)]
        self.count = 0

    def _hash_function(self, key):
        """Fungsi hash sederhana menggunakan metode modulo."""
        return key % self.size

    def insert(self, key):
        """1. INPUT DATA"""
        index = self._hash_function(key)
        # Pastikan data belum ada di dalam slot (menghindari duplikasi)
        if key not in self.table[index]:
            self.table[index].append(key)
            self.count += 1
            return True
        return False

    def delete(self, key):
        """2. HAPUS DATA"""
        index = self._hash_function(key)
        if key in self.table[index]:
            self.table[index].remove(key)
            self.count -= 1
            return True
        return False

    def search(self, key):
        """3. CARI DATA"""
        index = self._hash_function(key)
        if key in self.table[index]:
            return True, index
        return False, None

    def display_all(self):
        """4. MENAMPILKAN SEMUA DATA"""
        if self.count == 0:
            print("Hash Table kosong!")
            return

        print(f"\n--- ISI HASH TABLE (Total: {self.count} data) ---")
        for index in range(self.size):
            # Hanya tampilkan indeks yang memiliki isi data
            if self.table[index]:
                # Berguna untuk melihat bagaimana chaining menangani tabrakan data
                format_data = " -> ".join(map(str, self.table[index]))
                print(f"Slot [{index:03d}]: {format_data}")
        print("------------------------------------------------")

# --- Program Utama ---
if __name__ == "__main__":
    # Inisialisasi Hash Table
    hash_table = HashTable(size=150)

    # Mengisi 100 data acak unik di awal program
    angka_random = set()
    while len(angka_random) < 100:
        angka_random.add(random.randint(1, 1000))

    for angka in angka_random:
        hash_table.insert(angka)

    print("=== PROGRAM HASH TABLE ===")
    print(f"Berhasil menginput {hash_table.count} data acak unik ke dalam Hash Table.")
    print("--------------------------")

    # Menu Interaktif
    while True:
        print("\nMENU:")
        print("1. Input Data")
        print("2. Hapus Data")
        print("3. Cari Data")
        print("4. Tampilkan Semua Data")
        print("5. Keluar")
        
        pilihan = input("Pilih menu (1-5): ")

        if pilihan == '1':
            try:
                data = int(input("Masukkan angka yang ingin diinput: "))
                if hash_table.insert(data):
                    print(f"Berhasil! Angka {data} telah ditambahkan.")
                else:
                    print(f"Gagal! Angka {data} sudah ada di dalam Hash Table.")
            except ValueError:
                print("Input harus berupa angka numerik!")

        elif pilihan == '2':
            try:
                data = int(input("Masukkan angka yang ingin dihapus: "))
                if hash_table.delete(data):
                    print(f"Berhasil! Angka {data} telah dihapus.")
                else:
                    print(f"Gagal! Angka {data} tidak ditemukan.")
            except ValueError:
                print("Input harus berupa angka numerik!")

        elif pilihan == '3':
            try:
                data = int(input("Masukkan angka yang dicari: "))
                ketemu, indeks = hash_table.search(data)
                if ketemu:
                    print(f"Angka {data} ditemukan di Hash Table pada indeks slot: {indeks}")
                else:
                    print(f"Angka {data} TIDAK ditemukan di Hash Table.")
            except ValueError:
                print("Input harus berupa angka numerik!")

        elif pilihan == '4':
            hash_table.display_all()

        elif pilihan == '5':
            print("Terima kasih! Program selesai.")
            break
        else:
            print("Pilihan tidak valid. Silakan coba lagi.")