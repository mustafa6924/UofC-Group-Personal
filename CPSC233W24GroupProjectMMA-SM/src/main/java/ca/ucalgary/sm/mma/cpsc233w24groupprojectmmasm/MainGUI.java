package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * GUI for racking financial information of clients in a company
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class MainGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Controller cont = fxmlLoader.getController();
        cont.load(file);
        stage.setTitle("MyFinanceTracker v3.0");
        stage.setScene(scene);
        stage.show();
    }

    private static File file = null;

    public static void main(String[] args) {
        if (args.length > 2) {
            System.err.println("Need one command-line argument for file name");
        } else if (args.length == 1) {
            String filename = args[0];
            file = new File(filename);
            if (!file.exists() || !file.canRead()) {
                System.err.println("Cannot load the file: " + filename);
                System.exit(1);
            }
        }
        launch();
    }
}