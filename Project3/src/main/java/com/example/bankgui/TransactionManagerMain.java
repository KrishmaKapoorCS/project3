package com.example.bankgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The TransactionManagerMain launches the application
 *
 * @author Krishma Kapoor
 */
public class TransactionManagerMain extends Application
{
    /**
     * Initializes the FXML GUI
     * @param stage the ui window
     * @throws IOException if the fxml file failed to load
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(TransactionManagerMain.class.getResource("TransactionManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 635, 500);
        stage.setTitle("Project 3 - Transaction Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main function
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        launch();
    }
}
