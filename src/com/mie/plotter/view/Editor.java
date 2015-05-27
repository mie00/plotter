package com.mie.plotter.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.event.EventHandler;

public class Editor extends HBox{
    final private SimpleStringProperty value= new SimpleStringProperty();
    public Editor(){
        super();
        TextField formulaEditor = new TextField();
        Button plot = new Button();
        this.getChildren().addAll(formulaEditor,plot);
        plot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                value.set(formulaEditor.getText());
            }
        });

    }
    public SimpleStringProperty getValueProperty(){
        return value;
    }
}












