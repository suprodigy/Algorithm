import sys
sys.stdin = open('input.txt', 'r')

board = [input() for i in range(8)]
count = 0
for i in range(8):
    if i%2 == 0:
        temp = list(filter(lambda x: x=='F', [board[i][j] for j in range(0, 8, 2)]))
    else:
        temp = list(filter(lambda x: x=='F', [board[i][j] for j in range(1, 8, 2)]))
    count += len(temp)
print(count)
