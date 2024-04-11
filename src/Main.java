

class RedBlackTree {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private class Node {

        int key;

        boolean color;

        Node left, right, parent;

        Node(int key) {

            this.key = key;

            color = RED;

            left = right = parent = null;

        }

    }

    private Node root;

    // Rotacje

    private void leftRotate(Node x) {

        Node y = x.right;

        x.right = y.left;

        if (y.left != null)

            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null)

            root = y;

        else if (x == x.parent.left)

            x.parent.left = y;

        else

            x.parent.right = y;

        y.left = x;

        x.parent = y;

    }

    private void rightRotate(Node y) {

        Node x = y.left;

        y.left = x.right;

        if (x.right != null)

            x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null)

            root = x;

        else if (y == y.parent.right)

            y.parent.right = x;

        else

            y.parent.left = x;

        x.right = y;

        y.parent = x;

    }

    // Funkcja pomocnicza do naprawy drzewa po wstawieniu

    private void fixInsert(Node z) {

        while (z != root && z.parent.color == RED) {

            if (z.parent == z.parent.parent.left) {

                Node y = z.parent.parent.right;

                if (y != null && y.color == RED) {

                    z.parent.color = BLACK;

                    y.color = BLACK;

                    z.parent.parent.color = RED;

                    z = z.parent.parent;

                } else {

                    if (z == z.parent.right) {

                        z = z.parent;

                        leftRotate(z);

                    }

                    z.parent.color = BLACK;

                    z.parent.parent.color = RED;

                    rightRotate(z.parent.parent);

                }

            } else {

                Node y = z.parent.parent.left;

                if (y != null && y.color == RED) {

                    z.parent.color = BLACK;

                    y.color = BLACK;

                    z.parent.parent.color = RED;

                    z = z.parent.parent;

                } else {

                    if (z == z.parent.left) {

                        z = z.parent;

                        rightRotate(z);

                    }

                    z.parent.color = BLACK;

                    z.parent.parent.color = RED;

                    leftRotate(z.parent.parent);

                }

            }

        }

        root.color = BLACK;

    }

    // Wstawi

    public void insert(int key) {

        Node z = new Node(key);

        Node y = null;

        Node x = root;

        while (x != null) {

            y = x;

            if (z.key < x.key)

                x = x.left;

            else

                x = x.right;

        }

        z.parent = y;

        if (y == null)

            root = z;

        else if (z.key < y.key)

            y.left = z;

        else

            y.right = z;

        z.left = z.right = null;

        z.color = RED;

        fixInsert(z);

    }

    // Funkcja pomocnicza do inorder traversal

    private void inorderTraversalHelper(Node x) {

        if (x != null) {

            inorderTraversalHelper(x.left);

            System.out.print(x.key + " ");

            inorderTraversalHelper(x.right);

        }

    }

    // Inorder traversal

    public void inorderTraversal() {

        inorderTraversalHelper(root);

    }

    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();

        // Wstawianie elemenw

        tree.insert(10);

        tree.insert(20);

        tree.insert(30);

        tree.insert(40);

        tree.insert(50);

        tree.insert(60);

        tree.insert(70);

        tree.insert(80);

        tree.insert(90);

        // Przechodzenie drzewa w porzdku inorder

        System.out.println("Inorder traversal:");

        tree.inorderTraversal();

    }

}