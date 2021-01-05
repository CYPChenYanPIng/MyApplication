package com.example.uxin.myapplication;

/**
 * Created by shuipingyue@uxin.com on 2020/12/11.
 */
public class TestMain {

    public static void main(String[] args) {

    }

    static class  TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;
    }

    private static int maxDeth(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = maxDeth(root.left);
        int right = maxDeth(root.right);
        return Math.max(left,right) +1;
    }

    private static int minDeth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = minDeth(root.left);
        int right = minDeth(root.right);
        if (root.left == null || root.right == null) {
            return Math.max(left,right) + 1;
        }
        return Math.min(left,right) +1;
    }

    private static int nodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = nodeCount(root.left);
        int right = nodeCount(root.right);
        return left + right + 1;
    }

}
