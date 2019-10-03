package com.kraken.pantheon19.Utils;
/*
 * created by RahulKraken on 09-09-2019 at 14:13.
 */

import com.kraken.pantheon19.Entities.Flagship;
import com.kraken.pantheon19.R;

public class Constants {

    public static final String FORMAL_EVENTS_API = "https://www.pantheonbit.com/api/event/getFormalEvents";
    public static final String INFORMAL_EVENTS_API = "https://www.pantheonbit.com/api/event/getInformalEvents";
    public static final String FLAGSHIP_EVENTS_API = "http://10.0.2.2:5000/formalevents";
    public static final String WINNERS_API = "http://10.0.2.2:5000/informalevents";
    public static final String LEADERBOARD_API = "https://www.pantheonbit.com/api/admin/leaderboard";

    public static final int[] COLORS = {
            R.color.md_red_500,
            R.color.md_blue_500,
            R.color.md_amber_500,
            R.color.md_green_500,
    };

    public static final int[] FLAGSHIP_POINTS = {250, 200, 150};
    public static final int[] FORMAL_POINTS = {100, 80, 60};
    public static final int[] INFORMAL_POINTS = {50, 30, 20};

    public static final String FACEBOOK_URL = "https://www.facebook.com/bitmesra.pantheon/";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/pantheon_techfest/";
    public static final String WEBSITE_URL = "https://pantheonbit.com";

    public static final Flagship[] FLAGSHIPS = {
            new Flagship(R.string.codezilla_label, R.string.codezilla_desc, R.string.codezilla_rules, R.string.codezilla_venue, R.string.codezilla_time, R.string.codezilla_day, R.string.codezilla_coordinators, R.color.md_deep_orange_500, R.drawable.codezilla),
            new Flagship(R.string.illuminati_label, R.string.illuminati_desc, R.string.illuminati_rules, R.string.illuminati_venue, R.string.illuminati_time, R.string.illuminati_day, R.string.illuminati_coordinators, R.color.md_amber_500, R.drawable.illuminati),
            new Flagship(R.string.droid_trooper_label, R.string.droid_trooper_desc, R.string.droid_trooper_rules, R.string.droid_trooper_venue, R.string.droid_trooper_time, R.string.droid_trooper_day, R.string.droid_trooper_coordinators, R.color.md_blue_500, R.drawable.droid_trooper),
            new Flagship(R.string.cube_de_cemento_label, R.string.cube_de_cemento_desc, R.string.cube_de_cemento_rules, R.string.cube_de_cemento_venue, R.string.cube_de_cemento_time, R.string.cube_de_cemento_day, R.string.cube_de_cemento_coordinators, R.color.md_brown_500, R.drawable.cube_de_cemento),
            new Flagship(R.string.eureka_label, R.string.eureka_desc, R.string.eureka_rules, R.string.eureka_venue, R.string.eureka_time, R.string.eureka_day, R.string.eureka_coordinators, R.color.md_green_500, R.drawable.eureka)
    };
}
