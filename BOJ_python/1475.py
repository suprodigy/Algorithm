import sys
sys.stdin = open('input.txt', 'r')

import math

N = input()
count = [0] * 10
for ch in N:
    count[ord(ch) - ord('0')] += 1
count[6] = math.ceil((count[6] + count[9]) / 2)
count[9] = 0
print(max(count))
