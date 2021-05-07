package com.zzzj.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Zzzj
 * @create 2021-04-26 20:58
 */
public class Graph {

    private int E;

    private int N;

    private Set<Integer>[] adjSet;

    private static final String PREFIX = "graph/";

    private static final String SUFFIX = ".txt";

    public Graph(String filename) {
        try {
            readFile(new Scanner(new FileInputStream(PREFIX.concat(filename).concat(SUFFIX))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found : " + filename);
        }
    }

    public boolean isConnected(int i, int j) {
        return adjSet[i].contains(j);
    }

    public List<Integer> adj(int i) {
        return new ArrayList<>(adjSet[i]);
    }

    private void readFile(Scanner scanner) {
        // N个元素
        this.N = scanner.nextInt();
        // E条边
        this.E = scanner.nextInt();

        adjSet = new TreeSet[N];

        for (int i = 0; i < N; i++) {
            adjSet[i] = new TreeSet<>();
        }

        for (int i = 0; i < E; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            adjSet[n1].add(n2);
            adjSet[n2].add(n1);
        }

        scanner.close();
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Graph("g01");
    }
}
