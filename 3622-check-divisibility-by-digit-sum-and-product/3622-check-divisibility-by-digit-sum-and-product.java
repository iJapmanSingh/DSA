class Solution {
    public boolean checkDivisibility(int n) {
        int digitSum = 0 ;
        int original = n ;
        int digitProduct = 1 ;
        while(original > 0){
            int digit = original%10 ;
            digitSum += digit ;
            digitProduct *= digit ;
            original = original/10 ;
        }
        int total = digitSum + digitProduct ;
        if(n%total == 0) return true ;
        return false ;
    }
}