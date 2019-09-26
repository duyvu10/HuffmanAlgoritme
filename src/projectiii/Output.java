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

/**
 *
 * @author NZXT
 */
public class Output {
    
    public static void main1(){
    
        FileInputStream stream; 
        
        try {
            stream = new FileInputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\abc.txt")); //Stream of the file?
            
            BitOutputStream  output = new BitOutputStream (new FileOutputStream(new File("C:\\Users\\NZXT\\Desktop\\Ost\\empty.txt")));
            
            int byteRead; //hold the int representation of the current byte read
            //int[] bytes = new int[256]; //Creates an array of 256 for the bytes
            
            try {
                byteRead = stream.read();
                while(byteRead != -1) { //while there are still bytes to read
                    //output.writeBit(byteRead%2);
                    //output.writeInt(byteRead);
                    
                    //bytes[byteRead]++;
                    output.writeInt(byteRead);
                    //System.out.println(byteRead);
                    
                    byteRead = stream.read(); // next byte
                }
//                for(int i = 0; i<bytes.length; i++){
//                    output.writeInt(bytes[i]);
//                }
                
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}
