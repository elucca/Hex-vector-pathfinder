<<WORK IN PROGRESS>>

# Week 6 progress report

## What has happened

- I finally have a heuristic that a) works and is, I believe, admissible and b) provides worthwhile results, making pathfinding, at a guess, about ten times faster than Dijkstra's algorithm. Performance needs further testing though. This heuristic is based a sum of the vector heuristic I implemented last week (which is exact - it considers the difference between the current and goal velocity vectors) and an inexact heuristic estimating the minimum time (in turns/state transitions) to the goal, both weighted by the same multipliers as the cost function.
- I implemented some more vector math and miscellaneous stuff needed by the heuristic and elsewhere. I'm now expressing vector magnitudes in terms of their length measured in hexes rather than their Euclidean length. This is much more relevant and makes more intuitive sense. (For example, the meaning of velocity being 2 hexes per turn is clear, whereas the meaning of a velocity of 2.82 is unclear.)
- I've reached a point where for my part I'm willing to call the algorithmic part done, in a minimum viable way: I have a working graph and pathfinding algorithm, with some variations to compare for performance, and I've managed to make a couple worthwhile heuristics. I'd like to go further on this because there's certainly room to further improve it (and it's not as fast as I'd like, considering that I'd like to later use this in an actual game), but for now it's probably best to start focusing on implementing data structures.
