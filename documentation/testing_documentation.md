This document is currently a work in progress, and will be updated as the testing of the program progresses.

# Testing

I've sought to test each part of program logic with JUnit tests. JaCoCo is used to generate code coverage reports.

In addition to unit tests the program contains performance testing to investigate and compare the performane of the implemented pathfinding algorithms.

## Unit testing

### Graph

Since the graph generates nodes on the fly (when a pathfinding algorithm reaches a node), it's important to verify that it generates the right nodes. As this is difficult to exhaustively test, I have focused on testing edge cases. 

- I test that a hex map of a particular size contains all the hexes it should have that it should have, and that it has no extraneous hexes.
- I test that a node has the neighbors it should have. Not all tests related to this are implemented yet.

### Pathfinder

Currently the pathfinder tests verify that the found path is valid, i.e. that it actually exists on the graph. This test is dependent on the Graph class finding a node's neighbors correctly, which is tested in its own test class.

Pathfinding can't be exhaustively tested, but I will implement some further tests to verify that the found paths are indeed the shortest possible ones.

### CoordTransform

Contains tests for methods used to transform cube coordinates to axial coordinates and vice versa.

### VectorMath

Tests the vector sum and magnitude methods in VectorMath.

## Performance testing

The PerformanceTester class tests the performance of the implemented algorithms and prints the results. The performance tester is supplied with an array of test cases. Each algorithm runs each of the test cases, and the average result is printed for each.

In addition, each algorithm runs each test case several times in order to minimize the effect of incidental factors like garbage collection on the end result. The first run is not accounted for to avoid JIT-compilation possibly having an effect. The number of runs is by default three, but can be adjusted by adjusting the constant in PerformanceTester. Higher numbers may result in inconeniently long run times on large graphs.
