package am.tir.games.memomemefree.activities;

import static am.tir.games.memomemefree.utils.MemeSettings.ALL_PAIRS_LEVEL_1;
import static am.tir.games.memomemefree.utils.MemeSettings.ALL_PAIRS_LEVEL_2;
import static am.tir.games.memomemefree.utils.MemeSettings.ALL_PAIRS_LEVEL_3;
import static am.tir.games.memomemefree.utils.MemeSettings.ALL_PAIRS_LEVEL_4;
import static am.tir.games.memomemefree.utils.MemeSettings.BEFORE_PREVIEW_TIME;
import static am.tir.games.memomemefree.utils.MemeSettings.FORMATTER;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_1_1;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_2_1;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_2_2;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_3_1;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_3_2;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_3_3;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_4_1;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_4_2;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_4_3;
import static am.tir.games.memomemefree.utils.MemeSettings.GAME_TIME_LEVEL_4_4;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_1_1;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_2_1;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_2_2;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_3_1;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_3_2;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_3_3;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_4_1;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_4_2;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_4_3;
import static am.tir.games.memomemefree.utils.MemeSettings.POINTS_LEVEL_4_4;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_1_1;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_2_1;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_2_2;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_3_1;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_3_2;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_3_3;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_4_1;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_4_2;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_4_3;
import static am.tir.games.memomemefree.utils.MemeSettings.PREV_TIME_LEVEL_4_4;
import static am.tir.games.memomemefree.utils.MemeSettings.boardBottomMargin;
import static am.tir.games.memomemefree.utils.MemeSettings.boardHeight;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_1;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_2;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_3;
import static am.tir.games.memomemefree.utils.MemeSettings.cHeight_lvl_4;
import static am.tir.games.memomemefree.utils.MemeSettings.isSoundOn;
import static am.tir.games.memomemefree.utils.MemeSettings.sound_mode;

import java.util.ArrayList;
import java.util.Collections;

