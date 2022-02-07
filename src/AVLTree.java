public class AVLTree<Item> {
    AVLNode<Item> root;

    public AVLTree() {
        root = null;
    }

    public int height(AVLNode<Item> d) {
        if (d == null) {
            return 0;
        } else {
            return d.height;
        }
    }


    public AVLNode<Item> leftRotate(AVLNode focus) {
        AVLNode<Item> k = focus.left;
        focus.left = k.right;
        k.right = focus;
        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }


    public AVLNode<Item> rightRotate(AVLNode focus) {
        AVLNode<Item> k = focus.right;
        focus.right = k.left;
        k.left = focus;
        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        k.height = Math.max(height(k.left), height(k.right)) + 1;
        return k;
    }

    public AVLNode<Item> doubleRotateLeftSide(AVLNode focus) {
        focus.left = rightRotate(focus.left);
        return leftRotate(focus);
    }

    public AVLNode<Item> doubleRotateRightSide(AVLNode focus) {
        focus.right = leftRotate(focus.right);
        return rightRotate(focus);
    }


    int getBalance(AVLNode<Item> focus) {
        if (focus == null) {
            return 0;
        }
        return Math.abs(height(focus.left) - height(focus.right));
    }

    public AVLNode<Item> insert(AVLNode focus, Item data, int key) {
        if (focus == null) {
            focus = new AVLNode(data, key);
        } else if (key < focus.key) {
            focus.left = insert(focus.left, data, key);
            if (getBalance(focus) == 2) {
                if (key < focus.left.key) {
                    focus = leftRotate(focus);
                } else {
                    focus = doubleRotateLeftSide(focus);
                }
            }
        } else if (key > focus.key) {
            focus.right = insert(focus.right, data, key);
            if (getBalance(focus) == 2) {
                if (key > focus.right.key) {
                    focus = rightRotate(focus);
                } else {
                    focus = doubleRotateRightSide(focus);
                }
            }
        } else {
            focus.data = data;
        }

        focus.height = Math.max(height(focus.left), height(focus.right)) + 1;
        return focus;
    }

    public void insert(Item data, int key) {
        root = insert(root, data, key);
    }

    public int search(int key) {
        return searchRecursive(this.root, key);
    }

    public int searchRecursive(AVLNode focus, int key) {
        if (focus == null) {
            return -1;
        }
        if (focus.key == key)
        {
            return focus.key;
        } else if (key < focus.key) {
            return searchRecursive(focus.left, key);
        } else {
            return searchRecursive(focus.right, key);
        }
    }

    AVLNode deleteNode(AVLNode root, int key)
    {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else
        {
            if ((root.left == null) || (root.right == null))
            {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else
            {
                AVLNode temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);


        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    AVLNode minValueNode(AVLNode node)
    {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

}
