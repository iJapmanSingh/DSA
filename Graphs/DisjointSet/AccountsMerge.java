public class DisjointSet {
    int[] parent ;
    int[] rank ;

    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];

        for(int i = 0 ; i < n ; i++){
            parent[i] = i ;
            rank[i] = 0 ;
        }
    }
    public int findParent(int node){
        if(node == parent[node]){
            return node ;
        }
        return parent[node] = findParent(parent[node]) ;
    }

    public void union ( int u , int v){
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv) return ;
        if(rank[pu] > rank[pv]) {
            parent[pv] = pu;
        }else if(rank[pv] > rank[pu]){
            parent[pu] = pv ;
        }else{
            parent[pu] = pv ;
            rank[pv]++ ;
        }
    }
}


class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size() ;
        DisjointSet ds = new DisjointSet(n);

        HashMap<String , Integer> mapMailNode = new HashMap<>();
        for(int i = 0 ; i < n ; i++){
            for(int j = 1 ; j< accounts.get(i).size() ; j++){
                String mail = accounts.get(i).get(j);
                if(mapMailNode.containsKey(mail) == false ){
                    mapMailNode.put(mail , i);
                }else{
                    ds.union(i , mapMailNode.get(mail));
                }
            }
        }

        List<List<String>> mergedMail = new ArrayList<>();
        for(int i = 0 ; i< n ; i++) mergedMail.add(new ArrayList<>());

        for(Map.Entry<String , Integer> it : mapMailNode.entrySet()){
            String mail = it.getKey();
            int node = ds.findParent(it.getValue());
            mergedMail.get(node).add(mail);
        }
        List<List<String>> ans = new ArrayList<>();
        for(int i = 0 ; i< n ; i++){
            if(mergedMail.get(i).size() == 0) continue ;
            Collections.sort(mergedMail.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for(String it : mergedMail.get(i)){
                temp.add(it);
            }
            ans.add(temp);
        }
        return ans ;


    }
}