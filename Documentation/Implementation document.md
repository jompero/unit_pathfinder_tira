# Implementation document
![Image of Unit Pathfinder UML Diagram](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/UML.jpg)
  
Unit Pathfinder now consists of 3 main parts; the pathfinders, graph and GUI. The user can load Maps by using the GUI and trigger the pathfinders by clicking coordinates on the graph. In general the program is as designed from the start, however, with the addition of JPS pathfinder the weighted graph was edited to a uniform-cost.

## Complexity and performance
Essentially two main algorithms are generated, Dijkstra's shortest path and A*, both which take a start and end position and returns the shortest path.
  
Where V denotes the number of vertices and E the number of edges in the graph the two algorithms are expected to run in time complexity of:

### Dijkstra
As MyPriorityQueue uses Binary Heap, the Time Complexity of handling of Nodes is O((|E|+|V|)log|V|). In worst case a Node is created for each visited vertices for a Space Complexity of O(|V|).
### A*
In worst case, A* would go through all vertices giving it a Time Complexity of O(|E|). In worst case a Node is created for each visited vertices for a Space Complexity of O(|V|).
### JPS
In worst case, JPS would find half of all vertices interesting (and a Node would be created for each) for Time and Space Complexities of O(|E|) and O(|V|/2) -> O(|V|).

## Improvements
As per Improving Jump Point Search, Daniel Harabor and Alban Grastien, 2014, JPS could be improved "from several factors to over one order of magnitude."

Additionally my implementations of PriorityQueue and ArrayList could potentially use optimization as I noticed slower performance when larger lists are needed (i.e. Dijkstra slowed down a lot). For example, I could investigate implementing the Priority Queue with a Fibonacci Heap instead of current Binary Heap.

## Sources
 1. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 1. https://en.wikipedia.org/wiki/A%2A_search_algorithm
 1. http://bigoref.com/
 1. https://www.movingai.com/benchmarks/mapf.html
 1. http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf
 1. https://zerowidth.com/2013/05/05/jump-point-search-explained.html
 1. https://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-icaps14.pdf
 1. http://pages.cs.wisc.edu/~vernon/cs367/notes/11.PRIORITY-Q.html#heap
