module library {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires junit;
    requires jlayer;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.services.polly;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.utils;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires software.amazon.awssdk.awscore;
    requires software.amazon.eventstream;
    
    opens library to javafx.fxml;

    exports library;

    opens controllers to javafx.fxml;

    exports controllers;

    opens model to javafx.fxml;

    exports model;

    opens narrator to javafx.fxml;

    exports narrator;
}
