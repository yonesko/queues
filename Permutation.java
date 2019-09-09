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
        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
