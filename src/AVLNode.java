public class AVLNode<Item> {
    public int key;
    public Item data;
    public AVLNode left;
    public AVLNode right;
    public int height = 1;

    public AVLNode(Item data, int key){

        this.data= data;
        this.key = key;
        left = null;
        right = null;
        height = 1;
    }
}

