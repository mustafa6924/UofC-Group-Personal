package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Saving a file
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class FileSaver {
    public static boolean save(File file, Data data) {
        try(FileWriter filewriter = new FileWriter(file)) {
            filewriter.write("Companies\n");
            for (int j = 0; j < data.companies.size(); j++) {
                filewriter.write(String.format("%s,%s%n", data.companies.get(j).getId(), data.companies.get(j).getName()));
            }
            filewriter.flush();
            filewriter.write("Clients\n");
            for (int i = 0; i < data.clients.size(); i++) {
                filewriter.write(String.format("%s,%s,%s,%s,%s,%s,%s%n", data.clients.get(i).getId(), data.clients.get(i).getName(), data.clients.get(i).getAge(), data.clients.get(i).getCountry(), data.clients.get(i).getIncome(), data.clients.get(i).getSpending(), data.clients.get(i).getCompany()));
            }
            filewriter.flush();
            return true;
        }catch (FileNotFoundException fnfe) {
            System.err.println("File not found");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
}
