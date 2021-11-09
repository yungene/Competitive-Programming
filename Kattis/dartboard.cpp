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
double be, bb, it, ot, id, od;
double sigma;

double f(double r){
  //((1)/(2*pi*s*s)) *
  return ((r)/(sigma*sigma)) *exp((-1*r*r) / (2*sigma*sigma))  ;
}

// https://cp-algorithms.com/num_methods/simpson-integration.html
const int N = 1000 * 1000; // number of steps (already multiplied by 2)

double simpson_integration(double a, double b){
    double h = (b - a) / N;
    double s = f(a) + f(b); // a = x_0 and b = x_2n
    for (int i = 1; i <= N - 1; ++i) { // Refer to final Simpson's formula
        double x = a + h * i;
        s += f(x) * ((i & 1) ? 4 : 2);
    }
    s *= h / 3;
    return s;
}


double solve() {

  double arr_be = simpson_integration(0, be);
  double arr_bb = simpson_integration(be, bb);
  double arr_triple = simpson_integration(it, ot);
  double arr_double = simpson_integration(id, od);
  double arr_norm = simpson_integration(bb, it) + simpson_integration(ot, id);


  return (50*arr_be + 25*arr_bb + 10.5*arr_norm + 21*arr_double 
            + 31.5*arr_triple);
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/dartboard
  cin >> be >> bb >> it >> ot >> id >> od;
  cin >> sigma;
  // radii affect the propostions of each segment. If I can calculate the alnswer in terms of proportions,
  // then I could easily get the answer

  // Note that the problem is in 2d/3d, but it it the same in every dierction sort of
  // perhaps I could turn it into 1d/2d and use the averages for pies.

  cout << setprecision(10) << solve() << endl;

  return 0;
}
/********  Main() Ends Here *************/
