# AlgoMonster — Algorithm Decision Cheat-sheet

## Index
- [TODO: Problems to practice](#todo)
- [Graph Algorithm Decision Cheat-sheet](#graph-algorithm-decision)
  - [BFS on Graph](#bfs)
  - [DFS on Graph](#dfs)
  - [Shortest Path (Unweighted → BFS)](#shortest-path)
  - [Matrix as Graph](#matrix-as-graph)
  - [Directed Graph — Topological Sort (Kahn)](#topological-sort)
  - [Weighted Graph — Dijkstra](#dijkstra)
  - [MST — Kruskal (with DSU)](#mst)
  - [The Master Decision Flow](#master-decision-flow)
  - [Most Common Confusion Pairs](#confusion-pairs)
  - [Interview Trick Awareness](#interview-trick)
  - [Stress Test Your Understanding](#graph-stress-test-your-understanding)
  - [Graph Interview Cheat Sheet](#graph-interview-cheat-sheet)

---

<a id="todo"></a>
## TODO: Problems to practice
1. https://algo.monster/liteproblems/647  - 647. Palindromic Substrings
2. https://algo.monster/liteproblems/2472  - 2472. Maximum Number of Non-overlapping Palindrome Substrings
3. https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/ - 30. Substring with Concatenation of All Words

---

<a id="graph-algorithm-decision"></a>
## 🧠 GRAPH ALGORITHM DECISION CHEAT-SHEET

Before coding, ask:

> ❓ What is the problem fundamentally asking?

<a id="bfs"></a>
### 1️⃣ BFS on Graph
**Use When:**
- You need level-by-level traversal
- You want minimum steps in an unweighted graph
- You need multi-source expansion
- You need layer information

**Signal Words:**
- "minimum moves"
- "shortest path"
- "fewest steps"
- "distance in grid"
- "spread"

**Mental Model:**
- Wave expanding outward.

**Complexity:**
- O(V + E)

---

<a id="dfs"></a>
### 2️⃣ DFS on Graph
**Use When:**
- You need full traversal
- You need connected components
- You need cycle detection
- You need backtracking
- You need to explore deeply before switching

**Signal Words:**
- "count components"
- "detect cycle"
- "explore all paths"
- "is there a path?"

**Mental Model:**
- Go deep, then backtrack.

---

<a id="shortest-path"></a>
### 3️⃣ Shortest Path (Unweighted → BFS)
> This is NOT regular BFS traversal. It's BFS with:
- Distance array
- Visited marking
- Return when reaching target

**Use When:**
- All edges have equal weight (or implicit weight = 1)

**Examples:**
- Knight moves
- Word ladder
- Grid shortest path

---

<a id="matrix-as-graph"></a>
### 4️⃣ Matrix as Graph
Grid problems are implicit graphs. Each cell is a node, neighbors are 4 or 8 directions.

**Choose:**
- Count islands → DFS
- Shortest path → BFS
- Multi-source spread → BFS

**Signal words:**
- "grid"
- "2D board"
- "islands"
- "infection spread"

---

<a id="topological-sort"></a>
### 5️⃣ Directed Graph — Topological Sort (Kahn)
**Use When:**
- There are dependencies
- You need a valid ordering
- Detect cycle in directed graph

**Signal Words:**
- "course schedule"
- "task dependency"
- "build order"
- "can we finish?"

**Mental Model:**
- Remove nodes with indegree 0. If processed nodes < total → cycle exists.

---

<a id="dijkstra"></a>
### 6️⃣ Weighted Graph — Dijkstra
**Use When:**
- Edges have weights
- We want minimum cost
- All weights are positive

**Signal Words:**
- "minimum cost"
- "minimum time"
- "weighted graph"
- "cheapest route"

**Mental Model:**
- Always expand smallest known distance first (min-heap).

---

<a id="mst"></a>
### 7️⃣ MST — Kruskal (with DSU)
**Use When:**
- We must connect ALL nodes
- Minimize total cost
- No direction dependency
- Build infrastructure / network

**Signal Words:**
- "connect all cities"
- "minimum cost to connect"
- "network wiring"
- "spanning"

**Mental Model:**
- Pick smallest edges without forming a cycle. DSU prevents cycles.

---

<a id="master-decision-flow"></a>
## 🚦 THE MASTER DECISION FLOW
Memorize this:

- Is it shortest path?
  - Yes → Weighted?
    - Yes → Dijkstra
    - No  → BFS

- Is it dependency ordering?
  - Yes → Topological sort

- Is it connect all with minimum total cost?
  - Yes → MST (Kruskal + DSU)

- Is it just connectivity / number of groups?
  - Static → DFS/BFS
  - Dynamic / repeated union → DSU

- Is it grid?
  - Counting → DFS
  - Minimum distance → BFS

<a id="confusion-pairs"></a>
## 🔥 Most Common Confusion Pairs
**BFS vs DFS**
- Need shortest path? → BFS
- Just explore/count? → DFS

**DFS vs DSU**
- One-time traversal → DFS
- Many union queries / dynamic edges → DSU

**Dijkstra vs MST**
- Dijkstra → Shortest path from ONE source.
- MST → Connect ALL nodes with minimum total cost.

Different goals.

---
<a id="graph-interview-cheat-sheet"></a>
## 🧠 GRAPH INTERVIEW CHEAT SHEET

(Signal → Algorithm → Why)

### 1️⃣ Connectivity Problems
🔎 Signal Words

    “Number of groups” 
    “Provinces”
    “Connected components”
    “Are they connected?”
    “Merge accounts”

✅ Use

DFS / BFS (static graph)

DSU (dynamic unions / repeated connectivity checks)

🧠 Why

We just need to group nodes into components.

⚠️ Pitfall

If edges are added incrementally → prefer DSU.

### 2️⃣ Shortest Path (Unweighted)
🔎 Signal Words

    “Minimum moves”
    “Fewest steps”
    “Shortest path”
    “Minimum buses”
    “Word ladder”

✅ Use

BFS

🧠 Why

Level-order expansion guarantees shortest path when all edges cost 1.

Core property:

⚠️ Pitfall

Do NOT use DFS for shortest path.

### 3️⃣ Shortest Path (Weighted, Positive)
🔎 Signal Words
    
    “Minimum cost”
    “Cheapest route”
    “Minimum time”
    “Weighted graph”

✅ Use

Dijkstra

🧠 Why

Greedy relaxation from smallest known distance.

Core principle:

⚠️ Pitfall

Dijkstra solves from one source — not global network connection.

### 4️⃣ Minimum Spanning Tree (MST)
🔎 Signal Words

    “Connect all cities”
    “Minimum total cost to connect”
    “Network wiring”
    “Spanning”

✅ Use

Kruskal (DSU)

OR Prim

🧠 Why

We want to minimize total edge cost globally.

Greedy rule:

⚠️ Pitfall

MST ≠ Shortest path from source.

### 5️⃣ Directed Graph – Ordering / Dependencies
🔎 Signal Words 

    “Course schedule”
    “Build order”
    “Task dependencies”
    “Alien dictionary”

✅ Use

Topological Sort (Kahn or DFS)

🧠 Why

We need valid ordering in DAG.

If nodes processed < total → cycle exists.

⚠️ Pitfall

If problem only asks “is it possible?” → cycle detection alone is enough.

### 6️⃣ Cycle Detection
Undirected Graph

    DFS with parent
    OR DSU

Directed Graph
    
    DFS + recursion stack
    OR Topological sort (cycle if incomplete)

### 7️⃣ Grid Problems (Matrix as Graph)
🔎 Signal Words
    
    “Islands”
    “Grid”
    “2D board”
    “Spread / infection”

✅ Use

Count regions → DFS

Shortest path → BFS

Multi-source spread → Multi-source BFS

### 8️⃣ Bipartite / 2-Coloring
🔎 Signal Words
    
    “Bipartite”
    “Divide into two groups”
    “No two adjacent same group”

✅ Use

BFS / DFS + 2-coloring

### 9️⃣ Bridges / Critical Connections
🔎 Signal Words
    
    “Critical connections”
    “If removed graph disconnects”
    “Bridges”

✅ Use

DFS + Low-link (Tarjan)

⚠️ Pitfall

Not DSU.

### 🔟 DAG Dynamic Programming
🔎 Signal Words
    
    “Longest path in DAG”
    “Number of ways in DAG”

✅ Use

Topological sort + DP

<a id="graph-stress-test-your-understanding"></a>
## Graphs - Stress Test Your Understanding

Quick reference — scenario → recommended approach (one-line rationale)

- Network delay time → Dijkstra — weighted shortest path; final answer = max distance from single source.
- Redundant connection → DSU (Union-Find) — dynamic union and cycle detection while adding edges.
- Minimum cost to connect points → MST (Kruskal + DSU) — connect all nodes with minimum total cost.
- Word ladder → BFS — unweighted, level-by-level minimum transformations.
- Alien dictionary → Topological sort — derive ordering from directed character dependencies.
- Evaluate division equations → DFS / backtracking — traverse graph and multiply edge weights to answer queries.
- Number of islands → DFS/BFS — count connected components in a grid.
- Critical connections → DFS low-link (bridges) ❗
- Bipartite → BFS/DFS 2-color ✅
- Minimum Buses to reach destination → BFS ❗
- Longest path in DAG → Topo + DP (or DFS memo) ⚠️
- Clone graph → DFS/BFS + map ✅
- Accounts merge → DSU ✅ 
- Check a graph can form a valid tree (no cycles) → DSU (or DFS) ✅

Quick tips:
- Weighted shortest path? Use Dijkstra.
- Unweighted shortest path / fewest steps? Use BFS.
- Dependency ordering? Topological sort (Kahn or DFS topo).
- Connect all nodes with minimal total cost? Use MST (Kruskal + DSU).

---



<a id="interview-trick"></a>
## 🧩 Interview Trick Awareness
Some problems look like shortest path but are actually MST.

**Example:**
- “Minimum cost to connect all points.” — That is MST, not Dijkstra.


# Appendix
1. Visualization of algorithms. Discovered when searching for Tarjan's algorithm [WilliamFiset YouTube Playlist](https://www.youtube.com/@WilliamFiset-videos/playlists)
2. William Fiset's [GitHub Repo](https://github.com/williamfiset/algorithms)