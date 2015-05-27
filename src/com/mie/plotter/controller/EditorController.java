package com.mie.plotter.controller;

import com.mie.plotter.view.Editor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class EditorController{
    Editor editor = new Editor();
    public EditorController(ViewController vc){
        final SimpleStringProperty dv = editor.getValueProperty();
        dv.addListener(new InvalidationListener()
                {
                @Override
                public void invalidated(Observable arg0)
                {

                vc.generateTree(dv.get());
                }
                });
    }
    public Editor getEditor(){
        return editor;
    }
}
