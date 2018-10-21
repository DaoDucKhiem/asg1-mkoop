/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import frame.Controller1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DUC KHIEM
 */
public class replaceWord {

    @FXML
    private TextField old_word;
    @FXML
    private TextField new_word;
    @FXML
    private TextField find_word_replace_explain;
    @FXML
    private TextField noun_explain;
    @FXML
    private TextField verb_explain;
    @FXML
    private TextField adjective_explain;
    @FXML
    private TextField other_explain;
    @FXML
    private AnchorPane anchorPane;

    private String newExplain;

    @FXML
    private void apply_replace_word(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Replace Word");
        if (!old_word.getText().equals("") && !new_word.getText().equals("")) {
            if (Controller1.getDic().replaceWord(old_word.getText(), new_word.getText())) {
                alert1.setContentText("Replace success!");
                Controller1.getData().updateDataBase();
                Controller1.list.remove(old_word.getText());
                Controller1.list.add(new_word.getText());
            } else {
                alert1.setContentText("This word didn't exist in dictionary!");
            }
        } else {
            alert1.setContentText("Error!");
        }
        alert1.show();
        ((Stage) anchorPane.getScene().getWindow()).close();
    }

    @FXML
    private void cancel_replace_word(ActionEvent event) {
        ((Stage) anchorPane.getScene().getWindow()).close();
    }

    @FXML
    private void apply_replace_explain(ActionEvent event) {
        newExplain = Controller1.getDic().setExlain(noun_explain.getText(), verb_explain.getText(), adjective_explain.getText(), other_explain.getText());
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Replace Explain");
        if (!find_word_replace_explain.getText().equals("")) {
            if (Controller1.getDic().replaceExplain(find_word_replace_explain.getText(), newExplain)) {
                alert1.setContentText("Replace explain success!");
                Controller1.getData().updateDataBase();
            } else {
                alert1.setContentText("This word didn't exist in dictionary!");
            }
        } else {
            alert1.setContentText("Error!");
        }
        alert1.show();
        ((Stage) anchorPane.getScene().getWindow()).close();
    }

    @FXML
    private void cancel_replace_explain(ActionEvent event) {
        ((Stage) anchorPane.getScene().getWindow()).close();
    }

}
