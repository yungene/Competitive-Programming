#include <assert.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#include <algorithm>
#include <bitset>
#include <deque>
#include <functional>
#include <iomanip>
#include <iostream>
#include <iterator>
#include <limits>
#include <list>
#include <map>
#include <numeric>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <unordered_set>
#include <utility>
#include <vector>

using namespace std;
typedef long int int32;
typedef unsigned long int uint32;
typedef long long int int64;
typedef unsigned long long int uint64;
/*******  All Required define Pre-Processors and typedef Constants *******/
#define SCD(t) scanf("%d", &t)
#define SCLD(t) scanf("%ld", &t)
#define SCLLD(t) scanf("%lld", &t)
#define SCC(t) scanf("%c", &t)
#define SCS(t) scanf("%s", t)
#define SCF(t) scanf("%f", &t)
#define SCLF(t) scanf("%lf", &t)
#define MEM(a, b) memset(a, (b), sizeof(a))
#define FOR(i, j, k, in) for (int i = j; i < k; i += in)
#define RFOR(i, j, k, in) for (int i = j; i >= k; i -= in)
#define REP(i, j) FOR(i, 0, j, 1)
#define RREP(i, j) RFOR(i, j, 0, 1)
#define all(cont) cont.begin(), cont.end()
#define rall(cont) cont.end(), cont.begin()
#define FOREACH(it, l) for (auto it = l.begin(); it != l.end(); it++)
#define IN(A, B, C) assert(B <= A && A <= C)
#define MP make_pair
#define PB push_back
#define INF (long double)1e19
#define EPS 1e-9
#define PI 3.1415926535897932384626433832795
#define MOD 1000000007
#define read(type) readInt<type>()
const double pi = acos(-1.0);
typedef pair<int64, int64> PII;
typedef vector<int64> VI;
typedef vector<string> VS;
typedef vector<PII> VII;
typedef vector<VI> VVI;
typedef map<int64, int64> MPII;
typedef set<int64> SETI;
typedef multiset<int64> MSETI;

typedef long long ll;
typedef long double ld;
typedef unsigned int uint;
typedef unsigned long long ull;

/****** Template of some basic operations *****/
template <typename T, typename U>
inline void amin(T &x, U y) {
  if (y < x) x = y;
}
template <typename T, typename U>
inline void amax(T &x, U y) {
  if (x < y) x = y;
}
/**********************************************/

int64 R, C;
char arr[500][500];
int64 X, Y;
int64 delx[2] = {-1, 1};
int64 dely[2] = {-1, 1};
bool vis[500][500];
/**************************************/

int64 solve() {
  int64 res = R * C;

  // try with bfs?

  priority_queue<pair<int64, PII>,vector<pair<int64, PII>>, greater<pair<int64, PII>>> q;
  // queue<pair> q2;
  q.push(make_pair(0, make_pair(X, Y)));
  vis[X][Y] = true;
  while (!q.empty()) {
    auto triple = q.top();
    q.pop();
    PII coords = triple.second;
    int64 lvl = triple.first;
    //cout << coords.first << ":" << coords.second << ";" << lvl << endl;
    if (coords.first == R - 1 || coords.second == C - 1 
    || coords.first == 0 || coords.second == 0) {
      // we found an exit
      res = lvl;
      break;
    }

    // check all the neighbours
    for (auto dx : delx) {
      int nx = coords.first + dx;
      int ny = coords.second;
      if (vis[nx][ny]) {
        continue;
      } else {
        vis[nx][ny] = true;
      }
      if (arr[nx][ny] == 'c') {
        q.push(make_pair(lvl + 1, make_pair(nx, ny)));
      } else if (arr[nx][ny] == 'D') {
        q.push(make_pair(lvl, make_pair(nx, ny)));
      }
    }

    for (auto dy : dely) {
      int nx = coords.first;
      int ny = coords.second + dy;
      if (vis[nx][ny]) {
        continue;
      } else {
        vis[nx][ny] = true;
      }
      if (arr[nx][ny] == 'c') {
        q.push(make_pair(lvl + 1, make_pair(nx, ny)));
      } else if (arr[nx][ny] == 'D') {
        q.push(make_pair(lvl, make_pair(nx, ny)));
      }
    }
  }

  return res+1;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  cin >> R >> C;
  string s;
  REP(r, R) {
    cin >> s;
    REP(c, C) { arr[r][c] = s[c]; }
  }
  cin >> X >> Y;
  X--;
  Y--;
  // shortest path? bfs?
  cout << solve() << endl;
  return 0;
}
/********  Main() Ends Here *************/
