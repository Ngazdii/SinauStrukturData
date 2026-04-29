class MaxHeap:
    def __init__(self):
        self.heap = []

    def insert(self, val):
        self.heap.append(val)
        self.heapify_up(len(self.heap) - 1)

    def heapify_up(self, i):
        while i > 0:
            parent = (i - 1) // 2
            if self.heap[i] > self.heap[parent]:
                self.heap[i], self.heap[parent] = self.heap[parent], self.heap[i]
                i = parent
            else:
                break

    def heapify_down(self, i):
        size = len(self.heap)
        while True:
            largest = i
            left = 2*i + 1
            right = 2*i + 2

            if left < size and self.heap[left] > self.heap[largest]:
                largest = left
            if right < size and self.heap[right] > self.heap[largest]:
                largest = right

            if largest != i:
                self.heap[i], self.heap[largest] = self.heap[largest], self.heap[i]
                i = largest
            else:
                break

    def delete_value(self, val):
        if val not in self.heap:
            print("Nilai tidak ditemukan")
            return

        index = self.heap.index(val)
        self.heap[index] = self.heap[-1]
        self.heap.pop()

        if index < len(self.heap):
            self.heapify_down(index)
            self.heapify_up(index)

    def display(self):
        for x in self.heap:
            print(x, end=" ")
        print()


# MAIN
if __name__ == "__main__":
    data = [100, 41, 51, 13, 31, 16]

    h = MaxHeap()

    for x in data:
        h.insert(x)

    print("Initial heap:", end=" ")
    h.display()

    h.delete_value(13)

    print("Heap after deleting 13:", end=" ")
    h.display()