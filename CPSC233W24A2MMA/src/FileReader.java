
/**
 * The FileReader class provides static methods for reading data from a file and constructing a GalacticMap object.
 * It reads a text file containing information about spaceships and their attributes, and initializes a GalacticMap
 * based on the data read from the file.
 *
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class FileReader {


    /**
     * Reads data from a specified file and constructs a GalacticMap object based on the information read.
     *
     * @param fileName the name of the file to read from
     * @return a GalacticMap object initialized with data read from the file
     * @throws RuntimeException if the file specified by fileName is not found or if an error occurs while reading the file
     *
     */
    public static GalacticMap readFromFile(String fileName) {
        // Your code goes here ....
        Spaceship ship = null;
        GalacticMap map = null;
        int size;
        String text = "";
        try {
            Scanner s = new Scanner(new File(fileName));
            size = Integer.parseInt(s.nextLine());
            map = new GalacticMap(size);
            while (s.hasNextLine()) {
                text = s.nextLine();
                String[] shipdetail = text.split(" ");
                if (shipdetail[0] == "FIGHTER") { //Fighter Ship
                    ship = new FighterShip(shipdetail[1], Integer.parseInt(shipdetail[2]), Integer.parseInt(shipdetail[3]), Integer.parseInt(shipdetail[4]));
                    map.placeSpaceship(ship);
                } else if (shipdetail[0] == "EXPLORER") { //Explorer Ship
                    ship = new ExplorerShip(shipdetail[1], Integer.parseInt(shipdetail[2]), Integer.parseInt(shipdetail[3]), Integer.parseInt(shipdetail[4]));
                    map.placeSpaceship(ship);
                } else if (shipdetail[0] == "CARGOSHIP") { //Cargo ship
                    ship = new CargoShip(shipdetail[1], Integer.parseInt(shipdetail[2]), Integer.parseInt(shipdetail[3]), Double.parseDouble(shipdetail[4]), Double.parseDouble(shipdetail[5]), Integer.parseInt(shipdetail[6]), Integer.parseInt(shipdetail[7]));
                    map.placeSpaceship(ship);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found: " + fileName);
            throw new UncheckedIOException(e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format: Unable to parse numeric value");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid data format: Missing spaceship attributes");
            System.out.println("Invalid data format: Missing cargo ship attributes");
            System.out.println("Invalid spaceship type: " + ship.getType());
            System.out.println("Invalid ID length: " + ship.getId());
        }


        // hint: you need to call placeSpaceship method....
        return map;
    }
}