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

using namespace std;

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
#define INF (int)1e9
#define EPS 1e-9
#define PI 3.1415926535897932384626433832795
#define MOD 1000000007
#define read(type) readInt<type>()
const double pi=acos(-1.0);
typedef pair<int, int> PII;
typedef vector<int> VI;
typedef vector<string> VS;
typedef vector<PII> VII;
typedef vector<VI> VVI;
typedef map<int,int> MPII;
typedef set<int> SETI;
typedef multiset<int> MSETI;
typedef long int int32;
typedef unsigned long int uint32;
typedef long long int int64;
typedef unsigned long long int  uint64;

typedef long long ll;
typedef long double ld;
typedef unsigned int uint;
typedef unsigned long long ull;

/****** Template of some basic operations *****/
template<typename T, typename U> inline void amin(T &x, U y) { if(y < x) x = y; }
template<typename T, typename U> inline void amax(T &x, U y) { if(x < y) x = y; }
/**********************************************/

int m; int l;
int M; int L;
int tm; int tl;

/**************************************/


int dist(int a, int b){
  return abs(a-b);
}

/******** User-defined Function *******/

// cow has to be at position M, L before tm and tl
// speed is one unit per second. 
string solve(){
  // res = 0;
  string res = "";

  // check for itnersection of paths m -> M and l -> L

  // try to enumarate all the valid walks
  // e.g. start -> m -> M -> l -> L
  //      s -> m -> l -> L -> l -> M
  //      s -> l -> L -> m -> M
  //      s -> l -> m -> M -> m -> L
  int s = 0;
  if(dist(s,m)+dist(m,M) <= tm && dist(s,m)+dist(m,M)+dist(M,l)+dist(l,L) <= tl){
    return "possible";
  }

  if(dist(s,l)+dist(l,L) <= tl && dist(s,l)+dist(l,L)+dist(L,m)+dist(m,M) <= tm){
    return "possible";
  }

  if(dist(s,m)+dist(m,l)+dist(l,L) <= tl && dist(s,m)+dist(m,l)+dist(l,L)+dist(L,l)+dist(l,M) <= tm){
    return "possible";
  }

  if(dist(s,l)+dist(l,m)+dist(m,M) <= tm && dist(s,l)+dist(l,m)+dist(m,M)+dist(M,m)+dist(m,L) <= tl){
    return "possible";
  }

  return "impossible";
}



/********** Main()  function **********/
int main()
{
  cin >> m >> l >> M >> L >> tm >> tl;

 cout << solve() << endl;


    return 0;
}
/********  Main() Ends Here *************/
