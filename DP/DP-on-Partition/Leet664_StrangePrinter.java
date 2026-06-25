class Solution {
    int[][] dp ;
    public int strangePrinter(String s) {
        int n = s.length();
        dp = new int[n][n] ;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                dp[i][j] = -1 ;
            }
        }
        return f(0 , n-1 , s);
    }
    public int f(int i , int j , String s){
        if(i > j) return 0 ;
        if(i == j) return 1 ;

        if(dp[i][j] != -1) return dp[i][j] ;

        int ans = f(i , j-1 , s) + 1 ;

        for(int k = i ; k < j ; k++){
            if(s.charAt(k) == s.charAt(j)){
                ans = Math.min(ans , f(i , k , s) + f(k+1 , j-1 , s));
            }
        }
        return dp[i][j] = ans ;
    }
}



/*
Algorithm (Interval DP)

1. solve(i, j) = minimum turns required to print substring s[i...j].

2. Base Case:
   - If i > j  -> 0 turns.
   - If i == j -> 1 turn.

3. Assume the worst case:
   - Print s[j] in a completely new turn.
   - Therefore,
         ans = solve(i, j - 1) + 1

4. Now try to merge the printing of s[j] with some previous
   occurrence of the same character.

5. Iterate k from i to j-1.

6. If s[k] == s[j]:
      Instead of printing s[j] separately,
      print s[k] and s[j] in the same turn.

      Left Part  : solve(i, k)
      Middle Part: solve(k + 1, j - 1)

      Candidate Cost:
          solve(i, k) + solve(k + 1, j - 1)

7. Take the minimum over all such k.

8. Memoize and return the answer.

Time Complexity  : O(n^3)
Space Complexity : O(n^2)
*/