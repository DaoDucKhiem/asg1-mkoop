/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.version2;

import java.util.Scanner;
import java.io.File;

/**
 *
 * @author DUC KHIEM
 */
public class DictionaryManagement {

    /**
     * @param args the command line arguments
     */
    //hàm có chức năng nhập liệu
    Dictionary insertFromCommandline() {
        Scanner input = new Scanner(System.in);
        System.out.print("nhap so tu: ");
        int n = input.nextInt();
        input.nextLine();
        Dictionary dic = new Dictionary();
        for (int i = 0; i < n; i++) {

            Word newWord = new Word();

            System.out.print("nhap tu moi: ");
            newWord.setWord_target(input.nextLine());

            System.out.print("nhap nghia: ");
            newWord.setWord_explain(input.nextLine());

            dic.dictionary.add(newWord);
        }
        return dic;
    }

    //from file
    public static Dictionary insertFromFile(String file) {

        File input = new File(file);

        //tao mảng để lưu từ mới
        Dictionary dic = new Dictionary();

        try (Scanner sc = new Scanner(input)) {
            String s;
            while (sc.hasNext()) {
                s = sc.nextLine();
                String[] word = s.split("\\t", 2);
                Word w = new Word();
                w.setWord_target(word[0]);
                w.setWord_explain(word[1]);
                dic.dictionary.add(w);
            }
        } catch (Exception e) {
            System.out.println("error 404!");
        }
        return dic;
    }

    public static Word dictionaryLookup(String SearchWord, Dictionary arr) {

        Word rw = new Word();
        boolean check = false;

        for (int i = 0; i < arr.dictionary.size(); i++) {
            if (arr.dictionary.get(i).getWord_target().equals(SearchWord)) {
                rw = arr.dictionary.get(i);
                check = true;
                break;
            }
        }

        if (!check) {
            rw = null;
        }
        return rw;
    }
}
