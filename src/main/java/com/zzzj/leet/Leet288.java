package com.zzzj.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zzzj
 * @create 2022-05-11 14:34
 */
public class Leet288 {

    // ["ValidWordAbbr","isUnique","isUnique"]
    // [[["a","b"]],["a"],["c"]]
    public static void main(String[] args) {
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(new String[]{"kdsgnpxqtkzvbazaffpw", "gajrazudktmqqdjh", "lgjblvpvbgyznferw", "fczawa", "rvypccr", "bybkfb", "wqbbriwhsggqrtoaerxv", "flnylwedkyzfvmzyut", "qquk", "glysr", "wvrqhgidcjkbljopeoi", "qbhlmfcpjb", "ioucp", "rtyxspielcs", "mfdzukccvvziskwqtjsy", "hedwayufqtj", "fqbxtksvgffivlhpve", "ffivkpp", "irqcrjdqqgmerkwshln", "piuahzszr", "obmgnaqungecjyuhcvb", "wobuwsmrnlwl", "cukjsflugejbdaozo", "perdqypj", "esyviyqafhbrcujrql", "oewxjrgpznm", "auz", "bdrsavklzbrbsg", "zsk", "gklzrbrrazusbckqkw", "xbs", "fzseszitn", "rcp", "strcfbezbcjvhyuurlb", "yrkjwpjifqqoavzveclo", "geuefixb", "ntqnuvcarot", "npegxcmzjnnvxkhfkkk", "awvopyuuarvf", "plosrgjbeqvbzcaaeen", "kssxynhuvcqeykatpjzn", "mxussze", "rgcpftnhyx", "ndgrmovtlapbzqhwvhgy", "qznjqzkleulhbwirp", "iywutfhldl", "iubqzusdvefijmvv", "cqwjqoifrv", "qvohthcfrkgznpmhu", "vaovexotdbuaxncxgm", "jgfbvhmjqlqmadf", "xegokfds", "umdjxhulm", "cbkzzaesyoufptkd", "frkjddzd", "xnbszczhpltvioe", "weufkluuxj", "isx", "evxqtpqwygfkzufevu", "zjmkhvkaanjjquxxzu", "ffylgfogbuqrux", "wzrzwot", "airaztho", "eqhnaykcohixrhsdb", "odqkckatdvdsiu", "urfynbsgjfiiyamsyxh", "yuz", "vfsu", "jqgmqdfsqnvqlnzxcng", "djafqjwkqrkt", "hiwcbr", "wxujdncxdngbfqkl", "jrvhzaxpluigd", "qidsfqtfhxbkvwaxeqo", "sfmgnvjxdindlsoealv", "vmadnuzosooouptl", "lzfxo", "rlju", "mdfwjy", "dzfyeexboxhekkoqwi", "dusakfemdk", "qkgmircogn", "sdnthxluabvaxlrph", "kkeeyq", "exgyehtzxzoa", "dfhqubzbdld", "pvzhxgn", "ovily", "xwxvqcbqlgoolxmkeqtb", "jaodmplyszkvfylmixqt", "uzeycb", "bqbyjafaobumiqqqhn", "sxaathuzxgxwmxz", "hzuhkbxwluusehrqfosg", "ttewgolwfwrabyecqut", "vapgergqrxbsn", "ahomm", "xrinlxid", "evtrwb", "hwjajltfpjmdiaixycfo", "wclvtccvnzg", "hghmoi", "oqufpnbgkmvzlhadejlc", "uejagirckydmrvnwijx", "zavuomvhfoirdwlvevj", "cqvqlfidhumdvjnifcb", "bvjorxm", "lfczcnxwceiasduujsic", "jkmukt", "gmmbjx", "hgfekwzskxfcds", "jvqadlmidhpyotoy", "bfvupebs", "gkvzotfxpw", "kuobmsaessdkrtbm", "ukwkqriojkyazzhg", "vjfmaay", "cqdjhyhcjrcjugbtma", "hyliscq", "msanrrcqsbiuthhgoqv", "whbqpaunux", "jmkoaqeemwdfclsxbdt", "hovniyvvcl", "lfdz", "jhtvfouxjamrkkvp", "wyugtxmtdgkrtu", "umqtghq", "jrzptrqeguzdxrqwciji"});
        System.out.println(validWordAbbr.isUnique("uisrebxgwakvih"));
        System.out.println(validWordAbbr.isUnique("dfhungxcjnyykpzry"));
    }

    static class ValidWordAbbr {

        private final HashMap<String, Set<String>> map;

        public ValidWordAbbr(String[] dictionary) {
            this.map = new HashMap<>(dictionary.length);

            for (String dict : dictionary) {
                if (dict.length() < 3) {
                    map.computeIfAbsent(dict, ignore -> new HashSet<>())
                            .add(dict);
                } else {
                    StringBuilder builder = new StringBuilder(5);
                    builder.append(dict.charAt(0));
                    builder.append(dict.length() - 2);
                    builder.append(dict.charAt(dict.length() - 1));
                    map.computeIfAbsent(builder.toString(), ignore -> new HashSet<>())
                            .add(dict);
                }
            }

        }

        /**
         * boolean isUnique(string word) 如果满足下述任意一个条件，返回 true ；否则，返回 false ：
         * 字典 dictionary 中没有任何其他单词的 缩写 与该单词 word 的 缩写 相同。
         * 字典 dictionary 中的所有 缩写 与该单词 word 的 缩写 相同的单词都与 word 相同 。
         * <p>
         * ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
         * validWordAbbr.isUnique("dear"); // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
         * validWordAbbr.isUnique("cart"); // 返回 true，字典中不存在缩写为 "c2t" 的单词
         * validWordAbbr.isUnique("cane"); // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
         * validWordAbbr.isUnique("make"); // 返回 true，字典中不存在缩写为 "m2e" 的单词
         * validWordAbbr.isUnique("cake"); // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
         */
        public boolean isUnique(String word) {
            Set<String> set;
            if (word.length() < 3) {
                set = map.get(word);
            } else {
                StringBuilder builder = new StringBuilder(5);
                builder.append(word.charAt(0));
                builder.append(word.length() - 2);
                builder.append(word.charAt(word.length() - 1));
                set = map.get(builder.toString());
            }
            return set == null || (set.size() == 1 && set.contains(word));
        }

    }

}
