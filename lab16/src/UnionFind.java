public class UnionFind {

    /* TODO: Add instance variables here. */
    public int[] disJoinSet;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        disJoinSet = new int[N];
        for(int i = 0; i< N; i ++){
            disJoinSet[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v);
        return Math.abs(disJoinSet[root]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return disJoinSet[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int root1,root2;
        root1 = find(v1);
        root2 = find(v2);
        return root1 == root2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if(v > disJoinSet.length - 1 || v < 0){
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
        int root;
        int changeRoot;
        int change;
        changeRoot = v;
        root = v;
        while(disJoinSet[root] >= 0){
            root = disJoinSet[root];
            }
        while(disJoinSet[changeRoot] >= 0){
            change = changeRoot;
            changeRoot = disJoinSet[changeRoot];
            disJoinSet[change] = root;
        }

        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. */
    public void union(int v1, int v2) {
        if(v1 > disJoinSet.length -1 || v2 > disJoinSet.length -1 || v1 < 0 || v2 < 0){
            throw new IllegalArgumentException("Cannot union with an out of range vertex!");
        }

        int root1,root2;
        root1 = find(v1);
        root2 = find(v2);
        if(v1 == v2 || root2 == root1){
            return;
        }
        if(disJoinSet[root1] < disJoinSet[root2]){
            disJoinSet[root1] += disJoinSet[root2];
            disJoinSet[root2] = root1;
        }else{
            disJoinSet[root2] += disJoinSet[root1];
            disJoinSet[root1] = root2;
        }

    }
}
