
public class Stack {
    int[] data;
    int top;

    Stack(int size) {
        data = new int[size];
        top = -1;
    }

    void push(int x) {
        if (top == data.length - 1) {
            System.out.println("-- overflow");
        } else {
            data[++top] = x;
        }
    }

    int pop() {
        if (top < 0) {
            System.out.println("-- underflow");
            return 0;
        }
        return data[top--];
    }

}