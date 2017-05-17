import sys
sys.stdin = open('input.txt', 'r')

def is_group_word(string):
    checked = [False] * 26
    idx = 0
    while idx < len(string):
        if checked[ord(string[idx]) - ord('a')]:
            return False
        checked[ord(string[idx]) - ord('a')] = True
        idx += 1
        while idx < len(string) and string[idx - 1] == string[idx]:
            idx += 1
    return True

N, count = int(input()), 0
for i in range(N):
    string = input()
    if is_group_word(string):
        count += 1
print(count)
