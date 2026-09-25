import java.util.*;

class Solution {

    int idx = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parseUnion(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> parseUnion(String s) {

        Set<String> result = new HashSet<>();

        while (idx < s.length() && s.charAt(idx) != '}') {

            Set<String> current = parseConcat(s);

            result.addAll(current);

            if (idx < s.length() && s.charAt(idx) == ',') {
                idx++;
            } else {
                break;
            }
        }

        return result;
    }

    private Set<String> parseConcat(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (idx < s.length()) {

            char ch = s.charAt(idx);

            if (ch == '}' || ch == ',') {
                break;
            }

            Set<String> next = parseFactor(s);

            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    private Set<String> parseFactor(String s) {

        Set<String> result;

        if (s.charAt(idx) == '{') {

            idx++; // {

            result = parseUnion(s);

            idx++; // }

        } else {

            result = new HashSet<>();
            result.add(String.valueOf(s.charAt(idx)));

            idx++;
        }

        return result;
    }
}
