package isbinarysearchtree;


public class Solution {


    public static void main(String[] args) {
    }

    static class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {
        return checkBSTIntegers(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkBSTIntegers(Node root, int min, int max) {
        if (root == null) return true;

        if (root.data <= min || root.data >= max)
            return false;

        if (!checkBSTIntegers(root.left, min, root.data) || !checkBSTIntegers(root.right, root.data, max))
            return false;

        return true;
    }
}
