/*
1. Put all words from wordList into a HashSet
2. If endWord is not in set:
      return 0
3. Create a Queue for BFS and add beginWord
4. Initialize level = 1
5. Start BFS:
6. While queue is not empty:
      6.1 Get current size of queue (level size)
      6.2 For each word in current level:
            6.2.1 Pop word from queue
            6.2.2 If word == endWord:
                       return level
            6.2.3 Try all transformations:
                  - Convert word to char array
                  - For each position in word:
                        - Try replacing with 'a' to 'z'
                        - Form new word
                        - If new word exists in set:
                              - Add new word to queue
                              - Remove new word from set
      6.3 After processing current level:
            level++
7. If BFS ends and no answer:
      return 0
*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //making hashset containing word list because lookup is easy O(1)
        HashSet<String> set = new HashSet<>(wordList);

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1 ;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0 ; i<size ; i++){
                String word = queue.poll();
                if(word.equals(endWord)){
                    return level;
                }
                for(int j = 0 ; j<word.length() ; j++){
                    char[] arr= word.toCharArray();          // h i t -> array

                    for(char ch = 'a' ; ch<= 'z' ; ch++){
                        arr[j] = ch ;           // array[0] = ch ,   h i t -> a i t , array[1] = ch , a i t -> b i t
                        String newWord = new String(arr); // new word = ait 

                        if(set.contains(newWord)){ // check if newword ( ait ) exists in set , if not try other character from a to z
                            queue.add(newWord);
                            set.remove(newWord);
                        }
                    }
                }
            }
            level++ ;
        }
        return 0 ;
    }
}