/*
* Note: This template uses some c++11 functions , so you have to compile it with c++11 flag.
*       Example:-   $ g++ -std=c++11 c++Template.cpp
*
*/
 
/********   All Required Header Files ********/
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

/****** Template of some basic operations *****/
template<typename T, typename U> inline void amin(T &x, U y) { if(y < x) x = y; }
template<typename T, typename U> inline void amax(T &x, U y) { if(x < y) x = y; }
/**********************************************/


/**************************************/

/******** User-defined Function *******/

class Graph {
public:
  vector<unordered_set<int>> adj;
  
};


/**************************************/
int M;
int N;
vector<PII> edges;
int last;

Graph getTranspose(const Graph& g){
  Graph g2;

  for(int i = 0; i < M; i++){
    unordered_set<int> v ;
    g2.adj.push_back(v);
  }


  for(int i =0;i <M;i++){
    for(int v: g.adj[i]){
      g2.adj[v].insert(i);
    }
  }

  return g2;
}


void dfs(const Graph& g, bool visited[], int u){
  if(visited[u]){
    return;
  }
  visited[u]=true;

  for(int v: g.adj[u]){
    dfs(g,visited,v);
  }

  last = u;

}

bool connected(const Graph& g){

  bool visited[M];
  for(int i =0; i < M; i++){
    visited[i]=false;
  }


  dfs(g,visited,0);


  for(int i =0; i < M; i++){
    if(!visited[i]){
      return false;
    }
  }

  for(int i =0; i < M; i++){
    visited[i]=false;
  }

  Graph g2 = getTranspose(g);
  dfs(g2,visited,0);


  for(int i =0; i < M; i++){
    if(!visited[i]){
      return false;
    }
  }

  return true;

}

string solve(){
  string res = "";
  Graph g;

  for(int i = 0; i < M; i++){
    unordered_set<int> v;
    g.adj.push_back(v);
  }
  for(int i = 0; i < N; i++){
    int a,b;
    cin >> a >> b;
    g.adj[a].insert(b);
    edges.push_back(make_pair(a,b));
  }

  if(N < M-1){
    return "invalid";
  }

  if(connected(g)){
    return "valid";
  }

  for(auto& p: edges){
    int u = p.first;
    int v = p.second;
      // cout << v << endl;
      g.adj[u].erase(v);
      g.adj[v].insert(u);

      if(connected(g)){
        std::ostringstream stream;
        stream << u << " " << v;
        return stream.str();
      }

      g.adj[u].insert(v);
      g.adj[v].erase(u);

  }

  return "invalid";
}


/********** Main()  function **********/
int main()
{
  int cs = 1;


  while( cin >> M >> N){
    edges.clear();
    cout << "Case " << cs << ": " << solve() << endl;
    cs++;
  }

  //cout << solve() << endl;

    return 0;
}
/********  Main() Ends Here *************/
