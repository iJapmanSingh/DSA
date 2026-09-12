class Solution {
    class Interval{
        int start ;
        int end ; 
        int weight ;
        int originalIndex ;
        Interval(int start , int end , int weight , int originalIndex){
            this.start = start ;
            this.end = end ;
            this.weight = weight ;
            this.originalIndex = originalIndex ;
        }

    }

    class Result{
        long weight ;
        List<Integer> indices ;
        Result(long weight , List<Integer> indices){
            this.weight = weight ;
            this.indices = indices ;
        }
    }

    Result[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] intervals2 = new Interval[n];
        //map to custom class to retain original indexes
        for(int i = 0; i < n ; i++){
            List<Integer> current = intervals.get(i);
            intervals2[i] = new Interval(current.get(0) , current.get(1), current.get(2) , i);
        }
        //sort by start time for binary search
        Arrays.sort(intervals2, (a , b) -> Integer.compare(a.start , b.start));
        dp = new Result[n][5];
        Result best = f(0 , 0 ,intervals2);
        //format output 
        List<Integer> ansList = best.indices ;
        Collections.sort(ansList);
        int[] ans = new int[ansList.size()];
        for(int i = 0 ; i < ansList.size() ; i++){
            ans[i] = ansList.get(i);
        }
        return ans ;
    }
    
    public Result f(int index , int count ,  Interval[] intervals){
        int n = intervals.length;
        if(index == n || count == 4){
            return new Result(0 , new ArrayList<>()) ;
        }
        if(dp[index][count] != null) return dp[index][count];

        //skip
        Result skip = f(index + 1 , count , intervals);
        //take
        //find next valid interval using binary search 
        int nextIndex = findNext(intervals , index); 
        Result nextTake = f(nextIndex , count + 1 , intervals);

        //build new list of indices
        List<Integer> takeIndices = new ArrayList<>();
        takeIndices.add(intervals[index].originalIndex);
        takeIndices.addAll(nextTake.indices);
        
        //create new take result 
        Result take = new Result(intervals[index].weight + nextTake.weight , takeIndices);

        //tie breaking comparison
        dp[index][count] = getBestResult(skip , take);
        return dp[index][count];
    }

    //binary search logic 
    private int findNext(Interval[] intervals, int currentIndex) {
        int target = intervals[currentIndex].end;
        int left = currentIndex + 1;
        int right = intervals.length - 1;
        int ans = intervals.length; // Default to out of bounds

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid].start > target) {
                ans = mid;
                right = mid - 1; // Try to find an earlier one
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    //resolves weights and lexicographical tie-breaks
    private Result getBestResult(Result skip, Result take) {
        if (take.weight > skip.weight) {
            return take;
        } 
        if (skip.weight > take.weight) {
            return skip;
        }
        
        // If weights are perfectly tied, we must return the lexicographically smaller list
        List<Integer> listTake = new ArrayList<>(take.indices);
        List<Integer> listSkip = new ArrayList<>(skip.indices);
        
        Collections.sort(listTake);
        Collections.sort(listSkip);
        
        // Compare element by element
        for (int i = 0; i < Math.min(listTake.size(), listSkip.size()); i++) {
            if (!listTake.get(i).equals(listSkip.get(i))) {
                return listTake.get(i) < listSkip.get(i) ? take : skip;
            }
        }
        
        // If one is a prefix of the other, the shorter one is lexicographically smaller
        return listTake.size() < listSkip.size() ? take : skip;
    }
}
