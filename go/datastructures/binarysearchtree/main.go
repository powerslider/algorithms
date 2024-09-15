package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

type Node struct {
	Key   int
	Left  *Node
	Right *Node
}

func NewNode(key int) *Node {
	return &Node{Key: key}
}

type BinarySearchTree struct {
	Root *Node
}

func NewBST() *BinarySearchTree {
	return &BinarySearchTree{}
}

func NewMinimalBSTFrom(nodeKeys []int) *BinarySearchTree {
	return &BinarySearchTree{
		Root: createMinimalBST(nodeKeys, 0, len(nodeKeys)-1),
	}
}

func (b *BinarySearchTree) Insert(key int) {
	b.Root = insertNode(b.Root, key)
}

func (b *BinarySearchTree) Search(key int) bool {
	return searchNode(b.Root, key)
}

func (b *BinarySearchTree) Get(key int) *Node {
	return getNode(b.Root, key)
}

func (b *BinarySearchTree) Delete(key int) {
	b.Root = deleteNode(b.Root, key)
}

func (b *BinarySearchTree) MaxDepth() int {
	return maxTreeDepth(b.Root)
}

func (b *BinarySearchTree) InvertTree() {
	b.Root = invert(b.Root)
}

func (b *BinarySearchTree) LowestCommonAncestor(node1 *Node, node2 *Node) *Node {
	return lca(b.Root, node1, node2)
}

func (b *BinarySearchTree) TraverseInOrder() {
	inOrderTraverse(b.Root)
}

func (b *BinarySearchTree) TraversePreOrder() {
	preOrderTraverse(b.Root)
}

func (b *BinarySearchTree) ContainsTree(b1 *BinarySearchTree) bool {
	var (
		sb1 strings.Builder
		sb2 strings.Builder
	)

	getOrderString(b.Root, sb1)
	getOrderString(b1.Root, sb2)

	return strings.Contains(sb1.String(), sb2.String())
}

func (b *BinarySearchTree) IsBalanced() bool {
	return isTreeBalanced(b.Root)
}

func createMinimalBST(nodeKeys []int, start, end int) *Node {
	if end < start {
		return nil
	}

	mid := (start + end) / 2
	node := NewNode(nodeKeys[mid])

	node.Left = createMinimalBST(nodeKeys, start, mid-1)
	node.Right = createMinimalBST(nodeKeys, mid+1, end)

	return node
}

func isTreeBalanced(node *Node) bool {
	if node == nil {
		return true
	}

	depthDiff := maxTreeDepth(node.Left) - maxTreeDepth(node.Right)
	if math.Abs(float64(depthDiff)) > 1 {
		return false
	}

	return isTreeBalanced(node.Left) && isTreeBalanced(node.Right)
}

func searchNode(node *Node, key int) bool {
	// if there is no parent node then we have an empty tree with no nodes.
	if node == nil {
		return false
	}

	// if the key is equal to the parent node key, then the parent is the searched node.
	if key == node.Key {
		return true
		// if the key is less than the parent node key, we descent to it's left child and recursively perform the same check.
	} else if key < node.Key {
		return searchNode(node.Left, key)
		// if the key is greater than the parent node key, we descent to it's right child and recursively perform the same check.
	} else {
		return searchNode(node.Right, key)
	}
}

func getNode(node *Node, key int) *Node {
	// if there is no parent node then we have an empty tree with no nodes.
	if node == nil {
		return nil
	}

	// if the key is equal to the parent node key, then the parent is the searched node.
	if key == node.Key {
		return node
		// if the key is less than the parent node key, we descent to it's left child and recursively perform the same check.
	} else if key < node.Key {
		return getNode(node.Left, key)
		// if the key is greater than the parent node key, we descent to it's right child and recursively perform the same check.
	} else {
		return getNode(node.Right, key)
	}
}

func insertNode(node *Node, key int) *Node {
	// if there is no parent node, then this node is the root and we create it.
	if node == nil {
		return NewNode(key)
	}

	// if there is a parent node, then we check if the new node's key is less then the parent's node key
	// which should insert it as the parent node left child, or right child otherwise
	if key < node.Key {
		node.Left = insertNode(node.Left, key)
	} else {
		node.Right = insertNode(node.Right, key)
	}

	return node
}

func invert(node *Node) *Node {
	if node == nil {
		return nil
	}

	node.Left = invert(node.Right)
	node.Right = invert(node.Left)

	return node
}

