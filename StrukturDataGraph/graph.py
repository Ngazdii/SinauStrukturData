from collections import deque

class Graph:
    def __init__(self):
        self.graph = {}

    # =========================
    # TAMBAH VERTEX
    # =========================
    def tambah_vertex(self, vertex):

        if vertex in self.graph:
            print(f"Vertex {vertex} sudah ada!\n")

        else:
            self.graph[vertex] = []
            print(f"Vertex {vertex} berhasil ditambahkan!\n")

    # =========================
    # HAPUS VERTEX
    # =========================
    def hapus_vertex(self, vertex):

        if vertex not in self.graph:
            print(f"Vertex {vertex} tidak ditemukan!\n")
            return

        # hapus semua edge yang terhubung
        for tetangga in self.graph[vertex]:
            self.graph[tetangga].remove(vertex)

        del self.graph[vertex]

        print(f"Vertex {vertex} berhasil dihapus!\n")

    # =========================
    # TAMBAH EDGE
    # =========================
    def tambah_edge(self, v1, v2):

        if v1 not in self.graph:
            print(f"Vertex {v1} belum ada!\n")
            return

        if v2 not in self.graph:
            print(f"Vertex {v2} belum ada!\n")
            return

        if v2 in self.graph[v1]:
            print("Edge sudah ada!\n")
            return

        self.graph[v1].append(v2)
        self.graph[v2].append(v1)

        print(f"Edge {v1} - {v2} berhasil ditambahkan!\n")

    # =========================
    # HAPUS EDGE
    # =========================
    def hapus_edge(self, v1, v2):

        if v1 in self.graph and v2 in self.graph[v1]:

            self.graph[v1].remove(v2)
            self.graph[v2].remove(v1)

            print(f"Edge {v1} - {v2} berhasil dihapus!\n")

        else:

            print(f"Edge {v1} - {v2} tidak ditemukan!\n")

    # =========================
    # TAMPILKAN GRAPH
    # =========================
    def tampilkan_graph(self):

        if not self.graph:
            print("Graph kosong!\n")
            return

        print("\n===== GRAPH =====")

        for vertex in self.graph:
            print(vertex, "->", " ".join(self.graph[vertex]))

        print()

    # =========================
    # DFS
    # =========================
    def dfs(self, start):

        if start not in self.graph:
            print("Vertex tidak ditemukan!\n")
            return

        visited = set()

        print("\n===== DFS =====")

        self._dfs_recursive(start, visited)

        print("\n")

    def _dfs_recursive(self, vertex, visited):

        visited.add(vertex)

        print(vertex, end=" ")

        for tetangga in self.graph[vertex]:

            if tetangga not in visited:
                self._dfs_recursive(tetangga, visited)

    # =========================
    # BFS
    # =========================
    def bfs(self, start):

        if start not in self.graph:
            print("Vertex tidak ditemukan!\n")
            return

        visited = set()

        queue = deque()

        queue.append(start)

        visited.add(start)

        print("\n===== BFS =====")

        while queue:

            vertex = queue.popleft()

            print(vertex, end=" ")

            for tetangga in self.graph[vertex]:

                if tetangga not in visited:

                    visited.add(tetangga)

                    queue.append(tetangga)

        print("\n")


# ==================================
# MAIN PROGRAM
# ==================================

g = Graph()

while True:

    print("========== MENU GRAPH ==========")
    print("1. Tambah Vertex")
    print("2. Hapus Vertex")
    print("3. Tambah Edge")
    print("4. Hapus Edge")
    print("5. Tampilkan Graph")
    print("6. Traversal DFS")
    print("7. Traversal BFS")
    print("8. Quit")
    print("================================")

    pilih = input("Pilih menu : ")

    # =========================
    # TAMBAH VERTEX
    # =========================
    if pilih == "1":

        vertex = input("Masukkan nama vertex : ")

        g.tambah_vertex(vertex)

    # =========================
    # HAPUS VERTEX
    # =========================
    elif pilih == "2":

        vertex = input("Masukkan vertex yang dihapus : ")

        g.hapus_vertex(vertex)

    # =========================
    # TAMBAH EDGE
    # =========================
    elif pilih == "3":

        v1 = input("Masukkan vertex pertama : ")

        v2 = input("Masukkan vertex kedua : ")

        g.tambah_edge(v1, v2)

    # =========================
    # HAPUS EDGE
    # =========================
    elif pilih == "4":

        v1 = input("Masukkan vertex pertama : ")

        v2 = input("Masukkan vertex kedua : ")

        g.hapus_edge(v1, v2)

    # =========================
    # TAMPILKAN GRAPH
    # =========================
    elif pilih == "5":

        g.tampilkan_graph()

    # =========================
    # DFS
    # =========================
    elif pilih == "6":

        start = input("Mulai DFS dari vertex : ")

        g.dfs(start)

    # =========================
    # BFS
    # =========================
    elif pilih == "7":

        start = input("Mulai BFS dari vertex : ")

        g.bfs(start)

    # =========================
    # EXIT
    # =========================
    elif pilih == "8":

        print("Program selesai.")

        break

    # =========================
    # INVALID
    # =========================
    else:

        print("Menu tidak valid!\n")