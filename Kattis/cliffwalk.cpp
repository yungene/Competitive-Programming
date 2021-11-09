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
using triple = pair<double, PII>;
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
double a, m;
int64 W, H, X, Y;
int64 r0, c0, R, C;
// each square is 10x10m, height is in mm, that is 1000mm = 1
int64 heis[202][202];
double entry_times[202][202];
double exit_times[202][202];

double dist(int r1, int c1, int r2, int c2) {
  r1 = 10 * r1;
  r2 = 10 * r2;
  c1 = 10 * c1;
  c2 = 10 * c2;
  return sqrt((r1 - r2) * (r1 - r2) + (c1 - c2) * (c1 - c2));
}

// Charlotte knows that it is possible to approximate the tide’s water level v
// as v=0.5a⋅(cos(t2π12)+1), where t is time in hours since the last high tide
// and a is a number depending on the location, time of the year, etc. time in
// hours
double f(double t) { return 0.5 * a * (cos(t * pi * (2.0 / 12.0)) + 1); }

// It is only possible to pass between two squares of height z1,z2 if the
// absolute height difference |z1−z2| is at most 1 meter.
bool canWalk(int r1, int c1, int r2, int c2) {
  return abs(heis[r1][c1] - heis[r2][c2]) <= 1000;
}

double addTime(double curr, double t) { double next = curr + (t / 3600.0); return next;}

double dryTime(int r1, int c1) {
  double h = heis[r1][c1]/1000.0;
  if(h > 0.5*a*2){
    return 0;
  }

  double x = (h / (0.5 * a)) - 1;
  double cos1 = acos(x);
  double ans =  12 * cos1 / (2 * pi);
  return ans;
}

double isDry(int r1, int c1, double t) { return f(t - 1) <= heis[r1][c1]; }

// Charlotte walks in such a way that it takes a constant amount of time to pass
// from one square to another and during the whole time period both squares must
// be dry.
bool hasTime(int r1, int c1, int r2, int c2, double startTime) {
  double th1 = f(startTime);
  double th2 = f(startTime + m);

  return min(heis[r1][c1], heis[r1][c1]) > max(th1, th2);
}

triple make_triple(double a, int b, int c) {
  return make_pair(a, make_pair(b, c));
}

// Output one line with the maximum Euclidean distance that Charlotte can get
// from home. The distance between two squares should be measured between their
// centers.
double solve() {
  REP(r, R) {
    REP(c, C) {
      double t = dryTime(r, c);
      entry_times[r][c] = t + 1;
      exit_times[r][c] = 12 - t;

      //cout << t << "-" << 12-t << ";";
    }
    //cout << endl;
  }

  // run shortest path from home at 0h0s to every other square

  // weights = time
  // weight = time to entry time vs curr time + m
  double dst1[202][202];
  REP(r, R) {
    REP(c, C) { dst1[r][c] = 10e9; }
  }

  pair<int, int> neighbours[] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  priority_queue<triple, vector<triple>, greater<triple>> pq;
  dst1[r0][c0]=0;
  pq.push(make_triple(0, r0, c0));
  while (!pq.empty()) {
    triple tr = pq.top();
    double w = tr.first;
    int r1 = tr.second.first;
    int c1 = tr.second.second;
    pq.pop();

    // skip duplicates
    if (w != dst1[r1][c1]) {
      continue;
    }

    // consider all the edges

    for (auto p : neighbours) {
      int r2 = r1 + p.first;
      int c2 = c1 + p.second;

      if (r2 < 0 || r2 >= R || c2 < 0 || c2 >= C) {
        continue;
      }

      if (!canWalk(r1, c1, r2, c2)) {
        continue;
      }

      double entry_time = entry_times[r2][c2];

      double nw = max(addTime(entry_time, m), addTime(w, m));
      //cout << "coords:" << r2 << ";" << c2 << "---" << nw << endl;
      if (nw < dst1[r2][c2]) {
        dst1[r2][c2] = nw;
        pq.push(make_triple(nw, r2, c2));
      }
    }
  }

  // REP(r, R) {
  //   REP(c, C) { cout << dst1[r][c] << "; "; }
  //   cout << endl;
  // }

  // cout << endl;

  // run backwards

  double dst2[202][202];
  REP(r, R) {
    REP(c, C) { dst2[r][c] = 10e9; }
  }

  pq.empty();
  dst2[r0][c0]=-12.0;
  pq.push(make_triple(-12.0, r0, c0));
  while (!pq.empty()) {
    triple tr = pq.top();
    double w = tr.first;
    int r1 = tr.second.first;
    int c1 = tr.second.second;
    pq.pop();

    // skip duplicates
    if (w != dst2[r1][c1]) {
      continue;
    }

    // consider all the edges
    for (auto p : neighbours) {
      int r2 = r1 + p.first;
      int c2 = c1 + p.second;
      if (r2 < 0 || r2 >= R || c2 < 0 || c2 >= C) {
        continue;
      }
      if (!canWalk(r1, c1, r2, c2)) {
        continue;
      }

      double exit_time = -1 * exit_times[r2][c2];

      double nw = max(addTime(exit_time, m), addTime(w, m));
      //cout << "coords:" << r2 << ";" << c2 << "--- " << nw << endl;
      if (nw < dst2[r2][c2]) {
        dst2[r2][c2] = nw;
        pq.push(make_triple(nw, r2, c2));
      }
    }
  }

  // REP(r, R) {
  //   REP(c, C) { cout << -1*dst2[r][c] << "; "; }
  //   cout << endl;
  // }

  // cout << endl;

  double res = 0;
  REP(r, R) {
    REP(c, C) {
      double entry = dst1[r][c];
      double exit = -1 * dst2[r][c];
      if (entry <= exit) {
        res = max(res, dist(r0, c0, r, c));
      }
    }
  }

  return res;
  // return res.substr(0 , e);
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/cliffwalk
  cin >> a >> m >> W >> H >> X >> Y;
  R = H;
  C = W;
  r0 = Y;
  c0 = X;
  REP(h, H) {
    REP(w, W) { cin >> heis[h][w]; }
  }
  cout << setprecision(10) << solve() << endl;

  return 0;
}
/********  Main() Ends Here *************/
