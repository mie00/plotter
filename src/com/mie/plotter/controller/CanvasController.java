package com.mie.plotter.controller;

import com.mie.plotter.model.node.VariableNode;

import com.mie.plotter.view.Canvas2;

public class CanvasController{
    private ViewController vc;
    private VariableNode variable;
    private Canvas2 canvas=new Canvas2();
    public CanvasController(ViewController vc,VariableNode variable)   {
        this.vc=vc;
        this.variable=variable;
    }
    public void setVariable(VariableNode variable){
        this.variable = variable;
        }
    public void plot(){

        Double[] res=new Double[1001];
            for( int i=0;i<=1000;i+=1){
                variable.changeValue(i*0.1);
                res[i]=vc.calc();
            }
        canvas.setData(res);
    }
    public Canvas2 getCanvas(){
        return canvas;
        }

}
