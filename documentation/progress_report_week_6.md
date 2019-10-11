# Week 6 progress report

## What has happened

- I finally have a heuristic that a) works and is, I believe, admissible and b) provides worthwhile results, making pathfinding, at a guess, 5-10 times faster than Dijkstra's algorithm. Performance needs further testing though. This heuristic is based a sum of the vector heuristic I implemented last week (which is exact - it considers the difference between the current and goal velocity vectors) and an inexact heuristic estimating the minimum time (in turns/state transitions) to the goal, both weighted by the same multipliers as the cost function.
- I implemented some more vector math and miscellaneous stuff needed by the heuristic and elsewhere. I'm now expressing vector magnitudes in terms of their length measured in hexes rather than their Euclidean length. This is much more relevant and makes more intuitive sense. (For example, the meaning of velocity being 2 hexes per turn is clear, whereas the meaning of a velocity of 2.82 or some such is unclear.)
- I've reached a point where for my part I'm willing to call the algorithmic part done, in a minimum viable way: I have a working graph and pathfinding algorithm, with some variations to compare for performance, and I've managed to make a couple worthwhile heuristics. I'd like to go further on this because there's certainly room to further improve it (and it's not as fast as I'd like, considering that I'd like to later use this in an actual game), but for now it's probably best to start focusing on implementing data structures.
- I finished implementing a minimum heap and replaced Java's priority queue with that.
- I implemented a dynamic (resizable) array to replace Java's lists, but I've yet to plug it in since I didn't have time to properly test it.

## Issues

- Besides the list, there are still lingering Java data structures, most notably HashSet, and probably some minor algorithms.
- The minimum heap currently has a fixed array size, which is usually too large, and will crash the program if the pathfinding algorithm expands too many nodes.

## Next week

- Finish the dynamic array and plug it in.
- Try to replace remaining Java data structures and algorithms.
- Continue working on documentation and testing.
- If time permits, continue working on the algorithm and seeking better heuristics.

Time taken: ~16 hours
