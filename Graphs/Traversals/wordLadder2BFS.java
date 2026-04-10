/*
    NOTE : this solution will give TLE 
            this is just for understanding

    This is BFS approach 
    👉 Use BFS to explore level by level
    👉 Store complete paths instead of nodes

    ALGORITHM -
    
     Put all words into a Set for fast lookup
     Initialize Queue of paths and add [beginWord]
     Create usedOnLevel list and set level = 0

     Start BFS:
       Take one path from queue
       Get the last word of the path

       If path.size() > level:
           Increase level
           Remove all words in usedOnLevel from set
           Clear usedOnLevel

       If last word == endWord:
           If answer is empty → add path
           Else if same length → add path

       For each character position in word:
           Try replacing with 'a' → 'z'
           If new word exists in set:
               Create new path and add word
               Push new path into queue
               Add word to usedOnLevel

     Repeat until queue becomes empty
*/



class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<>();

        HashSet<String> set = new HashSet<>(wordList);

        Queue<ArrayList<String>> queue = new LinkedList<>();
        ArrayList<String> start = new ArrayList<>();

        start.add(beginWord);
        queue.add(start);

        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);

        int level = 0 ;

        while(!queue.isEmpty()){
            ArrayList<String> path = queue.poll();

            if(path.size() > level){
                level++ ;

                for(String word : usedOnLevel){
                    set.remove(word);
                }
                usedOnLevel.clear();
            }
            String word = path.get(path.size() - 1);
            if(word.equals(endWord)){
                if(ans.size() == 0){
                    ans.add(path);
                }else if(ans.get(0).size() == path.size()){
                    ans.add(path);
                }
            }
            char[] arr = word.toCharArray();

            for(int i = 0 ; i< arr.length; i ++){
                char original = arr[i];
                for(char ch = 'a' ; ch <= 'z' ; ch++){
                    arr[i] = ch ;
                    String newWord = new String(arr);

                    if(set.contains(newWord)){
                        ArrayList<String> newPath = new ArrayList<>(path);
                        newPath.add(newWord);

                        queue.add(newPath);
                        usedOnLevel.add(newWord);
                    }
                }
                arr[i] = original ;
            }
        }
        return ans ;
    }
}