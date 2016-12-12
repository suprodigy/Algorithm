#include <cstdio>
const int MAX = 1111;
int R, C, dir[4];
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};
bool a[MAX][MAX], c[MAX][MAX];

bool inRange(int y, int x){
    return 0<=y && y<R && 0<=x && x<C;
}

void go(int y, int x, int idx)
{
    c[y][x] = true;
    for(int i=0; i<4; i++){
        int nextIdx = dir[(idx+i)%4];
        int nextY = y + dy[nextIdx];
        int nextX = x + dx[nextIdx];
        if(inRange(nextY, nextX) && !a[nextY][nextX] && !c[nextY][nextX]){
            go(nextY, nextX, (idx+i)%4);
            return;
        }
    }
    printf("%d %d\n", y, x);
    return;
}

int main()
{
    scanf("%d%d", &R, &C);

    int k;
    scanf("%d", &k);

    for(int i=0; i<k; i++){
        int br, bc;
        scanf("%d%d", &br, &bc);
        a[br][bc] = true;
    }

    int sr, sc;
    scanf("%d%d", &sr, &sc);

    for(int i=0; i<4; i++){
        scanf("%d", &dir[i]);
        dir[i]--;
    }

    go(sr, sc, 0);
    return 0;
}