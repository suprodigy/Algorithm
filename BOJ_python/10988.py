import sys
sys.stdin = open('input.txt', 'r')

def isPalindrome(string):
    n, mid = len(string), len(string) // 2
    for i in range(mid):
        if string[i] != string[n-i-1]:
            return False
    return True

print(1 if isPalindrome(input()) else 0)
