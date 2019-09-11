/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        if (k == 0) return;
        int n = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }

        while (!StdIn.isEmpty()) {
            final int j = StdRandom.uniform(n + 1);
            final String item = StdIn.readString();
            if (j < k) {
                queue.dequeue();
                queue.enqueue(item);
            }
            n++;
        }

        queue.forEach(System.out::println);
    }
}
