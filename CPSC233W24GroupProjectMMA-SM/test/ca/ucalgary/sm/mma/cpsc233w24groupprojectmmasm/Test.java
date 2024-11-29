package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Client;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Company;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.ForeignStatistics;
import org.junit.jupiter.api.Assertions;

/**
 * ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.Test cases for the project
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * March 21, 2024
 * Tutorial 06, Tejash Shrestha
 */
class Test {

    static final int id = 1111;
    static final String  name = "Bob";
    static final int age = 18;
    static final String country = "Canada";
    static final double income = 10000;
    static final double spending = 2000;
    static final String companyId = "ABC123";
    static final String companyName = "Pencil Store";
    static final int id2 = 2222;
    static final String name2 = "David";
    static final int age2 = 81;
    static final String country2 = "United States";
    static final double income2 = 500000;
    static final double spending2 = 10000;
    static final String companyId2 = "CDE123";
    static final String companyName2 = "Pen Shop";

    private Data data;

    @org.junit.jupiter.api.BeforeEach
    void BeforeEach() {
        this.data = new Data();
    }


    @org.junit.jupiter.api.Test
    void storeNewClient() {
        data.storeNewCompany(companyId,companyName);
        data.storeNewClient(id, name, age, country, income, spending, companyName);
        Assertions.assertEquals(id, data.clients.getFirst().getId());
        Assertions.assertEquals(name, data.clients.getFirst().getName());
        Assertions.assertEquals(age, data.clients.getFirst().getAge());
        Assertions.assertEquals(country, data.clients.getFirst().getCountry());
        Assertions.assertEquals(income, data.clients.getFirst().getIncome());
        Assertions.assertEquals(spending, data.clients.getFirst().getSpending());
        Assertions.assertEquals(companyName, data.clients.getFirst().getCompany());
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void storeTwoClient() {
        data.storeNewCompany(companyId,companyName);
        data.storeNewCompany(companyId2,companyName2);
        data.storeNewClient(id, name, age, country, income, spending, companyName);
        data.storeNewClient(id2, name2, age2, country2, income2, spending2, companyName2);
        Assertions.assertEquals(id, data.clients.getFirst().getId());
        Assertions.assertEquals(name, data.clients.getFirst().getName());
        Assertions.assertEquals(age, data.clients.getFirst().getAge());
        Assertions.assertEquals(country, data.clients.getFirst().getCountry());
        Assertions.assertEquals(income, data.clients.getFirst().getIncome());
        Assertions.assertEquals(spending, data.clients.getFirst().getSpending());
        Assertions.assertEquals(companyName, data.clients.getFirst().getCompany());
        Assertions.assertEquals(id2, data.clients.get(1).getId());
        Assertions.assertEquals(name2, data.clients.get(1).getName());
        Assertions.assertEquals(age2, data.clients.get(1).getAge());
        Assertions.assertEquals(country2, data.clients.get(1).getCountry());
        Assertions.assertEquals(income2, data.clients.get(1).getIncome());
        Assertions.assertEquals(spending2, data.clients.get(1).getSpending());
        Assertions.assertEquals(companyName2, data.clients.get(1).getCompany());
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void storeExistingClient() {
        data.storeNewCompany(companyId,companyName);
        data.storeNewClient(id, name, age, country, income, spending, companyName);
        Assertions.assertTrue(data.checkExistClient(id));
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void storeNewCompany() {
        data.storeNewCompany(companyId,companyName);
        Assertions.assertEquals(companyName, data.companies.getFirst().getName());
        Assertions.assertEquals(companyId, data.companies.getFirst().getId());
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void storeTwoCompany() {
        data.storeNewCompany(companyId,companyName);
        data.storeNewCompany(companyId2,companyName2);
        Assertions.assertEquals(companyName, data.companies.getFirst().getName());
        Assertions.assertEquals(companyId, data.companies.getFirst().getId());
        Assertions.assertEquals(companyName2, data.companies.get(1).getName());
        Assertions.assertEquals(companyId2, data.companies.get(1).getId());
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void storeExistingCompany() {
        data.storeNewCompany(companyId,companyName);
        Assertions.assertTrue(data.checkExistCompany(companyName));
        data.clients.clear();
        data.companies.clear();
    }


    @org.junit.jupiter.api.Test
    void Checkcurrenciesmap() {
        double exchangeRate= ForeignStatistics.Currencies.get("USA");
        Assertions.assertEquals(0.740755, exchangeRate);
    }


    @org.junit.jupiter.api.Test
    void CheckInterestRate() {
        double interestRate = ForeignStatistics.InterestRate.get("China");
        Assertions.assertEquals(0.0345, interestRate);
    }

    @org.junit.jupiter.api.Test
    void CheckClientsToString() {
        data.storeNewCompany(companyId, "Finance Co");
        Client client = new Client(11111, "Mustafa", 19, "Canada", 10000.0, 3000.0, new Company("ABC123", "Finance Co"));
        String clientToString = client.toString();
        Assertions.assertEquals(String.format("| %-20s || %-20s || %-20s || %-20s || %-20s || %-20s || %-20s |",
                client.getId(), client.getName(), client.getAge(),client.getCountry(), String.format("$%.2f", client.getIncome()), String.format("$%.2f", client.getSpending()), client.getCompany()),
                clientToString);
    }

    @org.junit.jupiter.api.Test
    void CheckCompanyToString() {
        Company company =  new Company(companyId, companyName);
        String companyToString = company.toString();
        Assertions.assertEquals(String.format("| %-15s || %-15s |", company.getId(), company.getName()), companyToString);
    }

    @org.junit.jupiter.api.Test
    void SuggestiveHolidaysTest() {
        Assertions.assertEquals("Havana, Dominican Republic, Cuba", ForeignStatistics.suggestiveHoliday.get(1));
        Assertions.assertEquals("Netherlands Antilles, Mexico, Grenada, Guadeloupe", ForeignStatistics.suggestiveHoliday.get(2));
        Assertions.assertEquals("Dubai UAE, Australia, New Zealand, Japan", ForeignStatistics.suggestiveHoliday.get(3));
    }

    @org.junit.jupiter.api.Test
    void checkExistsCompanyIDTest() {
        data.storeNewCompany(companyId, companyName);
        Assertions.assertTrue(data.checkExistCompanyID(companyId));
    }

    @org.junit.jupiter.api.Test
    void checkExistsClientIDTest() {
        data.storeNewClient(id, name, age, country, income, spending, companyName);
        Assertions.assertFalse(data.checkExistClient(id));
    }

    @org.junit.jupiter.api.Test
    void getClientThroughIDTest () {
        Company company = new Company(companyId, companyName);
        Client client = new Client(id, name, age, country, income, spending, company);
        data.storeNewCompany(companyId,companyName);
        data.storeNewClient(id, name, age, country, income, spending, companyName);
        Assertions.assertEquals(data.getClientthroughID(id).toString(), client.toString());
    }

}