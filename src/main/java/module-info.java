module tubryansk.lisitsyn.cryptapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens tubryansk.lisitsyn.cryptapp to javafx.fxml;
    exports tubryansk.lisitsyn.cryptapp;
}