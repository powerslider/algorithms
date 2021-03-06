package com.ceco.algorithms.datastructure.tree;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 05-Feb-2017
 */
public class BinarySearchTree {

    private static class Node {

        int key;
        String name;
        Node left;
        Node right;

        public Node(int key, String name) {
            this.key = key;
            this.name = name;
        }
    }

    Node root;

    void addNode(int key, String name) {
        Node newNode = new Node(key, name);

        if (root == null) {
            root = newNode;
        } else {
            // current focus in on the root node
            Node focusNode = root;

            // future parent of our new node
            Node parent;

            while (true) {
                // root is the top parent so we start there
                parent = focusNode;

                // check whether the new node should go on the left or on the right
                if (key < focusNode.key) {
                    // switch focus to left child
                    focusNode = focusNode.left;

                    // if left child has no childen
                    if (focusNode == null) {
                        // place newNode on the left of it
                        parent.left = newNode;
                        return;
                    }
                } else {
                    // switch focus to right node
                    focusNode = focusNode.right;

                    // if right child has no children
                    if (focusNode == null) {
                        // place new node on the right of it
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
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
}
