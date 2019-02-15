/**
 * @Author Joakim Olsson <lomo133>
 * Last edit: 2019-02-11
 */

/**
 * Binary search tree data structure implementation.
 */
public class Tree<T extends Comparable<T>> {
    private int size;
    private Node<T> root;

    /**
     * A class for the nodes in the tree.
     * @param <T> generic type
     */
    static class Node<T> {
        private T value;
        private Node<T> leftNode;
        private Node<T> rightNode;

        /**
         * Constructor for nodes.
         * @param value in that node
         */
        public Node(T value) {
            this.value = value;
            leftNode = null;
            rightNode = null;
        }
    }

    /**
     * Constructor for the BST.
     */
    public Tree() {
        size = 0;
        root = null;
    }

    /**
     * Test for presence of a value, searched with iteration.
     * @param elem the element searched for.
     * @return true if found, false otherwise.
     */
    public boolean search(T elem) {
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(elem) == 0)
                return true;
            else if (current.value.compareTo(elem) < 0)
                current = current.leftNode;
            else
                current = current.rightNode;
        }
        return false;
    }

    /**
     * Adds an element to the tree and returns true, adding a duplicate will return false.
     * @param elem the element to be addded
     * @return true if added, otherwise false
     */
    public boolean insert(T elem) {

        if (size == 0) {
            root = new Node<>(elem);
            size++;
            return true;
        }
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(elem) == 0)
                return false;
            else if (current.value.compareTo(elem) > 0) {
                if (current.rightNode == null) {
                    current.rightNode = new Node<>(elem);
                    size++;
                    return true;
                }
                else {
                    current = current.rightNode;
                }
            } else {
                if (current.leftNode == null) {
                    current.leftNode = new Node<>(elem);
                    size++;
                    return true;
                }
                else {
                    current = current.leftNode;
                }
            }
        }
        return false;
    }

    /**
     * Returns the size of the tree.
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Returns the height of the tree. Empty tree and a tree with only a root node will both return 0.
     * @return the height
     */
    public int height() {
        return height(root);
    }


    /**
     * Helper method for calculating the height of the tree.
     * @param node the node
     * @return
     */
    private int height(Node<T> node) {
        if (node == null)
            return 0;
        if (node.rightNode == null && node.leftNode == null)
            return 0;
        int heightLeft = height(node.leftNode);
        int heightRight = height(node.rightNode);
        return Math.max(heightLeft, heightRight) + 1;
    }

    /**
     * Returns the number of leaves in the tree using recursion.
     * @return the number of leaves using the helper method
     */
    public int leaves() {
        return leaves(root);
    }

    /**
     * Helper method for counting the number of leaves in the tree.
     * @param node the node
     * @return 0 if the node is null, 1 if the node is a leaf or returns a recursive call if neither
     */
    private int leaves(Node<T> node) {
        if (node == null)
            return 0;
        if (node.leftNode == null && node.rightNode == null)
            return 1;
        return leaves(node.leftNode) + leaves(node.rightNode);
    }

    /**
     * Returns a String representation of the tree using StringBuilder, the elements are printed in ascending order.
     * @return the String representation
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        return toString(str, root);
    }

    /**
     * Helper for the toString method to create the String.
     * @param str the StringBuilder
     * @param node a node in the tree
     * @return the String representation for the values in the tree
     */
    private String toString(StringBuilder str, Node<T> node) {
        if (node == null)
            return "[]";
        toString(str, node.rightNode);
        str.append(", ").append(node.value);
        toString(str, node.leftNode);
        return "[" + str.substring(2) + "]";

    }
}
