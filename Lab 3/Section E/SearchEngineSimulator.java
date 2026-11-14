import java.util.*;

public class SearchEngineSimulator {
    static final int SIZE = 37;

    static class Entry {
        String word;
        int value;

        Entry(String w, int v) {
            word = w;
            value = v;
        }
    }

    static List<Entry>[] chain = new List[SIZE];
    static Entry[] probe = new Entry[SIZE];

    static int hash(String word) {
        int h = 0;
        for (char c : word.toLowerCase().toCharArray())
            h = (31 * h + c) % SIZE;
        return Math.floorMod(h, SIZE);
    }

    static void insert(String w, int v) {
        int index = hash(w);
        if (chain[index] == null)
            chain[index] = new ArrayList<>();
        chain[index].add(new Entry(w, v));
        while (probe[index] != null)
            index = (index + 1) % SIZE;
        probe[index] = new Entry(w, v);
    }

    static int[] binary(String[] words, String target) {
        int l = 0, r = words.length - 1, checks = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            checks++;
            int c = words[m].compareTo(target);
            if (c == 0)
                return new int[] { m, checks };
            if (c < 0)
                l = m + 1;
            else
                r = m - 1;
        }
        return new int[] { -1, checks };
    }

    static int[] chaining(String w) {
        int i = hash(w), checks = 0;
        if (chain[i] != null)
            for (Entry e : chain[i]) {
                checks++;
                if (e.word.equals(w))
                    return new int[] { e.value, checks };
            }
        return new int[] { -1, checks };
    }

    static int[] probing(String w) {
        int i = hash(w), checks = 0;
        for (int n = 0; n < SIZE; n++) {
            checks++;
            if (probe[i] == null)
                return new int[] { -1, checks };
            if (probe[i].word.equals(w))
                return new int[] { probe[i].value, checks };
            i = (i + 1) % SIZE;
        }
        return new int[] { -1, checks };
    }

    public static void main(String[] args) {
        String[] data = { "algorithm", "array", "binary", "bubble", "class", "compiler", "database", "graph", "hash",
                "heap", "index", "java", "linked", "merge", "queue", "recursion", "search", "sorting", "stack", "tree",
                "variable", "vertex", "quick", "radix", "insertion", "selection", "pointer", "method", "object",
                "program" };
        for (int i = 0; i < data.length; i++)
            insert(data[i], 1001 + i);
        String[] sorted = data.clone();
        Arrays.sort(sorted);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a word to search: ");
        String target = sc.nextLine().toLowerCase();
        int[] b = binary(sorted, target), c = chaining(target), p = probing(target);
        System.out.printf("Binary search: %s, comparisons=%d%n", b[0] >= 0 ? "FOUND" : "NOT FOUND", b[1]);
        System.out.printf("Hash (separate chaining): %s, comparisons=%d%n", c[0] >= 0 ? "FOUND" : "NOT FOUND", c[1]);
        System.out.printf("Hash (linear probing): %s, probes=%d%n", p[0] >= 0 ? "FOUND" : "NOT FOUND", p[1]);
        if (b[0] >= 0)
            System.out.println("Mock document/frequency value: " + (1001 + Arrays.asList(sorted).indexOf(target)));
        sc.close();
    }
}
