class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean allEven = true ;
        boolean allOdd = true ;
        int n = nums1.length ;
        for(int i = 0 ; i < n ; i++){
            if(nums1[i] % 2 != 0){
                allEven = false ;
            }
            if(nums1[i] % 2 == 0){
                allOdd = false ;
            }
        }
        if(allEven || allOdd) return true ;
        int smallestEven = Integer.MAX_VALUE ;
        int smallestOdd = Integer.MAX_VALUE ;
        for(int i = 0; i < n ; i++){
            if(nums1[i] % 2 == 0){
                smallestEven = Math.min(smallestEven , nums1[i] );
            }
            if(nums1[i] % 2 != 0){
                smallestOdd = Math.min(smallestOdd , nums1[i]);
            }
        }
        if(smallestOdd < smallestEven){
            return true ;
        }
        return false ;
    }
}