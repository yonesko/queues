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
            n++;
        }

        while (!StdIn.isEmpty()) {
            n++;
            final String item = StdIn.readString();
            final int j = StdRandom.uniform(n);
            if (j < k) {
                queue.dequeue();
                queue.enqueue(item);
            }
        }

        queue.forEach(System.out::println);
    }
}
