class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        StringBuilder ans = new StringBuilder();

        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        int i = 0;  
        while(i < n){
            if(s.charAt(i) != '('){
                ans.append(s.charAt(i));
                i++;
            }else{
                int j = i+1 ;
                while(s.charAt(j) != ')'){
                    j++ ;
                }
                String key = s.substring(i+1 , j);
                String value = map.getOrDefault(key, "?");
                
                ans.append(value);
                i = j + 1;
            }
        }
        return ans.toString();
    }
}