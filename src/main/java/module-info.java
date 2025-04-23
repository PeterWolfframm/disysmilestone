module at.uastw.disysvz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires spring.web;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;

    opens at.uastw.disysvz to javafx.fxml;
    opens at.uastw.disysvz.api to spring.core;
    
    exports at.uastw.disysvz;
    exports at.uastw.disysvz.api;
}