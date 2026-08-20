/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        f(root , targetSum , ans , path);
        return ans ;
    }
    public void f(TreeNode root , int targetSum , List<List<Integer>> ans , List<Integer> path){
        if(root == null) return ;
        path.add(root.val);
        if(root.val == targetSum && root.left == null && root.right == null){
            ans.add(new ArrayList<>(path));
            path.remove(path.size() - 1) ;
            return ;
        }
        f(root.left , targetSum - root.val , ans , path);
        f(root.right , targetSum - root.val , ans , path);
        path.remove(path.size() -1 );

    }
}