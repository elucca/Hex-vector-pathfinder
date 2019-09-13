# Week 2 progress report

## What has happened

- I started on the program's core functionality. Thus far however it's just infrastructure - most of the graph's functionality, infrastructure for hex coordinates and coordinate transforms and a bit of vector math.
- I did further thinking on what my graph should be like, and have a good sense of how its remaining functionality should be implemented now.
- I documented the code I wrote with Javadocs.
- I wrote unit tests for most of the code that exists so far (though not the classes in the Data package, since they're trivial data containers), though tests for the Graph class are not yet exhaustive since I didn't have time to finish its functionality.

Currently the program doesn't actually do anything, but logic for the graph itself is more or less in place (which turned out to be a more complex problem than implementing some pathfinding algorithm is likely to be), along with all the infrastructure it needs. I had hoped to get something running this week, but that will have to wait until week 3. I think I have a grasp now of how I can begin to solve the actual pathfinding problem so I'm optimistic I can get something running.

## Questions
- I am not yet assigning edge weights to the graph. I think I have the right idea with generating nodes on the fly, and now I just need to give them weighs with whatever cost function I decide to have. I'm not 100% sure about how this will work yet though.
- What specific algorithms are actually useful for this project? I will start with Dijkstra, which should work, and then move on to A*. Jump point search could work, but I should also see if there are other possibilities.

## Next week
- Finish implementation of the graph.
- Get a 'walking skeleton' version of the program running: Some rudimentary pathfinding, not necessarily efficient, not necessarily even exactly correct. Just something that actually gives a result I can look at and diagnose and that lets me see if my work so far is on the right path.

Time spent: ~10 hours
