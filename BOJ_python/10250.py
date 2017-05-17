import sys
sys.stdin = open('input.txt', 'r')

TC = int(input())
for test_case in range(TC):
    H, W, N = map(int, input().split())
    room_number = (N / H) + 1 if N % H else (N / H)
    floor = N % H if N % H else H
    print('%d%02d' % (floor, room_number))
