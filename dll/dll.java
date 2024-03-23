package dll;
public class dll {

    public static void main(String[] args) {
        DoublyLinkedList myList = new DoublyLinkedList();

        myList.prepend(5);
        myList.prepend(10);
        myList.prepend(15);
        myList.append(12);
        myList.prepend(3);
        myList.prepend(1);
        myList.append(2);
        myList.delete(1);
        myList.delete(2);
        myList.delete(4);
        myList.prepend(9);
        myList.prepend(1);
        myList.prepend(140);
        myList.prepend(115);

        System.out.println("Original DLL:");
        myList.printList();
        myList.printListReversed();
        myList.getLen();

        myList.head = myList.mergeSort(myList.head);

        System.out.println("\nSorted DLL:");
        myList.printList();
        myList.printListReversed();
        myList.getLen();
    }

    static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public void prepend(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }

        public void append(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public void delete(int data) {
            Node currentNode = head;
            Node nodeTail = tail;

            if (currentNode != null && currentNode.data == data) {
                head = currentNode.next;
                if (head != null) {
                    head.prev = null;
                }
                size--;
                return;
            } else if (nodeTail != null && nodeTail.data == data) {
                tail = nodeTail.prev;
                if (tail != null) {
                    tail.next = null;
                }
                size--;
                return;
            } else {
                while (currentNode != null && currentNode.data != data) {
                    currentNode = currentNode.next;
                }
                if (currentNode != null) {
                    currentNode.prev.next = currentNode.next;
                    if (currentNode.next != null) {
                        currentNode.next.prev = currentNode.prev;
                    }
                    size--;
                } else {
                    System.out.println(data + " not found.");
                }

            }
        }

        public int search(int data) {
            Node currentNode = head;
            int pos = 0;
            while (currentNode != null && currentNode.data != data) {
                currentNode = currentNode.next;
                pos++;
            }
            System.out.println("\n position:" + pos);
            return pos;
        }

        // Merge two sorted DLLs
        Node merge(Node left, Node right) {
            if (left == null)
                return right;
            if (right == null)
                return left;

            if (left.data < right.data) {
                left.next = merge(left.next, right);
                left.next.prev = left;
                left.prev = null;
                return left;
            } else {
                right.next = merge(left, right.next);
                right.next.prev = right;
                right.prev = null;
                return right;
            }
        }

        Node mergeSort(Node head) {
            if (head == null || head.next == null)
                return head;

            Node mid = findMiddle(head);
            Node nextOfMid = mid.next;
            mid.next = null;
            nextOfMid.prev = null;

            Node left = mergeSort(head);
            Node right = mergeSort(nextOfMid);

            Node sortedList = merge(left, right);

            // Update the prev reference of the new head (sortedList)
            sortedList.prev = null;

            Node current = sortedList;
            while (current.next != null) {
                current.next.prev = current;
                current = current.next;
            }

            this.head = sortedList;
            this.tail = current;

            return sortedList;
        }

        // Find the middle node of the DLL
        Node findMiddle(Node node) {
            if (node == null)
                return node;
            Node fast = node.next;
            Node slow = node;

            while (fast != null) {
                fast = fast.next;
                if (fast != null) {
                    slow = slow.next;
                    fast = fast.next;
                }
            }
            return slow;
        }

        public void getLen() {
            int lenght = search(tail.data);
            System.out.println("\nlargo: " + lenght + " numero:" + tail.data);
        }

        public void printList() {
            Node current = head;
            System.out.println("\n");
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
        }

        public void printListReversed() {
            Node current = tail;
            System.out.println("\n");
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.prev;
            }
        }
    }

}