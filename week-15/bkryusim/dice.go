package main

import (
	"math"
	"sort"
)

func diceSolution(dice [][]int) []int {
	maxWin := 0
	var answer []int

	for _, dices := range combination(len(dice)) {
		myDice := dices[0]
		opDice := dices[1]

		mySums := scores(dice, myDice)
		opSums := scores(dice, opDice)

		sort.Ints(mySums)
		sort.Ints(opSums)

		win := 0
		lose := 0

		for _, n := range mySums {
			win += sort.SearchInts(opSums, n)
		}

		for _, n := range opSums {
			lose += sort.SearchInts(mySums, n)
		}

		if win > maxWin {
			maxWin = win
			answer = myDice
		}

		if lose > maxWin {
			maxWin = lose
			answer = opDice
		}
	}

	for i, _ := range answer {
		answer[i] += 1
	}

	sort.Ints(answer)
	
	return answer
}

func scores(dice [][]int, myDice []int) []int {
	var scores []int
	for _, myRoll := range roll(len(dice) / 2) {
		score := 0
		for i, n := range myRoll {
			score += dice[myDice[i]][n]
		}
		scores = append(scores, score)
	}
	return scores
}

func roll(n int) [][]int {
	var result [][]int

	for i := 0; i < int(math.Pow(6, float64(n))); i++ {
		target := i
		var tmp []int
		for j := 0; j < n; j++ {
			tmp = append(tmp, target%6)
			target = target / 6
		}
		result = append(result, tmp)
	}

	return result
}

func combination(n int) [][][]int {
	var result [][][]int

	for i := 0; i < (1<<n)/2; i++ {
		target := i
		var one []int
		var zero []int

		for j := 0; j < n; j++ {
			if target%2 == 1 {
				one = append(one, j)
			}

			if target%2 == 0 {
				zero = append(zero, j)
			}
			target = target >> 1
		}

		if len(one) == len(zero) {
			result = append(result, [][]int{one, zero})
		}
	}

	return result
}
