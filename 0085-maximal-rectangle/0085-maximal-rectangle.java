class Solution {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length ;
        int m = matrix[0].length ;

        int[] height = new int[m] ;
        int maxArea = 0 ;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(matrix[i][j] == '1') height[j]++ ;
                else height[j] = 0 ;
            }

            int area = largestRectangleArea(height);
            maxArea = Math.max(maxArea , area) ;
        }
        return maxArea ;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int element = stack.peek();
                stack.pop();
                int nse = i;
                int pse = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
            }
            stack.push(i);

        }
        while (!stack.isEmpty()) {
            int nse = heights.length;
            int element = stack.peek();
            stack.pop();
            int pse = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
        }
        return maxArea;

    }
}