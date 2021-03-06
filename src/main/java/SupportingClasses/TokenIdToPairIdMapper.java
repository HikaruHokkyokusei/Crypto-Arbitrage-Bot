package SupportingClasses;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class is order sensitive.
 * It works on the general assumption of this project about the ordering of the GraphQueryMakers.
 * Main usability of this class comes when the data stored in the ArrayList of the HashMap is accessed in the same order as it was stored in.
 * */
public class TokenIdToPairIdMapper extends HashMap<String, ArrayList<String>> {

    public int containsKey(String token0, String token1) {

        token0 = token0.toLowerCase();
        token1 = token1.toLowerCase();

        if (token0.compareTo(token1) > 0) {
            String temp = token0;
            token0 = token1;
            token1 = temp;
        }

        String key1 = token0 + ", " + token1;
        String key2 = token1 + ", " + token0;
        if (this.containsKey(key1)) {
            return 0;
        } else if (this.containsKey(key2)) {
            return 1;
        } else {
            return -1;
        }
    }

    public String getKey(String token0, String token1) {
        token0 = token0.toLowerCase();
        token1 = token1.toLowerCase();

        int containVal = containsKey(token0, token1);
        if (containVal == 0) {
            return token0 + ", " + token1;
        } else if (containVal == 1) {
            return token1 + ", " + token0;
        } else {
            return null;
        }
    }

    public ArrayList<String> getAllTrackers(String token0, String token1) {

        token0 = token0.toLowerCase();
        token1 = token1.toLowerCase();

        if (token0.compareTo(token1) > 0) {
            String temp = token0;
            token0 = token1;
            token1 = temp;
        }

        ArrayList<String> retVal;
        retVal = this.get(token0 + ", " + token1);
        if (retVal != null) {
            return retVal;
        } else {
            return this.get(token1 + ", " + token0);
        }
    }

    public void addPairTracker(String token0, String token1, String pairId) {

        token0 = token0.toLowerCase();
        token1 = token1.toLowerCase();

        if (token0.compareTo(token1) > 0) {
            String temp = token0;
            token0 = token1;
            token1 = temp;
        }

        if (containsKey(token0, token1) == -1) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(pairId);
            this.put(token0 + ", " + token1, arrayList);
        } else {
            getAllTrackers(token0, token1).add(pairId);
        }

    }

    public void addPairTracker(String key, String pairId) {
        if (containsKey(key)) {
            String[] tokens = key.split(", ");
            addPairTracker(tokens[0], tokens[1], pairId);
        }
    }
}