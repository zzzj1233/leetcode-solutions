package com.zzzj.leet;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zzzj
 * @create 2022-09-15 19:01
 */
public class Leet690 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = employees.stream()
                .collect(Collectors.toMap(employee -> employee.id, Function.identity()));
        return dfs(map, id);
    }

    public static int dfs(Map<Integer, Employee> map, int id) {
        Employee employee = map.get(id);

        if (employee == null) {
            return 0;
        }

        int result = employee.importance;

        if (employee.subordinates != null) {
            for (Integer subordinate : employee.subordinates) {
                result += dfs(map, id);
            }
        }


        return result;
    }

}
