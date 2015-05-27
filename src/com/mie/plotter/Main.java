package com.mie.plotter;

import com.mie.plotter.controller.ViewController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args){
        Application.launch();
    }
    @Override public void start(Stage stage){
        ViewController vc = new ViewController();
        stage.setScene(vc.getView());
        stage.show();
    }
}
