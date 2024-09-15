package main

import (
	"container/list"
	"fmt"
)

type Graph struct {
	adjacencyList map[int][]int
}

func NewGraph() *Graph {
	return &Graph{
		adjacencyList: make(map[int][]int),
	}
}

func (g *Graph) AddUndirectedEdge(v1, v2 int) {
	g.adjacencyList[v1] = append(g.adjacencyList[v1], v2)
	g.adjacencyList[v2] = append(g.adjacencyList[v2], v1)
}

func (g *Graph) AddDirectedEdge(v1, v2 int) {
	g.adjacencyList[v1] = append(g.adjacencyList[v1], v2)
}

func (g *Graph) DFS(start int, visited map[int]bool) {
	visited[start] = true

	fmt.Printf("DFS Visited %d\n", start)

	for _, neighbour := range g.adjacencyList[start] {
		if !visited[neighbour] {
			g.DFS(neighbour, visited)
		}
	}
}

func (g *Graph) BFS(start int) {
	visited := make(map[int]bool)

	queue := list.New()

	queue.PushBack(start)

	for queue.Len() > 0 {
		element := queue.Front()
		node := element.Value.(int)
		fmt.Printf("BFS Visited %d\n", node)
		queue.Remove(element)

		for _, neighbour := range g.adjacencyList[start] {
			if !visited[neighbour] {
				visited[neighbour] = true
				queue.PushBack(neighbour)
			}
		}
	}
}

func (g *Graph) TopologicalSort() []int {
	var stack []int
	visited := make(map[int]bool)

	var dfs func(int)
	dfs = func(start int) {
		visited[start] = true

		for _, neighbour := range g.adjacencyList[start] {
			if !visited[start] {
				dfs(neighbour)
			}
		}

		stack = append(stack, start)
	}

	for vertex := range g.adjacencyList {
		if !visited[vertex] {
			dfs(vertex)
		}
	}

	reverse(stack)

	return stack
}

func reverse(s []int) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

func main() {
	//g := NewGraph()
	//
	//// Add edges
	//g.AddUndirectedEdge(0, 1)
	//g.AddUndirectedEdge(0, 2)
	//g.AddUndirectedEdge(1, 2)
	//g.AddUndirectedEdge(2, 0)
	//g.AddUndirectedEdge(2, 3)
	//g.AddUndirectedEdge(3, 3)
	//
	//visited := make(map[int]bool)
	//
	//g.DFS(3, visited)
	//g.BFS(3)

	dag := NewGraph()
	dag.AddDirectedEdge(5, 2)
	dag.AddDirectedEdge(5, 0)
	dag.AddDirectedEdge(4, 0)
	dag.AddDirectedEdge(4, 1)
	dag.AddDirectedEdge(2, 3)
	dag.AddDirectedEdge(3, 1)
	//dag.DFS(4, make(map[int]bool))
	fmt.Printf("Topological Sort Order: %v", dag.TopologicalSort())
}
