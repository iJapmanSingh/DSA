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

    public void unionBySize(int u, int v){
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv) return;

        if(size[pu] < size[pv]){
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }

    public static void main(String[] args) {

        DisjointSet ds = new DisjointSet(8);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);

        if(ds.findParent(3) == ds.findParent(7)){
            System.out.println("same");
        }else{
            System.out.println("not same");
        }

        ds.union(3, 7);

        if(ds.findParent(3) == ds.findParent(7)){
            System.out.println("same");
        }else{
            System.out.println("not same");
        }

    }
}
