# Implementation document
![Image of Unit Pathfinder UML Diagram](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/UML.jpg)
  
Unit Pathfinder now consists of 3 main parts; the pathfinders, graph and GUI. The user can load Maps by using the GUI and trigger the pathfinders by clicking coordinates on the graph. In general the program is as designed from the start, however, with the addition of JPS pathfinder, the weighted graph was edited to a uniform-cost.

## Complexity and performance
Essentially two main algorithms are generated, Dijkstra's shortest path and A*, both which take a start and end position and returns the shortest path.
  
Where V denotes the number of vertices and E the number of edges in the graph the two algorithms are expected to run in time complexity of:

### Dijkstra
O(|E|+|V|log|V||)
### A*
O(|E|)
### JPS
In worst case, JPS would find all Nodes interesting giving it a Time Complexity of O(|E|).

## Improvements
As per Improving Jump Point Search, Daniel Harabor and Alban Grastien, 2014, JPS could be improved "from several factors to over one order of magnitude."

## Sources
 1. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 1. https://en.wikipedia.org/wiki/A%2A_search_algorithm
 1. http://bigoref.com/
 1. https://www.movingai.com/benchmarks/mapf.html
 1. http://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-aaai11.pdf
 1. https://zerowidth.com/2013/05/05/jump-point-search-explained.html
 1. https://users.cecs.anu.edu.au/~dharabor/data/papers/harabor-grastien-icaps14.pdf
