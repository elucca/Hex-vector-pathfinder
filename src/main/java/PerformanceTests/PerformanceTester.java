package PerformanceTests;

import Data.Node;
import Pathfinder.Algorithm;
import Pathfinder.Graph;
import Pathfinder.Pathfinder;
import java.util.concurrent.TimeUnit;

/**
 * Class for testing the performance of pathfinding algorithms. Performance is tested by running
 * each implemented pathfinding algorithm on a battery of start and goal node pairs.
 */
public class PerformanceTester {

    private final int numberOfRuns = 6 ;
    private final Graph graph;
    private final TestPath[] testBattery;

    /**
     * Constructs a new performance tester. Pathfinding will occur on the supplied graph. Tests will
     * be ran for each implemented pathfinding algorithm using the supplied test battery, which
     * contains objects which define a start and goal node.
     *
     * @param graph
     * @param testBattery
     */
    public PerformanceTester(Graph graph, TestPath[] testBattery) {
        this.graph = graph;
        this.testBattery = testBattery;
    }

    /**
     * Tests each implemented algorithm and prints the average time taken to find a path. Each
     * algorithm is ran numberOfRuns times and the result averaged, and the first result is ignored
     * in order to mitigate issues with just in time compilation or garbage collection fouling the
     * results.
     */
    public void test() {
        Pathfinder pathfinder = new Pathfinder(graph);

        int i = 0;
        while (i < Algorithm.values().length) {
            double pathTimesSum = 0;
            for (TestPath testPath : testBattery) {
                pathTimesSum += timePath(pathfinder, Algorithm.values()[i], testPath);
            }

            double algoAverageTime = pathTimesSum / testBattery.length;

            printTime(Algorithm.values()[i], algoAverageTime);

            i++;
        }

    }

    private double timePath(Pathfinder pathfinder, Algorithm algorithm, TestPath path) {
        Node start = path.getStart();
        Node goal = path.getGoal();

        long[] times = new long[numberOfRuns - 1];
        long t = 0;

        int i = 0;
        while (i < numberOfRuns) {
            // Don't count the first run
            if (i == 0) {
                pathfinder.findPath(start, goal, algorithm);
            } else {
                t = System.nanoTime();
                pathfinder.findPath(start, goal, algorithm);
                t = System.nanoTime() - t;
                times[i - 1] = t;
            }
            i++;
        }

        return calcAverage(times);
    }

    private double calcAverage(long[] times) {
        double sum = 0.0;

        for (long time : times) {
            sum += time;
        }

        return sum / times.length;
    }

    private void printTime(Algorithm algorithm, double nanoTimeTaken) {
        double milliTime = TimeUnit.MILLISECONDS.convert((long) nanoTimeTaken, TimeUnit.NANOSECONDS);
        double secondsTime = TimeUnit.SECONDS.convert((long) nanoTimeTaken, TimeUnit.NANOSECONDS);

        System.out.println("Algorithm: " + algorithm);
        System.out.println("Average time: " + milliTime + " ms");
    }

}
