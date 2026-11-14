import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        String[] tasks = {"Task1", "Task2", "Task3", "Task4", "Task5"};

        //  Stack Implementation
        Stack<String> stack = new Stack<>();

        for (String task : tasks) {
            stack.push(task);
        }

        System.out.println("Stack Order: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        // Queue Implementation
        Queue<String> queue = new LinkedList<>();

        for (String task : tasks) {
            queue.offer(task);
        }

        System.out.println("Queue Order:");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();

        System.out.println("\nPrinter should use: Queue");
        System.out.println("Print Order: Task1 Task2 Task3 Task4 Task5");
    }
}