#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <queue>
#include <deque>
#include <bitset>
#include <iterator>
#include <list>
#include <stack>
#include <map>
#include <set>
#include <functional>
#include <numeric>
#include <utility>
#include <limits>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <unordered_set>
#include <iomanip>

using namespace std;
typedef long int int32;
typedef unsigned long int uint32;
typedef long long int int64;
typedef unsigned long long int  uint64;
/*******  All Required define Pre-Processors and typedef Constants *******/
#define SCD(t) scanf("%d",&t)
#define SCLD(t) scanf("%ld",&t)
#define SCLLD(t) scanf("%lld",&t)
#define SCC(t) scanf("%c",&t)
#define SCS(t) scanf("%s",t)
#define SCF(t) scanf("%f",&t)
#define SCLF(t) scanf("%lf",&t)
#define MEM(a, b) memset(a, (b), sizeof(a))
#define FOR(i, j, k, in) for (int i=j ; i<k ; i+=in)
#define RFOR(i, j, k, in) for (int i=j ; i>=k ; i-=in)
#define REP(i, j) FOR(i, 0, j, 1)
#define RREP(i, j) RFOR(i, j, 0, 1)
#define all(cont) cont.begin(), cont.end()
#define rall(cont) cont.end(), cont.begin()
#define FOREACH(it, l) for (auto it = l.begin(); it != l.end(); it++)
#define IN(A, B, C) assert( B <= A && A <= C)
#define MP make_pair
#define PB push_back
#define INF (long double)1e19
#define EPS 1e-9
#define PI 3.1415926535897932384626433832795
#define MOD 1000000007
#define read(type) readInt<type>()
const double pi=acos(-1.0);
typedef pair<int64, int64> PII;
typedef vector<int64> VI;
typedef vector<string> VS;
typedef vector<PII> VII;
typedef vector<VI> VVI;
typedef map<int64,int64> MPII;
typedef set<int64> SETI;
typedef multiset<int64> MSETI;


typedef long long ll;
typedef long double ld;
typedef unsigned int uint;
typedef unsigned long long ull;

/****** Template of some basic operations *****/
template<typename T, typename U> inline void amin(T &x, U y) { if(y < x) x = y; }
template<typename T, typename U> inline void amax(T &x, U y) { if(x < y) x = y; }
/**********************************************/

int64 N;
string s1, s2;
/**************************************/


bool solve(){
  bool res = true;

  string s3 = s1;
  REP(i, s1.length()){
    if(N%2 == 0){
      if(s1[i] != s2[i]){
        return false;
      }
    } else {
      if(s1[i] == s2[i]){
        return false;
      }
    }
  }

  return true;
}
/******** User-defined Function *******/

/********** Main()  function **********/
int main()
{
  // https://open.kattis.com/problems/erase
  cin >> N; 
  cin >> s1 >> s2;
  

  if(solve()){
    cout << "Deletion succeeded" << endl;
  } else {
    cout << "Deletion failed" << endl;
  }
  return 0;
}
/********  Main() Ends Here *************/
