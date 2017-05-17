import sys
sys.stdin = open('input.txt', 'r')

count = [3, 3, 3, 3, 3, 4, 3, 4]
mapping = [0] * 26
string, number, idx = input(), 1, 0
for i in range(len(count)):
    for j in range(count[i]):
        mapping[idx] = number
        idx += 1
    idx -= 1
    number += 1; idx += 1
print(sum(list(map(lambda x: mapping[ord(x) - ord('A')] + 2, string))))
