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
public class PQHeap implements PQ {
    
    public Element[] queue;
    private int heapsize = 0; //The heap starts off with the size of 0
    
    
    //Constructor
    public PQHeap(int maxElms){ //Initializes an empty priority queue with a given size.
        
        queue = new Element[maxElms+1]; //Because we won't be using index 0.
        //heapsize = queue.length;
       
    }
    
   
    
    public void minheapify(int i){
        
       
        Element aux; //defining auxilary element to assist in swapping. 
        
        int l = 2*i; //index of left child
        int r = 2*i+1; // index of right child
        int smallest; 
        if(l<=heapsize && (queue[l].getKey()< queue[i].getKey())){ //If left child is smaller than the parent, we give it the title of the current smallest element
            smallest = l;
        }
        else{
            smallest = i;  // If not the parent is the current smallest
        }
        if(r<=heapsize && queue[r].getKey()<queue[smallest].getKey()){ // If right child is smaller than smallest, it gets the title
            smallest = r;
        }
        if(smallest!=i){ //if the parrent does not hold the title of smallest we swap them with smallest and call heapify on it
            
            aux = queue[i];
            queue[i]=queue[smallest];
            queue[smallest] = aux;
             
            minheapify(smallest);  
        }
     }
     
     
     
    @Override// Return the element with lowest priority
    public Element extractMin() {
        
       if(heapsize<1){
           System.out.println("Error: Heap Underflow");
           return null;
       }
      
        Element min = queue[1]; // Because we are dealing with a min-heap, the min. element will be at root, which is index 1.
        queue[1] = queue[heapsize]; // The last element in the heap becomes the the root
        heapsize = heapsize-1; //We decrease the heap size, since the element in the last node has been moved up to the root, and total elements in the heap
        minheapify(1); // Because we cannot guarantee that the new element at the root satisfies the min-heap, we call heapify to fix any violations
       
        return min; 
    }

   

    @Override //Inserts an Element in the heap structure
    public void insert(Element e) {
        
      heapsize = heapsize+1;//When adding an element to the heap, we also have to update the heap size
      int i = heapsize;
      queue[i] = e;// The new element is inserted onto the latest heapnode.

      while( i>1 && (queue[(int)Math.floor(i/2)].getKey())>(queue[i].getKey())){//While the child is smaller than its parent (and is not the root) we swap them. 

          
          Element aux = queue[i];
          queue[i]=queue[(int)Math.floor(i/2)];
          queue[(int)Math.floor(i/2)] = aux;
          
          i = (int)Math.floor(i/2);
      }
      
       
    }

    public int getHeapSize(){
         return this.heapsize;
     }
}
