package com.zzzj.mst;

import com.zzzj.graph.weighted.WeightedGraph;
import com.zzzj.graph.weighted.WeightedGraph.WeightedEdge;
import com.zzzj.uf.IUf;
import com.zzzj.uf.UnionFind5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Zzzj
 * @create 2021-05-07 22:24
 */
// 最小生成树算法
public class Kruskal {

    private final WeightedGraph graph;

    private List<WeightedEdge> mst;

    public Kruskal(WeightedGraph graph) {
        this.graph = graph;
        // assert graph.isConnected()
        mst = new ArrayList<>(10);
        buildMst();
    }

    private void buildMst() {
        // 1. 对所有的边进行排序,按照weight从小到大排序
        List<WeightedEdge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(o -> o.weight));

        // 2. 创建一个unionFind
        IUf uf = new UnionFind5(edges.size());

        // 3. 构建最小生成树
        for (WeightedEdge edge : edges) {
            if (!uf.isConnected(edge.i, edge.j)) {
                mst.add(edge);
                uf.union(edge.i, edge.j);
            }
        }
    }

    public List<WeightedEdge> getMst() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("wg01");
        System.out.println(new Kruskal(graph).getMst());
    }

}