func maxTreeDepth(node *Node) int {
	if node == nil {
		return -1
	}

	leftSubtreeDepth := maxTreeDepth(node.Left)
	//fmt.Printf("Left subtree depth %d\n", leftSubtreeDepth)
	rightSubtreeDepth := maxTreeDepth(node.Right)
	//fmt.Printf("Right subtree depth %d\n", rightSubtreeDepth)

	return max(leftSubtreeDepth, rightSubtreeDepth+1)
}

func inOrderTraverse(node *Node) {
	if node != nil {
		inOrderTraverse(node.Left)
		fmt.Printf("%d\n", node.Key)
		inOrderTraverse(node.Right)
	}
}

func preOrderTraverse(node *Node) {
	if node != nil {
		fmt.Printf("%d\n", node.Key)
		preOrderTraverse(node.Left)
		preOrderTraverse(node.Right)
	}
}

// Pre-order traversal of the tree and appending node keys to a string to mark the order of the nodes.
func getOrderString(node *Node, sb strings.Builder) {
	if node == nil {
		// append null node marker
		sb.WriteString("X")
		return
	}

	// append root
	sb.WriteString(strconv.Itoa(node.Key) + " ")
	// append left subtree
	getOrderString(node.Left, sb)
	// append right subtree
	getOrderString(node.Right, sb)
}

func deleteNode(node *Node, key int) *Node {
	// if the parent node does not exist, then we have and empty tree with no nodes.
	if node == nil {
		return nil
	}

	// if the key is less than the parent node key, we descent to it's left child and recursively perform the same check.
	if key < node.Key {
		node.Left = deleteNode(node.Left, key)
		// if the key is greater than the parent node key, we descent to it's right child and recursively perform the same check.
	} else if key > node.Key {
		node.Right = deleteNode(node.Right, key)
		// if we get a key match with the parent node
	} else {
		// we check if that node has only a right child and replace itself with that child.
		if node.Left == nil {
			return node.Right
			// we check if that node has only a left child and replace itself with that child.
		} else if node.Right == nil {
			return node.Left
		}

		// if the node has two children we search for the in-order successor from the right subtree and substitute it with its key.
		node.Key = inOrderSuccessor(node.Right)

		// then we recursively delete the in-order successor node from its original position.
		node.Right = deleteNode(node.Right, node.Key)
	}

	return node
}

func inOrderSuccessor(current *Node) int {
	for current.Left != nil {
		current = current.Left
	}

	return current.Key
}

func lca(current *Node, node1 *Node, node2 *Node) *Node {
	// if the parent node does not exist, then we have and empty tree with no nodes.
	if current == nil {
		return nil
	}

	// if either of both nodes match the current node, then it is the LCA.
	if node1 == current || node2 == current {
		return current
	}

	// recursively check the left subtree of the current node for LCA
	leftLCA := lca(current.Left, node1, node2)
	// recursively check the right subtree of the current node for LCA
	rightLCA := lca(current.Right, node1, node2)

	// if both return a non-empty node, then the current node is their LCA
	if leftLCA != nil && rightLCA != nil {
		return current
	}

	if leftLCA != nil {
		return leftLCA
	}

	return rightLCA
}

func main() {
	tree := NewBST()
	tree.Insert(50)
	tree.Insert(30)
	tree.Insert(70)
	tree.Insert(20)
	tree.Insert(40)
	tree.Insert(60)
	tree.Insert(80)

	fmt.Printf("Is tree balanced: %v\n", tree.IsBalanced())
	fmt.Printf("Node %d exists? -> %t\n", 50, tree.Search(50))
	fmt.Printf("Node %d exists? -> %t\n", 30, tree.Search(30))
	fmt.Printf("Node %d exists? -> %t\n", 80, tree.Search(80))
	fmt.Printf("Node %d exists? -> %t\n", 100, tree.Search(100))

	fmt.Printf("Delete Node %d\n", 50)
	tree.Delete(50)
	fmt.Printf("Node %d exists? -> %t\n", 50, tree.Search(50))

	fmt.Printf("Is tree balanced: %v\n", tree.IsBalanced())

	tree.TraverseInOrder()

	lowestCommonAncestor := tree.LowestCommonAncestor(tree.Get(40), tree.Get(70))
	fmt.Printf("Lowest common ancestor of node 40 and 70 is %d\n", lowestCommonAncestor.Key)

	minTree := NewMinimalBSTFrom([]int{50, 30, 70, 20, 40, 60, 80})
	minTree.TraverseInOrder()
	fmt.Printf("Max depth of min tree is: %d\n", minTree.MaxDepth())
	fmt.Printf("Is min tree balanced: %v\n", minTree.IsBalanced())
}
