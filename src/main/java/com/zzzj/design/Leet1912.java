package com.zzzj.design;

import com.zzzj.leet.LeetUtils;

import java.util.*;

/**
 * @author zzzj
 * @create 2024-06-25 17:10
 */
public class Leet1912 {

    public static void main(String[] args) {

        // [[70,[[0,8780,2738],[20,8214,6582],[64,3470,334],[56,5093,3915],[8,3501,8380],[62,3888,3526],[37,9669,7305],[12,5267,9439],[23,5268,6714],[65,41,6665],[24,9625,9801],[40,6331,1088],[12,6849,9647],[48,9725,9210],[0,4316,5003],[68,9660,8204],[48,6951,8007],[9,3470,4576],[50,2854,4721],[0,7253,9719],[0,3415,2467],[3,5093,6765],[7,4316,2918],[64,1061,6021],[46,5043,4438],[24,8993,4256],[60,4598,500],[0,7907,8894],[44,7367,7173],[69,4316,1200],[53,7798,8978],[10,6898,9653],[59,8313,3671],[4,1385,358],[42,7367,9860],[17,8912,5660],[25,3031,9804],[21,8921,4800],[16,9109,9569],[37,4316,1210],[44,7533,9893],[66,4598,7577],[22,5452,636],[25,6849,8934],[10,3630,5028],[56,5168,452],[2,9725,5511],[48,4548,6777],[39,5768,3549],[58,2058,4970],[41,3415,3889],[48,2627,2499],[62,7907,9564],[40,7798,8432],[5,7393,1237],[20,2627,3097],[33,6898,521],[48,9625,4180],[51,301,9896],[61,7533,5511],[28,6951,8716],[32,2442,2814],[59,3188,1955],[25,2058,2197],[59,1756,3278],[24,9669,1754],[48,833,7483],[58,41,6847],[55,2627,1230],[32,4423,3264],[1,8313,8915],[47,7367,7240],[11,6723,1963],[51,9894,3776],[18,8313,8420],[55,1061,1465],[45,1207,7340],[50,1207,733],[27,8519,9968],[28,8204,4983],[43,3888,9306],[37,788,3089],[10,5267,9421],[25,1129,4091],[63,2570,4336],[26,8537,8000],[2,9691,32],[53,7043,2395],[58,7043,9909],[18,9725,1997],[30,8893,4015],[35,8214,7117],[0,9258,3104],[11,8519,8041],[23,9052,4134],[20,5403,9987]]],[9669],[18,8313],[],[45,1207],[45,1207],[],[41,3415],[],[],[0,7253],[46,5043],[24,9669],[46,5043],[45,1207],[2423],[7798],[],[41,3415],[7140],[4316],[],[24,9669],[37,788],[6951],[24,8993],[2423],[48,6951],[37,4316],[61,7533],[6331],[4368],[],[],[5168],[],[18,8313],[9625],[37,4316],[16,9109],[8921],[],[35,8214],[9109],[],[],[8893],[],[1,8313],[9188],[48,9725],[9691],[62,7907],[0,8780],[8893],[45,1207],[48,6951],[64,3470],[8921],[6331],[],[5452],[37,788],[],[],[7393],[],[37,4316],[64,3470],[],[33,6898],[7367],[9125],[8993],[1,8313],[53,7798],[39,5768],[46,5043],[39,5768],[],[6358],[22,5452],[4598]]
        MovieRentingSystem ms = new MovieRentingSystem(
                70,
                LeetUtils.convertInts("[[0,8780,2738],[20,8214,6582],[64,3470,334],[56,5093,3915],[8,3501,8380],[62,3888,3526],[37,9669,7305],[12,5267,9439],[23,5268,6714],[65,41,6665],[24,9625,9801],[40,6331,1088],[12,6849,9647],[48,9725,9210],[0,4316,5003],[68,9660,8204],[48,6951,8007],[9,3470,4576],[50,2854,4721],[0,7253,9719],[0,3415,2467],[3,5093,6765],[7,4316,2918],[64,1061,6021],[46,5043,4438],[24,8993,4256],[60,4598,500],[0,7907,8894],[44,7367,7173],[69,4316,1200],[53,7798,8978],[10,6898,9653],[59,8313,3671],[4,1385,358],[42,7367,9860],[17,8912,5660],[25,3031,9804],[21,8921,4800],[16,9109,9569],[37,4316,1210],[44,7533,9893],[66,4598,7577],[22,5452,636],[25,6849,8934],[10,3630,5028],[56,5168,452],[2,9725,5511],[48,4548,6777],[39,5768,3549],[58,2058,4970],[41,3415,3889],[48,2627,2499],[62,7907,9564],[40,7798,8432],[5,7393,1237],[20,2627,3097],[33,6898,521],[48,9625,4180],[51,301,9896],[61,7533,5511],[28,6951,8716],[32,2442,2814],[59,3188,1955],[25,2058,2197],[59,1756,3278],[24,9669,1754],[48,833,7483],[58,41,6847],[55,2627,1230],[32,4423,3264],[1,8313,8915],[47,7367,7240],[11,6723,1963],[51,9894,3776],[18,8313,8420],[55,1061,1465],[45,1207,7340],[50,1207,733],[27,8519,9968],[28,8204,4983],[43,3888,9306],[37,788,3089],[10,5267,9421],[25,1129,4091],[63,2570,4336],[26,8537,8000],[2,9691,32],[53,7043,2395],[58,7043,9909],[18,9725,1997],[30,8893,4015],[35,8214,7117],[0,9258,3104],[11,8519,8041],[23,9052,4134],[20,5403,9987]]")
        );

        System.out.println(ms.search(8893));

    }

