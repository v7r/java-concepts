# Backtracking 2 - Aggregation
All the backtracking problems we have seen so far ask us to generate all the combinations of things. For example, generating all combinations or permutations of letters, generating all valid parentheses and generating all valid palindrome partitions.

In this section, we will look at problems that ask questions such as

- Is it possible to make up a word using other words from a dictionary?
- Find the number of ways to decode a message
- Find the minimum number of coins to make up an amount

We categorize these "aggregation" problems because we aggregate the return value from 
child recursive calls to parent and bubble them up. It's very similar to how 
[Max Depth of a Tree](../../dfs/MaxDepthOfATree.java) and 
[Visible Tree Node](../../dfs/NumberOfVisibleNodes.java) 
aggregate return values.

Here's the backtracking template modified to aggregate return values:
```
private static int dfs(Integer startIndex, List<T> target) {
    if (isLeaf(startIndex)) {
        return 1;
    }

    ans = initialValue;
    for (T edge : getEdges(startIndex, [...additional states])) {
        if (additional states) {
            update([...additional states]);
        }
        ans = aggregate(ans, dfs(startIndex + edge.length(), [...additional states])
        if (additional states) {
            revert([...additional states]);
        }
    }
    return ans;
}
```
The main differences between this and the previous template are:

- no path and push/pop since we don't need to actually generate the solutions. We just need the aggregated value.
- use return value to aggregate results from child dfs calls.

Depending on what the problem asks for, the initial_value and aggregate function here can be

| Problem statement                | Initial Value | Aggregate    |
|----------------------------------|---------------|--------------|
| If it's possible? does it exist? | boolean value | logical OR   |
| Number of ways                   | 0             | addition (+) |
| Maximum/minimum ways/value to...                 | 0, if         | max/min      |

We will go through a couple of concrete problems in the following articles. Before that, let's introduce one more useful technique that is often used in backtracking aggregation problems.