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
            raise Exception("Stack kosong!")
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


# ================= OPERATOR =================
def precedence(op):
    if op == "^": return 3
    if op in ["*", "/"]: return 2
    if op in ["+", "-"]: return 1
    return 0


def is_operator(op):
    return op in ["+", "-", "*", "/", "^"]


# ================= TOKENIZER =================
def tokenize(expr):
    tokens = re.findall(r'\d+\.?\d*|[()+\-*/^]', expr)

    # handle unary minus
    result = []
    i = 0
    while i < len(tokens):
        if tokens[i] == "-" and (i == 0 or tokens[i-1] in "(+*/^-"):
            result.append("-" + tokens[i+1])
            i += 2
        else:
            result.append(tokens[i])
            i += 1

    return result


# ================= INFIX → POSTFIX =================
def infix_to_postfix(tokens):
    stack = StackLL()
    postfix = []

    print("\n=== PROSES INFIX → POSTFIX ===")

    for t in tokens:
        print("\nToken:", t)

        if re.match(r'-?\d+\.?\d*', t):
            postfix.append(t)
            print("Masuk ke postfix")

        elif t == "(":
            stack.push(t)

        elif t == ")":
            while stack.peek() != "(":
                postfix.append(stack.pop())
            stack.pop()

        elif is_operator(t):
            while (not stack.is_empty() and
                   precedence(stack.peek()) >= precedence(t)):
                postfix.append(stack.pop())
            stack.push(t)

        print("Stack:", end=" ")
        stack.print_stack()
        print("Postfix:", postfix)

    while not stack.is_empty():
        postfix.append(stack.pop())

    print("\nFinal Postfix:", postfix)
    return postfix


# ================= EVALUASI =================
def evaluate_postfix(postfix):
    stack = StackLL()

    print("\n=== PROSES EVALUASI POSTFIX ===")

    for t in postfix:
        if re.match(r'-?\d+\.?\d*', t):
            stack.push(float(t))
            print("Push", t)

        else:
            try:
                b = stack.pop()
                a = stack.pop()
            except:
                raise Exception("Error: operand kurang!")

            if t == "+": result = a + b
            elif t == "-": result = a - b
            elif t == "*": result = a * b
            elif t == "/":
                if b == 0:
                    raise Exception("Error: pembagian dengan nol!")
                result = a / b
            elif t == "^": result = a ** b

            print(f"Hitung: {a} {t} {b} = {result}")
            stack.push(result)

        print("Stack:", end=" ")
        stack.print_stack()

    return stack.pop()


# ================= MAIN =================
while True:
    print("\n=================================")
    expr = input("Masukkan ekspresi aritmatika : ")

    try:
        tokens = tokenize(expr)
        postfix = infix_to_postfix(tokens)
        result = evaluate_postfix(postfix)

        print("\nHASIL AKHIR =", result)

    except Exception as e:
        print("ERROR:", e)

    ulang = input("\nHitung lagi? (y/n): ").lower()
    if ulang != "y":
        break

print("Program selesai")