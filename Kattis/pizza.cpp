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

int64 N, x, y, d;
int64 mat[101][101];
/**************************************/

int64 partx[101];
int64 party[101];

int64 solve() {
  int64 res = (int64)INF;
  REP(i, x) { partx[i] = 0; }
  REP(j, y) { party[j] = 0; }
  REP(i, x) {
    REP(j, y) {
      partx[i] += mat[i][j];
      party[j] += mat[i][j];
    }
  }

  // generate the first score
  int64 score = 0;
  REP(i, x) {
    REP(j, y) { score += mat[i][j] * (i + j); }
  }
  amin(res, score);
  int64 prevx = 0;
  int64 prevy = 0;
  int64 prev = score;
  // O(10^4*10^2) = O(10^6)
  REP(i, x) {
    REP(j, y) {
      //cout << i << "," << j << "=" << score << endl;
      amin(res, score);
      // O(100)
      for (int k = 0; k <= j; k++) {
        score += party[k];
      }
      for (int k = j + 1; k < y; k++) {
        score -= party[k];
      }
    }
    score = prev;
    // O(100)
    for (int k = 0; k <= i; k++) {
      score += partx[k];
    }
    for (int k = i + 1; k < x; k++) {
      score -= partx[k];
    }
    prev = score;
  }

  return res;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  cin >> N;

  REP(t, N) {
    cin >> y >> x;
    REP(i, x) {
      REP(j, y) { cin >> mat[i][j]; }
    }

    cout << solve() << " blocks" << endl;
  }

  return 0;
}
/********  Main() Ends Here *************/
