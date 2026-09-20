class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            int value = 26 - (s.charAt(i) - 'a');
            count += (int) value * (i + 1);
        }

        return count;
    }
}