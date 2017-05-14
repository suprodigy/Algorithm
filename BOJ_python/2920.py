import sys
sys.stdin = open('input.txt', 'r')

a = [int(x) for x in input().split()]
if all([a[i] <= a[i+1] for i in range(len(a) - 1)]):
    print('ascending')
elif all([a[i] >= a[i+1] for i in range(len(a) - 1)]):
    print('descending')
else:
    print('mixed')