    private static class MovieRentingSystem {

        // Map<Movie, TreeMap<Price, Set<Shop>>>
        private final Map<Integer, TreeMap<Integer, TreeSet<Integer>>> unRent = new HashMap<>();

        // TreeMap<Price, Map<Shop, TreeSet<Movie>>>
        private final TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> rented = new TreeMap<>();

        private final Map<Integer, Map<Integer, Integer>> repo = new HashMap<>();

        public MovieRentingSystem(int n, int[][] entries) {
            // entries[i][0] = shop
            // entries[i][1] = movie
            // entries[i][2] = price
            for (int[] entry : entries) {

                int shop = entry[0];

                int movie = entry[1];

                int price = entry[2];

                repo.computeIfAbsent(shop, integer -> new HashMap<>())
                        .put(movie, price);

                unRent.computeIfAbsent(movie, integer -> new TreeMap<>())
                        .computeIfAbsent(price, integer -> new TreeSet<>())
                        .add(shop);
            }
        }

        public List<Integer> search(int movie) {

            TreeMap<Integer, TreeSet<Integer>> map = unRent.get(movie);

            if (map == null)
                return Collections.emptyList();

            List<Integer> r = new ArrayList<>(5);

            for (Map.Entry<Integer, TreeSet<Integer>> entry : map.entrySet()) {

                Iterator<Integer> it = entry.getValue().iterator();

                while (it.hasNext() && r.size() < 5) {
                    r.add(it.next());
                }

                if (r.size() == 5)
                    break;
            }

            return r;
        }

        public void rent(int shop, int movie) {
            int price = repo.get(shop).get(movie);

            TreeMap<Integer, TreeSet<Integer>> map = unRent.get(movie);

            Set<Integer> shopSet = map.get(price);

            shopSet.remove(shop);

            rented.computeIfAbsent(price, integer -> new TreeMap<>())
                    .computeIfAbsent(shop, integer -> new TreeSet<>())
                    .add(movie);
        }

        public void drop(int shop, int movie) {

            int price = repo.get(shop).get(movie);

            rented.get(price).get(shop).remove(movie);

            unRent.computeIfAbsent(movie, integer -> new TreeMap<>())
                    .computeIfAbsent(price, integer -> new TreeSet<>())
                    .add(shop);
        }

        public List<List<Integer>> report() {

            List<List<Integer>> r = new ArrayList<>(5);

            for (Map.Entry<Integer, TreeMap<Integer, TreeSet<Integer>>> entry : rented.entrySet()) {

                // key = shop
                // value = Set<Movie>
                TreeMap<Integer, TreeSet<Integer>> inner = entry.getValue();

                for (Map.Entry<Integer, TreeSet<Integer>> innerEntry : inner.entrySet()) {

                    int shop = innerEntry.getKey();

                    for (Integer movie : innerEntry.getValue()) {
                        r.add(Arrays.asList(shop, movie));
                        if (r.size() == 5)
                            return r;
                    }

                }

            }

            return r;
        }

    }
}