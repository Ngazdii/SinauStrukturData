# INSERT (Min Heap)
def insert(heap, value):
    heap.append(value)
    index = len(heap) - 1

    while index > 0 and heap[(index - 1) // 2] > heap[index]:
        parent = (index - 1) // 2
        heap[index], heap[parent] = heap[parent], heap[index]
        index = parent


# DELETE (Min Heap - mengikuti gaya referensi)
def deleteMin(heap, value):
    index = -1

    # cari index
    for i in range(len(heap)):
        if heap[i] == value:
            index = i
            break

    if index == -1:
        return

    # ganti dengan elemen terakhir
    heap[index] = heap[-1]
    heap.pop()

    # heapify DOWN saja (biar output sama persis)
    while True:
        left = 2 * index + 1
        right = 2 * index + 2
        smallest = index

        if left < len(heap) and heap[left] < heap[smallest]:
            smallest = left
        if right < len(heap) and heap[right] < heap[smallest]:
            smallest = right

        if smallest != index:
            heap[index], heap[smallest] = heap[smallest], heap[index]
            index = smallest
        else:
            break


# MAIN
if __name__ == "__main__":
    arr = []
    values = [13, 16, 31, 41, 51, 100]

    for v in values:
        insert(arr, v)

    print("Initial heap:", end=" ")
    for x in arr:
        print(x, end=" ")
    print()

    deleteMin(arr, 13)

    print("Heap after deleting 13:", end=" ")
    for x in arr:
        print(x, end=" ")
    print()