#include <iostream>
#include <set>
#include <vector>

class Node {
 public:
  int f1, f2;
  int id;
  std::vector<int> edges;
  Node(int f1_, int f2_, int id_) : f1(f1_), f2(f2_), id(id_) {}
};

std::vector<Node> adj;
int visited[10001];

void vClear() {
  for (int i = 0; i < 10001; i++) {
    visited[i] = 0;
  }
}

void dfs(std::set<int>& set, int node) {
  if (visited[node]) {
    return;
  }
  visited[node] = 1;
  Node& n = adj[node];
  for (int vi : n.edges) {
    Node& v = adj[vi];
    if (n.f1 == v.f1 || n.f1 == v.f2) {
      set.insert(n.f1);
    }
    if (n.f2 == v.f2 || n.f2 == v.f1) {
      set.insert(n.f2);
    }
    dfs(set, v.id);
  }
}

int count(int u) {
  std::set<int> set;
  dfs(set, 0);
  return set.size();
}

void solve(int prev, int curr) {
  if (visited[curr]) {
    return;
  }
  visited[curr] = 1;
  Node& u = adj[curr];
  // f1 is already set up by the curr
  if (u.edges.size() == 1) {
    Node& v = adj[u.edges[0]];
    u.f1 = v.f1;
    u.f2 = v.f2;
  } else {
    for (auto iter = u.edges.begin(); iter != u.edges.end(); iter++) {
      if (visited[*iter]) {
        continue;
      }
      Node& v = adj[*iter];
      v.f1 = u.f2;
      solve(curr, *iter);
    }
  }
}

void solve(int start) {
  Node& u = adj[start];
  visited[start] = 1;
  if (u.edges.size() == 1) {
    Node& v = adj[u.edges[0]];
    v.f1 = u.f1;
    v.f2 = u.f2;
    solve(start, u.edges[0]);
  } else {
    auto iter = u.edges.begin();
    Node& v = adj[*iter];
    v.f1 = u.f1;
    solve(start, *iter);
    iter++;
    for (; iter != u.edges.end(); iter++) {
      Node& v = adj[*iter];
      v.f1 = u.f2;
      solve(start, *iter);
    }
  }
}

int main() {
  int n;
  std::cin >> n;
  int f = 1;
  for (int i = 0; i < n; i++) {
    adj.emplace_back(f++, f++, i);
  }
  for (int i = 0; i < n-1; i++) {
    int u, v;
    std::cin >> u >> v;
    u--;
    v--;
    adj[u].edges.emplace_back(v);
    adj[v].edges.emplace_back(u);
  }

  vClear();
  solve(0);
  vClear();
  int res = count(0);
  //std::cout << res << std::endl;

  for (auto kIter = adj.cbegin(); kIter != adj.cend(); kIter++) {
    std::cout << kIter->f1 << " " << kIter->f2 << std::endl;
  }
   }