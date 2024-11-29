package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects;

/**
 * Company Class represents a single company's information
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Company {
    // Attributes for Company
    private String id, name;

    // Constructor for Company

    /**
     * Constructs a Company object
     *
     * @param id The ID of the company
     * @param name The name of the company
     */
    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters for Company

    /**
     * Getter for ID
     *
     * @return The ID of the company
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for name
     *
     * @return The name of the company
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("| %-15s || %-15s |", getId(), getName());
    }
}
