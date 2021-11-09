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
int64 n;
ld p, s, v;
ld c,t;

int64 solve() {
  int64 res = 500;


  return res;
  // return res.substr(0 , e);
}
/******** User-defined Function *******/

double tour_time(){
  return (1+(1.0/c))*(s/v);
}

double calc_time(){
  return pow(log2(n), c*sqrt(2)) * (((ld)n) / (p*1e9));
}

double total_time(){
  return tour_time() + calc_time();
}

double f(ld cc){
  c = cc;
  return total_time();
}
#define EPSILON 10e-6


// https://cp-algorithms.com/num_methods/ternary_search.html
double ternary_search(ld l, ld r) {
    double eps = 1e-6;              //set the error limit here
    while (r - l > eps) {
        ld m1 = l + (r - l) / 3;
        ld m2 = r - (r - l) / 3;
        ld f1 = -1*f(m1);      //evaluates the function at m1
        ld f2 = -1*f(m2);      //evaluates the function at m2
        if (f1 < f2)
            l = m1;
        else
            r = m2;
    }
    return f(l);                    //return the maximum of f(x) in [l, r]
}


/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/euclideantsp
 cin >> n >> p >> s >> v;

  c = 0;
  t = 0;
  double cmin, tmin;

  double eps = 10e-6;

  double a1 = pow(log2(n),sqrt(2));
  double a2 = s;

  // find when the convex function starts to rise
  ld c1 = f(0), c2 = f(1), cc = 1;
  while(c1 > c2){
    cc*= 2;
    c1 = c2;
    c2 = f(cc);
  }

  t = ternary_search(0, cc);

  cout << setprecision(15) << total_time() << " " << c << endl;

  return 0;
}
/********  Main() Ends Here *************/
