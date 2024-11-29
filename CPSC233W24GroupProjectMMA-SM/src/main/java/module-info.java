module ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm to javafx.fxml;
    exports ca.ucalgary.sm.mma.cpsc233w24groupprojectmmasm;
}