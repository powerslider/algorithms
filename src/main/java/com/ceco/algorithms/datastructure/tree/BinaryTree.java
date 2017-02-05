package com.ceco.algorithms.datastructure.tree;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Feb-2017
 */
public class BinaryTree {

    private static class Node {

        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key + "";
        }
    }

    static int preIndex = 0;

    public Node buildTree(int[] preOrder, int[] inOrder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        Node root = new Node(preOrder[preIndex]);
        preIndex++;

        if (inStart == inEnd) return root;

        int index = getInOrderIndex(inOrder, inStart, inEnd, root.key);
        root.left = buildTree(preOrder, inOrder, inStart, index - 1);
        root.right = buildTree(preOrder, inOrder, index + 1, inEnd);

        return root;
    }

    public int getInOrderIndex(int[] inOrder, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public void inOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            inOrderTraverse(focusNode.left);
            System.out.print(focusNode + " ");
            inOrderTraverse(focusNode.right);
        }
    }

    public void preOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            System.out.print(focusNode + " ");
            preOrderTraverse(focusNode.left);
            preOrderTraverse(focusNode.right);
        }
    }

    public void postOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            postOrderTraverse(focusNode.left);
            postOrderTraverse(focusNode.right);
            System.out.print(focusNode + " ");
        }
    }

    public static void main(String[] args) {
        int[] inOrder = {2, 5, 6, 10, 12, 14, 15};
        int[] preOrder = {10, 5, 2, 6, 14, 12, 15};

        BinaryTree tree = new BinaryTree();
        Node rootNode = tree.buildTree(preOrder, inOrder, 0, inOrder.length - 1);
        System.out.println("Inorder traversal:");
        tree.inOrderTraverse(rootNode);
        System.out.println();
        System.out.println("Preorder traversal:");
        tree.preOrderTraverse(rootNode);
        System.out.println();
        System.out.println("Postorder traversal:");
        tree.postOrderTraverse(rootNode);
    }
}
