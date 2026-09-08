class Solution {
    public int reverse(int x) {
        int number = x ;
        if(number < 0){
            number = Math.abs(number);
        }
        int result = 0 ;
        while(number > 0){
            int temp = number % 10 ;
            // Check overflow before result * 10 + digit
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && temp > 7)) {
                return 0;
            }

            if (result < Integer.MIN_VALUE / 10 ||
                (result == Integer.MIN_VALUE / 10 && temp < -8)) {
                return 0;
            }
            result = result * 10 + temp ;
            number /= 10 ;
        }
        if(x < 0){
            return -result ;
        }
        return result ; 
    }
}