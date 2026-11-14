public class Part4 {

    public static void main(String[] args) {

        int singleCount = 0;

        System.out.println("Single Loop Output:");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
            singleCount++;
        }

        System.out.println("\nSingle Loop Runs: " + singleCount);

        int n = 20;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            count++;
        }

        System.out.println("Single Loop Runs for n = 20: " + count);

        int nestedCount = 0;

        System.out.println("\nNested Loop Output:");

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.println("(" + i + ", " + j + ")");
                nestedCount++;
            }
        }

        System.out.println("Nested Loop Total Prints: " + nestedCount);

        int m = 10;
        int total = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                total++;
            }
        }

        System.out.println("Nested Loop Runs for n = 10: " + total);
    }
}