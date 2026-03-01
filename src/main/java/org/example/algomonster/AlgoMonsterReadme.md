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

<a id="graph-stress-test-your-understanding"></a>
## Stress Test Your Understanding
#### 1. ✅ Network delay time.
Dijkstra's algorithm. Shortest path in a weighted graph.

Because:
* Weighted directed graph
* Need shortest path from one source
* Final answer = max distance among reachable nodes

#### ✅ Redundant connection
You said: DSU

✔ Correct.

We detect cycle while adding edges dynamically.
#### ✅ Minimum cost to connect points in plane
You said: MST (Kruskal + DSU)
✔ Correct.

This is classic MST.

Greedy rule:

Pick smallest edge that does not form a cycle

#### ✅ Word ladder
You said: BFS

✔ Correct.

Why?

Signal words:

    Minimum transformations 
    Fewest steps
    Unweighted transitions
    Each word transformation = cost 1.

Level expansion property:

    𝑑𝑖𝑠𝑡[𝑣]= 𝑑𝑖𝑠𝑡[𝑢] + 1

#### ✅ Alien dictionary
You said: Topo

✔ Correct.

Dependency ordering of characters → Directed acyclic graph → Topo.
#### ✅ Evaluate division equations
DFS + Backtracking

#### ✅ Number of islands
DFS

---

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

<a id="interview-trick"></a>
## 🧩 Interview Trick Awareness
Some problems look like shortest path but are actually MST.

**Example:**
- “Minimum cost to connect all points.” — That is MST, not Dijkstra.


# Appendix
1. Visualization of algorithms. Discovered when searching for Tarjan's algorithm [WilliamFiset YouTube Playlist](https://www.youtube.com/@WilliamFiset-videos/playlists)
2. William Fiset's [GitHub Repo](https://github.com/williamfiset/algorithms)