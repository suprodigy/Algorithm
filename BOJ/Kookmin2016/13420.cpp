#include <cstdio>

int main()
{
    int T;
    scanf("%d", &T);

    while(T--)
    {
        long long num1, num2, num3, result;
        char op, eq;
        scanf("%lld %c %lld %c %lld", &num1, &op, &num2, &eq, &num3);
        switch(op) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1 / num2; break;
        }

        if(result == num3) puts("correct");
        else puts("wrong answer");
    }

    return 0;
}