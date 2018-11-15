
// @author yungene
// problem: 97. Interleaving String - hard
// runtime complexity = O(s3.length*s1.length)
public class Solution {
    public static boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length()+1;
        int len2 = s2.length()+1;
        int len3 = s3.length()+1;
        if(len1-1 + len2-1 != len3-1)return false;
        boolean[][] arr = new boolean[len1][len2];
        arr[0][0] = true;
        for(int c3=1;c3<len3;c3++){
            
            //for(int c1=0;c1<len1 && c1 <= c3;c1++){
            int s = c3-len2 > 0?c3-len2:0;
            for(int c1=s;c1<len1 && c1 <= c3;c1++){
                int c2 = c3 - c1;
                if(c2 >= len2){
                    continue;
                }
                boolean res = false;
                if(c1 > 0){
                    res = res || ((s1.charAt(c1-1)==s3.charAt(c3-1)) &&arr[c1-1][c2]);
                }
                if(c2 > 0){
                     res =res || ((s2.charAt(c2-1)==s3.charAt(c3-1)) && arr[c1][c2-1]);
                }
                arr[c1][c2] = res;
                    
            }
        }
        return arr[len1-1][len2-1];
        
    }
    
    
}
