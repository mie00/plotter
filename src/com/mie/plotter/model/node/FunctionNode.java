package com.mie.plotter.model.node;

import com.mie.plotter.model.function.Function;
import java.util.ArrayList;

public class FunctionNode extends Node{
    private Double value;
    private Node[] args;
    private Double[] argsValue;
    private Function function;
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
    public FunctionNode(Function function,Node...args){
        this.function=function;
        this.args=args;
        argsValue=new Double[args.length];
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
            if (value == null){

                for (int i = 0,j=args.length;i<j;i++)
                    argsValue[i]=args[i].calc();

                value = function.call(argsValue);
            }

            return value;
        }
}
