package com.mie.plotter.model.node;

import com.mie.plotter.model.nonOrdFunction.NonOrdFunction;
import java.util.ArrayList;

public class NonOrdFunctionNode extends Node{
    private Double value;
    private Node[] args;
    private NonOrdFunction function;
    private Node[] parents;
    private ArrayList<Node> tempParents = new ArrayList<Node>();
    @Override
    public void addParent(Node parent){
        tempParents.add(parent);
    }
    @Override
    public void parentsFinished(){
        parents=tempParents.toArray(new Node[tempParents.size()]);
    }
    public NonOrdFunctionNode(NonOrdFunction function,Node... args){
        this.function=function;
        this.args=args;
    }
    @Override
        public void resetValue(){
            value = null;
            for (Node i: parents){
                i.resetValue();
            }
        }
    @Override
        public Double calc(){
            value = function.call(value,args);
            return value;
        }
}
