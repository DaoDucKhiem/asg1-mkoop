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

    Scanner input = new Scanner(System.in);

    //hiển thị kết quả danh sách dữ liệu từ điển
    void showAllWords(Dictionary arr) {
        System.out.printf("%-2s|\t%-20s|\t\t%-30s\n", "No", "English", "VietNamese");
        arr.dictionary.forEach((obj) -> {
            System.out.printf("%-2d|\t%-20s|\t\t%-30s\n", arr.dictionary.indexOf(obj), obj.getWord_target(), obj.getWord_explain());
        });
    }

    /**
     * gọi hàm insert, DictionaryLookup và commandline;
     */
    void dictionaryBasic(String nameFile) {
        Dictionary dic = DictionaryManagement.insertFromFile(nameFile);
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

    void testReplaceAndDelete(String nameFile) {
        DictionaryManagement dic = new DictionaryManagement();
        int chose;
        System.out.print("nhap 0 de sua tu, 1 de sua nghia, 2 de xoa: ");
        chose = input.nextInt();
        input.nextLine();
        System.out.print("nhap tu muon sua hoac xoa: ");
        String target = input.nextLine();
        dic.replaceOrRemoveFile(target, chose, nameFile);
    }

    void testInsert(String nameFile) {
        DictionaryManagement dic = new DictionaryManagement();
        Word newWord = new Word();
        System.out.print("nhap tu muon them: ");
        newWord.setWord_target(input.nextLine());
        System.out.print("nhap nghia cua tu do: ");
        newWord.setWord_explain(input.nextLine());
        dic.insertWord(newWord, nameFile);
    }
}
