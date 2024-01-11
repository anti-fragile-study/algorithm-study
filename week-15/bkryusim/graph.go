package main

func graphSolution(edges [][]int) []int {
	outs := [1000001]int{}
	ins := [1000001]int{}
	graph := map[int][]int{}

	vertex := 0
	donut := 0
	stick := 0
	eight := 0

	for _, edge := range edges {
		outs[edge[0]] += 1
		ins[edge[1]] += 1

		graph[edge[0]] = append(graph[edge[0]], edge[1])
	}

	for i, out := range outs {
		if out-ins[i] >= 2 {
			vertex = i
			break
		}
	}

	for _, start := range graph[vertex] {
		node := start
		meet := false
		for {
			if len(graph[node]) == 2 {
				eight += 1
				break
			}

			if len(graph[node]) == 0 {
				stick += 1
				break
			}

			if start == node && meet {
				donut += 1
				break
			}

			meet = true
			node = graph[node][0]
		}
	}

	return []int{vertex, donut, stick, eight}
}
