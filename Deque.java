import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> head, tail;


    private final static class Node<Item> {
        private final Item item;

        private Node<Item> next, prev;

        private Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (head == null) {
            head = tail = new Node<>(item);
        }
        else {
            Node<Item> node = new Node<>(item);
            node.prev = head;
            head.next = node;
            head = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (head == null) {
            head = tail = new Node<>(item);
        }
        else {
            Node<Item> node = new Node<>(item);
            node.next = tail;
            tail.prev = node;
            tail = node;
        }
        size++;
    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = head.item;
        head = head.prev;
        head.next = null;
        size--;
        return item;
    }


    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = tail.item;
        tail = tail.next;
        tail.prev = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node<Item> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Item next() {
                if (curr == null) throw new NoSuchElementException();
                Item item = curr.item;
                curr = curr.prev;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    // unit testing (required


    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        assert_(deque.isEmpty());
        assert_(deque.size() == 0);
        deque.addFirst("privet");
        assert_(!deque.isEmpty());
        assert_(deque.size() == 1);
        deque.addFirst("world");
        assert_(deque.size() == 2);
        assert_(fromIterator(deque.iterator()).equals(Arrays.asList("world", "privet")));
        assert_(deque.removeLast().equals("privet"));
        assert_(fromIterator(deque.iterator()).equals(Collections.singletonList("world")));
        deque.addLast("1");
        deque.addLast("6");
        assert_(fromIterator(deque.iterator()).equals(Arrays.asList("world", "1", "6")));
        assert_(deque.size() == 3);
        assert_(deque.removeFirst().equals("world"));
    }

    private static List<String> fromIterator(Iterator<String> iterator) {
        List<String> ans = new LinkedList<>();

        while (iterator.hasNext()) {
            ans.add(iterator.next());
        }

        return ans;
    }

    private static void assert_(boolean cond) {
        if (!cond) {
            throw new RuntimeException();
        }
    }

}