This document is a work in progress and will be updated as the program is completed.

# Implementation

## Implementing the problem domain

The problem the program solves is described in detail in the [project specification](documentation/project_specification.md). To perform pathfinding, it is necessary to define the hex grid, velocity vectors and the coordinate systems to be used for them.

### The hex grid

![Hex grid](documentation/hex_grid_reference.png)

The hex grid used for pathfinding is defined by specifying the q and r dimensions (as in the above image) in number of hexes. The map is rectangular in shape. It is implemented implicitly: If a given hex is about to be reached by the pathfinding algorithm, the graph object checks if its coordinates lay on the map as defined by its dimensions.

### Coordinate systems

There are two coordinate systems in use: A two-dimensional "axial" coordinate system, where a coordinate is expressed in the form (q,r), and a three-dimensional "cube" coordinate system where a coordinate is expressed in the form (x,y,z). Cube coordinates are used for applications such as vector math as they are conveniently defined by the standard orthogonal basis of a three-dimensional vector space, unlike the axial system where the r-axis is skewed. Technically, cube coordinates could be used everywhere, but since they are difficult to intuitively understand compared to the axial system, I use axial coordinates where possible and convert them to cube coordinates when needed. This is possible as there is a 1:1 relationship between them.

A note: The cube coordinate space here is not the full three-dimensional vector space R3, but a subspace W = {w ∈ W | x + y + z = 0}. In other words, the cube coordinate space is composed of vectors where the sum of all components is zero.

## Program structure

## Time and space complexities

To be added once there's more worthwhile results.

## Performance testing results.

To be added.
