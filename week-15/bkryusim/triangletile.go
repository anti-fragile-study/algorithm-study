package main

func triangleSolution(n int, tops []int) int {
	right := make([]int, n+1)
	noright := make([]int, n+1)

	right[0] = 0
	noright[0] = 1

	for i := 1; i <= n; i++ {
		right[i] = (right[i-1] + noright[i-1]) % 10007

		if tops[i-1] == 1 {
			noright[i] = (2*right[i-1] + 3*noright[i-1]) % 10007
		} else {
			noright[i] = (right[i-1] + 2*noright[i-1]) % 10007
		}
	}

	return (right[n] + noright[n]) % 10007
}
