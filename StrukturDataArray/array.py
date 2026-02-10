capacity = 10
nim = [None] * capacity
nama = [None] * capacity
count = 0
# ===== FUNGSI INSERT DAN DELETE =====

def insert_beginning():
    global count
    if count == capacity:
        print("Array penuh")
        return

    n = input("Input NIM : ")
    nm = input("Input Nama : ")

    for i in range(count, 0, -1):
        nim[i] = nim[i-1]
        nama[i] = nama[i-1]

    nim[0] = n
    nama[0] = nm
    count += 1


def insert_position():
    global count
    if count == capacity:
        print("Array penuh")
        return

    pos = int(input("Input posisi : "))
    if pos < 0 or pos > count:
        print("Posisi tidak valid")
        return

    n = input("Input NIM : ")
    nm = input("Input Nama : ")

    for i in range(count, pos, -1):
        nim[i] = nim[i-1]
        nama[i] = nama[i-1]

    nim[pos] = n
    nama[pos] = nm
    count += 1


def insert_end():
    global count
    if count == capacity:
        print("Array penuh")
        return

    nim[count] = input("Input NIM : ")
    nama[count] = input("Input Nama : ")
    count += 1


def delete_beginning():
    global count
    if count == 0:
        print("Data kosong")
        return

    for i in range(count-1):
        nim[i] = nim[i+1]
        nama[i] = nama[i+1]

    count -= 1


def delete_position():
    global count
    if count == 0:
        print("Data kosong")
        return

    pos = int(input("Input posisi : "))
    if pos < 0 or pos >= count:
        print("Posisi tidak valid")
        return

    for i in range(pos, count-1):
        nim[i] = nim[i+1]
        nama[i] = nama[i+1]

    count -= 1


def delete_end():
    global count
    if count == 0:
        print("Data kosong")
        return
    count -= 1


def delete_first():
    global count
    if count == 0:
        print("Data kosong")
        return

    target = input("Input NIM yang dihapus : ")

    for i in range(count):
        if nim[i] == target:
            for j in range(i, count-1):
                nim[j] = nim[j+1]
                nama[j] = nama[j+1]
            count -= 1
            return

    print("Data tidak ditemukan")


def show_data():
    if count == 0:
        print("Data kosong")
        return

    for i in range(count):
        print(nim[i], "-", nama[i])


# ===== MENU UTAMA =====
while True:
    print("\n=== MENU PYTHON (Data: {}/{} ) ===".format(count, capacity))
    print("1. Insert at Beginning")
    print("2. Insert at Position")
    print("3. Insert at End")
    print("4. Delete from Beginning")
    print("5. Delete at Position")
    print("6. Delete from End")
    print("7. Delete First Occurrence")
    print("8. Show Data")
    print("9. Exit")

    pilih = int(input("Choose menu : "))

    if pilih == 1:
        insert_beginning()
    elif pilih == 2:
        insert_position()
    elif pilih == 3:
        insert_end()
    elif pilih == 4:
        delete_beginning()
    elif pilih == 5:
        delete_position()
    elif pilih == 6:
        delete_end()
    elif pilih == 7:
        delete_first()
    elif pilih == 8:
        show_data()
    elif pilih == 9:
        print("Program selesai")
        break
    else:
        print("Menu tidak valid")
