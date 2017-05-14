import sys
sys.stdin = open('input.txt', 'r')

string, ans = input(), ''

for ch in string:
    if ch.isupper():
        ans += ch.lower()
    else:
        ans += ch.upper()

print(ans)
