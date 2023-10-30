package com.example.bankgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * The TransactionManagerController handles the events
 *
 * @author Krishma Kapoor
 */
public class TransactionManagerController {
    @FXML
    private TextField textFirstName;

    @FXML
    private ToggleGroup accountTypeTG;

    public void openAccountButtonClicked() {
        String firstName = textFirstName.getText();
        String accountType = ((RadioButton)accountTypeTG.getSelectedToggle()).getText();
        System.out.println(accountType);
    }
}