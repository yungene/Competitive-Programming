//Problem        : 2016 Qualifiers - A Game of Thrones
//Language       : Java
//Compiled Using : javac
//Version        : Java 1.8
//Input for your program will be provided from STDIN
//Print out all output from your program to STDOUT

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
//Your submission should *ONLY* use the following class name
//status: accepted
public class AGameOfThrones
{
    
    static class House{
        String name;
        int swornKings;
        int bannermenCount;
        HashSet< House> bannermen;
        House parent;
        
        House(String name, int swornKings, int bC){
            this.name = name;
            this.swornKings=swornKings;
            this.bannermenCount=bC;
            bannermen = new HashSet<House>();
            parent = null;
        }
        
        boolean equals(House that){
            return this.name.equals(that.name);
        }
    }
    public static void main(String[] args)
    {

       Scanner reader = new Scanner(System.in);
        int T = reader.nextInt();
        HashMap<String, House> houses = new HashMap<>();
        House root = null;
        for(int t = 0; t < T; t++){
            String name = reader.next();
            int sK = reader.nextInt();
            int bm = reader.nextInt();
            House nh = new House(name,sK,bm);
            for(int b = 0; b < bm; b++){
                String child = reader.next();
                House ch = houses.get(child);
                ch.parent = nh;
                nh.bannermen.add(ch);
            }
            houses.put(name,nh);
            root = nh;
        }
        int height = height(root);
        //for(int i = 0; i < h; i++){
            solve(root);
        //}
        //print in bfs manner
          PriorityQueue<House> queue = new PriorityQueue<>(10,new Comparator<House>() {
              public int compare(House n1, House n2) {
                return n1.name.compareTo(n2.name);
              }
  });
          PriorityQueue<House> children = new PriorityQueue<>(10,new Comparator<House>() {
              public int compare(House n1, House n2) {
                return n1.name.compareTo(n2.name);
              }
  });
        queue.add(root);
        while(!queue.isEmpty()){
            
            while(!queue.isEmpty()){
                House h = queue.poll();
                System.out.print(h.name + " ");
                for(House ch: h.bannermen){
                    children.add(ch);
                }
            }
            System.out.println();
            queue = children;
            children = new PriorityQueue<>(10,new Comparator<House>() {
            public int compare(House n1, House n2) {
              return n1.name.compareTo(n2.name);
            }
});
        }
    }
    
    static void solve(House root){
        
        if(root.bannermenCount == 0){
            return;
        }
        
        for(House nm: root.bannermen){
            solve(nm);
        }
        
        //find the potetntial house to ovethrow
        House max = null;
        
        for(House h: root.bannermen){
            if(max == null || h.swornKings >= max.swornKings){
                if(max != null&& h.swornKings == max.swornKings){
                    int cmp = h.name.compareTo(max.name);
                    if(cmp < 0){
                        max = h;
                    }
                }else{
                    max = h;
                }
            }
        }
        
        if( max.swornKings - root.swornKings >= 3){
            House oldroot = root;
            String name = root.name;
            int swornKings = root.swornKings;
            int bannermenCount = root.bannermenCount;
            HashSet<House> bannermen = root.bannermen;
            House parent = root.parent;
            
            root.name = max.name;
            root.swornKings = max.swornKings;
            
            max.name = name;
            max.swornKings = swornKings;
            
        }
        
        
        
    }
    
    static int height(House root){
        if(root.bannermenCount == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for(House h: root.bannermen){
            max = Math.max(max,height(h));
        }
        return max + 1;
    }

}
