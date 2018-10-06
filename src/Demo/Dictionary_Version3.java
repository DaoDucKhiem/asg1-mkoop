package Demo;

import Dictionary.DataBase;
import Dictionary.Dictionary;
import java.util.Scanner;


public class Dictionary_Version3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataBase data = new DataBase();
        Dictionary dic = data.getData();
        dic.searchWord("hello");
        dic.addWord();
        data.updateData();
        String w = sc.nextLine();
        dic.searchWord(w);
        
    }
    
}
