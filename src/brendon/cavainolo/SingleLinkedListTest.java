package brendon.cavainolo;

public class SingleLinkedListTest {
    public static SingleLinkedList singleList;

    public static void main(String[] args) {

        // Default constructor
        singleList = new SingleLinkedList();

        // add more elements to LinkedList
        singleList.add("1");
        singleList.add("2");
        singleList.add("3");
        singleList.add("4");
        singleList.add("5");

        /*
         * Please note that primitive values can not be added into
         * LinkedList directly. They must be converted to their
         * corresponding wrapper class.
         */

        System.out.println("Print: singleList: \t" + singleList);
        System.out.println(".size(): \t" + singleList.size());
        System.out.println(".get(3): \t" + singleList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".remove(2): \t" + singleList.remove(2) + " (element removed)");
        System.out.println(".get(3): \t" + singleList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".size(): \t" + singleList.size());
        System.out.println("Print again: singleList: \t" + singleList);
    }

}

class SingleLinkedList {

    private static int counter;
    private Node head;

    // Default constructor
    public SingleLinkedList() {

    }

    // appends the specified element to the end of this list.
    public void add(Object data) {

        // Initialize Node only incase of 1st element
        if (head == null) {
            head = new Node(data);
        }

        Node singleTemp = new Node(data);
        Node singleCurrent = head;

        // Let's check for NPE before iterate over singleCurrent
        // NPE? Null Pointer Exception (Google returns some other bizarre defns)
        if (singleCurrent != null) {

            // starting at the head node,
            //crawl to the end of the list and
            //then add element after last node
            while (singleCurrent.getNext() != null) {
                singleCurrent = singleCurrent.getNext();
            }

            // the last node's "next" reference set to our new node
            singleCurrent.setNext(singleTemp);
        }

        // increment the number of elements variable
        incrementCounter();
    }

    private static int getCounter() {
        return counter;
    }

    private static void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }

    // inserts the specified element at the specified position in this list
    public void add(Object data, int index) {
        Node singleTemp = new Node(data);
        Node singleCurrent = head;

        // Let's check for NPE before iterate over singleCurrent
        if (singleCurrent != null) {
            // crawl to the requested index or the last element in the list,
            // whichever comes first
            for (int i = 0; i < index && singleCurrent.getNext() != null; i++) {
                singleCurrent = singleCurrent.getNext();
            }
        }

        // set the new node's next-node reference to this node's next-node reference
        singleTemp.setNext(singleCurrent.getNext());

        // now set this node's next-node reference to the new node
        singleCurrent.setNext(singleTemp);

        // increment the number of elements variable
        incrementCounter();
    }

    public Object get(int index)
    // returns the element at the specified position in this list.
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        Node singleCurrent = null;
        if (head != null) {
            singleCurrent = head.getNext();
            for (int i = 0; i < index; i++) {
                if (singleCurrent.getNext() == null)
                    return null;

                singleCurrent = singleCurrent.getNext();
            }
            return singleCurrent.getData();
        }
        return singleCurrent;

    }

    // removes the element at the specified position in this list.
    public boolean remove(int index) {

        // if the index is out of range, exit
        if (index < 1 || index > size())
            return false;

        Node singleCurrent = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (singleCurrent.getNext() == null)
                    return false;

                singleCurrent = singleCurrent.getNext();
            }
            singleCurrent.setNext(singleCurrent.getNext().getNext());

            // decrement the number of elements variable
            decrementCounter();
            return true;

        }
        return false;
    }

    // returns the number of elements in this list.
    public int size() {
        return getCounter();
    }

    public String toString() {
        String output = "";

        if (head != null) {
            Node singleCurrent = head.getNext();
            while (singleCurrent != null) {
                output += "[" + singleCurrent.getData().toString() + "]";
                singleCurrent = singleCurrent.getNext();
            }

        }
        return output;
    }

    private class Node {
        // reference to the next node in the chain, or null if there isn't one.
        Node next;

        // data carried by this node. could be of any type you need.
        Object data;

        // Node constructor
        public Node(Object dataValue) {
            next = null;
            data = dataValue;
        }

        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(Object dataValue, Node nextValue) {
            next = nextValue;
            data = dataValue;
        }

        // these methods should be self-explanatory
        public Object getData() {
            return data;
        }

        @SuppressWarnings("unused")
        public void setData(Object dataValue) {
            data = dataValue;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextValue) {
            next = nextValue;
        }

    }
}
