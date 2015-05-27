package com.mie.plotter.model.node;

public class NumberNode extends Node{
	private Double value;
	private Node[] parents;
	public NumberNode(Double value){
		this.value = value;
	}
	@Override
	public void resetValue(){}
	@Override
	public Double calc(){
		return value;
	}
}
