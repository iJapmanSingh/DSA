class Solution {
    public int minOperations(String s) {
        int miniStep = Integer.MAX_VALUE ; 
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            int cost = 0 ;
            for (int low = 0, high = n - 1; low < high; low++, high--) {
                char a = s.charAt(low);
                char b = s.charAt(high);
            
                if (a != b) {
                    int d1 = Math.abs(a - b);
                    int d2 = 26 - d1;
                    
                    cost += Math.min(d1, d2);
                }
            }
            miniStep = Math.min(cost + i , miniStep);
            s = s.substring(1) + s.charAt(0);
        }
        return miniStep ;
    }
}