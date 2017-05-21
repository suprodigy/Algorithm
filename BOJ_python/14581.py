import sys
sys.stdin = open('input.txt')

hongjun = input()
for i in range(3):
    for j in range(3):
        temp = 'fan'
        if i == 1 and j == 1:
            temp = hongjun
        print(':%s:' % temp, end='')
    print()
