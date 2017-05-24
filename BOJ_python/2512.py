import sys
sys.stdin = open('input.txt', 'r')

def isPossible(amount):
    total, max_request = 0, 0
    for request in a:
        now = request
        if request > amount: now = amount
        max_request = max(max_request, now)
        total += now
    if total <= M:
        global ans
        ans = max(ans, max_request)
        return True
    else:
        return False


N, a, M = int(input()), list(map(int, input().split())), int(input())
left, right, ans = 0, M, 0
while left <= right:
    mid = (left + right) // 2
    if isPossible(mid):
        left = mid + 1
    else:
        right = mid - 1
print(ans)
