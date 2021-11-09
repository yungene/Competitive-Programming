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
typedef double ld;
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
const int N = 510;
int64 n, k;
ld arr[N];
ld cumsum[N];
ld res[N];
ld combs[N];

ld binom[N][N];

ld binomialCoeff(ld n, ld k) {
  // Base Cases
  if (k > n) return 0;
  if (k == 0 || k == n) return 1;

  // Recur
  return binomialCoeff(n - 1, k - 1) + binomialCoeff(n - 1, k);
}

double get(int64 st, int64 fi) {
  if (st < 0) {
    st += n;
  }
  if (fi > st) {
    return cumsum[fi] - cumsum[st];
  }
  return get(st, n) + get(0, fi);
}

int64 solve() {
  cumsum[0] = 0;
  for (int i = 0; i < n; i++) {
    cumsum[i + 1] = cumsum[i] + arr[i];
    // cout << cumsum[i+1] << " ";
  }

  if(k == 1){
    REP(i, n) { res[i] = 100.0 / n; }
    return 0;
  }

  for (int64 d = 0; d <= n - 2; d++) {
    combs[d] = binom[n-d- 2][k - 2] / binom[n][ k];
  }
  // cout << endl;
  ld nn = (ld)n;
  ld kk = (ld)k;
  for (int i = 0; i < n; i++) {
    for (int u = 0; u < n - 1; u++) {
      ld val = get(i - u, i + 1);
      // ld prob = (kk/nn)*((kk-1.0)/(nn-1));
      ld prob = combs[u];
      // for(int j = 0; j < u; j++){
      //   prob *= 1- (kk-2)/(nn-2-j);
      // }
      // cout << i << " " << u << " " << prob << " " << val << " " << prob*val
      // << endl;
      res[i] += prob * val;
    }
  }

  ld sum = 0;
  REP(i, n) { sum += res[i]; }
  REP(i, n) { res[i] = 100 * (res[i] / sum); }

  return 0;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/gnollhypothesis
  cin >> n >> k;
  REP(i, n) { cin >> arr[i]; }

  for(int i = 0; i < N; i++){
    binom[i][0]=binom[i][i] = 1;
    for(int k = 1; k < i; k++){
      binom[i][k] = binom[i-1][k-1] + binom [i-1][k];
      //cout << binom[i][k] << " ";
    }
  }

  if (n == 1 || n == k) {
    REP(i, n) { cout << setprecision(7) << arr[i] << " "; }
    cout << endl;
    return 0;
  }
  solve();
  cout<< fixed;
  REP(i, n) { cout << setprecision(7) << res[i] << " "; }
  cout << endl;
  return 0;
}
/********  Main() Ends Here *************/
