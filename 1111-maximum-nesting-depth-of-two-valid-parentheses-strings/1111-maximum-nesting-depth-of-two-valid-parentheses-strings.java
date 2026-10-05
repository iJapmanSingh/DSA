class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        Stack<char[]> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int n = seq.length();
        for(int i = 0 ; i < n ; i++){
            char ch = seq.charAt(i);
            if(stack.isEmpty()){
                stack.push(new char[]{ch , '0'});
                ans.add(0);
                continue ;
            }
            char[] seen = stack.peek() ;
            if(ch == seen[0]){
                if(seen[1] == '0'){
                    stack.push(new char[]{ch , '1'});
                    ans.add(1);
                }else{
                    stack.push(new char[]{ch , '0'});
                    ans.add(0);
                }
            }
            else if(ch != seen[0]){
                char[] popy = stack.pop() ;
                if(popy[1] == '0'){
                    ans.add(0);
                }else{
                    ans.add(1);
                }
            }
        }

        int[] arr = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }

        return arr;
    }
}