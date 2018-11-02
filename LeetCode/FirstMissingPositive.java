// @author yungene
//Complexity = O(n)
class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int size = nums.length;
        int m = 1;
        
        //e.g. put 1 into nums[0];
        // and put old value at num[0] into num[i] = num[1] in this case
        // however, if nums[0] != 
        for(int i = 0 ; i < size; i++){
            int value = nums[i];
            int valuePos = value-1;
            if(valuePos != i && value > 0 && valuePos < size){
                //swap nums[i] and nums[valuePos]
                int temp = nums[valuePos];
                nums[valuePos] = value; //at the correct position now
                nums[i] = temp;
                //
                if(nums[valuePos]  != temp){    //avoid infinite loops such as in [1,1]
                    i--;
                }
            }
        }
        
        //look for first number that is missing
        for(int i = 0 ;i < size; i++){
            if(i+1 != nums[i]){
                return i+1;
            }
        }
        // case 1: empty array
        if(size == 0)return 1;
        // case 2 : all number withnin range of [1,size] are present
        return size+1;

        
    }
}
