// ---------------------------------- ALGORITHM ----------------------------------

/*
Goal:
Find the Shortest Common Supersequence (SCS) of two strings.

Key Observation:
SCS contains both strings as subsequences.
To make the supersequence shortest, we should avoid repeating the common part.

So:
1. First find the Longest Common Subsequence (LCS)
2. Then build the answer using the LCS table.

Why LCS helps?
Common characters should appear only once in the final answer.
Non-common characters must be added separately.

-------------------------------------------------------------------------------

STEP 1: Build LCS DP Table

dp[i][j] = length of LCS between:
text1[0...i-1] and text2[0...j-1]

Transition:

If characters match:
dp[i][j] = 1 + dp[i-1][j-1]

Else:
dp[i][j] = max(dp[i-1][j], dp[i][j-1])

-------------------------------------------------------------------------------

STEP 2: Reconstruct the Shortest Common Supersequence

Start from dp[n][m] and move backwards.

Case 1:
If characters are equal:
- Add character only once
- Move diagonally (i--, j--)

Case 2:
If dp[i-1][j] > dp[i][j-1]:
- Character from text1 is not common
- Add text1[i-1]
- Move up (i--)

Case 3:
Else:
- Character from text2 is not common
- Add text2[j-1]
- Move left (j--)

-------------------------------------------------------------------------------

STEP 3: Add remaining characters

If one string finishes earlier,
append all remaining characters of the other string.

-------------------------------------------------------------------------------

STEP 4:
Since we built the string from back to front,
reverse the final answer.

-------------------------------------------------------------------------------

Time Complexity:
O(n * m)

Space Complexity:
O(n * m)

*/



class Solution {
    public String shortestCommonSupersequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        // LCS table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = n;
        int j = m;

        StringBuilder ans = new StringBuilder();

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                ans.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                ans.append(text1.charAt(i - 1));
                i--;
            } else {
                ans.append(text2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            ans.append(text1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            ans.append(text2.charAt(j - 1));
            j--;
        }

        return ans.reverse().toString();
    }
}
