package com.kraken.pantheon19.Utils;
/*
 * created by RahulKraken on 11-09-2019 at 02:16.
 */

import android.content.Context;

import java.util.Random;

public class Services {

    /**
     * @return : resource id of random color from set of available colors
     */
    public static int getRandomColor() {
        Random random = new Random();
        return Constants.COLORS[random.nextInt(Constants.COLORS.length)];
    }

    /**
     * return the res id for image with @param "name"
     * @param context of the requesting activity
     * @param name of the requested image
     * @return id of the requested image if exists
     */
    public static int getImgResourceId(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
