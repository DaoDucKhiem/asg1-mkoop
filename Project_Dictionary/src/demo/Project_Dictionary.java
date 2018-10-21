package demo;

import dictionary.DataBase;
import dictionary.Dictionary;
import dictionary.TranslateAPI;
import java.io.IOException;
import java.util.Scanner;

public class Project_Dictionary {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        DataBase data = new DataBase();
        Dictionary dic = data.getDataBase();
        
        //hiển thị danh sách
        //dic.show();
        
        //tìm từ
        System.out.print("nhap tu can tim: ");
        String wordSearch = sc.nextLine();
        System.out.println(dic.SearchInMap(wordSearch));
        
        //thêm từ
        System.out.print("nhap tu can them: ");
        String newWord = sc.nextLine();
        System.out.print("noun: ");
        String noun = sc.nextLine();
        System.out.print("verb: ");
        String verb = sc.nextLine();
        System.out.print("adjective: ");
        String adjective = sc.nextLine();
        System.out.print("other: ");
        String other = sc.nextLine();
        String explain = dic.setExlain(noun, verb, adjective, other);
        if(dic.AddWord(newWord, explain)) {
            data.updateDataBase();
            System.out.println("them thanh cong");
        }
        else System.out.println("them that bai, tu nay da co trong tu dien");
        String text1 = dic.Search(newWord);
        if(!text1.equals("")) System.out.println(dic.getMap().get(text1));
        else System.out.println("khong co tu do trong tu dien");
        
        //xóa từ
        System.out.print("nhap tu can xoa: ");
        String deleteWord = sc.nextLine();
        if(dic.removeWord(deleteWord)) {
            data.updateDataBase();
            System.out.println("xoa thanh cong");
        }
        else System.out.println("xoa that bai");
        String text2 = dic.Search(deleteWord);
        if(!text2.equals("")) System.out.println(dic.getMap().get(text2));
        else System.out.println("khong co tu do trong tu dien");
        
        //sửa từ
        System.out.print("nhap tu can sua: ");
        String wordReplace = sc.nextLine();
        System.out.print("nhap tu thay the: ");
        String newword = sc.nextLine();
        if(dic.replaceWord(wordReplace, newword)) {
            data.updateDataBase();
            System.out.println("sua thanh cong");
        }
        else System.out.println("sua that bai, tu nay khong co trong tu dien");
        String text3 = dic.Search(deleteWord);
        if(!text3.equals("")) System.out.println(dic.getMap().get(text3));
        else System.out.println("khong co tu do trong tu dien");
        
        //sửa nghĩa của từ
        System.out.print("nhap tu can sua nghia: ");
        String wordReplaceExplain = sc.nextLine();
        System.out.println("nhap nghia thay the");
        System.out.print("noun: ");
        String nounExplain = sc.nextLine();
        System.out.print("verb: ");
        String verbExplain = sc.nextLine();
        System.out.print("adjective: ");
        String adjectiveExplain = sc.nextLine();
        System.out.print("other: ");
        String otherExplain = sc.nextLine();
        String replaceExplain = dic.setExlain(nounExplain, verbExplain, adjectiveExplain, otherExplain);
        if(dic.replaceExplain(wordReplaceExplain, replaceExplain)) {
            data.updateDataBase();
            System.out.println("sua thanh cong");
        }
        else System.out.println("sua that bai, tu nay khong co trong tu dien");
        String text4 = dic.Search(deleteWord);
        if(!text4.equals("")) System.out.println(dic.getMap().get(text4));
        else System.out.println("khong co tu do trong tu dien");
        
        //dịch api
        System.out.print("nhap tu can dich: ");
        String wordSearch1 = sc.nextLine();
        System.out.println(TranslateAPI.translate("en", "vi", wordSearch1));
        
    }
    
}
