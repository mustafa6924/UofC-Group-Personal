package ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;

import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.comparators.IncomeComparator;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Client;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.Company;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.objects.ForeignStatistics;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util.FileLoader;
import ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm.util.FileSaver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class to control the GUI
 *
 * @author Muhammad Mustafa Amer, Shayaan Musthafa
 * @email muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca
 * April 11, 2024
 * Tutorial 06, Tejash Shrestha
 */
public class Controller {

    //Variables in adding clients section
    @FXML
    private TextField clientID;
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientAge;
    @FXML
    private TextField clientCountry;
    @FXML
    private TextField clientIncome;
    @FXML
    private TextField clientSpending;
    @FXML
    private TextField clientCompany;

    //Variables in adding company section
    @FXML
    private TextField companyID;
    @FXML
    private TextField companyName;

    @FXML
    private TextField specialID;
    @FXML
    private TextField convertToCountry;
    @FXML
    private TextField canadaMonths;
    @FXML
    private TextField foreignMonths;
    @FXML
    private TextField foreignCountry;

    //Text areas for list of clients and companies
    @FXML
    private TextArea listClients;
    @FXML
    private TextArea listCompanies;

    //Variables in special functions pane
    @FXML
    private RadioButton currencyConverter;
    @FXML
    private RadioButton  suggestedHoliday;
    @FXML
    private RadioButton  canadaSavings;
    @FXML
    private RadioButton foreignSavings;
    @FXML
    private RadioButton canada10;
    @FXML
    private RadioButton canada20;
    @FXML
    private RadioButton canada30;
    @FXML
    private RadioButton foreign10;
    @FXML
    private RadioButton foreign20;
    @FXML
    private RadioButton foreign30;
    @FXML
    private RadioButton companyEmployees;
    @FXML
    private RadioButton companyAverageAge;
    @FXML
    private ToggleGroup companyFunctions;
    @FXML
    private ToggleGroup specialFunctions;

    @FXML
    private ComboBox<String> specialIDdropdown;
    @FXML
    private ComboBox<String> convertToCountryDropdown;
    @FXML
    private ComboBox<String> foreignCountryDropdown;
    @FXML
    private ComboBox<String> companyDropdown;
    @FXML
    private ComboBox<String> newClientCompanyListDropdown;

    //Status bar
    @FXML
    private Label status;

    private Data data = new Data();
    private Stage stage;

