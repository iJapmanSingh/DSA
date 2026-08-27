class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < n; i++) {

            char ch = target.charAt(i);

            // Same character available
            if (map.containsKey(ch)) {

                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) {
                    map.remove(ch);
                }

            } else {

                // Try to make current position greater
                String result = makeGreater(target, i, map);

                if (result != null) {
                    return result;
                }

                // Current position doesn't work.
                // Backtrack to previous positions.
                for (int j = i - 1; j >= 0; j--) {

                    char previous = target.charAt(j);

                    // Restore the character at j
                    map.put(previous, map.getOrDefault(previous, 0) + 1);

                    result = makeGreater(target, j, map);

                    if (result != null) {
                        return result;
                    }
                }

                return "";
            }
        }

        // target itself was possible, but we need something greater.
        for (int i = n - 1; i >= 0; i--) {

            char ch = target.charAt(i);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            String result = makeGreater(target, i, map);

            if (result != null) {
                return result;
            }
        }

        return "";
    }

    private String makeGreater(
        String target,
        int index,
        HashMap<Character, Integer> map
    ) {

        char ch = target.charAt(index);

        char greater = 0;
        boolean found = false;

        for (char key : map.keySet()) {

            if (key > ch && (!found || key < greater)) {
                greater = key;
                found = true;
            }
        }

        if (!found) {
            return null;
        }

        map.put(greater, map.get(greater) - 1);

        if (map.get(greater) == 0) {
            map.remove(greater);
        }

        StringBuilder result =
            new StringBuilder(target.substring(0, index));

        result.append(greater);

        // Smallest possible suffix
        for (char key = 'a'; key <= 'z'; key++) {

            if (map.containsKey(key)) {

                int freq = map.get(key);

                while (freq-- > 0) {
                    result.append(key);
                }
            }
        }

        return result.toString();
    }
}










// class Solution {
//     public String lexGreaterPermutation(String s, String target) {
//         int n = s.length();
//         HashMap<Character, Integer> map = new HashMap<>();
//         for (char ch : s.toCharArray()) {
//             map.put(ch, map.getOrDefault(ch, 0) + 1);
//         }
//         StringBuilder ans = new StringBuilder();
//         for (int i = 0; i < n; i++) {
//             char ch = target.charAt(i);
//             // Same character is available
//             if (map.containsKey(ch)) {
//                 ans.append(ch);
//                 map.put(ch, map.get(ch) - 1);
//                 if (map.get(ch) == 0) {
//                     map.remove(ch);
//                 }
//             } else {
//                 // Find smallest character greater than ch
//                 char sg = 0;
//                 boolean found = false;
//                 for (char key : map.keySet()) {
//                     if (key > ch) {
//                          if (!found || key < sg) {
//                             sg = key;
//                             found = true;
//                         }
//                     }
//                 }
//                 // No greater character available
//                 if (!found) {
//                     return "";
//                 }
//                 // Use the greater character
//                 ans.append(sg);
//                 map.put(sg, map.get(sg) - 1);
//                 if (map.get(sg) == 0) {
//                     map.remove(sg);
//                 }
//                 // Fill remaining characters in ascending order
//                 for (char key = 'a'; key <= 'z'; key++) {
//                     if (map.containsKey(key)) {
//                         int freq = map.get(key);
//                         while (freq > 0) {
//                             ans.append(key);
//                             freq--;
//                         }
//                     }
//                 }
//                 return ans.toString();
//             }
//         }
//         return "";
//     }
// }





// class Solution {
//     public String lexGreaterPermutation(String s, String target) {
//         int n = s.length();
//         HashMap<Character , Integer> map = new HashMap<>();
//         for(int i = 0 ; i < n ; i++){
//             char ch = s.charAt(i);
//             map.put(ch , map.getOrDefault(ch , 0) + 1);
//         }
//         StringBuilder ans = new StringBuilder();
//         for(int i = 0 ; i < n ; i++){
//             char ch = target.charAt(i);
//             if(map.containsKey(ch)){
//                 ans.append(ch);
//                 map.put(ch, map.get(ch) - 1);
//                 if (map.get(ch) == 0) {
//                     map.remove(ch);
//                 }
//             }

//             if(!map.containsKey(ch)){
//                 //we will put character that is greater than this char , available in map
//                 char sg = 0;
//                 boolean found = false;

//                 for (char key : map.keySet()) {
//                     if (key > ch) {
//                         if (!found || key < sg) {
//                             sg = key;
//                             found = true;
//                         }
//                     }
//                 }
//                 if(sg != 0){
//                     ans.append(sg);
//                     map.put(sg, map.get(sg) - 1);
//                     if (map.get(sg) == 0) {
//                         map.remove(sg);
//                     }
//                 }
//                 else{
//                     return "";
//                 }
//             }
//         }

//         return ans.toString() ;
//     }
// }