package am.tir.games.memomemechallenge.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * @author Artak.Gevorgyan
 *
 */
public class Card {
	public ImageSwitcher card;
	private boolean isTurned = false;
	private boolean isOut = false;
	private final int cover;
	private final int memeFace;
	private final int pos;

	public Card(final ImageSwitcher card, int memeFace, int pos,
			final Context context, final int height, final int coverRes) {

		cover = coverRes;

		card.setFactory(new ViewFactory() {

			public View makeView() {
				ImageView iView = new ImageView(context);
				iView.setScaleType(ImageView.ScaleType.FIT_XY);
				ImageSwitcher.LayoutParams lp = new ImageSwitcher.LayoutParams(
						height, height);
				iView.setLayoutParams(lp);

				return iView;
			}
		});

		if (android.os.Build.VERSION.SDK_INT >= 8) {
			card.setInAnimation(AnimationUtils.loadAnimation(context,
					android.R.anim.slide_in_left));
			card.setOutAnimation(AnimationUtils.loadAnimation(context,
					android.R.anim.slide_out_right));
		}

		card.setImageResource(cover);

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

	public int getMemeInt() {
		return memeFace;
	}
}