package com.mie.plotter.model.function;

public class Add extends Function{
    @Override
        public Double call(Double[] arguments){
            return arguments[0]+arguments[1];
        }
}
