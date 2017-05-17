import sys
sys.stdin = open('input.txt', 'r')

import itertools
heights = []
for i in range(9):
    heights.append(int(input()))
heights.sort()
for subset in itertools.combinations(heights, 7):
    if sum(subset) == 100:
        for x in subset:
            print(x)
        break
