import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
class PermutationTest {

    @Test
    public void t() {
        int k = 7;
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> nums = new LinkedList<>();
        final int N_NUMS = 100;
        for (int i = 1; i <= N_NUMS; i++) {
            nums.add(i);
        }

        final int N_TESTS = 1_000_000;
        for (int i = 0; i < N_TESTS; i++) {
            for (Integer x : rand50(k, nums.iterator())) {
                if (freq.containsKey(x)) {
                    freq.put(x, freq.get(x) + 1);
                }
                else {
                    freq.put(x, 1);
                }
            }
        }

        int perfect = k * N_TESTS / N_NUMS;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("perfect = " + perfect);
        System.out.println(
                "div " +
                        freq.values().stream()
                            .mapToInt(x -> Math.abs(x - perfect))
                            .sum()
                                / N_NUMS

        );
    }

    private List<Integer> fairAll(int k, Iterator<Integer> nums) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        nums.forEachRemaining(queue::enqueue);

        List<Integer> ans = new LinkedList<>();

        Iterator<Integer> iterator = queue.iterator();
        for (int i = 0; i < k; i++) {
            ans.add(iterator.next());
        }

        return ans;
    }

    private List<Integer> rand50(int k, Iterator<Integer> nums) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        int n = 0;
        while (nums.hasNext()) {
            final Integer item = nums.next();
            if (queue.size() == k) {
                if (StdRandom.uniform() < (k + 0.0D) / n) {
                    queue.dequeue();
                    queue.enqueue(item);
                }
            }
            else {
                queue.enqueue(item);
            }
            n++;
        }

        List<Integer> ans = new LinkedList<>();

        queue.iterator().forEachRemaining(ans::add);

        return ans;
    }
}