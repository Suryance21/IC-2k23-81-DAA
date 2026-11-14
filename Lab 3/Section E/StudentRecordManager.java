import java.util.*;

public class StudentRecordManager {
    static class Student {
        int roll;
        String name;
        double marks;

        Student(int roll, String name, double marks) {
            this.roll = roll;
            this.name = name;
            this.marks = marks;
        }

        public String toString() {
            return String.format("Roll=%d, Name=%s, Marks=%.2f", roll, name, marks);
        }
    }

    static void sort(List<Student> list, String field, String algorithm) {
        Comparator<Student> c = field.equals("name") ? Comparator.comparing(s -> s.name.toLowerCase())
                : field.equals("marks") ? Comparator.comparingDouble(s -> s.marks)
                        : Comparator.comparingInt(s -> s.roll);
        // The selected algorithm is implemented as a generic insertion/merge/quick/heap
        // style choice.
        // Java's comparator-based list sort is avoided: these routines sort the
        // supplied list.
        Student[] a = list.toArray(new Student[0]);
        if (algorithm.equals("Bubble")) {
            for (int i = 0; i < a.length - 1; i++)
                for (int j = 0; j < a.length - 1 - i; j++)
                    if (c.compare(a[j], a[j + 1]) > 0) {
                        Student t = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = t;
                    }
        } else if (algorithm.equals("Selection")) {
            for (int i = 0; i < a.length - 1; i++) {
                int min = i;
                for (int j = i + 1; j < a.length; j++)
                    if (c.compare(a[j], a[min]) < 0)
                        min = j;
                Student t = a[i];
                a[i] = a[min];
                a[min] = t;
            }
        } else if (algorithm.equals("Insertion")) {
            for (int i = 1; i < a.length; i++) {
                Student key = a[i];
                int j = i - 1;
                while (j >= 0 && c.compare(a[j], key) > 0) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = key;
            }
        } else if (algorithm.equals("Quick"))
            quick(a, 0, a.length - 1, c);
        else if (algorithm.equals("Heap"))
            heap(a, c);
        else
            merge(a, 0, a.length - 1, c);
        list.clear();
        list.addAll(Arrays.asList(a));
    }

    static void quick(Student[] a, int l, int r, Comparator<Student> c) {
        if (l < r) {
            int p = l, i = l;
            Student pivot = a[r];
            for (int j = l; j < r; j++)
                if (c.compare(a[j], pivot) <= 0) {
                    Student t = a[i];
                    a[i++] = a[j];
                    a[j] = t;
                }
            Student t = a[i];
            a[i] = a[r];
            a[r] = t;
            quick(a, l, i - 1, c);
            quick(a, i + 1, r, c);
        }
    }

    static void merge(Student[] a, int l, int r, Comparator<Student> c) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        merge(a, l, m, c);
        merge(a, m + 1, r, c);
        Student[] t = new Student[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r)
            t[k++] = c.compare(a[i], a[j]) <= 0 ? a[i++] : a[j++];
        while (i <= m)
            t[k++] = a[i++];
        while (j <= r)
            t[k++] = a[j++];
        System.arraycopy(t, 0, a, l, t.length);
    }

    static void heap(Student[] a, Comparator<Student> c) {
        for (int i = a.length / 2 - 1; i >= 0; i--)
            down(a, a.length, i, c);
        for (int i = a.length - 1; i > 0; i--) {
            Student t = a[0];
            a[0] = a[i];
            a[i] = t;
            down(a, i, 0, c);
        }
    }

    static void down(Student[] a, int n, int i, Comparator<Student> c) {
        int m = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && c.compare(a[l], a[m]) > 0)
            m = l;
        if (r < n && c.compare(a[r], a[m]) > 0)
            m = r;
        if (m != i) {
            Student t = a[i];
            a[i] = a[m];
            a[m] = t;
            down(a, n, m, c);
        }
    }

    static Student binarySearch(List<Student> a, int roll) {
        int l = 0, r = a.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (a.get(m).roll == roll)
                return a.get(m);
            if (a.get(m).roll < roll)
                l = m + 1;
            else
                r = m - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(104, "Aman", 82),
                new Student(101, "Riya", 91), new Student(103, "Karan", 76), new Student(102, "Neha", 88)));
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\\n1 Sort records  2 Search by roll number  3 Compare algorithms  0 Exit");
            int choice = sc.nextInt();
            if (choice == 0)
                break;
            if (choice == 1) {
                System.out.println("Sort by: 1 Roll 2 Name 3 Marks");
                int f = sc.nextInt();
                System.out.println("Algorithm: 1 Bubble 2 Selection 3 Insertion 4 Merge 5 Quick 6 Heap");
                int alg = sc.nextInt();
                String field = f == 2 ? "name" : f == 3 ? "marks" : "roll";
                String[] algorithms = { "Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap" };
                String algorithm = alg >= 1 && alg <= 6 ? algorithms[alg - 1] : "Merge";
                sort(students, field, algorithm);
                students.forEach(System.out::println);
            } else if (choice == 2) {
                List<Student> copy = new ArrayList<>(students);
                sort(copy, "roll", "Merge");
                System.out.print("Enter roll number: ");
                Student found = binarySearch(copy, sc.nextInt());
                System.out.println(found == null ? "Student not found" : found);
            } else if (choice == 3) {
                String[] names = { "Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap" };
                long fastest = Long.MAX_VALUE, slowest = Long.MIN_VALUE;
                String fast = "", slow = "";
                for (String name : names) {
                    List<Student> copy = new ArrayList<>(students);
                    long start = System.nanoTime();
                    sort(copy, "roll", name);
                    long time = System.nanoTime() - start;
                    System.out.printf("%s: %d ns%n", name, time);
                    if (time < fastest) {
                        fastest = time;
                        fast = name;
                    }
                    if (time > slowest) {
                        slowest = time;
                        slow = name;
                    }
                }
                System.out.printf("Fastest: %s, slowest: %s, difference: %d ns%n", fast, slow, slowest - fastest);
            }
        }
        sc.close();
    }
}
