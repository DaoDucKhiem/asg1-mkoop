/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.version2;

/**
 *
 * @author DUC KHIEM
 */
public class testDictionary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DictionaryCommandline dicCom = new DictionaryCommandline();

        String fi = "input.txt";

        dicCom.dictionaryBasic(fi);

        dicCom.testReplaceAndDelete(fi);

        dicCom.testInsert(fi);
    }

}
