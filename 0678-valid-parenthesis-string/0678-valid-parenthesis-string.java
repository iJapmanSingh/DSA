class Solution {
    public boolean checkValidString(String s) {
        int min = 0 ; 
        int max = 0 ; 
        int n = s.length();

        for(int i = 0 ; i < n ; i++){
            if(s.charAt(i) == '('){
                min++ ;
                max++ ;
            }else if(s.charAt(i) == ')'){
                min-- ;
                max-- ;
            }else{
                min-- ;
                max++ ;
            }
            if(max < 0) return false ;
            if(min < 0) min = 0 ;

        }

        return (min == 0) ;
    }
}