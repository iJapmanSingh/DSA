class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int[] cell : reservedSeats) {
            int row = cell[0];
            int seat = cell[1];
            if (!map.containsKey(row)) {
                map.put(row, new int[11]);
            }
            map.get(row)[seat] = 1;
        }
        int ans = 2 * n;
        for (int[] seats : map.values()) {
            boolean left = seats[2] == 0 && seats[3] == 0 && seats[4] == 0 && seats[5] == 0;
            boolean middle = seats[4] == 0 && seats[5] == 0 && seats[6] == 0 && seats[7] == 0;
            boolean right = seats[6] == 0 && seats[7] == 0 && seats[8] == 0 && seats[9] == 0;
            if (left && right) {
                // This row can still fit 2.
                // Nothing to subtract.
            }
            else if (left || middle || right) {
                // This row can fit only 1 instead of 2.
                ans--;
            }
            else {
                // This row cannot fit any family.
                ans -= 2;
            }
        }
        return ans;
    }
}