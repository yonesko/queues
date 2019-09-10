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
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (queue.size() == k) {
                if (StdRandom.uniform(100) < 50) {
                    queue.dequeue();
                    queue.enqueue(item);
                }
            }
            else {
                queue.enqueue(item);
            }
        }

        queue.forEach(System.out::println);
    }
}
