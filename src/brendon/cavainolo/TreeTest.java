package brendon.cavainolo;

public class TreeTest {
    public static void main(String[] args){
        BST_class bst = new BST_class();
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        System.out.println("The BST creted with input data from left root right: ");
        bst.inorder();
    }
}

class BST_class{
    class Node{
        int key;
        Node left; Node right;

        public Node(int data){
            key = data;
            left = right = null;
        }
    }
    //BST Root Node
    Node root;

    //Constructor for initial empty tree
    BST_class(){
        root = null;
    }
    //delete a node
    void deleteKey(int key){
        root = delete(root, key);
    }
    //recursive delete method
    Node delete(Node root, int key){
        //if tree is empty
        if(root == null) return root;

        //traverse the tree
        if(key < root.key)
            root.left = delete(root.left, key);
        else if(key > root.key)
            root.right = delete(root.right, key);
        else {
            //node contains only one child
            if(root.left == null)
                return root.right;
            else if(root.right == null)
                return root.left;

            //node has two children
            //get inorder succesor (min value in right subtree)
            root.key = minValue(root.right);

            //delete inorder successor
            root.right = delete(root.right, root.key);

        }
        return root;
    }

    int minValue(Node root){
        //initially minVal = root
        int minVal = root.key;
        while (root.left != null){
            minVal = root.left.key;
            root = root.left;
        }
        return minVal;
    }

    //insert methods
    void insert(int key){
        root = insert_rec(root,key);
    }

    //recursive insert
    Node insert_rec(Node root, int key){
        //if tree is empty
        if (root == null){
            root = new Node(key);
            return root;
        }
        //traverse tree
        if(key < root.key) //insert in left subtree
            root.left = insert_rec(root.left, key);
        else if (key > root.key) //insert in right subtree
            root.right = insert_rec(root.right, key);
        //return pointer
        return root;

    }

    //traversal methods

    //inorder traversal
    void inorder(){
        inorder_rec(root);
    }
    void inorder_rec(Node root){
        if (root != null) {
            inorder_rec(root.left);
            System.out.print(root.key + " ");
            inorder_rec(root.right);
        }
    }

    //boolean search
    boolean search(int key){
        root = search_rec(root, key);
        if(root != null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node search_rec(Node root, int key){
        //base cases: root is null or key is present at root
        if (root == null || root.key == key)
            return root;
        //val is greater than root's key
        if (root.key > key)
            return search_rec(root.left, key);
        return search_rec(root.right, key);
    }

}
