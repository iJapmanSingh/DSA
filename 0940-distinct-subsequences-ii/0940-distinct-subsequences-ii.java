class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        long[] endsWith = new long[26];
        long total = 0 ;
        long mod = 1000000007 ;
        
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            long newCount = (total + 1) % mod ;
            long oldCount = endsWith[idx];
            total = (total + newCount - oldCount + mod) % mod ;
            endsWith[idx] = newCount ;
        }
        return (int) total ;
    }
}