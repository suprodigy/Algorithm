A = int(input())
B = int(input())
C = int(input())

mul = str(A*B*C)
ans = [0] * 10
for ch in mul:
    ans[ord(ch) - ord('0')] += 1

for x in ans:
    print(x)
