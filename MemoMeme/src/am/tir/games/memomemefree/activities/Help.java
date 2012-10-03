package am.tir.games.memomemefree.activities;

import static am.tir.games.memomemefree.utils.MemeSettings.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		TextView tvPairsCount1 = (TextView) findViewById(R.id.value_1_PairsCount);
		TextView tvPairsCount2 = (TextView) findViewById(R.id.value_2_PairsCount);
		TextView tvPairsCount3 = (TextView) findViewById(R.id.value_3_PairsCount);
		TextView tvPairsCount4 = (TextView) findViewById(R.id.value_4_PairsCount);
		TextView tvPairsCount5 = (TextView) findViewById(R.id.value_5_PairsCount);
		TextView tvPairsCount6 = (TextView) findViewById(R.id.value_6_PairsCount);
		TextView tvPairsCount7 = (TextView) findViewById(R.id.value_7_PairsCount);
		TextView tvPairsCount8 = (TextView) findViewById(R.id.value_8_PairsCount);
		TextView tvPairsCount9 = (TextView) findViewById(R.id.value_9_PairsCount);
		TextView tvPairsCount10 = (TextView) findViewById(R.id.value_10_PairsCount);

		tvPairsCount1.setText(String.valueOf(ALL_PAIRS_LEVEL_1));
		tvPairsCount2.setText(String.valueOf(ALL_PAIRS_LEVEL_2));
		tvPairsCount3.setText(String.valueOf(ALL_PAIRS_LEVEL_2));
		tvPairsCount4.setText(String.valueOf(ALL_PAIRS_LEVEL_3));
		tvPairsCount5.setText(String.valueOf(ALL_PAIRS_LEVEL_3));
		tvPairsCount6.setText(String.valueOf(ALL_PAIRS_LEVEL_3));
		tvPairsCount7.setText(String.valueOf(ALL_PAIRS_LEVEL_4));
		tvPairsCount8.setText(String.valueOf(ALL_PAIRS_LEVEL_4));
		tvPairsCount9.setText(String.valueOf(ALL_PAIRS_LEVEL_4));
		tvPairsCount10.setText(String.valueOf(ALL_PAIRS_LEVEL_4));

		TextView tvPrevTime1 = (TextView) findViewById(R.id.value_1_PreviewTime);
		TextView tvPrevTime2 = (TextView) findViewById(R.id.value_2_PreviewTime);
		TextView tvPrevTime3 = (TextView) findViewById(R.id.value_3_PreviewTime);
		TextView tvPrevTime4 = (TextView) findViewById(R.id.value_4_PreviewTime);
		TextView tvPrevTime5 = (TextView) findViewById(R.id.value_5_PreviewTime);
		TextView tvPrevTime6 = (TextView) findViewById(R.id.value_6_PreviewTime);
		TextView tvPrevTime7 = (TextView) findViewById(R.id.value_7_PreviewTime);
		TextView tvPrevTime8 = (TextView) findViewById(R.id.value_8_PreviewTime);
		TextView tvPrevTime9 = (TextView) findViewById(R.id.value_9_PreviewTime);
		TextView tvPrevTime10 = (TextView) findViewById(R.id.value_10_PreviewTime);

		tvPrevTime1.setText(String.valueOf((int) PREV_TIME_LEVEL_1_1 / 1000));
		tvPrevTime2.setText(String.valueOf((int) PREV_TIME_LEVEL_2_1 / 1000));
		tvPrevTime3.setText(String.valueOf((int) PREV_TIME_LEVEL_2_2 / 1000));
		tvPrevTime4.setText(String.valueOf((int) PREV_TIME_LEVEL_3_1 / 1000));
		tvPrevTime5.setText(String.valueOf((int) PREV_TIME_LEVEL_3_2 / 1000));
		tvPrevTime6.setText(String.valueOf((int) PREV_TIME_LEVEL_3_3 / 1000));
		tvPrevTime7.setText(String.valueOf((int) PREV_TIME_LEVEL_4_1 / 1000));
		tvPrevTime8.setText(String.valueOf((int) PREV_TIME_LEVEL_4_2 / 1000));
		tvPrevTime9.setText(String.valueOf((int) PREV_TIME_LEVEL_4_3 / 1000));
		tvPrevTime10.setText(String.valueOf((int) PREV_TIME_LEVEL_4_4 / 1000));

		TextView tvGameTime1 = (TextView) findViewById(R.id.value_1_LevelTime);
		TextView tvGameTime2 = (TextView) findViewById(R.id.value_2_LevelTime);
		TextView tvGameTime3 = (TextView) findViewById(R.id.value_3_LevelTime);
		TextView tvGameTime4 = (TextView) findViewById(R.id.value_4_LevelTime);
		TextView tvGameTime5 = (TextView) findViewById(R.id.value_5_LevelTime);
		TextView tvGameTime6 = (TextView) findViewById(R.id.value_6_LevelTime);
		TextView tvGameTime7 = (TextView) findViewById(R.id.value_7_LevelTime);
		TextView tvGameTime8 = (TextView) findViewById(R.id.value_8_LevelTime);
		TextView tvGameTime9 = (TextView) findViewById(R.id.value_9_LevelTime);
		TextView tvGameTime10 = (TextView) findViewById(R.id.value_10_LevelTime);

		tvGameTime1.setText(String.valueOf((int) GAME_TIME_LEVEL_1_1 / 1000));
		tvGameTime2.setText(String.valueOf((int) GAME_TIME_LEVEL_2_1 / 1000));
		tvGameTime3.setText(String.valueOf((int) GAME_TIME_LEVEL_2_2 / 1000));
		tvGameTime4.setText(String.valueOf((int) GAME_TIME_LEVEL_3_1 / 1000));
		tvGameTime5.setText(String.valueOf((int) GAME_TIME_LEVEL_3_2 / 1000));
		tvGameTime6.setText(String.valueOf((int) GAME_TIME_LEVEL_3_3 / 1000));
		tvGameTime7.setText(String.valueOf((int) GAME_TIME_LEVEL_4_1 / 1000));
		tvGameTime8.setText(String.valueOf((int) GAME_TIME_LEVEL_4_2 / 1000));
		tvGameTime9.setText(String.valueOf((int) GAME_TIME_LEVEL_4_3 / 1000));
		tvGameTime10.setText(String.valueOf((int) GAME_TIME_LEVEL_4_4 / 1000));

		TextView tvMinPoints1 = (TextView) findViewById(R.id.value_1_MinPoints);
		TextView tvMinPoints2 = (TextView) findViewById(R.id.value_2_MinPoints);
		TextView tvMinPoints3 = (TextView) findViewById(R.id.value_3_MinPoints);
		TextView tvMinPoints4 = (TextView) findViewById(R.id.value_4_MinPoints);
		TextView tvMinPoints5 = (TextView) findViewById(R.id.value_5_MinPoints);
		TextView tvMinPoints6 = (TextView) findViewById(R.id.value_6_MinPoints);
		TextView tvMinPoints7 = (TextView) findViewById(R.id.value_7_MinPoints);
		TextView tvMinPoints8 = (TextView) findViewById(R.id.value_8_MinPoints);
		TextView tvMinPoints9 = (TextView) findViewById(R.id.value_9_MinPoints);
		TextView tvMinPoints10 = (TextView) findViewById(R.id.value_10_MinPoints);

		tvMinPoints1.setText(String.valueOf(POINTS_LEVEL_1_1));
		tvMinPoints2.setText(String.valueOf(POINTS_LEVEL_2_1));
		tvMinPoints3.setText(String.valueOf(POINTS_LEVEL_2_2));
		tvMinPoints4.setText(String.valueOf(POINTS_LEVEL_3_1));
		tvMinPoints5.setText(String.valueOf(POINTS_LEVEL_3_2));
		tvMinPoints6.setText(String.valueOf(POINTS_LEVEL_3_3));
		tvMinPoints7.setText(String.valueOf(POINTS_LEVEL_4_1));
		tvMinPoints8.setText(String.valueOf(POINTS_LEVEL_4_3));
		tvMinPoints9.setText(String.valueOf(POINTS_LEVEL_4_3));
		tvMinPoints10.setText(String.valueOf(POINTS_LEVEL_4_4));

		TextView tvPointsForSecond1 = (TextView) findViewById(R.id.value_1_PointsForSecond);
		TextView tvPointsForSecond2 = (TextView) findViewById(R.id.value_2_PointsForSecond);
		TextView tvPointsForSecond3 = (TextView) findViewById(R.id.value_3_PointsForSecond);
		TextView tvPointsForSecond4 = (TextView) findViewById(R.id.value_4_PointsForSecond);
		TextView tvPointsForSecond5 = (TextView) findViewById(R.id.value_5_PointsForSecond);
		TextView tvPointsForSecond6 = (TextView) findViewById(R.id.value_6_PointsForSecond);
		TextView tvPointsForSecond7 = (TextView) findViewById(R.id.value_7_PointsForSecond);
		TextView tvPointsForSecond8 = (TextView) findViewById(R.id.value_8_PointsForSecond);
		TextView tvPointsForSecond9 = (TextView) findViewById(R.id.value_9_PointsForSecond);
		TextView tvPointsForSecond10 = (TextView) findViewById(R.id.value_10_PointsForSecond);

		tvPointsForSecond1.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_1_1));
		tvPointsForSecond2.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_2_1));
		tvPointsForSecond3.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_2_2));
		tvPointsForSecond4.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_3_1));
		tvPointsForSecond5.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_3_2));
		tvPointsForSecond6.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_3_3));
		tvPointsForSecond7.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_4_1));
		tvPointsForSecond8.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_4_2));
		tvPointsForSecond9.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_4_3));
		tvPointsForSecond10.setText(String.valueOf(POINTS_FOR_SEC_LEVEL_4_4));
	}
}