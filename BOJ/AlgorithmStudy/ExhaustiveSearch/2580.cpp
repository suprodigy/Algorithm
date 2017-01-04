#include <cstdio>
#include <vector>
using namespace std;

bool flag = false;
int board[9][9];

void fillBlank(int y, int x)
{
    if(flag) return;
    if(y == 9) {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                printf("%d ", board[i][j]);
            }
            puts("");
        }
        flag = true;
        return;
    }
    if(x >= 9) fillBlank(y+1, 0);
    else if(board[y][x] != 0) fillBlank(y, x+1);
    else {
        vector<bool> checked(10, false);

        for(int i=0; i<9; i++) checked[board[y][i]] = true;
        for(int i=0; i<9; i++) checked[board[i][x]] = true;

        int rows = (y/3)*3;
        int cols = (x/3)*3;
        for(int i=rows; i<rows+3; i++)
            for(int j=cols; j<cols+3; j++)
                checked[board[i][j]] = true;

        for(int i=1; i<=9; i++) {
            if(!checked[i]) {
                board[y][x] = i;
                fillBlank(y, x+1);
                board[y][x] = 0;
            }
        }
    }
}

int main()
{
    for(int i=0; i<9; i++)
        for(int j=0; j<9; j++)
            scanf("%d", &board[i][j]);

    fillBlank(0, 0);
    return 0;
}