package com.mie.plotter.model.function;

public class Sin extends Function{
    @Override
        public Double call(Double[] arguments){
            return Math.sin(arguments[0]);
        }
}
