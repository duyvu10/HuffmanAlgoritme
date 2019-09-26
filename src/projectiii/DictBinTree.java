/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiii;

/**
 *
 * @author NZXT
 */
public class DictBinTree implements Dict {
    
    public Node root; // Root of the Search Tree
    private int treeSize; // Keeps track of the number of nodes in the Search Tree
    
    
    
    public DictBinTree(){
        
        this.root = null;
        this.treeSize = 0; // There are no nodes at the beginning, therefore the treesize is 0 
        
    }
    
    public DictBinTree(int k){
       this.root = new Node(k);
       this.treeSize = 0;
    
    }
    
    public DictBinTree(int k, Node l, Node r){
        this.root = new Node (k, l, r);
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    
    @Override
    public void insert(int k) {
        
        Node z = new Node(k); //creating new node containing the key k
        
        Node x = this.root; 
        Node y = null;
        
        //Goes through the tree to determine where the new node should be inserted
        while(x != null){
            
            y = x;
            
            if(z.key < x.key){ 
                x = x.leftChild;
            }
            else{
                x = x.rightChild;
            }
        }
        
        if(y == null){
            this.root = z;
        }
        else if(z.key < y.key){
            y.leftChild = z;
        }
        else{
            y.rightChild = z;
        }
        
        treeSize++; //Because the number of nodes in the Search Tree has increased
    }
    
    private static int counter; // A static counter that is used in method treeSearch to keep track the progress of the array indexes
    
    public int[] orderedTraversal() {
        
        int [] treeArray = new int[treeSize]; //Initialiases an array with size equal to treeSize
        counter = 0; //Sets counter to 0(also resets counter) - The counter keeps track of where we are in the array, when inserting.
        
        insertWalk(treeArray,this.root); // Void method that inserts the keys in the array in sorted order
        
        return treeArray;
    }
    
    public String[] modifiedOT(){
        
        String [] huffmannTable = new String[256];
        String str = "";
        
        visit(huffmannTable, this.root, str);
        
        return huffmannTable;
        
    }
    
    public void visit(String [] huffmanTable, Node x, String str){
        
//        if(x==null){
//            
//            return;
//        }
//        else if(x.leftChild==null && x.rightChild==null){
//            huffmanTable[x.key] = str;
//        }
//        visit(huffmanTable, x.leftChild, str+"0");
//        visit(huffmanTable, x.rightChild, str+"1");

         if(x!=null){
             
            if(x.leftChild==null && x.rightChild==null){
                huffmanTable[x.key] = str;
            }
            visit(huffmanTable, x.leftChild, str+"0");
            visit(huffmanTable, x.rightChild, str+"1");
         }
   
    }
    
    public void insertWalk(int[] treeArray, Node x){ //Goes through the tree in-order, while inserting the keys in the array
        
        if(x!= null){
            insertWalk(treeArray, x.leftChild);
            treeArray[counter] = x.key; // key gets "inserted" in the array
            counter++; // Increments counter to the next index of the array
            insertWalk(treeArray,x.rightChild);
        }
    }

    
    
    @Override
    public boolean search(int k) { 
       
        Node x = this.root;
        
        return treeSearch(x,k) != null; //Returns true if the returned value is not null

    }   
    
    private Node treeSearch(Node x, int k){ //Either returns a node or null
        
        if(x == null || k == x.key){
            return x;
        }
        if(k < x.key){
            return treeSearch(x.leftChild,k);
        }
        else{
            return treeSearch(x.rightChild,k);
        }
    } 
    
    public static void main(String[] args){
    
        DictBinTree tree = new DictBinTree();
        
        System.out.print("TEST: ");
        tree.insert(20);
    }
    
}
