package com.zzzj.graph.directed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

// 有向图
public class DirectedGraph {

    // 多少条边
    private int E;

    // 多少个顶点
    private int N;

    private TreeSet<Integer>[] adj;

    private int[] inDegrees;

    private Boolean hasCycle;

    public DirectedGraph(String fileName) {
        try {
            readFile(new Scanner(new FileInputStream("H:\\kotlin-project\\algorithm\\src\\main\\java\\com\\zzzj\\graph\\directed\\" + fileName)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile(Scanner scanner) {
        // N个元素
        this.N = scanner.nextInt();
        // E条边
        this.E = scanner.nextInt();

        adj = new TreeSet[N];

        inDegrees = new int[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new TreeSet<>();
        }

        for (int i = 0; i < E; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();

            adj[n1].add(n2);

            // 入度++
            inDegrees[n2]++;
        }

        scanner.close();
    }

    public int getE() {
        return E;
    }

    public int getN() {
        return N;
    }

    public List<Integer> adj(int V) {
        return new ArrayList<>(adj[V]);
    }

    public boolean hasCycle() {
        if (hasCycle == null) {
            hasCycle = detectCycle();
        }
        return hasCycle;
    }

    /**
     * i 这个节点的入度
     */
    public int inDegree(int i) {
        return inDegrees[i];
    }

    /**
     * i 这个节点的出度
     */
    public int outDegree(int i) {
        return adj[i].size();
    }

    private boolean detectCycle() {
        boolean[] path = new boolean[this.N];
        boolean[] visited = new boolean[this.N];

        for (int i = 0; i < N; i++) {
            if (dfs(path, i, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(boolean[] path, int cur, boolean[] visited) {
        if (visited[cur]) {
            return false;
        }

        visited[cur] = true;

        path[cur] = true;

        TreeSet<Integer> neighbors = adj[cur];

        for (Integer neighbor : neighbors) {
            if (path[neighbor] || dfs(path, neighbor, visited)) {
                return true;
            }
        }

        path[cur] = false;

        return false;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph("ug.txt");

        for (int i = 0; i < graph.N; i++) {
            System.out.println("V = " + i + " , inDegree = " + graph.inDegree(i) + " , outDegree = " + graph.outDegree(i));
        }
    }
}
