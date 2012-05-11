package org.ultradark.memomeme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import ultradark.memomeme.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageSwitcher;
import android.widget.TextView;

public class Game extends Activity {
	/** Called when the activity is first created. */
	int score;
	int pairFound;
	int turnedCardsCount;

	int[] currentTurnedCards;

	ArrayList<Integer> currentSet;
	ArrayList<Integer> positions;
	ArrayList<Integer> turnedPos;
	ArrayList<Integer> pulledPos;

	Card cards[];
	Card turnedCard1;
	Card turnedCard2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		if (getIntent().getExtras() != null && getIntent().getBooleanExtra("isExit", false)) {
			Intent go = new Intent(this.getBaseContext(), org.ultradark.memomeme.Main.class);
			go.putExtra("isExit", true);
			startActivity(go);
			finish();
		}

		final TextView scoreText = (TextView) findViewById(R.id.textScore);
		cards = new Card[16];
		
		final TextView mTextField = (TextView) findViewById(R.id.textTimer);

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
				(ImageSwitcher) findViewById(R.id.imageView16) };

		if (savedInstanceState == null) {

			turnedPos = new ArrayList<Integer>();
			pulledPos = new ArrayList<Integer>();
			currentTurnedCards = new int[2];
			score = 0;

			ArrayList<Integer> cardInts = new ArrayList<Integer>(Arrays.asList(
					R.drawable.troll01, R.drawable.troll02, R.drawable.troll03,
					R.drawable.troll04, R.drawable.troll05, R.drawable.troll06,
					R.drawable.troll07, R.drawable.troll08, R.drawable.troll09));

			positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5,
					6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

			Collections.shuffle(cardInts);
			Collections.shuffle(positions);
			currentSet = new ArrayList<Integer>(cardInts.subList(0, 8));

		} else {
			currentTurnedCards = savedInstanceState
					.getIntArray("currentTurnedCards");

			score = savedInstanceState.getInt("score");
			pairFound = savedInstanceState.getInt("pairFound");
			turnedCardsCount = savedInstanceState.getInt("turnedCardsCount");

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
						positions.get(j), this);
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

		OnClickListener ocl = new OnClickListener() {

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

		for (Card card : cards) {
			card.getImage().setOnClickListener(ocl);
		}
		
		new CountDownTimer(30000, 1000) {

		     public void onTick(long millisUntilFinished) {
		         mTextField.setText("00:" + (millisUntilFinished / 1000 >= 10 ? (millisUntilFinished / 1000) : ("0" + millisUntilFinished / 1000)));
		     }

		     public void onFinish() {
		    	 Intent go = new Intent(Game.this,
							org.ultradark.memomeme.GameOver.class);
					if (Integer.parseInt((String) scoreText.getText()) >= 0) {
						go.putExtra("isWin", true);
					} else {
						go.putExtra("isWin", false);
					}
					startActivity(go);
					finish();
		     }
		  }.start();
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
		int sc = 0;

		if (exact) {
			pairFound++;
			score += 2;
			sc = score;
		} else {
			sc = --score;
		}

		setScoreText(v, sc);

		if (pairFound == 8 || sc < (pairFound * 2 - 16)) {
			Intent go = new Intent(v.getContext(),
					org.ultradark.memomeme.GameOver.class);
			if (sc >= 0) {
				go.putExtra("isWin", true);
			} else {
				go.putExtra("isWin", false);
			}
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

		savedInstanceState.putIntegerArrayList("currentSet", currentSet);
		savedInstanceState.putIntegerArrayList("positions", positions);
		savedInstanceState.putIntegerArrayList("turnedPos", turnedPos);
		savedInstanceState.putIntegerArrayList("pulledPos", pulledPos);

		super.onSaveInstanceState(savedInstanceState);
	}
}