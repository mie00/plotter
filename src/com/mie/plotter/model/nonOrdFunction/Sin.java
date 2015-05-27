package com.mie.plotter.model.nonOrdFunction;

import com.mie.plotter.model.node.Node;



public class Sin extends NonOrdFunction{
    @Override public Double call(Double lastValue,Node[] arguments){
        return Math.sin(arguments[0].calc());
    }
}
