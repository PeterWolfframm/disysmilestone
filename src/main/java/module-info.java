module at.uastw.disysvz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens at.uastw.disysvz to javafx.fxml;
    exports at.uastw.disysvz;
}