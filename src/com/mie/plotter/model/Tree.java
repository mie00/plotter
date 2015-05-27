package com.mie.plotter.model;

import com.mie.plotter.model.node.Node;
import com.mie.plotter.model.node.NumberNode;
import com.mie.plotter.model.node.VariableNode;
import com.mie.plotter.model.node.FunctionNode;
import com.mie.plotter.model.node.RootNode;
import com.mie.plotter.model.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree{
    final private RootNode root;
    final private VariableNode[] variables;;
    public static Tree parse(String formula){
        if (formula.trim().isEmpty()){
            return new Tree(new RootNode(new NumberNode(0.0)),new VariableNode());
        }
        formula = formula.toLowerCase();
        System.out.println("Formula-01:"+formula);
        ArrayList<Node> nodes = new ArrayList<Node>();
        ArrayList<VariableNode> variables = new ArrayList<VariableNode>();
        int index=0;
        ArrayList<String> reservedNames = getReservedNames();
        Pattern p = Pattern.compile("\\([^(),]+\\)");
        Matcher m = p.matcher(formula);
        /*
        //Parentheses hadnling
        while (m.find()){

        Tree t = Tree.parse(m.group(0).substring(1,m.group(0).length()-1));
        nodes.add(t.getRoot().getArg());
        variables.addAll(t.getVariablesAsList());
        formula = formula.replace(m.group(0),"S"+index);
        index++;
        m = p.matcher(formula);
        }

        System.out.println("Formula-02:"+formula);
        //Argument Extractor
        p = Pattern.compile("\\([^()]\\)");
        m = p.matcher(formula);
        while (m.find()){
        for (String sub: m.group(0).substring(1,m.group(0).length()-1).split(",")){

        Tree t = Tree.parse(sub);
        nodes.add(t.getRoot().getArg());
        variables.addAll(t.getVariablesAsList());
        formula = formula.replace(sub,"S"+index);
        index++;
        }
        m = p.matcher(formula);
        }
        System.out.println("Formula-03:"+formula);
         */

        //Number Extractor
        p = Pattern.compile("\\b(?:\\d*\\.\\d*|\\d+)\\b");
        m = p.matcher(formula);
        while (m.find()){
            nodes.add(new NumberNode(new Double(m.group(0))));
            formula = replace(m.start(),m.end(),formula,"S"+index);
            index++;
            m = p.matcher(formula);
        }
        System.out.println("Formula-04:"+formula);
        //Variable extractor
        p = Pattern.compile("\\b[a-z_][a-z_0-9]*\\b");
        m=p.matcher(formula);
        while (m.find()){
            if (reservedNames.contains(m.group(0)))
                continue;
            VariableNode v = new VariableNode();
            nodes.add(v);
            if (m.group(0).equals("x"))
                variables.add(0,v);
            else
                variables.add(v);
            formula = formula.replace(m.group(0),"S"+index);
            index++;
            m = p.matcher(formula);
        }
        System.out.println("Formula-05:"+formula);
        Pattern[] ps = getFunctionsPattern();
        Pattern vars = Pattern.compile("S(\\d+)");
        //Function extractor
        for (int i=0,j=ps.length;i<j;i++){
            Matcher m3=ps[i].matcher(formula);
            if (m3.find()){
                Matcher m2 = vars.matcher(m3.group(0));
                ArrayList<Node> args = new ArrayList<Node>();
                while (m2.find()){
                    args.add(nodes.get(new Integer(m2.group(1))));
                }
                Node[] args2=args.toArray(new Node[args.size()]);
                try{
                    nodes.add(new FunctionNode((Function)Class.forName("com.mie.plotter.model.function."+capitalize(reservedNames.get(i))).getConstructor().newInstance(),args2));
                }
                catch (Exception e){
                    System.out.println("class not found");
                    System.out.println(e.getMessage());
                    return null;
                }
                formula = m3.replaceFirst("S"+index);
                index++;
                for (Node i3 : args2){
                    i3.addParent(nodes.get(index-1));
                }
                i=-1;
                System.out.println("Formula-06:"+formula);
            }
        }

        for (Node i3 : nodes){
            i3.parentsFinished();
        }
        System.out.println("Formula-07:"+formula);
        m =Pattern.compile("\\d+").matcher(formula);
        m.find();
        return new Tree(new RootNode(nodes.get(new Integer(m.group(0)))),variables.toArray(new VariableNode[variables.size()]));
    }
    protected Tree(RootNode root,VariableNode... variables){
        this.root = root;
        this.variables = variables;
    }
    private static String capitalize(final String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    public static String[] getPatterns(){
        String[] a ={"sin#","cos#","#\\*#","#\\+#"};
        return a;
    }
    public static ArrayList<String> getReservedNames(){
        String[] p = getPatterns();
        String[] a = new String[p.length];
        for(int i=0,j=p.length;i<j;i++)
            a[i]=getNameFromPattern(p[i]);
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(a));
        return arrayList;
    }
    public static String getNameFromPattern(String pattern){
        if (pattern.equals("#\\+#"))
            return "add";
        else if (pattern.equals("#\\*#"))
            return "mult";
        else{
            Matcher m = Pattern.compile("[a-z_][a-z_0-9]+").matcher(pattern);
            m.find();
            return m.group(0);
        }
    }
    public static Pattern[] getFunctionsPattern(){
        String[] pt = getPatterns();
        Pattern[] ps = new Pattern[pt.length];
        for (int i=0,j=pt.length;i<j;i++){
            ps[i] = Pattern.compile(pt[i].replace("#","\\s*(S\\d+|\\((?:S\\d+,?)+\\))\\s*"));
        }
        return ps;
    }
    public static String replace(int start,int end,String text,String replacement){
        return text.substring(0,start)+replacement+text.substring(end);
    }
    public RootNode getRoot(){
        return root;
    }
    public VariableNode[] getVariables(){
        return variables;
    }
    public List<VariableNode> getVariablesAsList(){
        return Arrays.<VariableNode>asList(variables);
    }
}
