
public class LinkedList<T> {
	public Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }
    public int size() {
        return size;
    }
   /* public void add(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }*/
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        if (compareGreaterThan(head.data, newNode.data)) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node<T> current = head;
        while (current.next != null && !compareGreaterThan(current.next.data, newNode.data)) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    private boolean compareGreaterThan(T a, T b) {
        
        if (a instanceof Integer && b instanceof Integer) {
            return (Integer)a > (Integer)b;
        }
        
        if (a instanceof String && b instanceof String) {
            return ((String)a).compareTo((String)b) > 0;
        }
        
        return a.toString().compareTo(b.toString()) > 0;
    }
   /* public void append(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public void insert(int position, T data) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position");
        }

        Node<T> newNode = new Node<>(data);
        
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }*/
    public T pop() {
        if (head == null) {
            System.out.println("List is empty");
        }       
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    public T popLast() {
        if (head == null) {
           System.out.println("List is empty");
        }
        
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    public boolean search(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
  /*  public void insertAfter(T afterData, T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(afterData)) {
                Node<T> newNode = new Node<>(data);
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
        }
        System.out.println("No such node with "+afterData);
    }*/
    public void delete(T data) {
        if (head == null) return;
        
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return;
        }
        
        Node<T> current = head;
        Node<T> prev = null;
        
        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }
        
        if (current != null) {
            prev.next = current.next;
            size--;
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append("->");
            }
            current = current.next;
        }
        return sb.toString();
    }
}
