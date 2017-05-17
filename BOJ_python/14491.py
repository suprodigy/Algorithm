import sys
sys.stdin = open('input.txt', 'r')

def convert(num):
    ret = ''
    while num >= 1:
        ret = str(num % 9) + ret
        num //= 9
    return ret

T = int(input())
print(convert(T))
