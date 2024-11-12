package DataStructures;

import Classes.Character;

public class Queue<T> {

    private Node<T> head, tail;
    private int size;

    // Constructor
    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Agrega un nodo a la cola
     *
     * @param data (Información a agregar)
     * @return Node agregado
     */
    public Node add(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            setHead(node);
            setTail(node);
        } else {
            Node pointer = getTail();
            pointer.setNext(node);
            setTail(node);
        }
        size++;
        return node;
    }

    /**
     * Desencola un Node pero retorna el Node eliminado
     *
     * @return Node desencolado
     */
    public T dispatch() {
        if (!isEmpty()) {
            Node pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            if (getHead() == null) {
                setTail(null);
            }
            size--;
            return (T) pointer.getData();
        }
        return null;
    }

    /**
     * Desencola un Node
     */
    public void remove() {
        if (!isEmpty()) {
            Node pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            if (getHead() == null) {
                setTail(null);
            }
            size--;
        }
    }

    public void removeElement(T element) {
        if (isEmpty()) {
            return; // Queue is empty, nothing to remove
        }

        // Handling the case when the element is at the front of the queue
        if (element.equals(getHead().getData())) {
            Node<T> temp = getHead();
            setHead(temp.getNext());
            ;
            if (getHead() == null) {
                setTail(null);
                ; // Queue becomes empty
            }
            size--;
            return;
        }

        // Find the element in the queue
        Node<T> current = getHead();
        Node<T> prev = null;
        while (current != null && !current.getData().equals(element)) {
            prev = current;
            current = current.getNext();
        }

        // Element not found
        if (current == null) {
            return;
        }

        // Remove the element
        prev.setNext(current.getNext());
        if (current.getNext() == null) {
            // Removing the last element, update the tail
            setTail(prev);
        }
        size--;
    }

    /**
     * Obtiene el elemento de la Cola que se encuentra en el índice indicado
     *
     * @param index (Índice del elemento que se desea obtener)
     * @return
     */
    public T getElement(int index) {
        if (isEmpty()) {
            return null;

        } else {
            Node<T> pointer = getHead();
            int counter = 0;
            while (counter < index && pointer.getNext() != null) {
                pointer = pointer.getNext();
                counter++;
            }
            return pointer.getData();

        }
    }

    /**
     * Imprime la cola
     */
    public String printQueue() {
        Node pointer = getHead();
        String sQueue = "";

        while (pointer != null) {
            Character character = (Character) pointer.getData();
            if (pointer == getTail()) {
                sQueue += character.getID();
            } else {
                sQueue += character.getID() + ", ";
            }
            pointer = pointer.getNext();
        }
        return sQueue;
    }

    // Getters and Setters
    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
