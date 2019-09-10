/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            if (queue.size() == k) {
                System.out.println(queue.dequeue());
            }
            queue.enqueue(StdIn.readString());
        }
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
