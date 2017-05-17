import sys
sys.stdin = open('input.txt', 'r')

end = ['0', 'W', '0']
while True:
    input_data = input().split()
    if input_data == end: break
    a, b, c = int(input_data[0]), input_data[1], int(input_data[2])
    if b == 'W': ans = a - c
    else: ans = a + c
    print(ans if ans >= -200 else 'Not allowed')
