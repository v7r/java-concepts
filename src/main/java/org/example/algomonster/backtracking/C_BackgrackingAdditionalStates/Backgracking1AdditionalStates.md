# Backtracking - Additional States
## Additional constraints
In Palindrome Partitioning, we had the constraint that each part of the partition must be a palindrome. We solved it by checking the validity of a prefix before branching out. In some cases, the constraints imposed by the problem require us to keep additional states to check the validity of a branch.

## Backtracking 1 Template Final Form
``` 
private static void dfs(int startIndex, List<T> path, List<List<T>> res, [...additional states]) {
    if (isLeaf(startIndex)) {
        res.add(new ArrayList<>(path)); // add a copy of the path to the result
        return;
    }
    for (T edge : getEdges(startIndex, [...additional states])) {
        path.add(choice);
        if (...additional states) update(...additional states)
        dfs(startIndex + edge.length(), res, [...additional states]);
        path.remove(path.size() - 1);
        // revert(...additional states) if necessary e.g. permutations
    }
}
```

The main difference here is we are keeping one or more additional states as dfs parameters. And we need to update those states when we update start_index.

Let's see a concrete example.
Refer: [GenerateAllValidParentheses.java](GenerateAllValidParentheses.java)

