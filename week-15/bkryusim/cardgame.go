package main

func cardSolution(coin int, cards []int) int {
	target := len(cards) + 1
	hand := make([]bool, target)
	prehand := make([]bool, target)
	round := 0

	count := 0
	pair := 0

	// n/3까지 뽑기
	for i := 0; i < len(cards)/3; i++ {
		count++
		if hand[target-cards[i]] {
			hand[target-cards[i]] = false
			pair++
			continue
		}

		hand[cards[i]] = true
	}

	// 게임 시작
	for {
		round++
		if count == len(cards) {
			break
		}
		prehand[cards[count]] = true
		prehand[cards[count+1]] = true
		count += 2

		if pair > 0 {
			pair--
			continue
		}

		if coin > 0 {
			next := false
			for i, d := range prehand {
				if d && hand[target-i] {
					prehand[i] = false
					hand[target-i] = false
					coin -= 1
					next = true
					break
				}
			}
			if next {
				continue
			}
		}

		if coin > 1 {
			next := false
			for i, d := range prehand {
				if d && prehand[target-i] {
					prehand[i] = false
					prehand[target-i] = false
					coin -= 2
					next = true
					break
				}
			}
			if next {
				continue
			}
		}

		break
	}

	return round
}
