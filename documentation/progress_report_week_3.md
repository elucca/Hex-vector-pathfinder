# Week 3 progress report

## What has happened

- I finished the core functionality of the Graph class, the vital piece of the program that generates the graph on the fly. Previously missing features like assigning edge weights are now there, and it accounts for parameters for weighing time or delta-v spent (one or the other, or any compromise) in edge weights.
- I fixed numerous bugs I had made in the graph, coord transforms and elsewhere.
- Once I had a graph, I implemented Dijkstra's algorithm. This was fairly straightforward. This is set up so that it's very easy to change into A* with the addition of a heuristic.
- I wrote more tests. Contrary to what I expected, this was the most time-consuming part! Test cases for the Graph class were not trivial to write at all, because I needed to test, for example, that it finds the right neighbors for a node - and the graph I have is not easy to intuitively understand. These tests are not as exhaustive yet as I'd like, and bugs very possibly remain.
- I changed the map to be rectangular, like the image I have in the documentation folder. Previously it was rhombus-shaped, which is easier to generate and test, but it was always intended to be rectangular. Implementing a rectangular map is a bit more involved due to the coordinate system used. (When I say "changed the map", I mean changing the nodeExists method in Graph that is what determines if a node in fact exists, including whether it is on the map or not.)
- I updated tests and documentation as I went.

## Cool stuff

The pathfinder can currently be used to find paths where the ship starts from a standstill, flies to the destination and stops there, with Newtonian physics. It's quite neat to see how if you set the pathfinder to minimize time spent and ignore delta-v spent it accelerates to the midpoint, and decelerates from there. If you instead set it to minimize delta-v spent and ignore time spent, it accelerates once to a minimum velocity, drifts all the way to the destination and brakes there. Using both multipliers makes it use some compromise - spent some dV accelerating, drift some of the way there, then brake.

## Questions

- The pathfinding works in some cases, but not in others. The goal I set last week - to have *some* pathfinding, not necessarily correct, turns out to have been very fitting... In particular, it works if the starting velocity vector and the goal velocity vector is zero, and then only in some cases. It finds paths which appear to be correct shortest path according to the supplied cost parameters. I've yet to write tests for this, but I've verified a few cases on paper. In cases where the start or goal velocity vectors are not zero, it does not work in the vast majority of cases, but works in some special cases. In the failure cases, it simply does not find any path, even though many paths should exist. The reason for this currently stumps me - pathfinding is done with bog standard Dijkstra. Then the Graph class is suspect, even though I can't yet find any bug there.
- Could the cost multipliers be expressed in a more meaningful way? Currently they work, in that say setting time cost modifier to be higher makes it value minimizing time more, but there is terribly clear meaning to what a multiplier of 2 or 100 means in practice, or how it plays with the delta-v modifier.

## Next week

- Make the pathfinding work in a general case. It's very hard to estimate if this is a small fix, or if it requires major rethinking. It could take me an hour or it could take me whole week!
- If I get the pathfinding to work, implement A* and begin experimenting with different heuristics.
