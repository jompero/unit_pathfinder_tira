# Testing document

## Benchmarking
Benchmarking was performed on three maps;	
1. open map with little obstacles (warcraft iii, duskwood; 'Duskwood')
1. a medium width maze (maze512-8-0; 'Maze')
1. open map with details (AR0602SR; 'Baldur's Gate')
  
100 paths of various length were calculated on each map and tracked in [Benchmarking sheet](https://docs.google.com/spreadsheets/d/1kvcresW9xrQ9jbkIvzC-njTkVvEbgHUNp7KCUPo89GM/edit?usp=sharing).
  

### Duskwood
![Chart of Duskwood](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/Warcraft%20III_%20warcraft%20iii%2C%20duskwood.png)
  
On Duskwood it can be observed that Dijkstra is performing extremely poorly as it needs to expand in multiple horizons. A few 'hiccups' can be observed on some longer paths due to the path following closely on one of the edges where Dijkstra will expand on approximately half of the horizon compared to same length with no wall. A* and JPS are performing at a similar speed at all distance with A* coming on top of most often. This can be attributed to the fact that JPS behaves similar to A* when it cannot find "interesting" nodes.  
  
 ### Maze
![Chart of Maze](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/Maze_%20maze512-8-0.png)
  
On Maze on the other hand, JPS is the clear winner. It is able to find interesting node quickly and zooms through the labyrinth even faster than on Duskwood. Dijkstra on this map is slightly faster than on Duskwood as the walls choke some horizon. A* is clearly losing the value of it's heuristics as it regresses closer to Dijkstra in behavior and thus speed.  
  
### Baldur's Gate
![Chart of Baldur's Gate](https://github.com/jompero/unit_pathfinder_tira/blob/master/Documentation/Resources/Baldur's%20Gate_%20AR0602SR.png)
  
Lastly, Baldur's Gate is not showing much difference between Maze. A* is slightly faster as less walls are in the way. A bigger notable difference is to JPS, however. As Baldur's Gate has more details in favor of complexity, JPS latches on to many spots it considers interesting. While so, it does not seem to slow the algorithm down by a great deal.

## Unit Tests
## unit_pathfinder_tira
Main package, nothing testable.
### gui
Done manually. Not sure if I can generate Unit Tests for this package.

package | coverage
gui						| 0.0%
pathfinder				| 93.3%
graph					| 97.2%
mycollections			| 98.9%
unit_pathfinder_tira		| 0.0%
Total					| 60.4%