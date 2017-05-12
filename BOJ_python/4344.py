TC = int(input())
for test_case in range(TC):
    data = input().split()
    N = int(data[0])
    scores = [int(x) for x in data[1:]]
    average = sum(scores) / N
    students = list(filter(lambda x:x>average, scores))
    print('%.3f' % (len(students) / N * 100) + '%')
