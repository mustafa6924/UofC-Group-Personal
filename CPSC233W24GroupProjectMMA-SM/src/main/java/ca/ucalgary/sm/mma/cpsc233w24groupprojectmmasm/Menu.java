package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.comparators.IncomeComparator;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Client;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Company;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.ForeignStatistics;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util.FileLoader;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util.FileSaver;

import java.io.File;
import java.util.*;

/**
 * Menu for tracking financial information of clients in a company
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Menu {
    private static Data data = new Data();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> options = new ArrayList<>();

    static {
        options.add("Exit");
        options.add("Add a new company");
        options.add("Enter a new client");
        options.add("Print all clients");
        options.add("Print all companies");
        options.add("Print a client's specific income");
        options.add("List clients from a specific country");
        options.add("Currency converter for a client's income");
        options.add("Suggestive holidays for client");
        options.add("Estimated savings in a foreign country");
        options.add("Estimated savings in Canada");
        options.add("Number of employees in a company");
        options.add("Average age of employees in a company");
        options.add("Save to a file");
        options.add("Load from a file");
    }
    private static String optMessage = """
            Store and access clients financial information
            
            ************************************************
            \t\t\t\tMenu Options
            ************************************************
            
            """;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append(optMessage);
        for (int i = 0; i < options.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i, options.get(i)));
        }
        optMessage = sb.toString();
    }

    /**
     * Creates the menu options loop for the user to interact with
     */
    public static void menuLoop(File file) {
        if (file != null) {
            loadArgFile(file);
        }

        // Prints out the menu
        System.out.println("\t\t\t\tFinance Tracker");
        System.out.println(optMessage);
        System.out.println("************************************************");
        System.out.println("Enter the option you would like: ");
        try {
            // Asks the user what their option is
            String choice = scanner.nextLine();
            int option = Integer.parseInt(choice);
            // Keeps looping until the user enters 0
            while (option != 0) {
                // Runs a function based on what option is selected
                switch (option) {
                    case 1 -> menuAddNewCompany();
                    case 2 -> menuEnterNewClient();
                    case 3 -> menuPrintAllClients();
                    case 4 -> menuPrintAllCompanies();
                    case 5 -> menuPrintClientSpecificIncome();
                    case 6 -> menuListClientCertainCountry();
                    case 7 -> menuIncomeCADtoForeign();
                    case 8 -> menuClientSuggestiveHolidays();
                    case 9 -> menuEstimatedSavingForeignCountry();
                    case 10 -> menuEstimatedSavingsCanada();
                    case 11 -> menuNumberOfEmployees();
                    case 12 -> menuAverageAgeOfEmployees();
                    case 13 -> save();
                    case 14 -> load();
                    default -> System.out.println("Option not recognized, pick a new one!\n");
                }
                System.out.println("Press ENTER to continue");
                scanner.nextLine();
                // Prints out the menu again
                System.out.println(optMessage);
                System.out.println("************************************************");
                System.out.println("Enter the option you would like: ");
                // Asks the user what their option is again
                choice = scanner.nextLine();
                option = Integer.parseInt(choice);
            }
        } catch (NumberFormatException e){
            System.err.println("Invalid input, try again!");
            System.out.println("Press ENTER to continue");
            scanner.nextLine();
            menuLoop(file);
        }
        // Exit message when user quits the program
        System.out.println("Thanks for trying our finance tracker program!");
    }

    /**
     * Saves to a file with a filename given by the user
     */
    private static void save() {
        // Initialize variables
        String filename;
        File file;
        do {
            do {
                // Asks for a filename
                System.out.println("Enter a filename to save to: ");
                filename = scanner.nextLine().trim();
            } while(filename.isEmpty());
            // Creates a file object
            file = new File(filename);
        } while(file.exists() && !file.canRead());
        // Save the data to the file
        FileSaver.save(file, data);
        // Checks if it was successfully saved
        if (FileSaver.save(file,data)) {
            System.out.printf("Saved to File %s%n", filename);
        }else {
            System.err.printf("Failed to save file %s%n", filename);
        }
    }

    /**
     * Loads a file with a filename given by the user
     */
    private static void load() {
        // Initialize variables
        String filename;
        File file;
        do {
            do {
                // Asks for a filename
                System.out.println("Enter a filename to load from: ");
                filename = scanner.nextLine().trim();
            } while(filename.isEmpty());
            // Creates a file object
            file = new File(filename);
        } while(!file.exists() || !file.canRead());
        // Loads the data from the file
        Data data = FileLoader.load(file);
        // Checks if it was successfully loaded
        if (data == null) {
            System.err.println("Failed to load data from file: " + file);
        } else {
            System.out.println("Loaded file: " + file);
            Menu.data = data;
        }
    }

    /**
     * Loads a file with a filename given by the command-line argument
     */
    private static void loadArgFile(File file) {
        // Loads the data from the file
        Data data = FileLoader.load(file);
        // Checks if it was successfully loaded
        if (data == null) {
            System.err.println("Failed to load data from file: " + file);
        } else {
            System.out.println("Loaded file: " + file);
            Menu.data = data;
        }
    }

    /**
     * Adds a new company to the HashSet in data.java
     */
    private static void menuAddNewCompany() {
        // Keeps looping through until it successfully stores in the HashSet
        boolean success;
        do {
            String companyID = inputCompanyID();
            String companyName = inputCompanyName();
            success = data.storeNewCompany(companyID, companyName);
            if (success) {
                // Print out a success message
                System.out.println("Company information stored!\n");
            }
        } while (!success);
    }

    private static String inputCompanyID() {
        String tempCompanyID;
        boolean idLetters = false;
        boolean idNumbers = false;
        do {
            System.out.println("Enter new company ID of length 6 (format AAA111): ");
            tempCompanyID = scanner.nextLine().trim();
            //Converts the input into an array of char to be manipulated
            char[] idparts = tempCompanyID.toCharArray();
            //Ensures the length of ID entered is 6
            if (idparts.length == 6) {
                //Check if the first 3 chars are between the ASCII value of A-Z
                if (idparts[0] >= 'A' && idparts[0] <= 'Z' && idparts[1] >= 'A' && idparts[1] <= 'Z' && idparts[2] >= 'A' && idparts[2] <= 'Z') {
                    idLetters = true;
                    // Checks if the last 3 chars are numbers from 0-9
                    if (idparts[3] >= '0' && idparts[3] <= '9' && idparts[4] >= '0' && idparts[4] <= '9' && idparts[5] >= '0' && idparts[5] <= '9') {
                        idNumbers = true;
                    //Error checking
                    } else {
                        System.err.println("Invalid Company ID format! Re-enter ID!");
                    }
                //Error checking
                } else {
                    System.err.println("Invalid Company ID format! Re-enter ID!");
                }
            //Error checking
            }else {
                System.err.println("Invalid Company ID Length! Re-enter ID!");
            }
        } while (tempCompanyID.isEmpty() || !idNumbers || !idLetters);
        return tempCompanyID;
    }

    /**
     * Asks the user for what new company name they want to enter
     *
     * @return The name of the company
     */
    private static String inputCompanyName() {
        // Keeps looping until a name is entered
        boolean exists =  true;
        String tempCompanyName;
        do {
            System.out.println("Enter new company name: ");
            tempCompanyName = scanner.nextLine().trim();
            //Checks if another company of that name exists
            if (!data.checkExistCompany(tempCompanyName)) {
                exists = false;
            //Error output if exists
            }else {
                System.err.println("Company already exists! Re-enter company name!");
            }
        } while (tempCompanyName.isEmpty() || exists);
        return tempCompanyName;
    }

    /**
     * Receives information about a new client and stores it in data class
     *
     */
    private static void menuEnterNewClient() {
        // initializes boolean for post condition check loop
        boolean success;
        do {
            // variables are assigned to what the user inputted
            int id = inputId();
            String name = inputName();
            int age = inputAge();
            String country = inputCountry();
            double income = inputIncome();
            double spending = inputSpending();
            String companyName = inputClientCompanyName();
            success = data.storeNewClient(id, name, age, country, income, spending, companyName);
            if (success) {
                // Print out a success message
                System.out.println("Client information stored!\n");
            } else {
                System.err.println("Unable to store client information. Client ID already exists and/or Company does not exist");
            }
        } while (!success);
        // if information is not stored successfully, loop will continue
    }

    /**
     * Receives ID from user and sends it to menuEnterNewClient
     *
     * @return ID
     */
    private static int inputId() {
        /*
        initializes string variable
        in a loop, ID is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        boolean islength = false;
        String tempId;
        do {
            System.out.println("Enter a new client ID of length 5: ");
            tempId = scanner.nextLine().trim();
            // Checks if the id exists
            if (data.clientIDs.contains(Integer.parseInt(tempId))) {
                System.err.println("ID already exists! Re-enter ID!");
            // Checks if the length of the entered ID is 5
            } else if (tempId.length() == 5) {
                islength = true;
            // Error output if ID length wrong
            } else {
                System.err.println("Invalid ID length! Re-enter ID!");
            }
        } while (tempId.isEmpty() || !islength);
        // converts string input into integer, returns that value
        return Integer.parseInt(tempId);
    }

    /**
     *  Receives Name from user and sends it to menuEnterNewClient
     *
     * @return Name
     */
    private static String inputName() {
        /*
        initializes string variable
        in a loop, Name is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        String tempName;
        do {
            System.out.println("Enter Full Name: ");
            tempName = scanner.nextLine().trim();
        } while (tempName.isEmpty());
        // returns user input
        return tempName;
    }

    /**
     * Receives Country from user and sends it to menuEnterNewClient
     *
     * @return Client's Country
     */
    private static String inputCountry() {
        /*
        initializes string variable
        in a loop, Country is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        String tempCountry;
        do {
            System.out.println("Enter Country of Residence: ");
            tempCountry = scanner.nextLine().trim();
        } while (tempCountry.isEmpty());
        // returns user input
        return tempCountry;
    }

    /**
     * Receives Income from user and sends it to menuEnterNewClient
     *
     * @return Client's Income
     */
    private static double inputIncome() {
        /*
        initializes string variable
        in a loop, Income is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        String tempIncome;
        do {
            System.out.println("Enter Income in CAD: ");
            tempIncome = scanner.nextLine().trim();
        } while (tempIncome.isEmpty());
        // converts string input into double, returns that value
        return Double.parseDouble(tempIncome);
    }

    /**
     * Receives Spending from user and sends it to menuEnterNewClient
     *
     * @return Client's Spending
     */
    private static double inputSpending() {
        /*
        initializes string variable
        in a loop, Spending is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        String tempSpending;
        do {
            System.out.println("Enter Spending in CAD: ");
            tempSpending = scanner.nextLine().trim();
        } while (tempSpending.isEmpty());
        // converts string input into double, returns that value
        return Double.parseDouble(tempSpending);
    }

    /**
     * Receives Age from user and sends it to menuEnterNewClient
     *
     * @return Client's Age
     */
    private static int inputAge() {
        /*
        initializes string variable
        in a loop, Age is asked from user, remove trailing and leading whitespace, assigned to variable
        if variable is empty, loop will continue
         */
        String tempAge;
        do {
            System.out.println("Enter Age: ");
            tempAge = scanner.nextLine().trim();
        } while (tempAge.isEmpty());
        // converts string input into integer, returns that value
        return Integer.parseInt(tempAge);
    }

    /**
     * Asks the user what company the client is in
     *
     * @return The name of the clients company
     */
    private static String inputClientCompanyName() {
        // Keep looping until a name is entered
        String tempClientCompanyName;
        boolean clientCompanyExists = false;
        do {
            System.out.println("Enter Client's Company Name: ");
            tempClientCompanyName = scanner.nextLine().trim();
            if (data.checkExistCompany(tempClientCompanyName)) {
                clientCompanyExists = true;
            } else {
                System.err.println("Company entered does not exist! Re-enter company name!");
            }
        } while (tempClientCompanyName.isEmpty() || !clientCompanyExists);
        return tempClientCompanyName;
    }

    /**
     * @return returns the income of a specific client using their ID number
     */
    private static double returnIncome() {
        while (true) {
            System.out.println("Enter your ID: ");
            String userinput = String.valueOf(scanner.nextInt());
            int id = Integer.parseInt(userinput);
            double income;
            if (data.checkExistClient(id)) {
                for (Client client : data.clients) {
                    if (client.getId() == id) {
                        income = client.getIncome();
                        return income;
                    }
                }
            } else {
                System.err.println("Client ID doesn't exists!");
            }
        }

    }


    /**
     * Prints out all the clients as a viewable table
     */
    private static void menuPrintAllClients() {
        // Headers for the table
        String[] headers = {"ID", "Name", "Age", "Country", "Income", "Spending", "Company"};

        // Get the clients ArrayList from Data
        ArrayList<Client> clients = data.getClients();

        // Sort them based on income
        clients.sort(new IncomeComparator());

        // Print out each header
        for (String header : headers) {
            System.out.format("| %-20s |", header);
        }
        System.out.println();

        // Print out a separator
        for (int i = 0; i < headers.length * 24; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print out each client
        for (Client client : data.clients) {
            System.out.printf(client.toString());

            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints out all the companies as a viewable table
     */
    private static void menuPrintAllCompanies() {
        // Header for table
        String[] headers = {"ID", "Name"};

        // Print out each header
        for (String header : headers) {
            System.out.format("| %-15s |", header);
        }
        System.out.println();

        // Print out a separator
        for (int i = 0; i < headers.length * 19; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print out each company
        for (Company company : data.companies) {
            System.out.printf(company.toString());
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Receives an ID and finds client by matching that ID to a client.
     * Outputs the name and income associated with ID.
     */
    private static void menuPrintClientSpecificIncome() {
        // Asks for an ID and assigns input to a variable, convert string to integer
        System.out.println("Enter ID of client: ");
        String userInput = scanner.nextLine();
        int id = Integer.parseInt(userInput);

        /*
        Loops through all objects in array list
        Matches given ID to the client ID,
        if yes, prints name and income associated with input ID
         */
        for (Client client : data.clients) {
            if (client.getId() == id) {
                System.out.printf("%s, with ID %d, has income $%.2f", client.getName(), client.getId(), client.getIncome());
            } else {
                System.err.println("Client ID doesn't exist");
            }
            break;
        }
        // new line to separate output and menu
        System.out.println();
    }

    /**
     * Receives a country and outputs all clients from that country.
     */
    private static void menuListClientCertainCountry() {
        ArrayList<String> clientsList = new ArrayList<>();
        // Asks for a country and assigns input to a variable
        System.out.println("Enter a country.");
        String country = scanner.nextLine();

        /*
        Loops through all objects in array list
        Matches given country to the object client country, if yes, prints name of the client
         */
        for (Client client : data.clients) {
            if (client.getCountry().equals(country)) {
                clientsList.add(client.getName());
            }
        }

        if (clientsList.isEmpty()) {
            System.err.println("No clients exist from this country: " + country);
        } else {
            System.out.println("These clients are from " + country + ":");
            for (String name : clientsList) {
                System.out.printf("    %s\n", name);
            }
        }

        // new line to separate output and menu
        System.out.println();
    }

    /**
     * converts a clients income to a foreign currency from CAD
     */
    private static void menuIncomeCADtoForeign() {
        double foreignincome = returnIncome();
        if (foreignincome == 0) {
            System.err.println("Value of income cannot be 0");
        } else {
            scanner.nextLine();
            System.out.println("Enter the currency you want to see your income in the following regions:\n USA, Europe, UK, India, Australia, Singapore, Switzerland, Malaysia, Japan, China: ");
            String tempCountry = scanner.nextLine();
            if (ForeignStatistics.Currencies.containsKey(tempCountry)) {
                foreignincome =  foreignincome * ForeignStatistics.Currencies.get(tempCountry);
                System.out.println("Your income in " + tempCountry + " is: " + foreignincome);
            } else {
                System.err.println("Country entered is not part of the list above!");
            }
        }
    }

    /**
     * Suggests a holiday based on the level of income of the client
     */
    private static void menuClientSuggestiveHolidays() {
        double income = returnIncome();
        String holiday;
        int option;
        if (income < 1500) {
            option = 1;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            System.out.println("We suggest the following holiday destinations with your specific income\n" + holiday + "\n");
        } else if (income > 1500 && income < 3000) {
            option = 2;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            System.out.println("We suggest the following holiday destinations at that price:\n" + holiday + "\n");
        } else if (income >= 3000) {
            option = 3;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            System.out.println("We suggest the following holiday destinations at that price:\n" + holiday + "\n");
        } else {
            System.err.println("Income cannot be 0, no holiday options available!");
        }
    }

    /**
     * Estimates Savings in any foreign market
     */
    private static void menuEstimatedSavingForeignCountry() {
        System.out.println("Enter your ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = data.getClientthroughID(id);
        System.out.println("Enter the Country you would like to invest in:\n USA, Europe, UK, India, Australia, Singapore, Switzerland, Malaysia, Japan, China:");
        String country = scanner.nextLine();
        double income = client.getIncome()  * (ForeignStatistics.Currencies.get(country));
        System.out.println("Enter the proportion of your income you will save as a percentage on a monthly basis: ");
        String userinput = scanner.nextLine();
        double proportion = Double.parseDouble(userinput);
        proportion = proportion / 100;
        double savedincome = income * proportion;
        System.out.println("Enter the number of months you would like to save this money for: ");
        String userinput2 = scanner.nextLine();
        double months = Double.parseDouble(userinput2);
        // Convert the interest rate to a monthly amount and add 1 as we are ending with the total.
        double interestrate = ((ForeignStatistics.InterestRate.get(country)) / 12) + 1;
        double savings = 0.0;
        // Multiplies each addition of the proportion of income every month to savings and multiplies it by the monthly interest rate.
        for ( int i = 0; i < months; i++) {
            savings += savedincome;
            savings = savings * interestrate;
        }
        double CADsavings = savings / (ForeignStatistics.Currencies.get(country));
        if (CADsavings != 0) {
            System.out.printf("Your savings in %.0f months is: CAD %.2f\n", months, CADsavings);
        } else {
            System.out.println("Invalid input");
        }
    }

    /**
     * Estimates savings in the canadian market
     */
    private static void menuEstimatedSavingsCanada() {
        System.out.println("Enter your id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Client client = data.getClientthroughID(id);
        double income = client.getIncome();
        scanner.nextLine();
        System.out.println("Enter the proportion of your income you will save as a percentage on a monthly basis: ");
        String userinput = scanner.nextLine();
        double proportion = Double.parseDouble(userinput);
        proportion = proportion / 100;
        double savedincome = income * proportion;
        System.out.println("Enter the number of months you would like to save this money for: ");
        String userinput2 = scanner.nextLine();
        double months = Double.parseDouble(userinput2);
        // Convert the interest rate to a monthly amount and add 1 as we are ending with the total.
        double interestrate = ((ForeignStatistics.InterestRate.get("Canada")) / 12) + 1;
        double savings = 0.0;
        // Multiplies each addition of the proportion of income every month to savings and multiplies it by the monthly interest rate.
        for ( int i = 0; i < months; i++) {
            savings += savedincome;
            savings = savings * interestrate;
        }
        if (savings != 0) {
            System.out.printf("Your savings in %.0f months is: CAD %.2f\n", months, savings);
        } else {
            System.out.println("Invalid input");
        }
    }

    private static void menuNumberOfEmployees() {
        int count = 0;
        System.out.println("Enter a company ID: ");
        String companyId = scanner.nextLine();
        Company company = data.getCompanyThroughID(companyId);
        if (company == null) {
            System.out.println("Company does not exist!\n");
        } else {
            for (int i = 0; i < data.clients.size(); i++) {
                if (data.clients.get(i).getCompany().equals(company.getName())) {
                    count++;
                }
            }
            System.out.println("There are " + count + " employees in " + company.getName() + "\n");
        }
    }

    private static void menuAverageAgeOfEmployees() {
        int count = 0;
        int total = 0;
        System.out.println("Enter a company ID: ");
        String companyId = scanner.nextLine();
        Company company = data.getCompanyThroughID(companyId);
        for (int i = 0; i < data.clients.size(); i++) {
            if (data.clients.get(i).getCompany().equals(company.getName())) {
                count++;
                total+=data.clients.get(i).getAge();
            }
        }
        int average = total/count;
        System.out.println("The average age is " + average + " for the company " + company.getName() + "\n");
    }
}
