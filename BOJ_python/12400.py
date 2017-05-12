# import sys
# sys.stdin = open('hint.txt', 'r')

def preprocess():
    global encode

    hint = ['ejp mysljylc kd kxveddknmc re jsicpdrysi',
            'our language is impossible to understand',
            'rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd',
            'there are twenty six factorial possibilities',
            'de kr kd eoya kw aej tysr re ujdr lkgc jv',
            'so it is okay if you want to just give up']

    for i in range(3):
        str1 = hint[2*i]
        str2 = hint[2*i + 1]

        for j in range(len(str1)):
            if str1[j] != ' ':
                encode[ord(str1[j]) - ord('a')] = str2[j]

# main

encode = [-1] * 26
encode[25] = 'q'
encode[ord('q') - ord('a')] = 'z'

preprocess()

T = int(input())
for test_case in range(1, T+1):
    string = input()
    ans = ''

    for i in range(len(string)):
        if string[i] != ' ':
            ans += encode[ord(string[i]) - ord('a')]
        else:
            ans += ' '

    print('Case', '#%d:' % test_case, ans)
