package am.tir.games.memomemefree.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import am.tir.games.memomemefree.utils.Card;
import am.tir.games.memomemefree.utils.MemeSettings;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Level01 extends Activity {

	private int level;

	private int levelTime;
	private String levelTimeText;

	private int score;
	private int pairFound;
	private int turnedCardsCount;
	private int isShowing;

	private int points;

	private int[] currentTurnedCards;

	private long ms;
	private long msshow1;
	private long msshow2;

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

	private LayoutParams lp0;
	private RelativeLayout.LayoutParams lpBoard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_01);

		initLevel();

		lp0 = new LayoutParams(MemeSettings.cHeight1, MemeSettings.cHeight1);
		ImageView iv0 = (ImageView) findViewById(R.id.imageView0);
		iv0.setLayoutParams(lp0);
		iv0.setScaleType(ImageView.ScaleType.FIT_XY);

		lpBoard = new RelativeLayout.LayoutParams(MemeSettings.boardHeight,
				MemeSettings.boardHeight);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			lpBoard.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			lpBoard.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lpBoard.bottomMargin = MemeSettings.boardBottomMargin;
		} else {
			lpBoard.addRule(RelativeLayout.CENTER_IN_PARENT);
		}

		TableLayout iBoard = (TableLayout) findViewById(R.id.tableBoard);
		ImageView imgBoard = (ImageView) findViewById(R.id.imageBoard);
		imgBoard.setLayoutParams(lpBoard);
		imgBoard.setScaleType(ImageView.ScaleType.FIT_XY);
		iBoard.setLayoutParams(lpBoard);

		timerText = (TextView) findViewById(R.id.textTimer);
		scoreText = (TextView) findViewById(R.id.textScore);
		comboText = (TextView) findViewById(R.id.textCombo);

		timerText.setTextColor(getResources().getColor(R.color.timerColor1));

		cards = new Card[8];

		combo = 1;

		ImageSwitcher[] slots = new ImageSwitcher[] {
				(ImageSwitcher) findViewById(R.id.imageView1),
				(ImageSwitcher) findViewById(R.id.imageView2),
				(ImageSwitcher) findViewById(R.id.imageView3),
				(ImageSwitcher) findViewById(R.id.imageView4),
				(ImageSwitcher) findViewById(R.id.imageView5),
				(ImageSwitcher) findViewById(R.id.imageView6),
				(ImageSwitcher) findViewById(R.id.imageView7),
				(ImageSwitcher) findViewById(R.id.imageView8) };

		if (savedInstanceState == null) {

			turnedPos = new ArrayList<Integer>();
			pulledPos = new ArrayList<Integer>();
			currentTurnedCards = new int[2];
			score = 0;

			TypedArray cardsArray = getResources().obtainTypedArray(
					R.array.cards_lvl_1);
			ArrayList<Integer> cardInts = new ArrayList<Integer>(
					cardsArray.length());
			for (int i = 0; i < cardsArray.length(); i++) {
				cardInts.add(cardsArray.getResourceId(i, 0));
			}

			positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5,
					6, 7));

			Collections.shuffle(cardInts);
			Collections.shuffle(positions);
			currentSet = new ArrayList<Integer>(cardInts.subList(0, 4));

		} else {

			currentTurnedCards = savedInstanceState
					.getIntArray("currentTurnedCards");

			score = savedInstanceState.getInt("score");
			pairFound = savedInstanceState.getInt("pairFound");
			turnedCardsCount = savedInstanceState.getInt("turnedCardsCount");
			combo = savedInstanceState.getInt("combo");
			isShowing = savedInstanceState.getInt("isShowing");

			ms = savedInstanceState.getLong("ms");
			msshow1 = savedInstanceState.getLong("msshow1");
			msshow2 = savedInstanceState.getLong("msshow2");

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
				cards[j] = new Card(slots[positions.get(j)], i,
						positions.get(j), this, MemeSettings.cHeight1,
						R.integer.cover_lvl_1);
				j++;
			}
		}

		if (savedInstanceState != null) {
			for (Integer i : turnedPos) {
				cards[positions.indexOf(i)].turnCard();
			}

			for (Integer i : pulledPos) {
				// cards[i].turnCard();
				cards[positions.indexOf(i)].pullOut();
			}

			switch (turnedCardsCount) {
			case 0:
				break;
			case 1:
				turnedCard1 = cards[positions.indexOf(currentTurnedCards[0])];
				// turnedCard1.turnCard();
				break;
			case 2:
				turnedCard1 = cards[positions.indexOf(currentTurnedCards[0])];
				// turnedCard1.turnCard();
				turnedCard2 = cards[positions.indexOf(currentTurnedCards[1])];
				// turnedCard2.turnCard();
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
							proceedScore(scoreText, true);
							turnedCard1.pullOut();
							turnedCard2.pullOut();
							turnedCardsCount = 0;
						} else {
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
				cd1 = new CountDownTimer(msshow1, 100) {
					public void onTick(long millisUntilFinished) {
						msshow1 -= 100;
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

			cd2 = new CountDownTimer(msshow2, 100) {

				public void onTick(long millisUntilFinished) {
					msshow2 -= 100;
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

		cdt = new CountDownTimer(ms, 1000) {

			public void onTick(long millisUntilFinished) {
				ms = millisUntilFinished;

				if (millisUntilFinished > levelTime) {
					timerText.setText(levelTimeText);
				} else {
					String seconds = MemeSettings.FORMATTER
							.format((millisUntilFinished / 1000) % 60);
					String minutes = MemeSettings.FORMATTER
							.format((millisUntilFinished / (1000 * 60)) % 60);

					timerText.setText(minutes + ":" + seconds);

					if (millisUntilFinished < 6000) {
						timerText.setTextColor(getResources().getColor(
								R.color.timerColor2));
					}
				}
			}

			public void onFinish() {
				Intent go = new Intent(getBaseContext(), EndLevel.class);
				go.putExtra("score", score);
				go.putExtra("user", getIntent().getParcelableExtra("user"));
				go.putExtra("isWin", false);
				go.putExtra("lastLevel", level);
				startActivity(go);
				finish();
			}
		};

		cdt.start();
	}

	private void initLevel() {
		level = getIntent().getIntExtra("level", 1);
		switch (level) {
		case 1:
			points = 50;
			ms = 38000;
			msshow1 = 1000;
			msshow2 = 7000;
			levelTime = (int) (ms - msshow1 - msshow2);
			levelTimeText = "00:30";
			break;
		default:
			break;
		}
	}

	private void setScoreText(View v, int sc) {
		TextView tv = (TextView) v;
		tv.setTextColor(getResources().getColor(R.color.scoreColor));
		tv.setText(Integer.toString(sc));
	}

	private void proceedScore(View v, boolean exact) {

		if (exact) {
			pairFound++;
			score += points * combo;
			combo++;
			setScoreText(v, score);
		} else {
			combo = 1;
		}

		comboText.setText(Integer.toString(points * combo));
		if (combo > 1 && pairFound != 4) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor2));
		} else if (pairFound != 4) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor1));
		} else {
			comboText.setVisibility(View.GONE);
		}

		if (pairFound == 4) {
			Intent go = new Intent(getBaseContext(), EndLevel.class);
			go.putExtra("score", score);
			go.putExtra("user", getIntent().getParcelableExtra("user"));
			go.putExtra("isWin", true);
			go.putExtra("lastLevel", level);
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

		savedInstanceState.putLong("ms", ms);
		savedInstanceState.putLong("msshow1", msshow1);
		savedInstanceState.putLong("msshow2", msshow2);

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