N = int(input())

now, count = N, 0
while True:
    # print(now)
    left, right = 0, 0
    if 0 <= now < 10:
        left, right = 0, now
    else:
        temp = str(now)
        left = int(temp[0])
        right = int(temp[1])
    now = right * 10 + (left + right) % 10
    count += 1

    if now == N:
        break

print(count)
