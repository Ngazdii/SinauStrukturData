import re


class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class StackLL:
    def __init__(self):
        self.top = None

    def is_empty(self):
        return self.top is None

    def push(self, data):
        new = Node(data)
        new.next = self.top
        self.top = new

    def pop(self):
        if self.is_empty():
            return None
        value = self.top.data
        self.top = self.top.next
        return value

    def peek(self):
        if self.is_empty():
            return None
        return self.top.data

    def print_stack(self):
        temp = self.top
        print("[ ", end="")
        while temp:
            print(temp.data, end=" ")
            temp = temp.next
        print("]")


def precedence(op):
    if op in ["+", "-"]:
        return 1
    if op in ["*", "/"]:
        return 2
    return 0


def is_operator(op):
    return op in ["+", "-", "*", "/"]


def infix_to_postfix(tokens):

    stack = StackLL()
    postfix = ""

    print("\n=== PROSES INFIX → POSTFIX ===")

    for t in tokens:

        print("\nToken :", t)

        if t.isdigit():
            postfix += t
            print("Masuk ke postfix")

        elif t == "(":
            stack.push(t)
            print("Push '(' ke stack")

        elif t == ")":

            while stack.peek() != "(":
                postfix += stack.pop()

            stack.pop()
            print("Pop sampai '('")

        elif is_operator(t):

            while (not stack.is_empty() and
                   precedence(stack.peek()) >= precedence(t)):

                postfix += stack.pop()

            stack.push(t)
            print("Push operator ke stack")

        print("Stack :", end=" ")
        stack.print_stack()

        print("Postfix :", postfix)

    while not stack.is_empty():
        postfix += stack.pop()

    print("\nFinal Postfix :", postfix)

    return postfix


def evaluate_postfix(postfix):

    stack = StackLL()

    print("\n=== PROSES EVALUASI POSTFIX ===")

    for t in postfix:

        if t.isdigit():

            stack.push(t)
            print("Push", t)

            print("Stack :", end=" ")
            stack.print_stack()

        else:

            b = float(stack.pop())
            a = float(stack.pop())

            if t == "+":
                result = a + b
            elif t == "-":
                result = a - b
            elif t == "*":
                result = a * b
            elif t == "/":
                result = a / b

            print("Pop", a, "dan", b)
            print("Hitung :", a, t, b, "=", result)

            stack.push(str(result))

            print("Stack :", end=" ")
            stack.print_stack()

    return float(stack.pop())


while True:

    print("\n=================================")
    expr = input("Masukkan ekspresi aritmatika : ")

    tokens = re.findall(r'\d+|[()+\-*/]', expr)

    postfix = infix_to_postfix(tokens)

    result = evaluate_postfix(postfix)

    print("\nHASIL AKHIR =", result)

    ulang = input("\nHitung lagi? (y/n): ").lower()

    if ulang != "y":
        break

print("Program selesai")