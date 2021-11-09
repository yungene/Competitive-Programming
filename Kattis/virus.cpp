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
//int64 N;
string s1, s2;

/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/virus

  cin >> s1  >> s2;

  int64 res = 0;

  int64 st = 0;
  int64 fi = 0;
  int64 M = s1.length();
  int64 N = s2.length();
  REP(i, min(N,M)){


    if(s1[i] != s2[i]){
      break;
    }
    st++;
  }

  if(st == N){
    cout << 0 << endl;
    return 0;
  }

  if(st == M){
    cout << N-M << endl;
    return 0;
  }



  int64 f1,f2;
  f1 = M-1; f2 =N-1;
  while(f1 >=0 && f2 >= 0){
    if(s1[f1] != s2[f2]){
      break;
    }
    f1--;f2--;
  }

  if(f2 >= 0){
    f2++;
  }

  //cout << st << ";" << f2 << endl;

  if(st == 0 && f2 ==N-1){
    cout << N << endl;
    return 0;
  }

  if(f2 > st){
    res = f2-st;
  } else if (st > f2 && N > M){
    res = st-f2-1;
  }
  

  cout << res << endl;

  return 0;
}
/********  Main() Ends Here *************/
