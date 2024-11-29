package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.comparators;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Client;

import java.util.Comparator;

/**
 * IncomeComparator Class that compares incomes
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class IncomeComparator implements Comparator<Client> {
    /**
     * Compare the income of two clients
     *
     * @param c1 the first object to be compared.
     * @param c2 the second object to be compared.
     * @return the value of the comparison
     */
    @Override
    public int compare(Client c1, Client c2) {
        return c1.getIncome().compareTo(c2.getIncome());
    }
}
