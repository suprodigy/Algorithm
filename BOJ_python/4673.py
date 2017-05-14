isSelfNumber = [True] * 10001

def nextNum(num):
    temp = str(num)
    ret = num
    for i in range(len(temp)):
        ret += int(temp[i])
    return ret

def check(i):
    global isSelfNumber
    next = nextNum(i)
    if next <= 10000 and isSelfNumber[next]:
        isSelfNumber[next] = False
        check(nextNum(i))

for i in range(1, 10001):
    check(i)
for i in range(1, 10001):
    if isSelfNumber[i]:
        print(i)
