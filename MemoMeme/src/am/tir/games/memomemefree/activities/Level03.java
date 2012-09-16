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

public class Level03 extends Activity {
	/** Called when the activity is first created. */
	int score;
	int pairFound;
	int turnedCardsCount;
	int isShowing;

	int points = 150;

	int[] currentTurnedCards;

	long ms = 68000;
	long msshow1 = 1000;
	long msshow2 = 7000;

	ArrayList<Integer> currentSet;
	ArrayList<Integer> positions;
	ArrayList<Integer> turnedPos;
	ArrayList<Integer> pulledPos;

	Card cards[];
	Card turnedCard1;
	Card turnedCard2;

	private int combo;
	private CountDownTimer cdt;
	private CountDownTimer cd1;
	private CountDownTimer cd2;

	OnClickListener ocl;
	TextView timerText;
	TextView scoreText;
	TextView comboText;

	private LayoutParams lp0;
	private RelativeLayout.LayoutParams lpBoard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_03);

		lp0 = new LayoutParams(MemeSettings.cHeight3, MemeSettings.cHeight3);
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
		iBoard.setLayoutParams(lpBoard);
		imgBoard.setLayoutParams(lpBoard);
		imgBoard.setScaleType(ImageView.ScaleType.FIT_XY);

		timerText = (TextView) findViewById(R.id.textTimer);
		scoreText = (TextView) findViewById(R.id.textScore);
		comboText = (TextView) findViewById(R.id.textCombo);

		timerText.setTextColor(getResources().getColor(R.color.timerColor1));

		cards = new Card[24];

		combo = 1;

		ImageSwitcher[] slots = new ImageSwitcher[] {
				(ImageSwitcher) findViewById(R.id.imageView1),
				(ImageSwitcher) findViewById(R.id.imageView2),
				(ImageSwitcher) findViewById(R.id.imageView3),
				(ImageSwitcher) findViewById(R.id.imageView4),
				(ImageSwitcher) findViewById(R.id.imageView5),
				(ImageSwitcher) findViewById(R.id.imageView6),
				(ImageSwitcher) findViewById(R.id.imageView7),
				(ImageSwitcher) findViewById(R.id.imageView8),
				(ImageSwitcher) findViewById(R.id.imageView9),
				(ImageSwitcher) findViewById(R.id.imageView10),
				(ImageSwitcher) findViewById(R.id.imageView11),
				(ImageSwitcher) findViewById(R.id.imageView12),
				(ImageSwitcher) findViewById(R.id.imageView13),
				(ImageSwitcher) findViewById(R.id.imageView14),
				(ImageSwitcher) findViewById(R.id.imageView15),
				(ImageSwitcher) findViewById(R.id.imageView16),
				(ImageSwitcher) findViewById(R.id.imageView17),
				(ImageSwitcher) findViewById(R.id.imageView18),
				(ImageSwitcher) findViewById(R.id.imageView19),
				(ImageSwitcher) findViewById(R.id.imageView20),
				(ImageSwitcher) findViewById(R.id.imageView21),
				(ImageSwitcher) findViewById(R.id.imageView22),
				(ImageSwitcher) findViewById(R.id.imageView23),
				(ImageSwitcher) findViewById(R.id.imageView24) };

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
					6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
					22, 23));

			Collections.shuffle(cardInts);
			Collections.shuffle(positions);
			currentSet = new ArrayList<Integer>(cardInts.subList(0, 12));

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
						positions.get(j), this, MemeSettings.cHeight3,
						R.integer.cover_lvl_3);
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

				if (millisUntilFinished > 60000) {
					timerText.setText("01:00");
				} else {
					timerText
							.setText("00:"
									+ (millisUntilFinished / 1000 >= 10 ? (millisUntilFinished / 1000)
											: ("0" + millisUntilFinished / 1000)));
					if (millisUntilFinished < 6000) {
						timerText.setTextColor(getResources().getColor(
								R.color.timerColor2));
					}
				}
			}

			public void onFinish() {
				Intent go = new Intent(getBaseContext(),
						am.tir.games.memomemefree.activities.EndLevel.class);
				go.putExtra("score", score);
				go.putExtra("user", getIntent().getParcelableExtra("user"));
				go.putExtra("isWin", false);
				go.putExtra("lastLevel", 3);
				startActivity(go);
				finish();
			}
		};

		cdt.start();
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
		if (combo > 1 && pairFound != 12) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor2));
		} else if (pairFound != 12) {
			comboText
					.setTextColor(getResources().getColor(R.color.comboColor1));
		} else {
			comboText.setVisibility(View.GONE);
		}

		if (pairFound == 12) {
			Intent go = new Intent(getBaseContext(),
					am.tir.games.memomemefree.activities.EndLevel.class);
			go.putExtra("score", score);
			go.putExtra("user", getIntent().getParcelableExtra("user"));
			go.putExtra("isWin", true);
			go.putExtra("lastLevel", 3);
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