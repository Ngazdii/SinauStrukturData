import java.util.*;

class Graph {

    private Map<String, List<String>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    // =========================
    // TAMBAH VERTEX
    // =========================
    public void tambahVertex(String vertex) {

        if (graph.containsKey(vertex)) {

            System.out.println("Vertex " + vertex + " sudah ada!\n");

        } else {

            graph.put(vertex, new ArrayList<>());

            System.out.println("Vertex " + vertex + " berhasil ditambahkan!\n");
        }
    }

    // =========================
    // HAPUS VERTEX
    // =========================
    public void hapusVertex(String vertex) {

        if (!graph.containsKey(vertex)) {

            System.out.println("Vertex tidak ditemukan!\n");

            return;
        }

        // Hapus edge ke vertex lain
        for (String tetangga : graph.get(vertex)) {

            graph.get(tetangga).remove(vertex);
        }

        graph.remove(vertex);

        System.out.println("Vertex " + vertex + " berhasil dihapus!\n");
    }

    // =========================
    // TAMBAH EDGE
    // =========================
    public void tambahEdge(String v1, String v2) {

        if (!graph.containsKey(v1)) {

            System.out.println("Vertex " + v1 + " belum ada!\n");

            return;
        }

        if (!graph.containsKey(v2)) {

            System.out.println("Vertex " + v2 + " belum ada!\n");

            return;
        }

        if (graph.get(v1).contains(v2)) {

            System.out.println("Edge sudah ada!\n");

            return;
        }

        graph.get(v1).add(v2);

        graph.get(v2).add(v1);

        System.out.println("Edge " + v1 + " - " + v2 + " berhasil ditambahkan!\n");
    }

    // =========================
    // HAPUS EDGE
    // =========================
    public void hapusEdge(String v1, String v2) {

        if (graph.containsKey(v1) && graph.get(v1).contains(v2)) {

            graph.get(v1).remove(v2);

            graph.get(v2).remove(v1);

            System.out.println("Edge " + v1 + " - " + v2 + " berhasil dihapus!\n");

        } else {

            System.out.println("Edge " + v1 + " - " + v2 + " tidak ditemukan!\n");
        }
    }

    // =========================
    // TAMPILKAN GRAPH
    // =========================
    public void tampilkanGraph() {

        if (graph.isEmpty()) {

            System.out.println("Graph kosong!\n");

            return;
        }

        System.out.println("\n===== GRAPH =====");

        for (String vertex : graph.keySet()) {

            System.out.print(vertex + " -> ");

            for (String tetangga : graph.get(vertex)) {

                System.out.print(tetangga + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    // =========================
    // DFS
    // =========================
    public void dfs(String start) {

        if (!graph.containsKey(start)) {

            System.out.println("Vertex tidak ditemukan!\n");

            return;
        }

        Set<String> visited = new HashSet<>();

        System.out.println("\n===== DFS =====");

        dfsRecursive(start, visited);

        System.out.println("\n");
    }

    private void dfsRecursive(String vertex, Set<String> visited) {

        visited.add(vertex);

        System.out.print(vertex + " ");

        for (String tetangga : graph.get(vertex)) {

            if (!visited.contains(tetangga)) {

                dfsRecursive(tetangga, visited);
            }
        }
    }

    // =========================
    // BFS
    // =========================
    public void bfs(String start) {

        if (!graph.containsKey(start)) {

            System.out.println("Vertex tidak ditemukan!\n");

            return;
        }

        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        queue.add(start);

        visited.add(start);

        System.out.println("\n===== BFS =====");

        while (!queue.isEmpty()) {

            String vertex = queue.poll();

            System.out.print(vertex + " ");

            for (String tetangga : graph.get(vertex)) {

                if (!visited.contains(tetangga)) {

                    visited.add(tetangga);

                    queue.add(tetangga);
                }
            }
        }

        System.out.println("\n");
    }
}

// ======================================
// MAIN CLASS
// ======================================
public class GraphProgram {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Graph g = new Graph();

        while (true) {

            System.out.println("========== MENU GRAPH ==========");
            System.out.println("1. Tambah Vertex");
            System.out.println("2. Hapus Vertex");
            System.out.println("3. Tambah Edge");
            System.out.println("4. Hapus Edge");
            System.out.println("5. Tampilkan Graph");
            System.out.println("6. Traversal DFS");
            System.out.println("7. Traversal BFS");
            System.out.println("8. Quit");
            System.out.println("================================");

            System.out.print("Pilih menu : ");

            String pilih = input.nextLine();

            // =========================
            // TAMBAH VERTEX
            // =========================
            if (pilih.equals("1")) {

                System.out.print("Masukkan nama vertex : ");

                String vertex = input.nextLine();

                g.tambahVertex(vertex);
            }

            // =========================
            // HAPUS VERTEX
            // =========================
            else if (pilih.equals("2")) {

                System.out.print("Masukkan vertex yang dihapus : ");

                String vertex = input.nextLine();

                g.hapusVertex(vertex);
            }

            // =========================
            // TAMBAH EDGE
            // =========================
            else if (pilih.equals("3")) {

                System.out.print("Masukkan vertex pertama : ");

                String v1 = input.nextLine();

                System.out.print("Masukkan vertex kedua : ");

                String v2 = input.nextLine();

                g.tambahEdge(v1, v2);
            }

            // =========================
            // HAPUS EDGE
            // =========================
            else if (pilih.equals("4")) {

                System.out.print("Masukkan vertex pertama : ");

                String v1 = input.nextLine();

                System.out.print("Masukkan vertex kedua : ");

                String v2 = input.nextLine();

                g.hapusEdge(v1, v2);
            }

            // =========================
            // TAMPILKAN GRAPH
            // =========================
            else if (pilih.equals("5")) {

                g.tampilkanGraph();
            }

            // =========================
            // DFS
            // =========================
            else if (pilih.equals("6")) {

                System.out.print("Mulai DFS dari vertex : ");

                String start = input.nextLine();

                g.dfs(start);
            }

            // =========================
            // BFS
            // =========================
            else if (pilih.equals("7")) {

                System.out.print("Mulai BFS dari vertex : ");

                String start = input.nextLine();

                g.bfs(start);
            }

            // =========================
            // EXIT
            // =========================
            else if (pilih.equals("8")) {

                System.out.println("Program selesai.");

                break;
            }

            // =========================
            // INVALID
            // =========================
            else {

                System.out.println("Menu tidak valid!\n");
            }
        }

        input.close();
    }
}