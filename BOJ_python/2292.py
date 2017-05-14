import sys
sys.stdin = open('input.txt', 'r')

seq, now, diff = [], 1, 6
while now <= 1000000000:
    seq.append(now)
    now, diff = now + diff, diff + 6

N, i = int(input()), 0
try:
    while N > seq[i]: i += 1
except IndexError:
    pass
print(i+1)
