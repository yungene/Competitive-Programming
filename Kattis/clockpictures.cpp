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


/**************************************/


void solve(){

}
/******** User-defined Function *******/

vector<int> rabin_karp(string const& s, string const& t) {
    const int p = 31; 
    const int m = 1e9 + 9;
    int S = s.size(), T = t.size();

    vector<long long> p_pow(max(S, T)); 
    p_pow[0] = 1; 
    for (int i = 1; i < (int)p_pow.size(); i++) 
        p_pow[i] = (p_pow[i-1] * p) % m;

    vector<long long> h(T + 1, 0); 
    for (int i = 0; i < T; i++)
        h[i+1] = (h[i] + (t[i] - 'a' + 1) * p_pow[i]) % m; 
    long long h_s = 0; 
    for (int i = 0; i < S; i++) 
        h_s = (h_s + (s[i] - 'a' + 1) * p_pow[i]) % m; 

    vector<int> occurences;
    for (int i = 0; i + S - 1 < T; i++) { 
        long long cur_h = (h[i+S] + m - h[i]) % m; 
        if (cur_h == h_s * p_pow[i] % m){
            occurences.push_back(i);
            cout << "yay";
            break;
        }
    }
    return occurences;
}

void buildTable(string& w, VI& t)
{
  t = VI(w.length());  
  int i = 2, j = 0;
  t[0] = -1; t[1] = 0;
  
  while(i < w.length())
  {
    if(w[i-1] == w[j]) { t[i] = j+1; i++; j++; }
    else if(j > 0) j = t[j];
    else { t[i] = 0; i++; }
  }
}

int KMP(string& s, string& w)
{
  int m = 0, i = 0;
  VI t;
  
  buildTable(w, t);  
  while(m+i < s.length())
  {
    if(w[i] == s[m+i])
    {
      i++;
      if(i == w.length()) return m;
    }
    else
    {
      m += i-t[i];
      if(i > 0) i = t[i];
    }
  }  
  return s.length();
}

int arr1[360002];
int arr2[360002];
  int dels1[360002];
  int dels2[360002];
/********** Main()  function **********/
int main()
{
  //https://open.kattis.com/problems/clockpictures
  int n;
  cin >> n;

  REP(i, n){
    cin >> arr1[i];
  }
  REP(i, n){
    cin >> arr2[i];
  }

  sort(arr1, arr1+n);
  sort(arr2, arr2+n);
    //cout << "here";

  for(int i = 0; i < n-1; i++){
    dels1[i] = arr1[i+1] - arr1[i];
    dels2[i] = arr2[i+1] - arr2[i];
  }

  dels1[n-1] = 360000+arr1[0]-arr1[n-1];
  dels2[n-1] = 360000+arr2[0]-arr2[n-1];

  string s1, s2;
  stringstream ss1, ss2;
  REP(i, n){
    ss1<<dels1[i];
    ss2<<dels2[i];
  }
  REP(i,n){
    ss1<<dels1[i];
  }

  s1 = ss1.str();
  s2 = ss2.str();

  //cout << s1 << endl;
  //cout << s2 << endl;

  // Rabin-Karp is too slow I think
  //auto out = rabin_karp(s2,s1);
  int res = KMP(s1,s2);
  if(res < s1.length()){
    cout << "possible" << endl;
  } else {
    cout << "impossible" << endl;
  }
  cout.flush();


  return 0;
}
/********  Main() Ends Here *************/
