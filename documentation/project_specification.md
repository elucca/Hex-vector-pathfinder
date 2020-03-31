# Project specification

## Purpose

The intent of the project is to implement pathfinding on a hexagonal grid with Newtonian movement - a spaceship accelerating and decelerating to make it to a destination. A challenge is that I will have to account for inertia. Further, I want to not only find a path to a particular grid coordinate, but to find a path there such that the spaceship has a given velocity vector on arrival. For example, "get to this hex and stop" (or in other words, "get to hex (x,y,z) with a vector of (0,0,0)") and "fly past this hex at high speed" are meaningfully very different goals, and I want to discriminate between them. 

Something to note is that a shortest path in this kind of space is somewhat ambiguous: Do we want to optimize for the shortest time to make it to the goal, or the least velocity changes needed? (in physical terms, delta-v, or more or less, fuel spent) The program should be parameterized so as to be capable of finding paths optimizing either goal, or some compromise between them.

The state space in this problem is large: The graph that corresponds to this problem has nodes for each combination of hex coordinate and velocity vector. Maximum velocity will have to be limited for the graph to be finite. Even a relatively small hex grid may result in millions of nodes. Thus performance is important, and I'm particularly interested in comparing the performance of different algorithms.

## Algorithms to be used

- Dijkstra's algorithm
- A*
  - Various heuristics: Simple Manhattan distance, some sum of Manhattan distance and velocity vector difference, possibly some steering algorithms
- Jump point search

Dijkstra's algorithm is relevant to the problem as edge weights will vary depending on the cost to get to a node in time, delta-v, or both. It should be able to find paths reasonably efficiently. It is also the simplest to implement. A* is likely a much better solution assuming I can come up with a good heuristic. Jump point search may or may not be applicable as an alternative. 
  
## Data structures to be implemented
- Heap
- Set
- List, maybe
- Hash table if time permits

## Program inputs
- Size of hex grid in x and y dimensions.
- Starting node (hex coords and movement vector)
- Goal node (hex coords and movement vector)
- Maximum movement vector magnitude (i.e. max velocity)
- Parameters for cost function: Prioritize minimum time, minimum delta-v, or some compromise of the two.

## Goals for future development
- Handle obstacles on the map
- Add maximum number of vector changes (max. delta-v) as an input
- Consider if the current best heuristic can be further improved
- Consider other performance improvements:
  - Alternate greedy algorithms, with exact solution used in some cases (recognize edge cases where greedy doesn't work, or just use the exact solution for short searches as those cases tend to be short)
  - Faster, but inadmissible heuristics

## Estimated time and space complexities

The time complexity of Dijkstra is O(n + mlog n), and this should be the worst case scenario for this program. The average case for A* should be better, but will depend heavily on how effective a heuristic I can devise.

Space complexity is O(n). While the graph is generated on the fly, either all the nodes must be stored on the heap used by the pathfinding algorithm, or an O(n)-sized distance table must be used to store best distances thus far. 

## Language
This project will be implemented with Java as per standard course requirements.

## Sources
- Tira course materials, Tirakirja
- http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html - Resource on pathfinding and A*
- http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.86.636&rep=rep1&type=pdf - Possibly relevant steering behaviors for heuristics
