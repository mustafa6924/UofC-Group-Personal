package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import java.io.File;

/**
 * Tracking financial information of clients in a company
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Main {
    public static void main(String[] args) {
        // Checks if argument is only of length 1
        if (args.length > 2) {
            System.err.println("Need one command-line argument for file name");
        } else if (args.length == 1) {
            String filename = args[0];
            File file = new File(filename);
            // Checks if the file exists and is readable
            if (!file.exists() || !file.canRead()) {
                System.err.println("Cannot load the file: " + filename);
                System.exit(1);
            }
            // Runs the menuLoop function with a file
            Menu.menuLoop(file);
        } else {
            // Runs the menuLoop function without a file
            Menu.menuLoop(null);
        }
    }
}
