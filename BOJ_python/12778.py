import sys
sys.stdin = open('input.txt', 'r')

def from_alpha(input_data):
    for alpha in input_data:
        print(ord(alpha) - ord('A') + 1, end=' ')

def from_number(input_data):
    for number in input_data:
        print(chr(ord('A') + number - 1), end = ' ')

TC = int(input())
for _ in range(TC):
    M, L = input().split()
    input_data = input().split()
    if L == 'C': from_alpha(input_data)
    else: from_number(map(int, input_data))
    print()
