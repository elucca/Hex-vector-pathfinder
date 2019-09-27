# Week 4 progress report

## What has happened

- I resolved my problem with pathfinding only working in special cases - it was actually working as intended! A lot of my work went into trying to resolve this problem that didn't actually exist, but the time was not wasted: I spent time validating my code (Especially test cases, since I was worried I had problems that my tests didn't catch.) The most important takeaway is that I realized my 3D vector space is a subspace where the sum of components is zero. I was trying to find paths to nodes involving vectors where this isn't true, and of course a path to a non-existing node cannot (and should not!) be found.
- I implemented performance testing that measures how long each algorithm takes to find paths. This'll be more interesting once I have more algorithms, but it is already set up to compare multiple algorithm, and calculate their average runtime in a battery of tests.
- I implemented a minimum heap to eventually replace the Java library's priority queue for my algorithm. It doesn't work yet, though.
- I updated documentation (particularly clarifying some unknown bits still lingering in the project specification), and started on testing and implementation documentation.

This week it feels like I got less done, but I think much of that is because I had to spend some significant time doing further thinking of my problem - more thinking, less code. I also have a better idea of A* heuristics which I hope to try out next week.

The program is, in essence, usable! I can't guarantee yet that there are no bugs or that the paths are always valid shortest paths, but it does indeed find paths that look good (whether they actually are will take proper testing). To try it, make a graph in Main, give it to a pathfinder, and make it find a path between two nodes. When supplying nodes to it, it does not matter what the "previous" and "costSoFar" values are. However, note that the movement vectors in the start and goal nodes must be given such that the sum of components is zero, or else they are not valid (as explained above). I will change this so that in the future it won't be possible to give it invalid vectors.

## Next week
- Fix the minimum heap implementation so that it actually works.
- Implement some simple heuristic(s) for A* to enable comparisons with Dijkstra's algorithm.
- Possibly implement a set.

Time spent: ~15 hours
