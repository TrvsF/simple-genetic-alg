package me.travis.javaga;

import java.util.ArrayList;
import java.util.List;

public class GA {

    public static int weightLimit = 100;

    private static int initPopulation = 10;

    private static final List<Genome> population = new ArrayList<>();

    public static void main(String[] args) {

        populate();
        printPopulation();

    }

    private static void populate() {
        for (int i = 0; i < initPopulation; i++) {
            Genome g = new Genome();
            population.add(g);
        }
    }

    private static void printPopulation() {
        for (Genome g : population) {
            g.printValue();
        }
    }

}