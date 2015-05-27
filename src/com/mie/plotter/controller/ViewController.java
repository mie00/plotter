package com.mie.plotter.controller;

import com.mie.plotter.model.Tree;
import com.mie.plotter.model.node.Node;
import com.mie.plotter.model.node.NumberNode;
import com.mie.plotter.model.node.RootNode;
import com.mie.plotter.model.node.FunctionNode;
import com.mie.plotter.model.node.VariableNode;
import com.mie.plotter.model.function.Sin;
import com.mie.plotter.model.function.Mult;
import com.mie.plotter.model.function.Add;
import com.mie.plotter.view.View;
import com.mie.plotter.view.Ranger;

public class ViewController{
    private CanvasController cc;
    private RootNode root;
    private View view;
    public ViewController(){

        cc = new CanvasController(this,new VariableNode());
        EditorController ec = new EditorController(this);
        view = new View(cc.getCanvas(),ec.getEditor());
        generateTree("");

    }
    public void generateTree(String formula){
        Tree tree = Tree.parse(formula);
        this.root = tree.getRoot();
        Ranger[] rgs = new Ranger[tree.getVariables().length-1];
        int j=0;
        for (VariableNode i:tree.getVariables()){
            if (j==0)cc.setVariable(i);
            else{

                i.changeValue(0.0);
                rgs[j-1] = new RangerController(this,i).getRanger();
            }
            System.out.println(i.getParents().length);
            j++;
        }
        view.setRangers(rgs);
        refresh();

    }
    public Double calc(){
        return root.calc();
    }
    public View getView(){
        return view;
    }
    public void refresh(){
        cc.plot();
    }

    public static Node[] arr(Node... a){
        return a;
    }
    public static VariableNode[] arr(VariableNode... a){
        return a;
    }
}
