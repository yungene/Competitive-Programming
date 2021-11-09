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
int64 T;
int64 R, C;
int64 arr[102][102];
int64 dp[102][102][102];
#define WALK '.'
#define NICE '*'
#define BLOCK '#'

int64 get(int64 x1, int64 y1, int64 x2, int64 y2) {
  return dp[x1][y1][x2];
}

int64 solve() {
  FOR(r,0, R+1,1) {
    FOR(c,0, C+1,1) {
      FOR(r2,0, R+1,1) {
        //FOR(c2,0, C+1,1) {
          dp[r][c][r2] = -1*1e10; 
          //}
      }
    }
  }
  dp[0][0][0]=0;
  FOR(r,0, R,1) {
    FOR(c,0, C,1) {
      for(int64 r2 =0; r2< min(R,(int64)(r+c+1));r2+=1) {
        //FOR(c2,1, C+1,1) {
          int64 c2 = r+c-r2;
          int64 maxi = -1*1e10;
          int64 x1,y1,x2,y2;
          x1=r;y1=c;x2=r2;y2=c2;
          if (arr[x1][y1] == BLOCK || arr[x2][y2] == BLOCK) {
             dp[r][c][r2]= -1*1e10;
             continue;
          }
          int64 val  =0;
          if (arr[x1][y1] == NICE && arr[x2][y2] == NICE) {
            if (x1 == x2 && y1 == y2) {
              val++;
            } else {
              val += 2;
            }
          } else if (arr[x1][y1] == NICE) {
            val++;
          } else if (arr[x2][y2] == NICE) {
            val++;
          }          

          if(r>0){
            if(r2 > 0){
          amax(maxi, get(r - 1, c, r2 - 1, c2));
            }
          
          amax(maxi, get(r - 1, c, r2, c2));
          }
        if(c > 0){
          if(r2 > 0){
          amax(maxi, get(r, c - 1, r2 - 1, c2));
          }
          amax(maxi, get(r, c - 1, r2, c2));
        }
        if(r ==0 && c ==0){
          amax(maxi, 0);
        }

          dp[r][c][r2] = maxi+val;
          //cout << r << ";" << c << ";" << r2 << ";" << c2 << "-->" << maxi << endl;
        //}
      }
    }
  }

  return max(dp[R-1 ][C-1 ][R-1 ],(int64)0) ;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main() {
  // https://open.kattis.com/problems/tourist
  cin >> T;
  string line;
  char kar;
  REP(t, T) {
    cin >> C >> R;
    REP(r, R) {
      cin >> line;
      REP(c, C) {
        kar = line[c];
        arr[r][c] = kar;
        //cout<<kar;
      }
      //cout << endl;
    }
    cout << solve() << endl;
  }

  return 0;
}
/********  Main() Ends Here *************/
