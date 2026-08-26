class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int len = Integer.MAX_VALUE ;
        String ans = "";
        int left = 0 ;
        int right = 0 ;
        int numberOfOnes = 0 ;
        while(right < n){
            if(s.charAt(right) == '1'){
                numberOfOnes++ ;
            }
            while (numberOfOnes > k || (left <= right && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    numberOfOnes--;
                }
                left++;
            }
            
            if(numberOfOnes == k){
                int currentLen = right - left + 1 ;
                String current = s.substring(left , right + 1);
                if (currentLen < len ||
                    (currentLen == len && current.compareTo(ans) < 0)) {

                    len = currentLen;
                    ans = current;
                }
            }
            right++ ;
        }
        return ans ;
    }
}