    /**
     * Helper function for checking validity of id in add company
     * @param id The company id to check
     * @return True if it exists, False otherwise
     */
    private boolean checkCompanyID(String id) {
        boolean idLetters = false;
        boolean idNumbers = false;
        //Converts the input into an array of char to be manipulated
        char[] idParts = id.toCharArray();
        //Ensures the length of ID entered is 6
        if (idParts.length == 6) {
            //Check if the first 3 chars are between the ASCII value of A-Z
            if (idParts[0] >= 'A' && idParts[0] <= 'Z' && idParts[1] >= 'A' && idParts[1] <= 'Z' && idParts[2] >= 'A' && idParts[2] <= 'Z') {
                idLetters = true;
                //Checks if the last 3 chars are numbers from 0-9
                if (idParts[3] >= '0' && idParts[3] <= '9' && idParts[4] >= '0' && idParts[4] <= '9' && idParts[5] >= '0' && idParts[5] <= '9') {
                    idNumbers = true;
                }
            }
        }
        if (idLetters && idNumbers) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Helper function for printing companies
     * @param data The Data object to print from
     */
    private void printCompanies(Data data) {
        // Clear previous things in text area
        listCompanies.clear();

        // Create new string builder
        StringBuilder sb = new StringBuilder();

        // New array for the companies headers
        String[] headers = {"ID", "Name"};

        // Loop to append the format of headers
        for (String header : headers) {
            sb.append(String.format("| %-12s |", header));
        }
        sb.append("\n");

        sb.append("-".repeat(headers.length * 14));
        sb.append("\n");

        for (Company company : data.companies) {
            sb.append(String.format(company.toString()));

            sb.append("\n");
        }
        sb.append("\n");

        listCompanies.setText(String.valueOf(sb));
        for (int i = 0; i < data.companies.size(); i++) {
            companyDropdown.getItems().add(data.companies.get(i).getId());
            newClientCompanyListDropdown.getItems().add(data.companies.get(i).getName());
        }

    }

    /**
     * helper function for printing clients
     * @param data The Data object to print from
     */
    private void printClients(Data data) {
        // Clear any old information
        listClients.clear();

        // Create a new StringBuilder
        StringBuilder sb = new StringBuilder();

        // Create headers
        String[] headers = {"ID", "Name", "Age", "Country", "Income", "Spending", "Company"};

        // Sort the clients by income
        data.clients.sort(new IncomeComparator());

        // Append header
        for (String header : headers) {
            sb.append(String.format("| %-20s |", header));
        }
        sb.append("\n");

        // Append separator
        sb.append("-".repeat(headers.length * 20));
        sb.append("\n");

        // Append client information
        for (Client client : data.clients) {
            sb.append(String.format(client.toString()));

            sb.append("\n");
        }
        sb.append("\n");

        // Set ListClients to the string generated
        listClients.setText(sb.toString());
        for (int i = 0; i < data.clients.size(); i++) {
            specialIDdropdown.getItems().add(String.valueOf(data.clients.get(i).getId()));
        }
    }

    /**
     * Helper function for suggested holiday in special functions
     */
    private void suggestiveHoliday() {
        int option;
        String holiday;
        Alert alert;
        //Creates new client
        Client client = data.getClientthroughID(Integer.parseInt(specialIDdropdown.getValue()));
        //Gets clients income
        Double income = client.getIncome();
        // Checks income ranges and accordingly suggest holidays using the enum class ForeignStatistics
        if (income < 1500 && income > 0) {
            option = 1;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            alert = createAlert("Suggested Holiday", "Here are our suggested holiday destinations for you", holiday);
            alert.showAndWait();
        } else if (income > 1500 && income < 3000) {
            option = 2;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            alert = createAlert("Suggested Holiday", "Here are our suggested holiday destinations for you", holiday);
            alert.showAndWait();
        } else if (income >= 3000) {
            option = 3;
            holiday = ForeignStatistics.suggestiveHoliday.get(option);
            alert = createAlert("Suggested Holiday", "Here are our suggested holiday destinations for you", holiday);
            alert.showAndWait();
        } else if (income == 0) {
            alert = createAlert("Error", "This client has an income of 0", "Please input the ID of a client who has an income greater than 0");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    /**
     * Helper function to count the number of employees
     */
    private void numberOfEmployees() {
        int count = 0;
        Company company = data.getCompanyThroughID(companyDropdown.getValue());
        for (int i = 0; i < data.clients.size(); i++) {
            if (data.clients.get(i).getCompany().equals(company.getName())) {
                count++;
            }
        }
        if (count == 0) {
            Alert alert = createAlert("Employee Count","Error" , "There are no employees in this company");
            alert.showAndWait();
        } else {
            Alert alert = createAlert("Employee Count","Number of employees" , "Number of employees in " + company.getName() + ": "+ count + " employees");
            alert.showAndWait();
        }
    }

    /**
     * Helper function to get the average age of employees
     */
    private void averageAgeOfEmployees() {
        int count = 0;
        int total = 0;
        Company company = data.getCompanyThroughID(companyDropdown.getValue());
        for (int i = 0; i < data.clients.size(); i++) {
            if (data.clients.get(i).getCompany().equals(company.getName())) {
                count++;
                total+=data.clients.get(i).getAge();
            }
        }
        if (count == 0) {
            Alert alert = createAlert("Employee Average Age", "Error", "There are no employees in this company");
            alert.showAndWait();
        } else {
            int average = total/count;
            Alert alert = createAlert("Employee Average Age", "Average Age","The average age is: " + average + " for the company: " + company.getName());
            alert.showAndWait();
        }

    }

    /**
     * Helper function for converting income to foreign currency in special functions
     */
    private void incomeCurrencyConvert() {
        // Creates new client
        Client client = data.getClientthroughID(Integer.parseInt(specialIDdropdown.getValue()));
        // Gets clients income
        double foreignincome = client.getIncome();
        Alert alert;
        // Error checking to see if textfield is empty
        if (convertToCountryDropdown.getValue() ==  null) {
            //Creates alert to showcase the error that occurred
            alert = createAlert("Error", "You have not entered a country", "Please input a country to convert your income to");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Error checking for if the income is 0
        } else if (foreignincome == 0) {
            //Creates alert to showcase the error that occurred
            alert = createAlert("Error", "This client has an income of 0", "Please input the ID of a client who has an income greater than 0");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Performs the conversion of the currency
        } else {
            String newCountry = convertToCountryDropdown.getValue();
            foreignincome =  foreignincome * ForeignStatistics.Currencies.get(newCountry);
            alert = createAlert("Converted income", "Here is your income in the foreign country chosen", newCountry + ": " + String.format("%.2f", foreignincome));
            alert.showAndWait();
        }
    }

    /**
     * Helper function for savings in Canada special function option
     */
    private void savingsInCanada() {
        Alert alert;
        Client client = data.getClientthroughID(Integer.parseInt(specialIDdropdown.getValue()));
        double income = client.getIncome();
        double savedIncome;
        double proportion = 0.0;
        double savings = 0.0;
        double months = 0;
        if (canadaMonths.getText().isEmpty() || (!canada10.isSelected() && !canada20.isSelected() && !canada30.isSelected())) {
            alert = createAlert("Error", "Error occurred", "Missing months value or have not selected income proportion");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }else {
            if (canada10.isSelected()) {
                proportion = 10;
            } else if (canada20.isSelected()) {
                proportion = 20;
            } else if (canada30.isSelected()) {
                proportion = 30;
            }
            proportion = proportion / 100;
            savedIncome = income * proportion;
            months = Double.parseDouble(canadaMonths.getText());
            // Convert the interest rate to a monthly amount and add 1 as we are ending with the total.
            double interestrate = ((ForeignStatistics.InterestRate.get("Canada")) / 12) + 1;
            // Multiplies each addition of the proportion of income every month to savings and multiplies it by the monthly interest rate.
            for (int i = 0; i < months; i++) {
                savings += savedIncome;
                savings = savings * interestrate;
            }
            if (savings != 0) {
                String formattedNumber = String.format("%.2f", savings);
                alert = createAlert("Savings in Canada","Information for savings in Canada","Your savings in " + months + " months is: " + formattedNumber);
                alert.showAndWait();
                canadaMonths.clear();
            } else {
                alert =createAlert("Savings in Canada", "No savings!" , "For some reason you have no savings");
                alert.showAndWait();
            }
        }

    }

    /**
     * helper function to manage savings in foreign currency of the special functions
     */
    private void savingsInForeignCountry() {
        Alert alert;
        // Creates new client
        Client client = data.getClientthroughID(Integer.parseInt(specialIDdropdown.getValue()));
        // Gets the clients income
        double income = client.getIncome();
        double savedIncome;
        double proportion = 0.0;
        double savings = 0.0;
        double months = 0;
        // Error checking to make sure radio buttons are selected and TextField is not empty
        if (foreignCountryDropdown.getValue() == null || foreignMonths.getText().isEmpty() || (!foreign10.isSelected() && !foreign20.isSelected() && !foreign30.isSelected())) {
            //Creates alert to showcase the error that occurred
            alert = createAlert("Error", "Error occurred", "Missing months value, or have not selected income proportion, or missing foreign country");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Error checking for if the country entered exists in our database
        }else {
            if (foreign10.isSelected()) {
                proportion = 10;
            } else if (foreign20.isSelected()) {
                proportion = 20;
            } else if (foreign30.isSelected()) {
                proportion = 30;
            }
            proportion = proportion / 100;
            savedIncome = income * proportion;
            months = Double.parseDouble(foreignMonths.getText());
            // Convert the interest rate to a monthly amount and add 1 as we are ending with the total.
            double interestRate = ((ForeignStatistics.InterestRate.get(foreignCountryDropdown.getValue())) / 12) + 1;
            // Multiplies each addition of the proportion of income every month to savings and multiplies it by the monthly interest rate.
            for (int i = 0; i < months; i++) {
                savings += savedIncome;
                savings = savings * interestRate;
            }
            if (savings != 0) {
                String formattedNumber = String.format("%.2f", savings);
                alert = createAlert("Savings in Foreign country","Information for savings in a foreign country","Your savings in " + months + " months is: " + formattedNumber);
                alert.showAndWait();
                foreignMonths.clear();
            } else {
                //Creates alert to showcase the error that occurred
                alert =createAlert("Savings in Foreign country", "No savings!" , "For some reason you have no savings");
                alert.showAndWait();
            }
        }
    }

    /**
     * Helper function to create an alert
     * @param title The tile for the alert
     * @param header The header text for the alert
     * @param contentText The context text for the alert
     * @return The alert
     */
    private Alert createAlert(String title, String header, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        return alert;
    }

    /**
     * Helper function to check if clients ID is of the right size
     * @param ID The clients ID
     * @return True if it is the correct size, False otherwise
     */
    private boolean clientIDCheck(String ID) {
        if (ID.length() == 5) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Helper function to load a file
     * @param file The file to load
     */
    public void load(File file) {
        if (file != null) {
            // Load the file and store it to data
            this.data = FileLoader.load(file);
            // Print all the clients and company
            newClientCompanyListDropdown.getItems().clear();
            companyDropdown.getItems().clear();
            specialIDdropdown.getItems().clear();
            printClients(data);
            printCompanies(data);
            // Set a success status message
            status.setTextFill(Color.GREEN);
            status.setText("Tracker data successfully loaded");
        } else {
            Alert alert = createAlert("Error", "Message", "Unable to load file for some reason!");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    /**
     * initialized setting of app
     */
    @FXML
    void initialize() {
        // Set the basic status message
        status.setTextFill(Color.BLACK);
        status.setText("Status messages appear here");
        for (int i = 0; i < ForeignStatistics.countryLists.size(); i++) {
            convertToCountryDropdown.getItems().add(ForeignStatistics.countryLists.get(i));
            foreignCountryDropdown.getItems().add(ForeignStatistics.countryLists.get(i));
        }
    }

    /**
     * Quits app from file dropdown
     */
    @FXML
    void handleQuitApp() {
        // Create a new Confirmation Alert
        Alert alert = createAlert("Quit App", "Confirmation", "Are you sure you want to quit the app?");
        alert.setAlertType(Alert.AlertType.CONFIRMATION);

        // Close the app is the user click OK
        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Gives app info help dropdown
     */
    @FXML
    void handleAboutApp() {
        // Create an alert to show about info
        Alert alert = createAlert("About", "Message", "Author: Muhammad Mustafa Amer, Shayaan Musthafa \nEmail: muhammadmustafa.amer@ucalgary.ca, shayaan.musthafa@ucalgary.ca\nVersion: v3.0\nThis is a Financial Tracker application using data of companies and clients provided!");
        alert.showAndWait();
    }

    /**
     * Adds company
     */
    @FXML
    void addCompany() {
        // Get inputs for company
        String id = companyID.getText();
        String name = companyName.getText();

        // Check if Company ID is in correct format
        if (!checkCompanyID(id)) {
            Alert alert = createAlert("Error", "Message", "Invalid ID format! Enter it in the format: ABC123");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }else {
            // Store the new company
            boolean checkCompany = data.storeNewCompany(id, name);
            if(checkCompany) {
                // Print all the companies
                newClientCompanyListDropdown.getItems().clear();
                companyDropdown.getItems().clear();
                printCompanies(data);
                status.setTextFill(Color.GREEN);
                status.setText("Successfully added company: " + companyName.getText());
                companyID.clear();
                companyName.clear();
            // Checks if ID already exists
            }else {
                Alert alert = createAlert("Error", "Message", "A company with the ID: " + id + " already exists!");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            }
        }
    }

    /**
     * Adds a client
     */
    @FXML
    void addClient() {
        try {
            // Initialize variables for client info
            int id = Integer.parseInt(clientID.getText());
            String name = clientName.getText();
            int age = Integer.parseInt(clientAge.getText());
            String country = clientCountry.getText();
            double income = Double.parseDouble(clientIncome.getText());
            double spending = Double.parseDouble(clientSpending.getText());
            String company = newClientCompanyListDropdown.getValue();

            // Check if client ID is the correct size
            if (!clientIDCheck(clientID.getText())) {
                Alert alert = createAlert("Error", "Message", "Invalid ID length, has to be length 5!");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            // Check if client company exists
            } else if (!data.checkExistCompany(company)) {
                Alert alert = createAlert("Error", "Message", "The company " + company + " does not exist!");
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.showAndWait();
            } else {
                // Store a new client
                boolean checkClient = data.storeNewClient(id, name, age, country, income, spending, company);
                // Print all the clients
                if (checkClient) {
                    specialIDdropdown.getItems().clear();
                    printClients(data);
                    status.setTextFill(Color.GREEN);
                    status.setText("Successfully added client: " + clientName.getText());
                    clientID.clear();
                    clientName.clear();
                    clientAge.clear();
                    clientCountry.clear();
                    clientIncome.clear();
                    clientSpending.clear();
                // Check if client ID exists
                } else {
                    Alert alert = createAlert("Error", "Message", "Client already exists with ID: " + id);
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.showAndWait();
                }
            }

        // Check if integers parsed correctly
        } catch (NumberFormatException e) {
            Alert alert = createAlert("Error", "Message", "Invalid integer format! Integers cannot be blank or have letter!");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }



    }

    /**
     * Loads file
     */
    @FXML
    void handleLoad() {
        try {
            // Create a new FileChooser
            FileChooser fileChooser = new FileChooser();
            // Set the initial directory
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            // Get the file
            File response = fileChooser.showOpenDialog(stage);
            // Load the file
            load(response);
        // Check if file loading was cancelled
        } catch (NullPointerException e) {
            status.setTextFill(Color.RED);
            status.setText("Cancelled file loading");
        }
    }

    /**
     * Saves file
     */
    @FXML
    void handleSave() {
        String defaultFileName = "FinanceTrackerData.txt";
        // Create a new FileChooser
        FileChooser filechooser = new FileChooser();
        // Set the initial directory
        filechooser.setInitialDirectory(new File("."));
        // Set the initial file name
        filechooser.setInitialFileName(defaultFileName);
        // Restrict the file extension
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        // Get the file
        File fileSave = filechooser.showSaveDialog(stage);
        // Save the file
        if (fileSave != null) {
            FileSaver.save(fileSave, data);
            // Give a status message
            status.setTextFill(Color.GREEN);
            status.setText("Tracker data successfully saved");
        } else {
            Alert alert = createAlert("Error", "Message", "Unable to save file for some reason!");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    /**
     * Handles the special functions pane
     */
    @FXML
    void handleSpecialFunctions() {
        Alert alert;
        // Error checking for when the id field is empty in the special functions pane
        if (specialIDdropdown.getValue() ==  null) {
            // Creates error alert
            alert = createAlert("Error", "Message", "ID in special functions column cannot be empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Error checking for when the ID entered is of an invalid format
        }else if (!clientIDCheck(specialIDdropdown.getValue())) {
            alert = createAlert("Error", "Message", "The ID length is incorrect, please enter in the format 00000");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Error checking for when none of the buttons are selected
        }else if (!currencyConverter.isSelected() && !suggestedHoliday.isSelected() && !canadaSavings.isSelected() && !foreignSavings.isSelected()) {
            alert = createAlert("Error", "Message", "No option selected from special functions");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Checks if the suggestive holiday button is selected
        }else if (clientIDCheck(specialIDdropdown.getValue()) && suggestedHoliday.isSelected()) {
            suggestiveHoliday();
        // Checks if the income currency button is selected
        }else if (clientIDCheck(specialIDdropdown.getValue()) && currencyConverter.isSelected()) {
            incomeCurrencyConvert();
        // Checks if th savings in canada button is selected
        }else if (clientIDCheck(specialIDdropdown.getValue()) && canadaSavings.isSelected()) {
            savingsInCanada();
        // Checks if the foreign savings button is selected
        }else if (clientIDCheck(specialIDdropdown.getValue()) && foreignSavings.isSelected()) {
            savingsInForeignCountry();
        }
    }

    /**
     * Handles client special functions
     */
    @FXML
    void handleCompanyFunctions() {
        Alert alert;
        // Checks if the dropdown has nothing
        if (companyDropdown.getValue() == null) {
            alert = createAlert("Error", "Message", "ID in company functions column cannot be empty");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Checks if nothing was selected for the dropdown
        }else if (!companyEmployees.isSelected() && !companyAverageAge.isSelected()) {
            alert = createAlert("Error", "Message", "No option selected from company functions");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.showAndWait();
        // Checks if employees count option was selected
        }else if (companyEmployees.isSelected() && companyDropdown.getValue() != null) {
            numberOfEmployees();
        // Checks if employees average age option was selected
        }else if (companyAverageAge.isSelected() && companyDropdown.getValue() != null) {
            averageAgeOfEmployees();
        }
    }
}