import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (item == null) throw new IllegalArgumentException();
        if (size == arr.length) {
            Object[] oldArr = this.arr;
            arr = Arrays.copyOf(arr, arr.length * 2);
            Arrays.fill(oldArr, null);
        }
        arr[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int ind = StdRandom.uniform(size);
        Object item = arr[ind];
        if (size <= arr.length / 4) {
            Object[] newArr = new Object[Math.max(16, size * 2)];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i >= ind ? i + 1 : i];
            }
            arr = newArr;
        }
        else {
            for (int i = ind; i < size; i++) {
                arr[i] = i + 1 < arr.length ? arr[i + 1] : null;
            }
        }
        size--;
        return ((Item) item);
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return ((Item) arr[StdRandom.uniform(size)]);
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
                if (!hasNext()) throw new NoSuchElementException();
                Item o = (Item) arr[inds[ind]];
                ind++;
                return o;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(698);
        rq.dequeue();
        rq.isEmpty();
        rq.enqueue(815);
    }

    private static void assert_(boolean cond) {
        if (!cond) {
            throw new RuntimeException();
        }
    }
}