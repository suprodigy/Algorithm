import sys
sys.stdin = open('input.txt', 'r')

N = int(input())
nums = [int(x) for x in input().split()]
nums.sort()
print(nums[0] * nums[len(nums)-1])
