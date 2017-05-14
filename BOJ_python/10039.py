import sys
sys.stdin = open('input.txt', 'r')

total = 0
for i in range(5):
    num = int(input())
    total += (40 if num < 40 else num)
print(total//5)
