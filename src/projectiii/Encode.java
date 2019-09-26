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
import static java.lang.Character.getNumericValue;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NZXT
 */
public class Encode {
    
    public static Element huffman(int[] bytes){
        
        PQHeap queue = new PQHeap(256);
        for(int i = 0; i< bytes.length; i++){
            queue.insert(new Element(bytes[i], new DictBinTree(i)));
                    
        }
        while(queue.getHeapSize()!=1){
           Element left = queue.extractMin();
           Element right = queue.extractMin();
           int frequency = left.getKey()+right.getKey();
           
           queue.insert(new Element(frequency, new DictBinTree(frequency,left.getData().getRoot(),right.getData().getRoot())));
        }
        return queue.extractMin();
    }
    
    public static void main(){
        
        FileInputStream stream;
        try {
            
            stream = new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\test.txt")); //Stream of the file?
            int[] bytes = new int[256]; //Creates an array of 256 for the bytes
            
            int byteRead; //hold the int representation of the current byte read
            
            try {
                
                byteRead = stream.read(); // method reads a byte of data from the inputstream - it gets saved in byteRead
                
                //stream.mark(byteRead); //marks the start of stream
                //System.out.println("FIRST BYTE: " + byteRead);
                while(byteRead != -1) { //while there are still bytes to read
                    
                    bytes[byteRead]++; //counts the frequency of 256 bytes in the text file
                    byteRead = stream.read(); // next byte
                }
                
                stream.close();
                
                //System.out.println("Resetting");
                //stream.reset();
                
                //System.out.println("First BYTE(RESET): " + stream.read());
                
//                PQHeap queue = new PQHeap(256); //Creates a PQ-Heap, that functions as a queue
//                System.out.println("*****Putting Elements in the queue******");
//                for(int i = 0; i< bytes.length; i++){
//                    queue.insert(new Element(bytes[i], new DictBinTree(i)));
//                    
//                }
//                System.out.println("Printer dem ud");
//                for(int i = 0; i<256; i++){
//                    System.out.println(queue.extractMin().getKey());
//                };
//               while(queue.getHeapSize()!=0) {
//                   System.out.println(queue.extractMin().getKey());
//               }
               
               
//                System.out.println("Extracter selvom der ikke er mere tilbage");
//                System.out.println(queue.extractMin().getKey());
               
                //System.out.println(queue.extractMin().getKey());

                Element huffmann = huffman(bytes); //Takes the array represent the frequency for the 256 different bytes and returns a huffman tree
                System.out.println("kost");
                String[] k = huffmann.getData().modifiedOT(); //Traveres the huffman tree to its leaves and puts the code of the bytes in a array.
                
                //System.out.println(Arrays.toString(k));
                
//                System.out.println("Huffman Kode for 'D': "+ k[68]);
//                System.out.println("Huffman Kode for 'j': "+ k[106]);
//                System.out.println("Huffman Kode for 'e': "+ k[101]);
//                 System.out.println("Huffman Kode for tallet '1': "+ k[49]);
//                System.out.println("Tjekker nu huffman kode for et bogstav som ikke befinder sig i filen");
//                System.out.println("Huffman Kode for 'i': " + k[105]);
//                System.out.println("Huffman Kode for 'i': " + k[122]);

                
            
//                for(int i = 0; i<k.length; i++){
//                    System.out.println(i+" kode: "+ k[i]);
//                }
                
              
                
                System.out.println("Testing BitOutputStream: ");
                
                BitOutputStream  output = new BitOutputStream (new FileOutputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\lilleTest.txt")));//output
                
                System.out.println("Skriver array til tekst-fil: ");
                for(int i = 0; i<bytes.length;i++){
                    output.writeInt(bytes[i]);
                }
                
                System.out.println("Læser filen igen og skriver bits: ");
                
                FileInputStream second = new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\test.txt")); //Original filen
                
                int byteRead2 = second.read();
                
                System.out.println(byteRead2);
                
                while(byteRead2 != -1) { //while there are still bytes to read
                    
                    String code = k[byteRead2];
                    
                    for(int i = 0; i<code.length(); i++){
                        
                        System.out.println("oste: "+ code.charAt(i));
                        output.writeBit(getNumericValue(code.charAt(i)));
                    }
                    
                    byteRead2 = second.read(); // next byte
                    System.out.println(byteRead2);
                }
                
                
//                FileInputStream stream2 = new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\miniblack.jpg")); //mini fil
//                
//                BitInputStream test = new BitInputStream(stream2);
//                
//                int [] newBytes = new int[256];
//                
//                
//                //int byteRead2 = stream2.read();
//                
//                System.out.println("læser array'et fra tekst-filen");
//                
//                for(int i = 0; i<newBytes.length; i++){
//                    newBytes[i] = test.readInt();
//                }
                
                
                //int ost = test.readInt(); // tester om der er flere bits tilbage, "Det er der ikke, vi får en error"
                
//                for(int i = 0; i<newBytes.length; i++){
//                    newBytes[i] = byteRead2;
//                    byteRead2 = stream.read();
//                    
//                }
//                int i = 0;
//                while(byteRead2 != -1) { //while there are still bytes to read
//                    System.out.println(i+": "+byteRead2);
//                    //bytes[byteRead]++; //counts the frequency of 256 bytes in the text file
//                    byteRead2 = stream2.read(); // next byte
//                    i++;
//                }
                //System.out.println("i: "+ i);
                
                // Sammenligner de to arrays for at se om de er ens
//                for(int i = 0; i<bytes.length; i++){
//                    System.out.println("Kode "+i+": " + "Input fil: "+bytes[i]+ " Output fil: "+newBytes[i] );
//                }
            output.close();

            } catch (IOException ex) {
                Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encode.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
