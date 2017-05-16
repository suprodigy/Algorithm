import sys
sys.stdin = open('input.txt', 'r')

# input_data = [int(x) for x in input().split()]
# A, B, C = input_data[0], input_data[1], input_data[2]
#
# ans, mods = 1, []
# mods.append(A % C)
# for i in range(1, 32):
#     mods.append((mods[i-1] ** 2) % C)
# for i in range(32):
#     if B & (1 << i) != 0:
#         ans *= mods[i]
# ans %= C
# print(ans)

A, B, C = map(int, input().split())
print(pow(A, B, C))
