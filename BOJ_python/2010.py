import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
input_data = [int(sys.stdin.readline().rstrip()) for i in range(N)]
print(sum(input_data) - N + 1)
