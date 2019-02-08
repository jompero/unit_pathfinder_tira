# Design document
Unit Pathfinder is an application that solves shortest path problem for a unit on a 2D grid.  
  
For benchmarking both Dijkstra’s and A* algorithms are used for time and space complexity as they are well known path finding algorithms.

## Algorithm design and Time complexity
Essentially two main algorithms are generated, Dijkstra's shortest path and A*, both which take a start and end position and returns the shortest path.
  
Where V denotes the number of vertices and E the number of edges in the graph the two algorithms are expected to run in time complexity of:
* Dijkstra's algorithm: O(|E|+|V|log|V||)
* A*: O(|E|)

## Data structures and Space complexity
Priority Queue will be implemented as both Dijkstra and A* utilizes it when determining which vertex to visit.  

Space Complexity for both algorithms is O(|V|) as visited vertices need to be stored in aformentioned queue, which in the worst case means that both algorithms visit each vertex in the graph.

## Input / Output
### Input
* Map (Nathan Sturtevant, Moving AI Labs Warcraft III 2D maps are to be used)
* Starting coordination
* Destination coordination
### Ouput
* Shortest path found (both algorithms are expected to return the same path)
* Benchmarking results
* Time
* Vertices enqueued

## Sources
 1. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 1. https://en.wikipedia.org/wiki/A%2A_search_algorithm
 1. http://bigoref.com/
 1. https://www.movingai.com/benchmarks/mapf.html
