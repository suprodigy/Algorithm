#include <cstdio>
#include <vector>
using namespace std;

int dy[] = {1, -1, 0, 0};
int dx[] = {0, 0, -1, 1};
int H, W;

/** not solved **/

int cover(int i, int j, vector<vector<bool> >& board, int cnt)
{
    /* test
    printf("%d %d %d\n", i, j, cnt);
    for(int k=0; k<H; k++) {
        for(int w=0; w<W; w++)
            printf("%d", board[k][w] ? 1 : 0);
        puts("");
    }
    puts(""); */

    if(cnt == 0) return 1;
    if(j == W) return cover(i+1, 0, board, cnt);
    if(i == H) return 0;
    int ret = 0;

    if(!board[i][j])
        ret += cover(i, j+1, board, cnt);
    else {
        if(i+1 < H && board[i+dy[0]][j+dx[0]]) {
            if(j-1 >= 0 && board[i+dy[2]][j+dx[2]]) {
                board[i][j] = board[i+dy[0]][j+dx[0]] = board[i+dy[2]][j+dx[2]] = false;
                ret += cover(i, j+1, board, cnt-1);
                board[i][j] = board[i+dy[0]][j+dx[0]] = board[i+dy[2]][j+dx[2]] = true;
            }
            else if(j+1 < W && board[i+dy[3]][j+dx[3]]) {
                board[i][j] = board[i+dy[0]][j+dx[0]] = board[i+dy[3]][j+dx[3]] = false;
                ret += cover(i, j+1, board, cnt-1);
                board[i][j] = board[i+dy[0]][j+dx[0]] = board[i+dy[3]][j+dx[3]] = true;
            }
            ret += cover(i, j+1, board, cnt);
        }
        else if(i-1 >= 0 && board[i+dy[1]][j+dx[1]]) {
            if(j-1 >= 0 && board[i+dy[2]][j+dx[2]]) {
                board[i][j] = board[i+dy[1]][j+dx[1]] = board[i+dy[2]][j+dx[2]] = false;
                ret += cover(i, j+1, board, cnt-1);
            }
            else if(j+1 < W && board[i+dy[3]][j+dx[3]]) {
                board[i][j] = board[i+dy[1]][j+dx[1]] = board[i+dy[3]][j+dx[3]] = false;
                ret += cover(i, j+1, board, cnt-1);
            }
        }
        else {
            ret += cover(i, j+1, board, cnt);
        }
    }

    return ret;
}

int main()
{
    int C;
    scanf("%d", &C);

    while(C--)
    {
        scanf("%d%d", &H, &W);

        vector<vector<bool> > board(H);
        for(int i=0; i<H; i++)
            board[i] = vector<bool>(W);

        int cnt = 0;
        for(int i=0; i<H; i++){
            char str[22];
            scanf("%s", str);
            for(int j=0; j<W; j++){
                char ch = str[j];
                if(ch == '.') {
                    board[i][j] = true;
                    cnt++;
                }
                else {
                    board[i][j] = false;
                }
            }
        }

        if((cnt % 3) != 0) puts("0");
        else printf("%d\n", cover(0, 0, board, cnt/3));
    }

    return 0;
}