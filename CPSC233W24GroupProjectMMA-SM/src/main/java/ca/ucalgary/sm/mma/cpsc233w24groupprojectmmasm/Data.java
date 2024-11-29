package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Client;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Company;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.ForeignStatistics;

import java.util.ArrayList;

/**
 * Data for tracking financial information of clients in a company
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Data {
    // ArrayList of Clients to store all the clients
    public final ArrayList<Client> clients;

    public final ArrayList<Integer> clientIDs;

    // ArrayList of Companies to store all the clients
    public final ArrayList<Company> companies;

    // Constructor for Data
    public Data() {
        clients = new ArrayList<>();
        companies = new ArrayList<>();
        clientIDs = new ArrayList<>();
    }

    // Getters for Data
    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /**
     * Stores a new client into the clients ArrayList
     *
     * @param id The id of the client
     * @param name The name of the client
     * @param age The client's age
     * @param country Where the client is from
     * @param income How much is the clients income
     * @param spending How much is the client's spending
     * @param companyName Which company is the client part of
     * @return True if it was able to store, False otherwise
     */

    public boolean storeNewClient(int id, String name, int age, String country, double income, double spending, String companyName) {
        // Makes sure that it is a new client and they are part of a company in the HashSet
        if (!checkExistClient(id) && checkExistCompany(companyName)) {
            for (int i = 0; i < companies.size(); i++) {
                Company company = companies.get(i);
                // Return true if the id already exists
                if (company.getName().equals(companyName)) {
                    Client client = new Client(id, name, age, country, income, spending, company);
                    clients.add(client);
                    clientIDs.add(id);
                    // Returns true if successfully stored
                    return true;
                }
            }
        }
        // Returns false if not able to store
        return false;
    }

    public Client getClientthroughID(int id) {
        Client client;
        String name = null;
        int age = 0;
        String country = null;
        double income = 0.0;
        double spending = 0.0;
        Company company = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == id) {
                name = clients.get(i).getName();
                age = clients.get(i).getAge();
                country = clients.get(i).getCountry();
                income = clients.get(i).getIncome();
                spending = clients.get(i).getSpending();
                break;
            }
        }
        for (int j = 0; j < companies.size(); j++) {
            if (companies.get(j).getName().equals(clients.get(j).getCompany())) {
                company = companies.get(j);
                break;
            }
        }
        return new Client(id, name, age, country, income, spending, company);
    }

    public Company getCompanyThroughID(String id) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId().equals(id)) {
                return companies.get(i);
            }
        }
        return null;
    }

    public Client getCLientThroughCompany(Company company) {
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            String companyName = client.getCompany();
            if (companyName.equals(company.getName())) {
                return client;
            }
        }
        return null;
    }

    /**
     * Check if a client already exists in the ArrayList based if the ID entered exists
     *
     * @param id The ID entered to check
     * @return True if the id exists, false otherwise
     */
    public boolean checkExistClient(int id) {
        // Loop through each client in the ArrayList and see if the ID matches
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            // Return true if the id already exists
            if (client.getId() == id) {
                return true;
            }
        }
        // Return false if the id doesn't exist
        return false;
    }

    /**
     * Stores a new company into the company ArrayList
     *
     * @param companyId The ID of the company
     * @param companyName The name of the company
     * @return True if it was able to store, False otherwise
     */
    public boolean storeNewCompany(String companyId, String companyName) {
        // Add a new company to the companies HashSet
        if (!checkExistCompany(companyName) && !checkExistCompanyID(companyId)) {
            Company company = new Company(companyId, companyName);
            companies.add(company);
            // Returns true if successfully stored
            return true;
        } else {
            // Returns false if not able to store
            return false;
        }
    }

    /**
     * Check if a company already exists in the ArrayList based if the name entered exists
     *
     * @param companyName The name entered to check
     * @return True if the name exists, false otherwise
     */
    public boolean checkExistCompany(String companyName) {
        // Loop through each company in the ArrayList and see if the name matches
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            // Return true if the name already exists
            if (company.getName().equals(companyName)) {
                return true;
            }
        }
        // Return false if the name doesn't exist
        return false;
    }

    public boolean checkExistCompanyID(String companyID) {
        // Loop through each company in the ArrayList and see if the id matches
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            // Return true if the id already exists
            if (company.getId().equals(companyID)) {
                return true;
            }
        }
        // Return false if the id doesn't exist
        return false;
    }

}