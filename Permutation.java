/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int printed = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty() && printed < k) {
            if (queue.size() == k) {
                System.out.println(queue.dequeue());
                printed++;
            }
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k - printed; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
