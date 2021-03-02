package me.travis.javaga;

import java.util.List;
import java.util.Random;

public class Util {

    private static final Random random = new Random();

    public static int oneOrZero() {
        return random.nextInt(2);
    }

    public static int getRandomIntFromPop(int size) {
        return random.nextInt(size);
    }

    public static int getCrossoverPoint() {
        return random.nextInt(8) + 1;
    }

    public static Genome getRandomGenomeFromPop(List<Genome> pop) {
        return pop.get(random.nextInt(pop.size()));
    }

    public static int getWeight(int i) {
        switch (i) {
            case 0:
                return 31;
            case 1:
                return 34;
            case 2:
                return 9;
            case 3:
                return 6;
            case 4:
                return 23;
            case 5:
                return 12;
            case 6:
                return 17;
            case 7:
                return 24;
        }
        return 0;
    }

    public static int getValue(int i) {
        switch (i) {
            case 0:
                return 23;
            case 1:
                return 34;
            case 2:
                return 2;
            case 3:
                return 21;
            case 4:
                return 12;
            case 5:
                return 31;
            case 6:
                return 16;
            case 7:
                return 7;
        }
        return 0;
    }

    private static int getTotalFitness(List<Genome> pop) {
        int total = 0;
        for (Genome g : pop) {
            total += g.getFitness();
        }
        return total;
    }

    public static double getAverageFitness(List<Genome> pop) {
        return (double) getTotalFitness(pop) / pop.size();
    }

    public static Genome getBestGenome(List<Genome> pop) {
        Genome best = null;
        int bestFitness = 0;
        for (Genome g : pop) {
            if (g.getFitness() > bestFitness) {
                best = g;
            }
        }
        return best;
    }

}
