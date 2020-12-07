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

/**************************************/
int64 x11;
int64 y11;
int64 x22;
int64 y22;
char op;
int64 N;

int64 gcd(int64 a, int64 b) {
  if (a < 0) {
    a *= -1;
  }
  if (b < 0) {
    b *= -1;
  }

  if (b == 0) {
    return a;
  }

  if (b > a) {
    return gcd(b, a);
  }

  return gcd(b, a % b);
}

int64 lcm(int64 a, int64 b) { return abs(a * b) / gcd(a, b); }

void solve() {
  int64 rx, ry, hcf;

  switch (op) {
    case '+':
      rx = x11 * y22 + x22 * y11;
      ry = y11 * y22;

      break;
    case '-':
      rx = x11 * y22 - x22 * y11;
      ry = y11 * y22;
      break;
    case '*':
      rx = x11 * x22;
      ry = y11 * y22;
      break;
    case '/':
      rx = x11 * y22;
      ry = y11 * x22;
      break;
  }

  hcf = gcd(rx, ry);
  rx /= hcf;
  ry /= hcf;

  if (ry < 0) {
    ry = -1 * ry;
    rx = rx * -1;
  }
  cout << rx << " / " << ry << endl;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  cin >> N;

  REP(i, N) {
    cin >> x11 >> y11 >> op >> x22 >> y22;
    // calculate x1/y1 op x2/y2
    solve();
  }
  return 0;
}
/********  Main() Ends Here *************/
