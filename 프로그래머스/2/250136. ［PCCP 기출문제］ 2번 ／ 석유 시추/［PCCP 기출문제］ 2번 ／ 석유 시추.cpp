#include <vector>
#include <queue>
#include <iostream>
#include <set>
#define p pair<int, int>
#define x first
#define y second

using namespace std;

const int MAX = 501;
const int dx[] = {0, 1, 0, -1};
const int dy[] = {1, 0, -1, 0};

int area[MAX][MAX];
int number[MAX][MAX];
bool vis[MAX][MAX];

int solution(vector<vector<int>> land) {
    int answer = 0;
    int n = land.size();
    int m = land.at(0).size();
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < m; ++j) {
            area[i][j] = land.at(i).at(j);
            vis[i][j] = 0;
        }
    }
    // 사이즈 생성
    int count = 0;
    queue<p> q;
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < m; ++j) {
            vector<p> checkList;
            if(vis[i][j] || land.at(i).at(j) == 0) continue;
            vis[i][j] = true;
            q.push({i, j});
            while(!q.empty()) {
                p cur = q.front();
                q.pop();
                checkList.push_back(cur);
                for(int dir = 0; dir < 4; ++dir) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if(vis[nx][ny] || land.at(nx).at(ny) == 0) continue;
                    vis[nx][ny] = true;
                    q.push({nx, ny});
                 }
            }
            ++count;
            int size = checkList.size();
            for(p cur : checkList) {
                area[cur.x][cur.y] = size;
                number[cur.x][cur.y] = count;
            }
        }
    }
    for(int i = 0; i < m; ++i) {
        int sum = 0;
        set<int> set;
        for(int j = 0; j < n; ++j) {
            if(set.contains(number[j][i])) continue;
            sum += area[j][i];
            set.insert(number[j][i]);
        }
        if(sum > answer) answer = sum;
    }
    return answer;
}