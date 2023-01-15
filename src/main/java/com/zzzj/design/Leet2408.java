package com.zzzj.design;

import java.util.*;

public class Leet2408 {

    public static void main(String[] args) {
        SQL sql = new SQL(Arrays.asList("one", "two", "three"), Arrays.asList(2, 3, 1));

        sql.insertRow("two", Arrays.asList("first", "second", "third"));

        System.out.println(sql.selectCell("two", 1, 3));
    }

    private static class SQL {

        final Set<Integer>[] deleted;

        final Map<String, Integer> nameIndex;

        final List<List<String>>[] values;

        public SQL(List<String> names, List<Integer> columns) {
            int N = names.size();
            deleted = new Set[N];
            nameIndex = new HashMap<>(N);
            values = new List[N];

            for (int i = 0; i < N; i++) {
                nameIndex.put(names.get(i), i);
            }
        }

        public void insertRow(String name, List<String> row) {
            Integer index = nameIndex.get(name);
            if (values[index] == null) {
                values[index] = new ArrayList<>();
            }
            values[index].add(row);
        }

        public void deleteRow(String name, int rowId) {
            Integer index = nameIndex.get(name);
            if (deleted[index] == null) {
                deleted[index] = new HashSet<>();
            }
            deleted[index].add(rowId);
        }

        public String selectCell(String name, int rowId, int columnId) {
            Integer index = nameIndex.get(name);

            if (deleted[index] != null && deleted[index].contains(rowId)) {
                return null;
            }

            if (values[index] == null || values[index].size() < rowId) {
                return null;
            }

            return values[index].get(rowId - 1).get(columnId - 1);
        }
    }

}
