package com.kraken.pantheon19.Utils;
/*
 * created by RahulKraken on 11-09-2019 at 05:06.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private static final String TAG = "StringUtils";

    /**
     * parse string with given delimiter
     * @param raw : raw string
     * @param delimiter : delimiter to slice on (should be a valid regex)
     * @return list of parsed strings
     */
    public static List<String> parseContact(String raw, String delimiter) {
        String[] s = raw.split(delimiter);
        return new ArrayList<>(Arrays.asList(s));
    }

    /**
     * process event name and convert into stored image names
     * @param eventName : name of the event
     * @return processed name with no spaces, hyphens, or other non permissible characters
     */
    public static String getImageResourceName(String eventName) {
        eventName = eventName.toLowerCase()
                .replace(" ", "_")
                .replace("-", "_")
                .replace(".", "_");
        if (eventName.contains("'")) {
            String[] s = eventName.split("'");
            eventName = "";
            for (String curr : s) {
                eventName += curr;
            }
        }

        if (eventName.charAt(0) >= '0' && eventName.charAt(0) <= '9') eventName = "_" + eventName;

        Log.d(TAG, "getImageResourceName: Prepared res name:" + eventName);
        return eventName;
    }
}
