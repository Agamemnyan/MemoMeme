package am.tir.games.memomemechallenge.utils;

import java.text.DecimalFormat;

import android.content.SharedPreferences;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class MemeSettings {
	// Initialization flag
	public static boolean isInit;

	// Prefs
	public static SharedPreferences preferences;
	public static final String prefName = "Settings";

	// Display metrics
	public static int dWidth;
	public static int dHeight;

	// Card metrics
	public static int cHeight_lvl_1;
	public static int cHeight_lvl_2;
	public static int cHeight_lvl_3;
	public static int cHeight_lvl_4;

	// Sound mode
	public static SoundMode sound_mode;
	public static boolean is_sound_on;

	// Time formatter
	public static final DecimalFormat FORMATTER = new DecimalFormat("00");

	// Level statics
	// Pairs in levels
	public static final int ALL_PAIRS_LEVEL_1 = 4;
	public static final int ALL_PAIRS_LEVEL_2 = 8;
	public static final int ALL_PAIRS_LEVEL_3 = 12;
	public static final int ALL_PAIRS_LEVEL_4 = 18;
	// Prev Time
	public static final long PREV_TIME_LEVEL_1_1 = 7000;
	public static final long PREV_TIME_LEVEL_2_1 = 7000;
	public static final long PREV_TIME_LEVEL_2_2 = 6000;
	public static final long PREV_TIME_LEVEL_3_1 = 7000;
	public static final long PREV_TIME_LEVEL_3_2 = 6000;
	public static final long PREV_TIME_LEVEL_3_3 = 5000;
	public static final long PREV_TIME_LEVEL_4_1 = 7000;
	public static final long PREV_TIME_LEVEL_4_2 = 6000;
	public static final long PREV_TIME_LEVEL_4_3 = 5000;
	public static final long PREV_TIME_LEVEL_4_4 = 4000;
	// Game Time
	public static final long GAME_TIME_LEVEL_1_1 = 30000;
	public static final long GAME_TIME_LEVEL_2_1 = 60000;
	public static final long GAME_TIME_LEVEL_2_2 = 45000;
	public static final long GAME_TIME_LEVEL_3_1 = 75000;
	public static final long GAME_TIME_LEVEL_3_2 = 60000;
	public static final long GAME_TIME_LEVEL_3_3 = 45000;
	public static final long GAME_TIME_LEVEL_4_1 = 90000;
	public static final long GAME_TIME_LEVEL_4_2 = 75000;
	public static final long GAME_TIME_LEVEL_4_3 = 60000;
	public static final long GAME_TIME_LEVEL_4_4 = 45000;
	// Before Preview Time
	public static final long BEFORE_PREVIEW_TIME = 1000;
	// Point constant
	public static final int POINTS_LEVEL_1_1 = 50;
	public static final int POINTS_LEVEL_2_1 = 100;
	public static final int POINTS_LEVEL_2_2 = 150;
	public static final int POINTS_LEVEL_3_1 = 200;
	public static final int POINTS_LEVEL_3_2 = 300;
	public static final int POINTS_LEVEL_3_3 = 500;
	public static final int POINTS_LEVEL_4_1 = 1000;
	public static final int POINTS_LEVEL_4_2 = 1500;
	public static final int POINTS_LEVEL_4_3 = 2500;
	public static final int POINTS_LEVEL_4_4 = 3500;
	// Point for sec
	public static final int POINTS_FOR_SEC_LEVEL_1_1 = 10;
	public static final int POINTS_FOR_SEC_LEVEL_2_1 = 20;
	public static final int POINTS_FOR_SEC_LEVEL_2_2 = 30;
	public static final int POINTS_FOR_SEC_LEVEL_3_1 = 40;
	public static final int POINTS_FOR_SEC_LEVEL_3_2 = 60;
	public static final int POINTS_FOR_SEC_LEVEL_3_3 = 100;
	public static final int POINTS_FOR_SEC_LEVEL_4_1 = 200;
	public static final int POINTS_FOR_SEC_LEVEL_4_2 = 300;
	public static final int POINTS_FOR_SEC_LEVEL_4_3 = 500;
	public static final int POINTS_FOR_SEC_LEVEL_4_4 = 700;
}
