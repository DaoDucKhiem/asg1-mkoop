/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.Scanner;

/**
 *
 * @author DUC KHIEM
 */
public class DictionaryManagement {

    Scanner input = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    //hàm có chức năng nhập liệu
    Dictionary insertFromCommandline() {
        System.out.print("nhap so tu: ");
        int n = input.nextInt();
        input.nextLine();
        Dictionary dic = new Dictionary();
        for(int i=0; i<n; i++) {
            
            Word newWord = new Word();
            
            System.out.print("nhap tu moi: ");
            newWord.setWord_target(input.nextLine());
            
            System.out.print("nhap nghia: ");
            newWord.setWord_explain(input.nextLine());
            
            dic.dictionary.add(newWord);
        }
        return dic;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
       DictionaryCommandline dicCom = new DictionaryCommandline();
       
       dicCom.dictionaryBasic();
    }
    
}
