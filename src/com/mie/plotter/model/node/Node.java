package com.mie.plotter.model.node;

public abstract class Node{
	private Double value;
	private Node[] parents;
	public abstract void resetValue();
	public abstract Double calc();
    public void addParent(Node n){}
    public void parentsFinished(){}
}
