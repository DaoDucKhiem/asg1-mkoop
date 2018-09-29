/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.version2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DUC KHIEM
 */
public class DictionaryManagement {

    /**
     * @param args the command line arguments
     */
    static Scanner input = new Scanner(System.in);

    /**
     * input data
     *
     * @return
     */
    Dictionary insertFromCommandline() {
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

    /**
     * insert from file
     *
     * @param file
     * @return arrayList
     */
    public static Dictionary insertFromFile(String file) {
        //arraylist để lưu file
        Dictionary dic = new Dictionary();

        FileReader f = null;
        BufferedReader rf = null;
        try {
            f = new FileReader(file);
            rf = new BufferedReader(f);

            String read;
            while ((read = rf.readLine()) != null) {
                String[] word = read.split("\\t", 2);
                Word w = new Word();
                w.setWord_target(word[0]);
                w.setWord_explain(word[1]);
                dic.dictionary.add(w);
            }
        } catch (IOException ex) {
            System.out.println("error read file!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                System.out.println("file don't close!");
            }
        }
        return dic;
    }

    /**
     * chức năng của phương thức là tìm kiếm từ
     *
     * @param SearchWord
     * @param arr
     * @return
     */
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

    /**
     * search words from keyboard
     *
     * @param arr
     */
    void dictionarySearcher(Dictionary arr) {
        String word = input.nextLine();
        int x = word.length();
        for (int i = 0; i < arr.dictionary.size(); i++) {
            String s = arr.dictionary.get(i).getWord_target();
            if (word.equals(s.substring(0, x))) {
                System.out.printf("%-2d|\t%-20s|\t\t%-30s\n", i, arr.dictionary.get(i).getWord_target(), arr.dictionary.get(i).getWord_explain());
            }
        }
    }

    /**
     * write on file
     *
     * @param arr
     */
    void dictionaryExportToFile(Dictionary arr, String nameFile) {

        try (BufferedWriter wf = new BufferedWriter(new FileWriter(nameFile))) {
            for (int i = 0; i < arr.dictionary.size(); i++) {
                wf.write(arr.dictionary.get(i).getWord_target() + "\t");
                wf.write(arr.dictionary.get(i).getWord_explain() + "\r");
            }
        } catch (IOException ex) {
            System.out.println("error!");
        }
    }

    /**
     * insert word in dictionary
     *
     * @param word
     * @param nameFile
     */
    void insertWord(Word word, String nameFile) {
        try (BufferedWriter wf = new BufferedWriter(new FileWriter(nameFile,true))) {
            wf.write(word.getWord_target() + "\t");
            wf.write(word.getWord_explain() + "\r");
        } catch (IOException ex) {
            System.out.println("error!");
        }
    }

    /**
     * delete file
     *
     * @param nameFile
     */
    void deleteFile(String nameFile) {
        File f = new File(nameFile);
        if (f.delete()) {
            System.out.println("delete success!");
        } else {
            System.out.println("error delete file");
        }
    }

    /**
     * replace and remove data
     *
     * @param target
     * @param answer
     * @param file
     */
    void replaceOrRemoveFile(String target, int answer, String file) {
        //arraylist để lưu file
        Dictionary dic = new Dictionary();

        FileReader f = null;
        BufferedReader rf = null;
        try {
            f = new FileReader(file);
            rf = new BufferedReader(f);

            String read;
            while ((read = rf.readLine()) != null) {
                String[] word = read.split("\\t", 2);
                if (word[0].equals(target)) {
                    Word w = new Word(); // từ thay thế cho từ cũ
                    switch (answer) {
                        case 0: //chon 0 để sửa từ
                            System.out.print("nhap tu thay doi cho tu cu: ");
                            String newTarget = input.nextLine();
                            w.setWord_target(newTarget);
                            w.setWord_explain(word[1]);
                            dic.dictionary.add(w);
                            break;
                        case 1: //chọn 1 để sửa nghĩa của từ
                            System.out.print("nhap nghia moi cua tu: ");
                            String newExplain = input.nextLine();
                            w.setWord_target(word[0]);
                            w.setWord_explain(newExplain);
                            dic.dictionary.add(w);
                        default: //chọn 2 để xóa từ
                            break;
                    }
                } else {
                    Word w = new Word();
                    w.setWord_target(word[0]);
                    w.setWord_explain(word[1]);
                    dic.dictionary.add(w);
                }
            }
        } catch (IOException ex) {
            System.out.println("error read file!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                System.out.println("file don't close!");
            }
        }

        // xóa file cũ đi
        deleteFile(file);

        //ghi dữ liệu vào file mới
        dictionaryExportToFile(dic, file);
    }
}
