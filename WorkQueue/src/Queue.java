public class Queue<T> {

    private class Node<T> {
        T value;
        Node next;
        public Node (T value){
            this.value = value;
        }
        public Node (T value, Node next){
            this.value = value;
            this.next = next;
        }
        public T getValue(){
            return value;
        }
    }
    private int counter;
    private Node head;
    private Node tail;

    public Queue() {
        head = null;
        tail = null;
        counter = 0;
    }



    public void enqueue(T newItem) {
        Node temp = new Node (newItem);

        if (tail == null){
            head = temp;
            tail = temp;
            return;
        }
        counter++;
        tail.next = temp;
        tail = temp;
    }

    public T dequeue() {
        if (head == null){
            return null;
        }
        Node temp = new Node (head.getValue(),head.next);
        head = head.next;

        if (head == null){
            tail = null;
        }
        counter--;
        return (T)temp.getValue();


    }

    public T peek() {
        return (T)head.getValue();
    }

    public boolean isEmpty() {
        if (head == null){
            return true;
        }
        return false;
    }

    public int size() {
        int counter = 0;
        Node current = head;
        while (current != null){
            counter++;
            current = current.next;
        }
        return counter;
    }
}