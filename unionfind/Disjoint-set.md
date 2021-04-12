# Disjoint-set



## 1. Disjoint-set operations

> A *disjoint-set data structure* maintains a collection δ = {S1; S2;.....;Sk} of disjoint dynamic sets. We identify each set by a *representative*, which is some member of the set.



Letting **x** denote an object, we wish to support the following operations:

* **MAKE-SET(x)**  :  creates a new set whose only member (and thus representative) is x. Since the sets are disjoint, we require that x not already be in some other set.
* **UNION(x,y)**  ：unites the dynamic sets that contain x and y, say S<sub>x</sub> and S<sub>y</sub>, into a new set that is the union of these two sets.  We assume that the two sets are disjoint prior to the operation.  And in practice, we often absorb the elements of one of the sets into the other set
* **FIND-SET(x)**： returns a pointer to the representative of the (unique) set containing x

---

An application of disjoint-set data structures

One of the many applications of disjoint-set data structures arises in determining the connected components of an undirected graph:

<img src="https://media.cheggcdn.com/media/f22/f228fdf5-89a2-4dd9-9d9e-61693b62837d/phpL0IbIZ.png" style="zoom:67%;" />

```pseudocode
CONNECTED-COMPONENTS(G)
1 for each vertex v in G.V
2	MAKE-SET(v)
3 for each edge (u,v) in G.E
4 	if FIND-SET(u) != FIND-SET(v)
5		UNION(u,v)

SAME-COMPONENT(u,v)
1 if FIND-SET(u) == FIND-SET(v)
2	return true
3 else return false
```

---











## 2. Linked-list representation of disjoint sets



![](https://media.geeksforgeeks.org/wp-content/uploads/Linked_List_representation_of_Disjoint_Set_Data_Structures_3.jpg)

The figure above shows a simple way to implement a disjoint-set data structure: **each set is represented by its own linked list**. 



> Time complexity analysis

* With this linked-list representation, both **MAKE-SET** and **FIND-SET** are easy, requiring O(1) time

* **UNION** operation takes  significantly more time. For example, we perform UNION(x,y) by appending y’s list onto the end of x’s list. 

  In the worst case, the above implementation of the UNION procedure requires an average of O(N) time per call because we may be appending a longer list onto a shorter list;



> A weighted-union heuristic

Suppose instead that each list also includes the length of the list (which we can easily maintain) and that we always append the shorter list onto the longer, breaking ties arbitrarily.

---









## 3. Disjoint-set forests

In a faster implementation of disjoint sets, we represent sets by rooted trees, with each node containing one member and each tree representing one set:



![](https://images0.cnblogs.com/blog/175043/201501/302022283002412.gif)

 As we shall see, although the straightforward algorithms that use this representation are no faster than ones that use the linked-list representation, by introducing two heuristics—“**union by rank**” and “**path compression**”—we can achieve an asymptotically optimal disjoint-set data structure.

We perform the three disjoint-set operations as follows :

* A MAKE-SET operation simply creates a tree with just one node. 
* We perform a FIND-SET operation by following parent pointers until we find the root of the tree. The nodes visited on this simple path toward the root constitute the find path. 
* A UNION operation causes the root of one tree to point to the root of the other

---

> Heuristics to improve the running time

A sequence of n-1 UNION operations may create a tree that is just a linear chain of n nodes. By using two heuristics, however, we can achieve a running time that is almost linear in the total number of operations m.

1. The first heuristic, **union by rank**, is similar to the weighted-union heuristic we used with the linked-list representation. The obvious approach would be to *make the root of the tree with fewer nodes point to the root of the tree with more nodes*. Rather than explicitly keeping track of the size of the subtree rooted at each node, we shall use an approach that eases the analysis. **For each node, we maintain a rank, which is an upper bound on the height of the node**. In union by rank, we make the root with smaller rank point to the root with larger rank during a UNION operation

2. The second heuristic, **path compression**, is also quite simple and highly effective. As shown below, we use it during FIND-SET operations to *make each node on the find path point directly to the root*. Path compression **does not** change any ranks

   ![](https://images2015.cnblogs.com/blog/1161357/201707/1161357-20170706161527487-252736533.png)

---



> Pseudocode for disjoint-set forests

* To implement a disjoint-set forest with the union-by-rank heuristic, we must keep track of ranks. With each node *x*, we maintain the integer value *x.rank*, which is an upper bound on the height of *x* (the number of edges in the longest simple path between *x* and a descendant leaf).
* When MAKE-SET creates a singleton set, the single node in the corresponding tree has an **initial rank of 0.** 
* Each FIND-SET operation leaves all ranks unchanged. 
* The UNION operation has two cases, depending on whether the roots of the trees have equal rank. If the roots have unequal rank, we make the root with higher rank the parent of the root with lower rank, but the ranks themselves remain unchanged. If, instead, the roots have equal ranks, we arbitrarily choose one of the roots as the parent and increment its rank

```pseudocode
MAKE-SET(x)
1 x.p = x
2 x.rank = 0


UNION(x, y)
1 LINK(FIND-SET(x), FIND-SET(y))


LINK(x,y)
1 if x.rank > y.rank
2 	y.p = x
3 else x.p = y
4 	  if x.rank == y.rank
5 			y.rank = y.rank + 1


FIND-SET(x)
1 if x != x.p
2 	 x.p = FIND-SET(x.p)
3 return x.p
```

> FIND-SET procedure 

This is a **two-pass method**: as it recurses, it makes one pass up the find path to find the root, and as the recursion unwinds, it makes a second pass back down the find path to update each node to point directly to the root.

Each call of FIND-SET(x) returns x.p in line 3. If x is the root, then FIND-SET skips line 2 and instead returns x.p, which is x; this is the case in which the recursion bottoms out. Otherwise, line 2 executes, and the recursive call with parameter x.p returns a pointer to the root. Line 2 updates node x to point directly to the root, and line 3 returns this pointer.

---

