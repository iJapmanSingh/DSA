// class Solution {
//     public boolean isMatch(String s, String p) {
//         int n = s.length();
//         int m = p.length(); 

//         return memoization(n-1 , m-1 , s , p);
//     }
//     public boolean memoization(int j , int i , String s , String p){
//         //base cases
//         if(i < 0 && j < 0) return true ;
//         if(i < 0 && j >= 0) return false ;
//         if(j < 0 && i >= 0){
//             for(int k = 0 ; k <= i ; k++){
//                 if(p.charAt(k) != '*'){
//                     return false ;
//                 }
//             }
//             return true; 
//         }
//         //case 1 
//         if(p.charAt(i) == s.charAt(j) || p.charAt(i) == '?'){
//             return memoization(j-1 , i-1 , s , p);
//         }

//         //case 2 
//         if(p.charAt(i) == '*'){
//             return memoization(j-1 , i , s , p) || memoization(j , i-1 , s , p) ;
//         }
//         return false ;
//     }
// }


// memoization case -----------------------------------------------------------------------------------------

class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        Boolean[][] dp = new Boolean[m][n] ;

        return memoization(n-1 , m-1 , s , p , dp);
    }
    public boolean memoization(int j , int i , String s , String p , Boolean[][] dp){
        //base cases
        if(i < 0 && j < 0) return true ;
        if(i < 0 && j >= 0) return false ;
        if(j < 0 && i >= 0){
            for(int k = 0 ; k <= i ; k++){
                if(p.charAt(k) != '*'){
                    return false ;
                }
            }
            return true;
        }
        if(dp[i][j] != null ) return dp[i][j] ;
        //case 1 
        if(p.charAt(i) == s.charAt(j) || p.charAt(i) == '?'){
            return dp[i][j] = memoization(j-1 , i-1 , s , p , dp);
        }

        //case 2 
        if(p.charAt(i) == '*'){
            return dp[i][j] = memoization(j-1 , i , s , p , dp) || memoization(j , i-1 , s , p , dp) ;
        }
        return dp[i][j] = false ;
    }
}
