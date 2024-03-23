package bst;

public class bst {
    public static void main(String[] args) {
        BinaryTree myTree = new BinaryTree();
        myTree.insert(6);
        myTree.insert(2);
        myTree.insert(4);
        myTree.insert(7);
        myTree.insert(5);
        myTree.insert(9);
        myTree.insert(10);
        myTree.insert(11);
        myTree.insert(16);
        myTree.insert(12);
        myTree.insert(3);
        myTree.insert(1);
        myTree.trueGet(16);
        myTree.delete(12);
        myTree.delete(9);
        myTree.delete(4);
        myTree.delete(100);
        myTree.trueGet(16);

        myTree.print();

        myTree.trueGet(16);
    }

    static class Tree {
        int data;
        Tree parent;
        Tree left;
        Tree right;
        int height;

        public Tree(int data) {
            this.data = data;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    static class BinaryTree {
        Tree root;
        int size;

        public BinaryTree() {
            this.root = null;
            this.size = 0;
        }

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private Tree insertRec(Tree root, int data) {
            // Paso 1: Realizar la inserción normal
            if (root == null) {
                return new Tree(data);
            }

            if (data < root.data) {
                root.left = insertRec(root.left, data);
            } else if (data > root.data) {
                root.right = insertRec(root.right, data);
            } else {
                // El nodo ya existe, no hacemos nada
                return root;
            }

            // Paso 2: Actualizar la altura del nodo actual
            updateHeight(root);

            // Paso 3: Calcular el balance del nodo actual
            int balance = getBalance(root);

            // Paso 4: Realizar rotaciones si el balance es incorrecto
            // Rotación simple a la derecha (RR)
            if (balance > 1 && data < root.left.data) {
                return rightRotate(root);
            }
            // Rotación simple a la izquierda (LL)
            if (balance < -1 && data > root.right.data) {
                return leftRotate(root);
            }
            // Rotación doble a la izquierda-derecha (LR)
            if (balance > 1 && data > root.left.data) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
            // Rotación doble a la derecha-izquierda (RL)
            if (balance < -1 && data < root.right.data) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            // Devolver el nodo actual
            return root;
        }

        private void updateHeight(Tree node) {
            if (node != null) {
                node.height = 1 + Math.max(height(node.left), height(node.right));
            }
        }

        private int height(Tree node) {
            return (node == null) ? -1 : node.height;
        }

        private int getBalance(Tree node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        private Tree rightRotate(Tree y) {
            Tree x = y.left;
            Tree T2 = x.right;

            x.right = y;
            y.left = T2;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        private Tree leftRotate(Tree x) {
            Tree y = x.right;
            Tree T2 = y.left;

            y.left = x;
            x.right = T2;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        public void delete(int data) {
            root = deleteRec(root, data);
        }
        
        private Tree deleteRec(Tree root, int data) {
            if (root == null) {
                return null; 
            }
        
            if (data < root.data) {
                root.left = deleteRec(root.left, data);
            } else if (data > root.data) {
                root.right = deleteRec(root.right, data);
            } else { 
                // Case 1: Node has no children or one child
                if (root.left == null) {
                    return root.right; // 
                } else if (root.right == null) {
                    return root.left; 
                }
        
                // Case 2: Node has two children
                Tree successor = findSuccessor(root.right); 
                root.data = successor.data; 
                root.right = deleteRec(root.right, successor.data); 
            }
        
            return root;
        }
        
        private Tree findSuccessor(Tree node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        public void trueGet(int data) {
            Tree nodeFound = get(root, data);
            System.out.println(nodeFound.data);
        }
        private Tree get(Tree root, int data) {
            if (root == null || root.data == data) {
                return root;
            }
        
            if (data < root.data) {
                return get(root.left, data);
            } else {
                return get(root.right, data);
            }
        }

        public void print() {
            System.out.println("\n");
            printPreOrder(root);
            System.out.println("\n");
            printPostOrder(root);
            System.out.println("\n");
            printInOrder(root);
            System.out.println("\n");
        }

        private void printInOrder(Tree node){
            if (node == null) {
                return;
            }

            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);

        }

        private void printPostOrder(Tree node){
            if (node == null) {
                return;
            }

            printPostOrder(node.left);
            
            printPostOrder(node.right);
            System.out.print(node.data + " ");
        }

        private void printPreOrder(Tree node) {
            if (node == null) {
                return;
            }

            // Imprimir el nodo actual
            System.out.print(node.data + " ");

            // Recorrer el subárbol izquierdo
            printPreOrder(node.left);

            // Recorrer el subárbol derecho
            printPreOrder(node.right);
        }
    }
}
