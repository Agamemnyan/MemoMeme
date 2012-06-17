package com.memomeme.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.memomeme.utils.Card;
import com.memomeme.utils.MemeSettings;

import com.memomeme.activities.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageSwitcher;
import android.widget.TextView;

public class Level04 extends Activity {
	/** Called when the activity is first created. */
	int score;
	int pairFound;
	int turnedCardsCount;
	int isShowing;

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

	private int cHeight;

	OnClickListener ocl;
	TextView scoreText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_04);

		cHeight = MemeSettings.dHeight * 108 / 768;

		scoreText = (TextView) findViewById(R.id.textScore);
		cards = new Card[36];

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
				(ImageSwitcher) findViewById(R.id.imageView24),
				(ImageSwitcher) findViewById(R.id.imageView25),
				(ImageSwitcher) findViewById(R.id.imageView26),
				(ImageSwitcher) findViewById(R.id.imageView27),
				(ImageSwitcher) findViewById(R.id.imageView28),
				(ImageSwitcher) findViewById(R.id.imageView29),
				(ImageSwitcher) findViewById(R.id.imageView30),
				(ImageSwitcher) findViewById(R.id.imageView31),
				(ImageSwitcher) findViewById(R.id.imageView32),
				(ImageSwitcher) findViewById(R.id.imageView33),
				(ImageSwitcher) findViewById(R.id.imageView34),
				(ImageSwitcher) findViewById(R.id.imageView35),
				(ImageSwitcher) findViewById(R.id.imageView36) };

		if (savedInstanceState == null) {

			turnedPos = new ArrayList<Integer>();
			pulledPos = new ArrayList<Integer>();
			currentTurnedCards = new int[2];
			score = 0;

			ArrayList<Integer> cardInts = new ArrayList<Integer>(Arrays.asList(
					R.drawable.troll01, R.drawable.troll02, R.drawable.troll03,
					R.drawable.troll04, R.drawable.troll05, R.drawable.troll06,
					R.drawable.troll07, R.drawable.troll08, R.drawable.troll09,
					R.drawable.troll10, R.drawable.troll11, R.drawable.troll12,
					R.drawable.troll13, R.drawable.troll14, R.drawable.troll15,
					R.drawable.troll16, R.drawable.troll17, R.drawable.troll18,
					R.drawable.troll19, R.drawable.troll20));

			positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5,
					6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
					22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35));

			Collections.shuffle(cardInts);
			Collections.shuffle(positions);
			currentSet = new ArrayList<Integer>(cardInts.subList(0, 18));

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

		int j = 0;
		for (Integer i : currentSet) {
			for (int t = 0; t < 2; t++) {
				cards[j] = new Card(slots[positions.get(j)], i,
						positions.get(j), this, cHeight);
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
		final TextView mTextField = (TextView) findViewById(R.id.textTimer);

		cdt = new CountDownTimer(ms, 1000) {

			public void onTick(long millisUntilFinished) {
				ms = millisUntilFinished;

				if (ms > 60000) {
					mTextField.setText("01:00");
				} else {
					mTextField
							.setText("00:"
									+ (millisUntilFinished / 1000 >= 10 ? (millisUntilFinished / 1000)
											: ("0" + millisUntilFinished / 1000)));
				}
			}

			public void onFinish() {
				Intent go = new Intent(Level04.this,
						com.memomeme.activities.EndLevel.class);
				go.putExtra("isWin", false);
				go.putExtra("lastLevel", 4);
				startActivity(go);
				finish();
			}
		};

		cdt.start();
	}

	protected void setScoreText(View v, int sc) {
		TextView tv = (TextView) v;

		if (sc > 0) {
			tv.setTextColor(Color.GREEN);
		} else if (sc == 0) {
			tv.setTextColor(Color.DKGRAY);
		} else {
			tv.setTextColor(Color.RED);
		}

		tv.setText(Integer.toString(sc));
	}

	protected void proceedScore(View v, boolean exact) {

		if (exact) {
			pairFound++;
			score += 250 * combo;
			combo++;
			setScoreText(v, score);
		} else {
			combo = 1;
		}

		if (pairFound == 18) {
			Intent go = new Intent(v.getContext(),
					com.memomeme.activities.EndLevel.class);
			go.putExtra("isWin", true);
			go.putExtra("lastLevel", 4);
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