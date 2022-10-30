package com.zzzj.design;

/**
 * @author zzzj
 * @create 2022-09-02 18:27
 */
public class Leet911 {

    public static void main(String[] args) {
        TopVotedCandidate candidate = new TopVotedCandidate(new int[]{0, 1, 1, 0, 0, 1, 0}, new int[]{0, 5, 10, 15, 20, 25, 30});
//        System.out.println(candidate.q(3));
//        System.out.println(candidate.q(12));
        System.out.println(candidate.q(25));
    }

    private static class TopVotedCandidate {

        private static class Rank {
            int time;
            int person;

            public Rank(int time, int person) {
                this.time = time;
                this.person = person;
            }
        }

        private final Rank[] ranks;

        public TopVotedCandidate(int[] persons, int[] times) {
            int N = persons.length;

            this.ranks = new Rank[N];

            int[] votes = new int[N];

            int maxVote = 0;
            int maxPerson = -1;

            for (int i = 0; i < N; i++) {
                int time = times[i];
                int person = persons[i];

                int vote = ++votes[person];

                if (vote >= maxVote) {
                    maxVote = vote;
                    maxPerson = person;
                }

                ranks[i] = new Rank(time, maxPerson);
            }

        }

        public int q(int t) {

            int low = 0;
            int high = ranks.length - 1;

            while (low <= high) {
                int mid = low + ((high - low) >> 1);
                if (ranks[mid].time > t) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return ranks[low - 1].person;
        }

    }

}
