# Backtracking with Pruning
## What is pruning?
If you look up "pruning" on Google Images, this is what you get:

It literally means cutting branches off a tree.

In our case, we want to cut branches off our state-space tree. The fewer branches, the faster the algorithm runs.

## When do we want to prune a branch?
When it's clear that going into that branch would not yield a valid final state. This happens when the problem comes with one or more constraints, and the branches violates those contraints.

## Template v1.1
Here we introduce an updated template.
```
function dfs(start_index, path):
    if is_leaf(start_index):
        report(path)
        return
    for edge in get_edges(start_index):
        # prune if needed
        if not is_valid(edge):
            continue
        path.add(edge)
        # increment start_index
        dfs(start_index + len(edge), path)
        path.pop()
```
The differences are

* we added a pruning step that checks if a branch is valid using is_valid
* we increment start_index by a variable size instead of always 1