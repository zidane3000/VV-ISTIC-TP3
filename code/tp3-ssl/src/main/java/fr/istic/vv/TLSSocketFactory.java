package fr.istic.vv;

import java.util.ArrayList;
import java.util.List;

public class TLSSocketFactory {


    public void prepareSocket(SSLSocket socket) {


    }

    /**
     * Returns true if the given element exists in the given array; false otherwise.
     */
    private boolean existsIn(String element, String[] a) {
        for (String s : a) {
            if (element.equals(s)) {
                return true;
            }
        }
        return false;
    }


}
