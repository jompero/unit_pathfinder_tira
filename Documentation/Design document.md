# Design document
Turtle Path is an application that solves shortest path problem for a unit on a 2D grid.

For benchmarking both Dijkstra’s and A* algorithms are used for time and space complexity as they are well known path finding algorithms.

## Algorithm design and Time complexity
Essentially two main algorithms are generated both which take a starting and ending position and returns the shortest path.
 and
Where V denotes the number of vertices and E the number of edges in the graph the two algorithms are expected to run in time complexity of:
Dijkstra's algorithm: O(|E|+|V|log|V||)
A*: O(|E|)

## Data structures and Space complexity
Space Complexity for both algorithms is O(|V|) as visited vertices need to be stored in memory, which in the worst case means that both algorithms need to remember each vertex in the graph.

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
 l. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 l. https://en.wikipedia.org/wiki/A%2A_search_algorithm
 l. http://bigoref.com/
 l. https://www.movingai.com/benchmarks/mapf.html
