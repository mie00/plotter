package com.mie.plotter.model.nonOrdFunction;

import com.mie.plotter.model.node.Node;

public abstract class NonOrdFunction{
	public abstract Double call(Double lastValue, Node[] arguments);
}
