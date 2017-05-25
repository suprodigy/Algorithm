import sys
sys.stdin = open('input.txt', 'r')
readlines = sys.stdin.readlines

input_data = [line[:-1] for line in readlines()]
max_len = max([len(x) for x in input_data])
ans = ''
for i in range(max_len):
    for j in range(5):
        if i < len(input_data[j]):
            ans += input_data[j][i]
print(ans)
