import sys
sys.stdin = open('input.txt', 'r')

cipher = input()
N = int(input())
dictionary = []
for i in range(N):
    dictionary.append(input())

for i in range(26):
    decoded = ''
    for ch in cipher:
        decoded += chr(((ord(ch) - ord('a') + i) % 26) + ord('a'))

    is_found = False
    for j in range(N):
        idx = decoded.find(dictionary[j])
        if idx != -1:
            is_found = True; break
    if is_found:
        print(decoded)
        break
