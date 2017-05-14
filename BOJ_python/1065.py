def isHansu(num):
    if len(num) > 2:
        diff = ord(num[1]) - ord(num[0])
        for i in range(2, len(num)):
            if diff != (ord(num[i]) - ord(num[i-1])):
                return False
    return True

number = input()
count = 0
for i in range(1, int(number) + 1):
    if isHansu(str(i)):
        count += 1
print(count)
