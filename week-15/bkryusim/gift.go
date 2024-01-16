package main

import (
	"strings"
)

func giftSolution(friends []string, gifts []string) int {

	users := map[string]int{}
	giftLog := [50][50]int{}
	giftScore := [50]int{}
	giftNum := [50]int{}

	for i, user := range friends {
		users[user] = i
	}

	for _, gift := range gifts {
		log := strings.Fields(gift)
		giftLog[users[log[0]]][users[log[1]]] += 1
		giftScore[users[log[0]]] += 1
		giftScore[users[log[1]]] -= 1
	}

	for u, user := range friends {
		for f, friend := range friends {
			if user == friend {
				continue
			}

			if giftLog[u][f] > giftLog[f][u] {
				giftNum[u] += 1
			} else if giftLog[u][f] < giftLog[f][u] {
				giftNum[f] += 1
			} else {
				if giftScore[u] > giftScore[f] {
					giftNum[u] += 1
				} else if giftScore[u] < giftScore[f] {
					giftNum[f] += 1
				}
			}

		}
	}

	max := 0

	for _, n := range giftNum {
		if max < n {
			max = n
		}
	}

	return max
}
