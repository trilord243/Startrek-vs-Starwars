/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataStructures;

import javax.swing.JOptionPane;



public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Constructor
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;

    }

    /**
     * Constructor si se indica el primer Node
     *
     * @param node
     */
    public LinkedList(Node<T> node) {
        this.head = this.tail = node;
        size++;
    }

    /**
     *
     * @return Booleano; "true" si la lista está vacía, "false" si posee al
     *         menos un elemento
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserta un elemento al principio de la Lista
     *
     * @param element (Elemento a agregar)
     * @return Node que se agregó
     */
    public Node<T> addFirst(T element) {
        Node<T> node = new Node(element);

        if (isEmpty()) {
            setHead(node);
            setTail(node);

        } else {
            node.setNext(head);
            setHead(node);

        }
        size++;
        return node;
    }

    /**
     * Inserta un elemento al final de la Lista
     *
     * @param element (Elemento a agregar)
     * @return Node que se agregó
     */
    public Node<T> addLast(T element) {
        Node<T> node = new Node(element);

        if (isEmpty()) {
            setHead(node);
            setTail(node);
            getHead().setNext(getTail());
            getTail().setNext(null);

        } else {
            getTail().setNext(node);
            setTail(node);

        }
        size++;
        return node;
    }

    /**
     * Inserta un elemento en el índice indicado de la Lista
     *
     * @param index   (Índice en el que se quiere agregar)
     * @param element (Elemento a agregar)
     * @return Node que se agregó
     */
    public Node<T> addInIndex(int index, T element) {
        Node<T> node = new Node(element);

        if (isEmpty()) {
            setHead(node);

        } else {

            if (index > getSize()) {
                JOptionPane.showMessageDialog(null,
                        "The index is out of bounds. The element will be added on the last index.");
                return addLast(element);

            } else {
                Node<T> pointer = getHead();
                int counter = 0;
                while (counter < (index - 1) && pointer.getNext() != null) {
                    pointer = pointer.getNext();
                    counter++;

                }
                node.setNext(pointer.getNext());
                pointer.setNext(node);

            }
        }
        size++;
        return node;
    }

    /**
     * Elimina el primer elemento de la Lista
     *
     * @return Node eliminado
     */
    public Node<T> deleteFirst() {

        if (!isEmpty()) {
            Node<T> pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            size--;

        } else {
            JOptionPane.showMessageDialog(null, "Your list is empty");

        }
        return null;
    }

    /**
     * Elimina el último elemento de la Lista
     *
     * @return Node eliminado
     */
    public Node<T> deleteLast() {

        if (!isEmpty()) {
            Node<T> pointer = getHead();

            if (getSize() == 1) {
                setHead(null);

            } else {
                while (pointer.getNext() != null && pointer.getNext().getNext() != null) {
                    pointer = pointer.getNext();
                }
                Node<T> nodeReturn = pointer.getNext();
                pointer.setNext(null);
                setTail(pointer);
                size--;
                return nodeReturn;

            }

        } else {
            JOptionPane.showMessageDialog(null, "Your list is empty");

        }

        return null;

    }

    /**
     * Elimina el elemento de la Lista que se encuentra en el índice indicado
     *
     * @param index (Índice del elemento que se desea eliminar)
     * @return Node eliminado
     */
    public Node<T> deleteInIndex(int index) {
        if (!isEmpty()) {
            Node<T> pointer = getHead();
            if (index > getSize()) {
                JOptionPane.showMessageDialog(null,
                        "The index is out of bounds. Instead, the last element will be deleted.");
                return deleteLast();

            } else {
                int counter = 0;
                while (counter < (index - 1) && pointer.getNext() != null) {
                    pointer = pointer.getNext();
                    counter++;

                }
            }
            Node<T> aux = pointer.getNext();
            pointer.setNext(aux.getNext());
            aux.setNext(null);
            size--;
        }
        return null;

    }

    /**
     * Imprime la lista
     */
    public void printList() {

        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your list is empty");

        } else {
            String sList = "[ ";

            for (int i = 0; i < getSize(); i++) {

                if (i == getSize() - 1) {
                    sList += getElement(i);
                } else {
                    sList += getElement(i) + ", ";
                }
            }
            sList += " ]";

            JOptionPane.showMessageDialog(null, sList);

        }
    }

    /**
     * Obtiene el elemento de la Lista que se encuentra en el índice indicado
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
     * Busca el nodo central de una Lista
     *
     * @param head (Cabeza de una lista)
     * @param tail (Cola de una lista
     * @return Node central
     */
    public Node middleNode(Node head, Node tail) {
        Node slow = head;
        Node fast = head;

        while (fast != tail && fast.getNext() != tail) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }
        return slow;
    }

    /**
     * Une dos listas dadas en orden ascendente
     *
     * @param l1
     * @param l2
     * @return Lista ordenada ascendentemente
     */
    public LinkedList merge(LinkedList l1, LinkedList l2) {
        Node one = l1.getHead();
        Node two = l2.getHead();

        LinkedList merged = new LinkedList();

        while (one != null && two != null) {
            if ((int) one.getData() < (int) two.getData()) {
                merged.addLast(one.getData());
                one = one.getNext();

            } else {
                merged.addLast(two.getData());
                two = two.getNext();

            }
        }
        while (one != null) {
            merged.addLast(one.getData());
            one = one.getNext();
        }
        while (two != null) {
            merged.addLast(two.getData());
            two = two.getNext();
        }

        return merged;
    }

    /**
     * Ordena una lista utilizando mergeSort ascendentemente
     *
     * @param head
     * @param tail
     * @return
     */
    public LinkedList mergeSort(Node head, Node tail) {
        if (head == tail) {
            LinkedList br = new LinkedList();
            br.addLast(head.getData());
            return br;
        }

        Node mid = middleNode(head, tail);

        LinkedList leftSide = mergeSort(head, mid);
        LinkedList rightSide = mergeSort(mid.getNext(), tail);

        LinkedList sorted = merge(leftSide, rightSide);
        return sorted;

    }

    /**
     * Determina si en la lista se encuentra un elemento con el nombre indicado
     *
     * @param name (Nombre del elemento)
     * @return Booleano; "true" si existe el elemento; "false si no existe
     */
    public boolean includes(String name) {
        int start = 0;
        Node<String> current = getHead();
        while (start < getSize() && current.getNext() != null) {
            if (name.equals(current.getData())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // Getters and Setters
    public Node getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }
}
