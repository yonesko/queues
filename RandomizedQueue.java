import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] arr = new Object[16];

    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        size++;
        arr[size] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        int ind = StdRandom.uniform(size + 1);
        Object item = arr[ind];
        System.arraycopy(arr, ind + 1, arr, ind, arr.length - ind - 1);
        size--;
        return ((Item) item);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return ((Item) arr[StdRandom.uniform(size + 1)]);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        int[] inds = new int[size];

        for (int i = 0; i < size; i++) {
            inds[i] = i;
        }

        StdRandom.shuffle(inds);

        return new Iterator<Item>() {

            private int ind;

            @Override
            public boolean hasNext() {
                return ind < size;
            }

            @Override
            public Item next() {
                Item o = (Item) arr[inds[ind]];
                ind++;
                return o;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        assert_(queue.isEmpty());
        assert_(queue.size() == 0);
        queue.enqueue("q");
        assert_(!queue.isEmpty());
        assert_(queue.size() == 1);
        queue.enqueue("v");
    }

    private static void assert_(boolean cond) {
        if (!cond) {
            throw new RuntimeException();
        }
    }
}