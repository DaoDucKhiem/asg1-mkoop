/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;


/**
 *
 * @author DUC KHIEM
 */
public class DictionaryCommandline {
    //hiển thị kết quả danh sách dữ liệu từ điển
    void showAllWords( Dictionary arr) {
        System.out.printf("%-2s|\t%-20s|\t\t%-30s\n","No","English","VietNamese");
        arr.dictionary.forEach((obj) -> {
            System.out.printf("%-2d|\t%-20s|\t\t%-30s\n",arr.dictionary.indexOf(obj), obj.getWord_target(), obj.getWord_explain());
        });
    }
    //gọi hai hàm insert và commandline
    void dictionaryBasic() {
       DictionaryManagement dicMan = new DictionaryManagement();
       
       showAllWords(dicMan.insertFromCommandline());
       
    }
}
