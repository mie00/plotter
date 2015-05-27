package com.mie.plotter.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class View extends Scene{
    private Canvas2 canvas;
    private VBox rbs = new VBox();
    public View(Canvas2 canvas,Editor editor,Ranger... rangers){

        super(new VBox());
        ((VBox)this.getRoot()).getChildren().addAll(canvas,editor,rbs);
        setRangers(rangers);
        this.canvas = canvas;
    }
    public void setRangers(Ranger... rangers){
            System.out.println(rangers.length);
            rbs.getChildren().clear();
            rbs.getChildren().addAll(rangers);
        }
    public void setData(Double[] data){
        canvas.setData(data);
    }
}
