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
public class Word {
    private String Word_target; //từ mới
    
    private String Word_explain; // từ giải thích

    public String getWord_target() {
        return Word_target;
    }

    public void setWord_target(String Word_target) {
        this.Word_target = Word_target;
    }

    public String getWord_explain() {
        return Word_explain;
    }

    public void setWord_explain(String Word_explain) {
        this.Word_explain = Word_explain;
    }    
}
