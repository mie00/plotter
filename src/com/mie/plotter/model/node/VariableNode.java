package com.mie.plotter.model.node;

import java.util.ArrayList;

public class VariableNode extends Node{
    private Double value;
    private Node[] parents=new Node[0];
    private ArrayList<Node> tempParents = new ArrayList<Node>();
    @Override
    public void addParent(Node parent){
        tempParents.add(parent);
    }
    @Override
    public void parentsFinished(){
        parents=tempParents.toArray(new Node[tempParents.size()]);
    }
    public VariableNode(){
    }
    @Override
        public void resetValue(){
            value = null;
            for (Node i: parents){
                i.resetValue();
            }
        }
    public void changeValue(Double newValue){
        resetValue();
        value = newValue;
    }
    @Override
        public Double calc(){
            return value;
        }
    public Node[] getParents(){
 return parents;       
        }
}
