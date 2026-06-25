/*
=========================================================
APPROACH (How I derived this recurrence)
=========================================================

Observation 1:
---------------
We are solving an interval [i...j].

f(i, j, k) =
Maximum score obtainable from boxes[i...j],
assuming boxes[i] already belongs to a group of size 'k'
(including itself).

Initially,
f(0, n-1, 1)

---------------------------------------------------------

Observation 2:
---------------
For the first box (boxes[i]), I have only TWO choices.

Choice 1)
----------
Remove it immediately.

Current group size = k

Score obtained = k * k

Remaining interval starts fresh because the next color has
nothing to do with the removed color.

Transition:

k*k + f(i+1, j, 1)

---------------------------------------------------------

Choice 2)
----------
Don't remove boxes[i] now.

Instead, try to merge it with every future box having the
same color.

Loop through every index m:

for(m = i+1 ... j)

If boxes[m] == boxes[i],

remove everything between them first

        i      m
        |------|
A A B C A D

Need to remove

B C

so that

A A

become adjacent.

Removing the middle gives

f(i+1, m-1, 1)

After merging,

the box at index m now belongs to a group of size k+1
(previous group size k + current box).

Continue recursion from m:

f(m, j, k+1)

Transition:

f(i+1, m-1, 1)
+
f(m, j, k+1)

Take maximum over all possible matching indices.

---------------------------------------------------------

Base Cases:
------------
1. Empty interval:
   i > j
   return 0

2. One box left:
   Group size = k
   Score = k*k

---------------------------------------------------------

Time Complexity:
----------------
Recursion  : Exponential
Memoization: O(n^4)
State       : (i, j, k)

=========================================================
MENTAL MODEL
=========================================================

At every interval, ask only one question:

"What should I do with the FIRST box?"

Option 1:
Remove it immediately.

Option 2:
Merge it with every future equal-colored box.

The second option requires removing everything between
them first, then continuing with a larger group (k+1).

This single idea generates the entire recurrence.
=========================================================
*/




class Solution {
    int[][][] dp ;
    public int removeBoxes(int[] boxes) {
        int n = boxes.length ;
        dp = new int[n][n][n+1] ;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j <n ;j++){
                for(int k = 0 ; k<=n ; k++){
                    dp[i][j][k] = -1 ;
                }
            }
        }
        return f(0 , n-1 , 1 , boxes);
    }
    public int f(int i , int j , int k , int[] boxes){
        if(i > j) return 0 ;

        if(i == j) return k*k ;

        if(dp[i][j][k] != -1) return dp[i][j][k] ;

        int ans = k*k + f(i+1 , j , 1 , boxes);

        for(int m = i+1 ; m <= j ; m++){
            if(boxes[i] == boxes[m]){
                ans = Math.max(ans , f(i+1 , m-1 , 1 , boxes) + f(m , j , k+1 , boxes)) ;
            }
        }
        return dp[i][j][k] = ans ;
    }
}



/*
INTERVAL DP PATTERN USED HERE

For every interval [i...j]:

1. Focus only on the FIRST element.

2. Either
   -> consume/remove it now

3. Or
   -> merge it with every future equal element

4. If merging,
   first solve the middle interval,
   then recurse with a larger group.

Whenever an interval DP involves "equal values becoming
adjacent after removing the middle", think of this pattern.

Examples:
- Remove Boxes
- Strange Printer
*/


/*

1. What does dp(i, j) represent?

2. If I look only at the FIRST element,
   what decisions can I make?

3. Can I remove it immediately?

4. Can I postpone removing it and merge it with a future
   equal element?

5. If I merge,
   what interval must disappear first?

6. After merging,
   what extra information must I carry?
   (Here it was k.)

7. Write the recurrence.

*/