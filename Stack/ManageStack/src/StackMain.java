import java.util.List;

public class StackMain {
    public static void main(String[] args) throws Exception {

        Stack stack = new Stack(5);
        // Verwendung:
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); // prints 3
        System.out.println(stack.pop()); // prints 2
        stack.push(4);
        System.out.println(stack.pop()); // prints 4
        System.out.println(stack.pop()); // prints 1
        System.out.println(stack.pop());

    }
}
