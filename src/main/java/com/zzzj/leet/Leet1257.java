package com.zzzj.leet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zzzj
 * @create 2022-07-22 23:04
 */
public class Leet1257 {

    public static void main(String[] args) {
        System.out.println(findSmallestRegion(
                LeetUtils.convertListStrings("[[\"zDkA\",\"GfAj\",\"lt\"],[\"GfAj\",\"rtupD\",\"og\",\"l\"],[\"rtupD\",\"IT\",\"jGcew\",\"ZwFqF\"],[\"og\",\"yVobt\",\"EjA\",\"piUyQ\"],[\"IT\",\"XFlc\",\"W\",\"rB\"],[\"l\",\"GwQg\",\"shco\",\"Dub\",\"KwgZq\"],[\"jGcew\",\"KH\",\"lbW\"],[\"KH\",\"BZ\",\"sauG\"],[\"yVobt\",\"Aa\",\"lJRmv\"],[\"BZ\",\"v\",\"zh\",\"oGg\",\"WP\"],[\"XFlc\",\"Sn\",\"ftXOZ\"],[\"sauG\",\"If\",\"nK\",\"HHOr\",\"yEH\",\"YWMgF\"],[\"GwQg\",\"Mfb\",\"gr\",\"S\",\"nQ\"],[\"shco\",\"xsUkW\"],[\"xsUkW\",\"Cssa\",\"TgPi\",\"qx\"],[\"v\",\"SAH\",\"Rjr\"],[\"lt\",\"Q\",\"fWB\",\"a\",\"Wk\",\"zpqU\"],[\"If\",\"e\",\"y\",\"quEA\",\"sNyV\"],[\"piUyQ\",\"G\",\"aTi\"],[\"Sn\",\"rVIh\",\"twv\",\"pYA\",\"Ywm\"],[\"zh\",\"PWeEf\"],[\"Mfb\",\"GEs\",\"XjpeC\",\"p\"],[\"GEs\",\"oXMG\",\"tNJYJ\"],[\"SAH\",\"bmFhM\"],[\"bmFhM\",\"SOvB\",\"RWsEM\",\"z\"],[\"SOvB\",\"iD\",\"pLGYN\",\"Zqk\"],[\"Dub\",\"PnGPY\"],[\"a\",\"TekG\",\"zp\"],[\"XjpeC\",\"vK\",\"aaO\",\"D\"],[\"pLGYN\",\"ldb\"],[\"oGg\",\"x\"],[\"nQ\",\"IOw\"],[\"Aa\",\"wmYF\"],[\"Zqk\",\"th\"],[\"ZwFqF\",\"GDl\"],[\"th\",\"JyOSt\",\"ALlyw\"],[\"lbW\",\"M\"],[\"yEH\",\"UY\",\"GIwLp\"],[\"JyOSt\",\"i\"],[\"x\",\"dclJ\"],[\"wmYF\",\"xreBK\"],[\"PnGPY\",\"Ev\",\"lI\"],[\"ALlyw\",\"jguyA\",\"Mi\"],[\"oXMG\",\"uqe\"],[\"sNyV\",\"WbrP\"]]"), "RWsEM", "GfAj"
        ));
    }

    public static class Region {
        String name;
        int level;

        public Region(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }

    // [["zDkA","GfAj","lt"],["GfAj","rtupD","og","l"],["rtupD","IT","jGcew","ZwFqF"],["og","yVobt","EjA","piUyQ"],["IT","XFlc","W","rB"],["l","GwQg","shco","Dub","KwgZq"],["jGcew","KH","lbW"],["KH","BZ","sauG"],["yVobt","Aa","lJRmv"],["BZ","v","zh","oGg","WP"],["XFlc","Sn","ftXOZ"],["sauG","If","nK","HHOr","yEH","YWMgF"],["GwQg","Mfb","gr","S","nQ"],["shco","xsUkW"],["xsUkW","Cssa","TgPi","qx"],["v","SAH","Rjr"],["lt","Q","fWB","a","Wk","zpqU"],["If","e","y","quEA","sNyV"],["piUyQ","G","aTi"],["Sn","rVIh","twv","pYA","Ywm"],["zh","PWeEf"],["Mfb","GEs","XjpeC","p"],["GEs","oXMG","tNJYJ"],["SAH","bmFhM"],["bmFhM","SOvB","RWsEM","z"],["SOvB","iD","pLGYN","Zqk"],["Dub","PnGPY"],["a","TekG","zp"],["XjpeC","vK","aaO","D"],["pLGYN","ldb"],["oGg","x"],["nQ","IOw"],["Aa","wmYF"],["Zqk","th"],["ZwFqF","GDl"],["th","JyOSt","ALlyw"],["lbW","M"],["yEH","UY","GIwLp"],["JyOSt","i"],["x","dclJ"],["wmYF","xreBK"],["PnGPY","Ev","lI"],["ALlyw","jguyA","Mi"],["oXMG","uqe"],["sNyV","WbrP"]]
    //"RWsEM"
    //"GfAj"
    public static String findSmallestRegion(List<List<String>> regions, String region1, String region2) {

        Map<String, Region> map = new HashMap<>();

        map.put(regions.get(0).get(0), new Region("TOP", 0));

        for (List<String> list : regions) {

            String first = list.get(0);

            Region region = map.get(first);

            for (int i = 1; i < list.size(); i++) {
                map.put(list.get(i), new Region(first, region.level + 1));
            }

        }

        Region bigger1 = map.get(region1);
        Region bigger2 = map.get(region2);

        while (!bigger1.name.equals(bigger2.name)) {

            if (bigger1.name.equals(region2)) {
                return bigger1.name;
            }

            if (bigger2.name.equals(region1)) {
                return bigger2.name;
            }

            if (bigger1.level < bigger2.level) {
                bigger2 = map.get(bigger2.name);
            } else if (bigger2.level < bigger1.level) {
                bigger1 = map.get(bigger1.name);
            } else {
                return bigger1.name;
            }
        }

        return bigger1.name;
    }

}
