package DataStructures;

public class Node<T> {
    private T data;
    private Node next;
    private Node previous;

    /**
     * 
     * @param data
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    //Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node siguiente) {
        this.next = siguiente;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
    
    
    
}
