package com.mie.plotter.view;

import javafx.scene.control.Slider;
import com.mie.plotter.model.node.VariableNode;
import javafx.beans.property.DoubleProperty;

public class Ranger extends Slider{
    private VariableNode variable;
    private DoubleProperty value;
    public Ranger(VariableNode varable){
        super();

        this.variable=variable;
        Slider slider = this;
        //slider.setMin(0);
        //slider.setMax(100);
        slider.setValue(0.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        value = slider.valueProperty();

    }
    public DoubleProperty getValueProperty(){
        return value;
    }
}












