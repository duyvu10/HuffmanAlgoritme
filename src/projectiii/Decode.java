/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projectiii.Encode.huffman;

/**
 *
 * @author NZXT
 */
public class Decode {
    
    public static void write(BitInputStream input , FileOutputStream output, DictBinTree tree, int sum ){
        
        Node x = tree.getRoot();
        
        for(int i = 0; i < sum; i++){
            
            try {
                while(x.leftChild!= null && x.rightChild!=null){ // traveres through the tree until a leaf is found
                    try {
                        
                        int bit = input.readBit(); // reads a bit from the input file, it must ether be 1 or 0
                        System.out.println(bit);
                        if(bit == 0){ // if it is 0, we go down left of the tree
                            System.out.println("venstre");
                            x = x.leftChild;
                        }
                        else if(bit == 1 ){ // if not it must be 1, so we go down right
                            System.out.println("højre");
                            x = x.rightChild;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                System.out.println("skriver");
                output.write(x.key); // a leave is found that holds the ASCII number 
                
                x = tree.getRoot(); //resets the root, so we start at the root of the huffman tree again
                
            } catch (IOException ex) {
                Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
    
    public static void main(){
        
                //FileInputStream stream2;
        try {
            //stream2 = new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\miniblack.jpg")); //mini fil
            
            BitInputStream input = new BitInputStream(new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\lilleTest.txt")));
                
            int [] bytes = new int[256]; // Array til at gemme hyppighederne 
                
                
                //int byteRead2 = stream2.read();
                
            System.out.println("læser array'et fra tekst-filen"); 
            int sum = 0;
            
            for(int i = 0; i<bytes.length; i++){ //laver array'et over hyppigheden 
                try {
                    int readNumber = input.readInt();
                    bytes[i] = readNumber; //læser int og gemmer det på index i
                    sum = sum + readNumber; //summen af alle hyppigheder
                } catch (IOException ex) {
                    Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Element huffmannTree = huffman(bytes); // Returns an Element object that has hold a frequency and a huffmanTree / tree object
            
            //String[] k = huffmannTree.getData().modifiedOT();
            
            FileOutputStream output = new FileOutputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\lilleTestDecoded.txt"));//output
            
            System.out.println("HALLLLOOOOOOO: "+ sum);
            
            write(input, output,huffmannTree.getData(), sum);
            
            input.close();
            output.close();
                
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Decode.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
    }
}
