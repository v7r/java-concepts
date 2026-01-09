# Tech Interview Pattern | Two Pointers Introduction

Two pointers is a common interview technique often used to solve certain problems involving an iterable data structure, such as an array. As the name suggests, this technique uses two (or more) pointers that traverse through the structure. It does not have to be physically using two pointers. As long as the other pointer can be easily calculated from existing values, such as the index of the other pointer, it counts as a two pointer question.

Since "two pointers" is kind of a broad topic, there is no singular way to implement it. Depending on the questions you encounter, you need to implement the answer differently. Generally speaking, a two pointer algorithm has these characteristics:

* Two moving pointers, regardless of directions, moving dependently or independently;
* A function that utilizes the entries referenced by the two pointers, which relates to the answer in a way;
* An easy way of deciding which pointer to move;
* A way to process the array when the pointers are moved. 

## Classifications
There are a lot of ways to classify two pointer problems. Below are some classifications, although they are in no way exhaustive.

* Same Directions: These questions have two pointers that move in the same direction. Here is an example of a same direction two pointer question: Remove Duplicates.
* Opposite Directions: These questions have two pointers that move in the opposite direction. Here is an example of an opposite direction two pointer question: Two Sum Sorted.
* Two Pointers vs Sliding Window: Sliding window problems are similar to the same directions problems, only instead, the function performs on the entire interval between the two pointers. Usually, however, we keep track of the cumulative result of the window, and each time we insert/remove an item from the window, we simply update the window according to the changes instead of recalculating everything. As an example, Longest Substring without Repeating Characters is a classic sliding window problem.
* Non-array Applications The two-pointer technique is not limited to arrays. Two pointer can be done on other structures, like linked list, as long as they are iterable. For example, in Linked List Cycle, you are asked to detect a cycle from a linked list structure, and it can be solved using a two-pointer technique called Floyd's Cycle-Finding Algorithm.

## Why Use Two Pointers?
Two pointers are helpful because it often offers a more efficient solution than the naive solution. From the examples above, if we use the naive solution and use two loops to iterate through the array, the time complexity would typically be O(n^2), which is generally insufficient. If we use two pointers for this type of problem, we are often only passing through the array once with the two pointers, which means that the time complexity is often O(n).