import am.tir.games.memomemefree.utils.Card;
import am.tir.games.memomemefree.utils.ScoreModel;
import am.tir.games.memomemefree.utils.User;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class Level extends Activity {

	private User user;

	private long levelTime;

	private MediaPlayer soundPairFail;
	private MediaPlayer soundPairFound;

	private int allPairs;
	private int coverId;
	private int cardDrawablesArrayId;

	private int cHeight;

	private int score;
	private int pairFound;
	private int turnedCardsCount;
	private int isShowing;

	private int points;

	private int[] currentTurnedCards;

	private long gameTotalTime;
	private long beforePreviewTime;
	private long previewTime;

	private ArrayList<Integer> currentSet;
	private ArrayList<Integer> positions;
	private ArrayList<Integer> turnedPos;
	private ArrayList<Integer> pulledPos;

	private Card cards[];
	private Card turnedCard1;
	private Card turnedCard2;

	private int combo;
	private CountDownTimer cdt;
	private CountDownTimer cd1;
	private CountDownTimer cd2;

	private OnClickListener ocl;
	private TextView timerText;
	private TextView scoreText;
	private TextView comboText;

	private RelativeLayout.LayoutParams lpBoard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initLevel();

		lpBoard = new RelativeLayout.LayoutParams(boardHeight, boardHeight);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			lpBoard.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			lpBoard.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lpBoard.bottomMargin = boardBottomMargin;
		} else {
			lpBoard.addRule(RelativeLayout.CENTER_IN_PARENT);
		}

		TableLayout iBoard = (TableLayout) findViewById(R.id.tableBoard);
		ImageView imgBoard = (ImageView) findViewById(R.id.imageBoard);
		iBoard.setLayoutParams(lpBoard);
		imgBoard.setLayoutParams(lpBoard);
		imgBoard.setScaleType(ImageView.ScaleType.FIT_XY);

		timerText = (TextView) findViewById(R.id.textTimer);
		scoreText = (TextView) findViewById(R.id.textScore);
		comboText = (TextView) findViewById(R.id.textCombo);

		timerText.setTextColor(getResources().getColor(R.color.timerColor1));

		cards = new Card[allPairs * 2];

		combo = 1;

		int[] slotIds = new int[] { R.id.imageView1, R.id.imageView2,
				R.id.imageView3, R.id.imageView4, R.id.imageView5,
				R.id.imageView6, R.id.imageView7, R.id.imageView8,
				R.id.imageView9, R.id.imageView10, R.id.imageView11,
				R.id.imageView12, R.id.imageView13, R.id.imageView14,
				R.id.imageView15, R.id.imageView16, R.id.imageView17,
				R.id.imageView18, R.id.imageView19, R.id.imageView20,
				R.id.imageView21, R.id.imageView22, R.id.imageView23,
				R.id.imageView24, R.id.imageView25, R.id.imageView26,
				R.id.imageView27, R.id.imageView28, R.id.imageView29,
				R.id.imageView30, R.id.imageView31, R.id.imageView32,
				R.id.imageView33, R.id.imageView34, R.id.imageView35,
				R.id.imageView36 };

		if (savedInstanceState == null) {

			turnedPos = new ArrayList<Integer>();
			pulledPos = new ArrayList<Integer>();
			currentTurnedCards = new int[2];
			score = 0;

			TypedArray cardsArray = getResources().obtainTypedArray(
					cardDrawablesArrayId);
			ArrayList<Integer> cardInts = new ArrayList<Integer>(
					cardsArray.length());
			for (int i = 0; i < cardsArray.length(); i++) {
				cardInts.add(cardsArray.getResourceId(i, 0));
			}

			positions = new ArrayList<Integer>(allPairs * 2);
			for (int i = 0; i < allPairs * 2; i++) {
				positions.add(i);
			}

			Collections.shuffle(cardInts);
			Collections.shuffle(positions);
			currentSet = new ArrayList<Integer>(cardInts.subList(0, allPairs));

		} else {

			currentTurnedCards = savedInstanceState
					.getIntArray("currentTurnedCards");

			score = savedInstanceState.getInt("score");
			pairFound = savedInstanceState.getInt("pairFound");
			turnedCardsCount = savedInstanceState.getInt("turnedCardsCount");
			combo = savedInstanceState.getInt("combo");
			isShowing = savedInstanceState.getInt("isShowing");

			gameTotalTime = savedInstanceState.getLong("ms");
			beforePreviewTime = savedInstanceState.getLong("msshow1");
			previewTime = savedInstanceState.getLong("msshow2");

			currentSet = savedInstanceState.getIntegerArrayList("currentSet");
			positions = savedInstanceState.getIntegerArrayList("positions");
			turnedPos = savedInstanceState.getIntegerArrayList("turnedPos");
			pulledPos = savedInstanceState.getIntegerArrayList("pulledPos");
		}

		setScoreText(scoreText, score);
		comboText.setText(Integer.toString(points * combo));
		if (combo > 1) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor2));
		} else {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor1));
		}

		int j = 0;
		for (Integer i : currentSet) {
			for (int t = 0; t < 2; t++) {
				ImageSwitcher isw = (ImageSwitcher) findViewById(slotIds[positions
						.get(j)]);
				cards[j] = new Card(isw, i, positions.get(j), this, cHeight,
						coverId);
				j++;
			}
		}

		if (savedInstanceState != null) {
			for (Integer i : turnedPos) {
				cards[positions.indexOf(i)].turnCard();
			}

			for (Integer i : pulledPos) {
				cards[positions.indexOf(i)].pullOut();
			}

			switch (turnedCardsCount) {
			case 0:
				break;
			case 1:
				turnedCard1 = cards[positions.indexOf(currentTurnedCards[0])];
				break;
			case 2:
				turnedCard1 = cards[positions.indexOf(currentTurnedCards[0])];
				turnedCard2 = cards[positions.indexOf(currentTurnedCards[1])];
			default:
				break;
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

		ocl = new OnClickListener() {

			public void onClick(View v) {

				for (Card card : cards) {
					if (card.getId() == v.getId() && card.getIsOut())
						return;
				}

				switch (turnedCardsCount) {
				case 0:
					for (Card card : cards) {
						if (card.getId() == v.getId()) {
							card.turnCard();
							turnedCard1 = card;
							currentTurnedCards[0] = card.getPos();
							turnedCardsCount = 1;
						}
					}
					break;
				case 1:
					if (v.getId() != turnedCard1.getId()) {
						for (Card card : cards) {
							if (card.getId() == v.getId()) {
								card.turnCard();
								turnedCard2 = card;
								currentTurnedCards[1] = card.getPos();
								turnedCardsCount = 2;
							}
						}
						if (turnedCard1.getMemeInt() == turnedCard2
								.getMemeInt()) {
							if (isSoundOn && soundPairFound != null) {
								soundPairFound.start();
							}
							proceedScore(scoreText, true);
							turnedCard1.pullOut();
							turnedCard2.pullOut();
							turnedCardsCount = 0;
						} else {
							if (isSoundOn && soundPairFail != null) {
								soundPairFail.start();
							}
							proceedScore(scoreText, false);
						}
					}
					break;
				case 2:
					if (!turnedCard1.getIsOut() && !turnedCard1.getIsOut()) {
						turnedCard1.turnCard();
						turnedCard2.turnCard();
					}

					if (v.getId() == turnedCard1.getId()
							|| v.getId() == turnedCard2.getId()) {
						turnedCardsCount = 0;
						break;
					}
					for (Card card : cards) {
						if (card.getId() == v.getId()) {
							card.turnCard();
							turnedCard1 = card;
							currentTurnedCards[0] = card.getPos();
							turnedCardsCount = 1;
						}
					}
					break;
				}
			}
		};

		if (isShowing != 2) {
			if (isShowing == 0) {
				cd1 = new CountDownTimer(beforePreviewTime, 100) {
					public void onTick(long millisUntilFinished) {
						beforePreviewTime -= 100;
					}

					public void onFinish() {
						for (Card card : cards) {
							card.turnCard();
						}
						isShowing = 1;
					}
				};
				cd1.start();
			}

			cd2 = new CountDownTimer(previewTime, 100) {

				public void onTick(long millisUntilFinished) {
					previewTime -= 100;
				}

				public void onFinish() {
					for (Card card : cards) {
						card.turnCard();
					}
					for (Card card : cards) {
						card.getImage().setOnClickListener(ocl);
					}
					isShowing = 2;
				}
			};
			cd2.start();
		} else {
			for (Card card : cards) {
				card.getImage().setOnClickListener(ocl);
			}
		}

		cdt = new CountDownTimer(gameTotalTime, 1000) {

			public void onTick(long millisUntilFinished) {
				gameTotalTime = millisUntilFinished;

				timerText
						.setText(millisUntilFinished > levelTime ? convertTimeText(levelTime)
								: convertTimeText(millisUntilFinished));

				if (millisUntilFinished < 6000) {
					timerText.setTextColor(getResources().getColor(
							R.color.timerColor2));
				}

			}

			public void onFinish() {
				Intent go = new Intent(getBaseContext(), EndLevel.class);
				go.putExtra("score", score);
				user.setPoints(user.getPoints() + score);
				user.setLevel(user.getLevel() + 1);
				ScoreModel scoreModel = new ScoreModel(getBaseContext());
				scoreModel.update(user);
				scoreModel.close();
				go.putExtra("user", user);
				go.putExtra("isWin", false);
				startActivity(go);
				finish();
			}
		};

		cdt.start();
	}

	private void initLevel() {
		// Get level
		user = (User) getIntent().getParcelableExtra("user");
		int level = user.getLevel();
		
		switch (sound_mode) {
		case TROLLISH:
			soundPairFail = MediaPlayer.create(getBaseContext(),
					R.raw.pair_fail_trollish);
			soundPairFound = MediaPlayer.create(getBaseContext(),
					R.raw.pair_found_trollish);
			break;
		case SIMPSONS:
			soundPairFail = MediaPlayer.create(getBaseContext(),
					R.raw.pair_fail_simpsons);
			soundPairFound = MediaPlayer.create(getBaseContext(),
					R.raw.pair_found_simpsons);
			break;
		case NORMAL:
			soundPairFail = MediaPlayer.create(getBaseContext(),
					R.raw.pair_fail_normal);
			soundPairFound = MediaPlayer.create(getBaseContext(),
					R.raw.pair_found_normal);
			break;
		}

		switch (level) {
		case 0:
			initLevels(R.layout.level_01, R.integer.cover_lvl_1,
					R.array.cards_lvl_1, cHeight_lvl_1, POINTS_LEVEL_1_1,
					GAME_TIME_LEVEL_1_1, PREV_TIME_LEVEL_1_1, ALL_PAIRS_LEVEL_1);
			break;
		case 1:
			initLevels(R.layout.level_02, R.integer.cover_lvl_2,
					R.array.cards_lvl_2, cHeight_lvl_2, POINTS_LEVEL_2_1,
					GAME_TIME_LEVEL_2_1, PREV_TIME_LEVEL_2_1, ALL_PAIRS_LEVEL_2);
			break;
		case 2:
			initLevels(R.layout.level_02, R.integer.cover_lvl_2,
					R.array.cards_lvl_2, cHeight_lvl_2, POINTS_LEVEL_2_2,
					GAME_TIME_LEVEL_2_2, PREV_TIME_LEVEL_2_2, ALL_PAIRS_LEVEL_2);
			break;
		case 3:
			initLevels(R.layout.level_03, R.integer.cover_lvl_3,
					R.array.cards_lvl_3, cHeight_lvl_3, POINTS_LEVEL_3_1,
					GAME_TIME_LEVEL_3_1, PREV_TIME_LEVEL_3_1, ALL_PAIRS_LEVEL_3);
			break;
		case 4:
			initLevels(R.layout.level_03, R.integer.cover_lvl_3,
					R.array.cards_lvl_3, cHeight_lvl_3, POINTS_LEVEL_3_2,
					GAME_TIME_LEVEL_3_2, PREV_TIME_LEVEL_3_2, ALL_PAIRS_LEVEL_3);
			break;
		case 5:
			initLevels(R.layout.level_03, R.integer.cover_lvl_3,
					R.array.cards_lvl_3, cHeight_lvl_3, POINTS_LEVEL_3_3,
					GAME_TIME_LEVEL_3_3, PREV_TIME_LEVEL_3_3, ALL_PAIRS_LEVEL_3);
			break;
		case 6:
			initLevels(R.layout.level_04, R.integer.cover_lvl_4,
					R.array.cards_lvl_4, cHeight_lvl_4, POINTS_LEVEL_4_1,
					GAME_TIME_LEVEL_4_1, PREV_TIME_LEVEL_4_1, ALL_PAIRS_LEVEL_4);
			break;
		case 7:
			initLevels(R.layout.level_04, R.integer.cover_lvl_4,
					R.array.cards_lvl_1, cHeight_lvl_4, POINTS_LEVEL_4_2,
					GAME_TIME_LEVEL_4_2, PREV_TIME_LEVEL_4_2, ALL_PAIRS_LEVEL_4);
			break;
		case 8:
			initLevels(R.layout.level_04, R.integer.cover_lvl_4,
					R.array.cards_lvl_4, cHeight_lvl_4, POINTS_LEVEL_4_3,
					GAME_TIME_LEVEL_4_3, PREV_TIME_LEVEL_4_3, ALL_PAIRS_LEVEL_4);
			break;
		case 9:
			initLevels(R.layout.level_04, R.integer.cover_lvl_4,
					R.array.cards_lvl_4, cHeight_lvl_4, POINTS_LEVEL_4_4,
					GAME_TIME_LEVEL_4_4, PREV_TIME_LEVEL_4_4, ALL_PAIRS_LEVEL_4);
			break;
		default:
			break;
		}

		final ImageButton ibSound = (ImageButton) findViewById(R.id.ibSound);
		if (isSoundOn) {
			ibSound.setImageResource(R.drawable.sounds_on);
		}

		ibSound.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				isSoundOn = !isSoundOn;
				if (isSoundOn) {
					ibSound.setImageResource(R.drawable.sounds_on);
				} else {
					ibSound.setImageResource(R.drawable.sounds_muted);
				}
			}
		});
	}

	private String convertTimeText(long milliseconds) {
		String seconds = FORMATTER.format((milliseconds / 1000) % 60);
		String minutes = FORMATTER.format((milliseconds / (1000 * 60)) % 60);
		return minutes + ":" + seconds;
	}

	/**
	 * @param layoutId
	 * @param coverId
	 * @param cardDrawablesArrayId
	 * @param cHeight
	 * @param points
	 * @param levelTime
	 * @param previewTime
	 * @param allPairs
	 */
	private void initLevels(int layoutId, int coverId,
			int cardDrawablesArrayId, int cHeight, int points, long levelTime,
			long previewTime, int allPairs) {

		this.setContentView(layoutId);

		this.beforePreviewTime = BEFORE_PREVIEW_TIME;

		this.coverId = coverId;
		this.cardDrawablesArrayId = cardDrawablesArrayId;
		this.cHeight = cHeight;
		this.points = points;
		this.levelTime = levelTime;
		this.previewTime = previewTime;
		this.allPairs = allPairs;

		this.gameTotalTime = this.levelTime + this.beforePreviewTime
				+ this.previewTime;
	}

	protected void setScoreText(View v, int sc) {
		TextView tv = (TextView) v;
		tv.setTextColor(getResources().getColor(R.color.scoreColor));
		tv.setText(Integer.toString(sc));
	}

	protected void proceedScore(View v, boolean exact) {

		if (exact) {
			pairFound++;
			score += points * combo;
			combo++;
			setScoreText(v, score);
		} else {
			combo = 1;
		}

		comboText.setText(Integer.toString(points * combo));
		if (combo > 1 && pairFound != allPairs) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor2));
		} else if (pairFound != allPairs) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor1));
		} else {
			comboText.setVisibility(View.GONE);
		}

		if (pairFound == allPairs) {
			Intent go = new Intent(getBaseContext(), EndLevel.class);
			go.putExtra("score", score);
			user.setPoints(user.getPoints() + score);
			user.setLevel(user.getLevel() + 1);
			ScoreModel scoreModel = new ScoreModel(getBaseContext());
			scoreModel.update(user);
			scoreModel.close();
			go.putExtra("user", user);
			go.putExtra("isWin", true);
			startActivity(go);
			finish();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		turnedPos.clear();
		pulledPos.clear();

		for (Card card : cards) {
			if (card.getIsTurned()) {
				turnedPos.add(card.getPos());
			}
			if (card.getIsOut()) {
				pulledPos.add(card.getPos());
			}
		}
		savedInstanceState
				.putIntArray("currentTurnedCards", currentTurnedCards);

		savedInstanceState.putInt("score", score);
		savedInstanceState.putInt("pairFound", pairFound);
		savedInstanceState.putInt("turnedCardsCount", turnedCardsCount);
		savedInstanceState.putInt("combo", combo);
		savedInstanceState.putInt("isShowing", isShowing);

		savedInstanceState.putLong("ms", gameTotalTime);
		savedInstanceState.putLong("msshow1", beforePreviewTime);
		savedInstanceState.putLong("msshow2", previewTime);

		savedInstanceState.putIntegerArrayList("currentSet", currentSet);
		savedInstanceState.putIntegerArrayList("positions", positions);
		savedInstanceState.putIntegerArrayList("turnedPos", turnedPos);
		savedInstanceState.putIntegerArrayList("pulledPos", pulledPos);

		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onPause() {
		super.onPause();
		cdt.cancel();
		if (isShowing != 2) {
			if (isShowing == 0)
				cd1.cancel();
			cd2.cancel();
		}
	}
}