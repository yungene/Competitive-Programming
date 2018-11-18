//Authro         : yungene
//Problem        : 2016 Qualifiers - Travel to the West
//Language       : Java
//Compiled Using : javac
//Version        : Java 1.8
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/* Idea is to build a graph and then run a DFS to find all the possible path from JFK to SFO.
 *  Care needs to be taken not to enter into infinite loops.
 */
public class TravelToTheWest
{
    public static void main(String[] args)
    {

       Scanner reader = new Scanner(System.in);
       int n = reader.nextInt();
       Graph g = new Graph();
       //build a graph
       for(int i = 0 ; i < n ; i++){
           String u = reader.next();
           String v = reader.next();
           g.put(u,v);
           //it is undirected
           //g.put(v,u);
       }
       
       //use a wrapper class to save states between the calls and to simplify rectusive returns
       Int res = new Int(0);
       dfs(g, "JFK", "SFO", res, new HashSet<String>());
       
       System.out.println(res.val);
       reader.close();
    }
    
    static class Int{
    	int val;
    	Int(int val){
    		this.val = val;
    	}
    }
    
    static class Graph{
        HashMap<String, ArrayList<String>> adj;
        
        Graph(){
            adj = new HashMap<>();
        }
        
        void put(String u, String v){
            if(!adj.containsKey(u)){
                adj.put(u,new ArrayList<String>());
            }
            adj.get(u).add(v);
        }

    }
    
    static void dfs(Graph g, String curr, String fin, Int res, HashSet<String> stack){
        if(curr.equals(fin)){
            res.val++;
            return;
        }
        stack.add(curr);
        if(!g.adj.containsKey(curr)) {
        	return;
        }
        ArrayList<String> neigh = g.adj.get(curr);
        for (String key : neigh) {
            if(!stack.contains(key)){
                dfs(g, key, fin, res,stack);
            }
        }
        stack.remove(curr);
    }

}


