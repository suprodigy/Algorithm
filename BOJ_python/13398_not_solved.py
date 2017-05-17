import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
nums = list(map(int, input().split()))
psum = [0] * (N+1)
for i in range(1, N+1):
    psum[i] = psum[i-1] + nums[i-1]

i, j, ans = 1, 1, -987654321
while i < N or j < N:
    ans = max(ans, psum[j] - psum[i-1])
    if j < N and (i > j + 1 or psum[j+1] - psum[i-1] >= psum[j] - psum[i]):
        j += 1
    else:
        i += 1
print(ans)
