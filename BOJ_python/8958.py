import sys
sys.stdin = open('input.txt', 'r')

T = int(input())
for test_case in range(T):
    string = input()
    score, total = 1, 0
    for ch in string:
        if ch == 'O':
            total += score
            score += 1
        else:
            score = 1
    print(total)
