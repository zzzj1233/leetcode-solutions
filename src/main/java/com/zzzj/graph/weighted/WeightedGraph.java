package com.zzzj.graph.weighted;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

/**
 * @author Zzzj
 * @create 2021-05-07 22:32
 */
public class WeightedGraph {

    private int E;

    private int N;

    private TreeMap<Integer, Integer>[] adjMap;

    private List<WeightedEdge> edges;

    private static final String PREFIX = "graph/";

    private static final String SUFFIX = ".txt";

    public WeightedGraph(String filename) {
        try {
            readFile(new Scanner(new FileInputStream(PREFIX.concat(filename).concat(SUFFIX))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found : " + filename);
        }
    }

    public WeightedGraph(Scanner scanner) {
        readFile(scanner);
    }

    public WeightedGraph(InputStream inputStream) {
        this(new Scanner(inputStream));
    }

    public WeightedGraph(Reader reader) {
        this(new Scanner(reader));
    }

    public boolean isConnected(int i, int j) {
        return adjMap[i].containsKey(j);
    }

    public List<Map.Entry<Integer, Integer>> adj(int i) {
        return new ArrayList<>(adjMap[i].entrySet());
    }

    private void readFile(Scanner scanner) {
        // N个元素
        this.N = scanner.nextInt();
        // E条边
        this.E = scanner.nextInt();

        edges = new ArrayList<>(E);

        adjMap = new TreeMap[N];

        for (int i = 0; i < N; i++) {
            adjMap[i] = new TreeMap<>();
        }

        for (int i = 0; i < E; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            int weight = scanner.nextInt();
            adjMap[n1].put(n2, weight);
            adjMap[n2].put(n1, weight);
            edges.add(new WeightedEdge(n1, n2, weight));
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

    public static class WeightedEdge {
        public final int i;
        public final int j;
        public final int weight;

        public WeightedEdge(int i, int j, int weight) {
            this.i = i;
            this.j = j;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "WeightedEdge{" +
                    "i=" + i +
                    ", j=" + j +
                    ", weight=" + weight +
                    '}';
        }
    }

    public List<WeightedEdge> getEdges() {
        return edges;
    }
}