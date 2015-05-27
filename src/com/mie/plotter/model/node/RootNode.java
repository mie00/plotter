package com.mie.plotter.model.node;

public class RootNode extends Node{
    private Double value;
    private Node[] parents;
    private Node arg;
    public RootNode(Node arg){
           this.arg=arg; 
        }
    public void setArg(Node arg){
        this.arg=arg;
    }
    public Node getArg(){
        return this.arg;
    }
    public RootNode(){}
    @Override
        public void resetValue(){
            value = null;
        }
    @Override
        public Double calc(){
            if (value == null)
                return arg.calc();
            else
                return value;
        }
}
