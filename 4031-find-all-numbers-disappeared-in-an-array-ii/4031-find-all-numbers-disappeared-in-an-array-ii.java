class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {

        List<Integer> list = new ArrayList<>();
        List<List<Integer>> answer = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Make list from lower to upper
        for (int i = lower; i <= upper; i++) {
            if (!set.contains(i)) {
                list.add(i);
            }
        }

        if (list.isEmpty()) {
            return answer;
        }

        int start = list.get(0);

        for (int i = 1; i < list.size(); i++) {

            int previous = list.get(i - 1);
            int current = list.get(i);

            if (current != previous + 1) {

                List<Integer> range = new ArrayList<>();
                range.add(start);
                range.add(previous);

                answer.add(range);

                start = current;
            }
        }

        int last = list.get(list.size() - 1);

        List<Integer> range = new ArrayList<>();
        range.add(start);
        range.add(last);

        answer.add(range);

        return answer;
    }
}



// class Solution {
//     public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
//         List<Integer> list = new ArrayList<>();
//         for(int i = lower ; i <= upper ; i++){
//             list.add(i);
//         }
//         for(int num : nums){
//             if(list.contains(num)){
//                 list.remove(Integer.valueOf(num));
//             }
//         }

//         List<List<Integer>> answer = new ArrayList<>();
//         if(list.size() == 0){
//             return answer ;
//         }
//         int start = list.get(0);
//          for (int i = 1; i < list.size(); i++) {
//             int previous = list.get(i - 1);
//             int current = list.get(i);
//             // Not consecutive -> previous range ends
//             if (current != previous + 1) {
//                 List<Integer> range = new ArrayList<>();
//                 range.add(start);
//                 range.add(previous);
//                 answer.add(range);
//                 // Start a new range
//                 start = current;
//             }
//         }
//         // Add the final range
//         int last = list.get(list.size() - 1);

//         List<Integer> range = new ArrayList<>();
//         range.add(start);
//         range.add(last);

//         answer.add(range);

//         return answer;
//     }
// }