# Week 5 progress report

## What has happened?
- I implemented A* with a variety of heuristics, so far mostly experimental. The two I implemented first (one based on the Manhattan distance between goal and desination hexes, and the other based on both hexes and vectors considered as a six-dimensional state space) are not admissible, and thus produce very bad paths. I will likely end up removing these since they're not good for much but implementing them and putting thought into why they don't work was informative. The third (ASTAR_VECTOR) considers the magnitude difference between the current node's vector and the goal vector, i.e. how much dV is at minimum needed to get from one to the other. This should be admissible and appears to produce current results, and cuts runtime to about half compared to Dijkstra. It completely neglects time, however, so it is not ideal. Further development is needed.
- Mostly I spent time reading about heuristics, thinking about heuristics, and talking about heuristics... Turns out coming up with a useful and admissible heuristic in this state space is harder than I thought. Basic ideas like Manhattan or Euclidean distance do not work - since the state space consists of combinations of location and velocity, there are effects like more dV spent to go at higher velocity reducing the time spent. I did come up with an idea for a heuristic that should work with some friends, and we even came up with a (hopefully correct) proof for its admissibility. Not sure yet how to implement it though.
- Miscellaneous bugfixing/refactoring/maintenance. Notably it's no longer possible to input invalid vectors to try and find paths to locations that don't exist, as vectors are now stored in axial hex coords. (And converted to cube coords on the fly when needed.)

## Next week
- I want to do tests for the pathfinding algorithm. This is a bit overdue since I'm not actually positively sure it works - though through manual testing it does seem to work!
- Finish up an idea for an A* heuristic that should be considerably better. This will combine the vector heuristic with a heuristic that also accounts for time and not solely delta-v.
- Hopefully finally get my heap implementation working.

Time spent: ~10 hours
