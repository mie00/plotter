package com.mie.plotter.view;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Canvas2 extends Canvas{
    GraphicsContext gc;
    public Canvas2(){
        super(500,500);
        Canvas canvas = this;
        gc = canvas.getGraphicsContext2D();

    }
    public void setData(Double[] values){
        gc.clearRect(0, 0, 500, 500);
        gc.beginPath();
        gc.moveTo(-10,0);
        for (int i = 0,j=values.length;i<j;i+=1)
            gc.lineTo(i*1.0,(-values[i])*50+250);
        gc.stroke();
    }
}
