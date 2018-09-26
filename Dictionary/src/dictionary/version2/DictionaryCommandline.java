/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.version2;

import java.util.Scanner;

/**
 *
 * @author DUC KHIEM
 */
public class DictionaryCommandline {

    //hiển thị kết quả danh sách dữ liệu từ điển
    void showAllWords(Dictionary arr) {
        System.out.printf("%-2s|\t%-20s|\t\t%-30s\n", "No", "English", "VietNamese");
        arr.dictionary.forEach((obj) -> {
            System.out.printf("%-2d|\t%-20s|\t\t%-30s\n", arr.dictionary.indexOf(obj), obj.getWord_target(), obj.getWord_explain());
        });
    }

    //gọi hàm insert, DictionaryLookup và commandline;
    void dictionaryBasic() {
        Scanner input = new Scanner(System.in);
        Dictionary dic = DictionaryManagement.insertFromFile("D:\\dictionary version2\\input.txt");
        showAllWords(dic);
        String word;
        System.out.print("nhap vao tu can tim: ");
        word = input.nextLine();
        Word rw = DictionaryManagement.dictionaryLookup(word, dic);
        if (rw == null) {
            System.out.println("This word isn't in dictionary!");
        } else {
            System.out.printf("%-2d|\t%-20s|\t\t%-20s\n", dic.dictionary.indexOf(rw), rw.getWord_target(), rw.getWord_explain());
        }
    }
}
