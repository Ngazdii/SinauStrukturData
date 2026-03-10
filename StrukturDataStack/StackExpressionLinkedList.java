import java.util.*;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class StackLL {
    Node top;

    boolean isEmpty() {
        return top == null;
    }

    void push(String data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    String pop() {
        if (isEmpty()) return null;
        String value = top.data;
        top = top.next;
        return value;
    }

    String peek() {
        if (isEmpty()) return null;
        return top.data;
    }

    void printStack() {
        Node temp = top;
        System.out.print("[ ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.print("]");
    }
}

public class StackExpressionLinkedList {

    static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*") || op.equals("/")) return 2;
        return 0;
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    static String infixToPostfix(String[] tokens) {

        StackLL stack = new StackLL();
        String postfix = "";

        System.out.println("\n=== PROSES INFIX → POSTFIX ===");

        for (String t : tokens) {

            System.out.println("\nToken : " + t);

            if (t.matches("\\d+")) {
                postfix += t;
                System.out.println("Masuk ke postfix");
            }

            else if (t.equals("(")) {
                stack.push(t);
                System.out.println("Push '(' ke stack");
            }

            else if (t.equals(")")) {

                while (!stack.peek().equals("(")) {
                    postfix += stack.pop();
                }
                stack.pop();
                System.out.println("Pop sampai '('");
            }

            else if (isOperator(t)) {

                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(t)) {

                    postfix += stack.pop();
                }

                stack.push(t);
                System.out.println("Push operator ke stack");
            }

            System.out.print("Stack : ");
            stack.printStack();

            System.out.println("\nPostfix : " + postfix);
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        System.out.println("\nFinal Postfix : " + postfix);

        return postfix;
    }

    static double evaluatePostfix(String postfix) {

        StackLL stack = new StackLL();

        System.out.println("\n=== PROSES EVALUASI POSTFIX ===");

        for (int i = 0; i < postfix.length(); i++) {

            String t = String.valueOf(postfix.charAt(i));

            if (t.matches("\\d")) {

                stack.push(t);
                System.out.println("Push " + t);

                System.out.print("Stack : ");
                stack.printStack();
                System.out.println();
            }

            else {

                double b = Double.parseDouble(stack.pop());
                double a = Double.parseDouble(stack.pop());

                double result = 0;

                switch (t) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = a / b; break;
                }

                System.out.println("Pop " + a + " dan " + b);
                System.out.println("Hitung : " + a + " " + t + " " + b + " = " + result);

                stack.push(String.valueOf(result));

                System.out.print("Stack : ");
                stack.printStack();
                System.out.println();
            }
        }

        return Double.parseDouble(stack.pop());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String loop;

        do {

            System.out.println("\n=================================");
            System.out.print("Masukkan ekspresi aritmatika : ");

            String input = sc.nextLine();

            String[] tokens = input.replaceAll("([+\\-*/()])", " $1 ").trim().split("\\s+");

            String postfix = infixToPostfix(tokens);

            double result = evaluatePostfix(postfix);

            System.out.println("\nHASIL AKHIR = " + result);

            System.out.print("\nHitung lagi? (y/n) : ");
            loop = sc.nextLine().toLowerCase();

        } while (loop.equals("y"));

        System.out.println("Program selesai");
    }
}