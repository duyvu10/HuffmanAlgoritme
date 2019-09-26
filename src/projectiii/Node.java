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
public class Node {
    
    public int key;
    public Node leftChild;
    public Node rightChild;
    
    
    public Node(int k){
        this.key = k;
        this.leftChild = null;
        this.rightChild = null; 
     
    };
    
    public Node (int k, Node l, Node r){
        this.key = k;
        this.leftChild = l;
        this.rightChild = r;
        
    }
    
    


}

