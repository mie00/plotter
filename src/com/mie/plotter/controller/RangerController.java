package com.mie.plotter.controller;

import com.mie.plotter.view.Ranger;
import com.mie.plotter.model.node.VariableNode;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;

public class RangerController{
    private VariableNode variable;
    private Ranger ranger;
    public RangerController(final ViewController vc,final VariableNode variable){
        this.variable=variable;
        this.ranger=new Ranger(variable);
        final DoubleProperty dv = ranger.getValueProperty();
        dv.addListener(new InvalidationListener()
                {
                @Override
                public void invalidated(Observable arg0)
                {

                variable.changeValue(dv.get());
                vc.refresh();
                }
                });


    }
    public void changeValue(Double value){
        ranger.setValue(value);
    }
    public Ranger getRanger(){
        return ranger;
    }
}
