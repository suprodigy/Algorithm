import sys
sys.stdin = open('input.txt', 'r')

L = int(input())
ans = ['Jolteon', 'Flareon', 'Vaporeon']
print(ans[L%3])
