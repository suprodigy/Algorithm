string = input()
cnt = [0] * 26
for ch in string:
    if ch.isupper():
        cnt[ord(ch) - ord('A')] += 1
    else:
        cnt[ord(ch) - ord('a')] += 1

max_count = max(cnt)
temp = list(filter(lambda x: x == max_count, cnt))
if len(temp) > 1:
    print('?')
else:
    for i in range(len(cnt)):
        if cnt[i] == max_count:
            print(chr(ord('A') + i))
            break
