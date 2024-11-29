package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects;

/**
 * Client Class represents a single client's information
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Client implements Comparable<Client>{
    // Attributes for Client
    private int id, age;
    private String name, country;
    private Double income, spending;
    private Company company;

    // Constructor for Client

    /**
     * Constructs a Client object
     *
     * @param id The ID of the client
     * @param name The name of the client
     * @param age The client's age
     * @param country Where the client is from
     * @param income How much is the clients income
     * @param spending How much is the client's spending
     * @param company The Company object linking to the Company class
     */
    public Client(int id, String name, int age, String country, Double income, Double spending, Company company) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.country = country;
        this.income = income;
        this.spending = spending;
        this.company = company;
    }

    // Getters for Client

    /**
     * Getter for ID
     *
     * @return The ID of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for age
     *
     * @return The client's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for name
     *
     * @return The name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for country
     *
     * @return Which company is the client part of
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for company name
     *
     * @return The ID of the client
     */
    public String getCompany() {
        return company.getName();
    }

    /**
     * Getter for income
     *
     * @return How much is the clients income
     */
    public Double getIncome() {
        return income;
    }

    /**
     * Getter for spending
     *
     * @return How much is the clients spending
     */
    public Double getSpending() {
        return spending;
    }

    @Override
    public String toString() {
        return String.format("| %-20s || %-20s || %-20s || %-20s || %-20s || %-20s || %-20s |", getId(), getName(), getAge(),getCountry(), String.format("$%.2f", getIncome()), String.format("$%.2f", getSpending()), getCompany());
    }

    /**
     * Compare two clients income
     * @param other the object to be compared.
     * @return the value of the compare method
     */
    @Override
    public int compareTo(Client other) {
        return Double.compare(income, other.income);
    }
}
