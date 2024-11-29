package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loading a file
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class FileLoader {
    public static Data load(File file) {
        Data data = new Data();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (!line.equals("Companies")) {
                return null;
            }

            boolean foundClients = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("Clients")) {
                    foundClients = true;
                    break;
                }

                String[] entries = line.split(",");

                if (entries.length == 2) {
                    String companyId = entries[0];
                    String companyName = entries[1];
                    data.storeNewCompany(companyId, companyName);
                } else {
                    return null;
                }
            }

            if (!foundClients) {
                return null;
            }

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(",");

                if (entries.length == 7) {
                    int id = Integer.parseInt(entries[0]);
                    String name = entries[1];
                    int age = Integer.parseInt(entries[2]);
                    String country = entries[3];
                    double income = Double.parseDouble(entries[4]);
                    double spending = Double.parseDouble(entries[5]);
                    String company = entries[6];
                    data.storeNewClient(id, name, age, country, income, spending, company);
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }

        return data;
    }
}
