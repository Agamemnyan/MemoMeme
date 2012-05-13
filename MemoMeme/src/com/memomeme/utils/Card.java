package com.memomeme.utils;

import com.memomeme.activities.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Card {
	public ImageSwitcher card;
	private boolean isTurned = false;
	private boolean isOut = false;
	private final int cover = R.drawable.cover1;
	private final int memeFace;
	private final int pos;
	
	public Card(final ImageSwitcher card, int memeFace, int pos, final Context context) {
		card.setFactory(new ViewFactory() {
			
			public View makeView() {
				ImageView iView = new ImageView(context);
				iView.setScaleType(ImageView.ScaleType.CENTER);
				iView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				iView.setBackgroundColor(0xFF000000);
				return iView;
			}
		});
		card.setInAnimation(
				AnimationUtils.loadAnimation(context,
						android.R.anim.slide_in_left));
		card.setOutAnimation(
				AnimationUtils.loadAnimation(context,
						android.R.anim.slide_out_right));
		card.setImageResource(R.drawable.cover1);
		
		this.card = card;
		this.memeFace = memeFace;
		this.pos = pos;
	}
	
	public void turnCard() {
		isTurned = !isTurned;
		if (!isTurned) {
			card.setImageResource(cover);
		}
		if (isTurned) {
			card.setImageResource(memeFace);
		}
	}
	
	public ImageSwitcher getImage() {
		return card;
	}
	
	public boolean getIsOut() {
		return isOut;
	}
	
	public boolean getIsTurned() {
		return isTurned;
	}
	
	public int getId() {
		return card.getId();
	}

	public void pullOut() {
		isOut = !isOut;
	}

	public int getPos() {
		return pos;
	}
	
	public int getMemeInt () {
		return memeFace;
	}
}
