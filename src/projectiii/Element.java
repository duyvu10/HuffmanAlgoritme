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
public class Element {
    
    private int key;
    private DictBinTree data;

    public Element(int i, DictBinTree o){
	this.key = i;
	this.data = o;
    }
    public int getKey(){
	return this.key;
    }
    public DictBinTree getData(){
	return this.data;
    }
}
