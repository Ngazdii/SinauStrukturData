import csv

class Node:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None
        self.count = 0

    # INSERT
    def insert(self, root, id, nama):
        if root is None:
            self.count += 1
            return Node(id, nama)

        if id < root.id:
            root.left = self.insert(root.left, id, nama)
        elif id > root.id:
            root.right = self.insert(root.right, id, nama)

        return root

    # SEARCH
    def search(self, root, id):
        if root is None:
            return None
        if id == root.id:
            return root
        elif id < root.id:
            return self.search(root.left, id)
        else:
            return self.search(root.right, id)

    # DELETE
    def delete(self, root, id):
        if root is None:
            return root

        if id < root.id:
            root.left = self.delete(root.left, id)
        elif id > root.id:
            root.right = self.delete(root.right, id)
        else:
            # 1 child / no child
            if root.left is None:
                self.count -= 1
                return root.right
            elif root.right is None:
                self.count -= 1
                return root.left

            # 2 children
            temp = self.minValue(root.right)
            root.id = temp.id
            root.nama = temp.nama
            root.right = self.delete(root.right, temp.id)

        return root

    def minValue(self, node):
        current = node
        while current.left:
            current = current.left
        return current

    # TRAVERSAL
    def inorder(self, root):
        if root:
            self.inorder(root.left)
            print(root.id, "-", root.nama)
            self.inorder(root.right)

    def preorder(self, root):
        if root:
            print(root.id, "-", root.nama)
            self.preorder(root.left)
            self.preorder(root.right)

    def postorder(self, root):
        if root:
            self.postorder(root.left)
            self.postorder(root.right)
            print(root.id, "-", root.nama)

    # LOAD CSV
    def load_csv(self, filename):
        try:
            with open(filename, newline='', encoding='utf-8') as file:
                reader = csv.DictReader(file)

                for row in reader:
                    id = int(row["ID"])
                    nama = row["Nama"]
                    self.root = self.insert(self.root, id, nama)

            print("Data berhasil di-load dari CSV!")

        except FileNotFoundError:
            print("File tidak ditemukan!")
        except Exception as e:
            print("Error:", e)


# MAIN PROGRAM
bst = BST()

while True:
    print("\n=== MENU BST ===")
    print("1. Insert Data")
    print("2. Search Data")
    print("3. Delete Data")
    print("4. Inorder")
    print("5. Preorder")
    print("6. Postorder")
    print("7. Load dari CSV")
    print("8. Exit")

    pilih = input("Pilih: ")

    if pilih == "1":
        id = int(input("ID: "))
        nama = input("Nama: ")
        bst.root = bst.insert(bst.root, id, nama)

    elif pilih == "2":
        id = int(input("Cari ID: "))
        hasil = bst.search(bst.root, id)
        if hasil:
            print("Ditemukan:", hasil.id, "-", hasil.nama)
        else:
            print("Tidak ditemukan")

    elif pilih == "3":
        id = int(input("Hapus ID: "))
        bst.root = bst.delete(bst.root, id)

    elif pilih == "4":
        bst.inorder(bst.root)

    elif pilih == "5":
        bst.preorder(bst.root)

    elif pilih == "6":
        bst.postorder(bst.root)

    elif pilih == "7":
        file = input("Nama file CSV: ")
        bst.load_csv(file)

    elif pilih == "8":
        break