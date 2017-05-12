input()
data = input().split()
data = [int(x) for x in data]
max_value = max(data)
data = [x / max_value * 100 for x in data]
print('%.2f' % (sum(data) / len(data)))
