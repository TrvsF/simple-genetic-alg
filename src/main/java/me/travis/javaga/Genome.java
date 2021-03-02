package me.travis.javaga;

public class Genome {

    private int[] value;
    private int fitness;

    public Genome() {
        this.value = generateValue();
    }

    private int[] generateValue() {
        int[] value = new int[8];
        for (int i = 0; i < value.length; i++) {
            value[i] = Util.oneOrZero();
        }
        return value;
    }

    public void printValue() {
        for (int v : value) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

}
