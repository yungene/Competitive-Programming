//Problem        : 2016 Qualifiers - Lannisters of Justice
//Language       : Java
//Compiled Using : javac
//Version        : Java 1.8
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

import java.util.Scanner;
//Your submission should *ONLY* use the following class name
//@author yungene
// status - accepted
public class LannistersOfJustice
{
    
    static class Node{
        Node left, right;
        String data;
        boolean isOp;
        Node(String data, boolean isOp){
            this.data = data;
            left = right = null;
            this.isOp = isOp;
        }
    }
    public static void main(String[] args)
    {

       Scanner reader = new Scanner(System.in);
        String expr = reader.nextLine();
        int len = expr.length();
        //O(n^2) brute force solution - go from left to right and try to 
        //evaluate one operator at a time
        //can use doubly linked lists for that
        
        //build a parse tree in O(n^2) and then evalute it in O(n)?
        Node root = parse(expr);
        //now I have an expression tree
        int res = evaluate(root);
        System.out.println(res);
    }
    
    static Node parse(String expr){
        if(expr.length() <= 0){
            return null;
        }
        
        int splitPos = -1;
        int splitP = 0;
        
        for(int i = 0; i < expr.length(); i++){
            if(!isNumber(expr.charAt(i))){
                //right most gets evaluated last
                if(priority(expr.charAt(i)) >= splitP){
                    splitP = priority(expr.charAt(i));
                    splitPos = i;
                }
            }
        }
        Node res = null;
        if(splitPos != -1){
            res = new Node(expr.substring(splitPos,splitPos+1),true);
            res.left = parse(expr.substring(0,splitPos));
            res.right = parse(expr.substring(splitPos+1,expr.length()));
        }else{
            res = new Node(expr,false);
        }
        return res;
    }
    
    static int evaluate(Node root){
        if(root.isOp){
            int l = evaluate(root.left);
            int r = evaluate(root.right);
            return evaluate(root.data.charAt(0),l,r);
        }else{
            return new Integer(root.data);
        }
    }
    
    static int evaluate(char op, int n1, int n2){
        switch(op){
            case '-': return n1-n2;
            case '/': return n1/n2;
            case '+': return n1+n2;
            case '*': return n1*n2;
            default: return 0;
        }
    }
    
    static int priority(char c){
        switch(c){
            case '-': return 10;
            case '/': return 15;
            case '+': return 23;
            case '*': return 31;
            default: return 0;
        }
    }
    
    static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }
    
    //static boolean isOp(String )

}
