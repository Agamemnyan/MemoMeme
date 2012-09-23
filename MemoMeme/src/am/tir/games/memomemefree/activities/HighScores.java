package am.tir.games.memomemefree.activities;

import java.util.List;

import am.tir.games.memomemefree.utils.ScoreModel;
import am.tir.games.memomemefree.utils.User;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class HighScores extends Activity {
	private ScoreModel scoreModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.high_scores);

		scoreModel = new ScoreModel(this);

		Button mainMenuButton = (Button) findViewById(R.id.buttonMainMenu);
		mainMenuButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		ListView scoreListView = (ListView) findViewById(R.id.scoreListView);

		List<User> users = scoreModel.getAllFormated();

		scoreListView.setAdapter(new ScoreListAdapter(
				R.layout.scores_list_item_view, users, this));
	}

	private static class ScoreListAdapter extends BaseAdapter {

		private List<User> dataSource;
		private int layoutId;
		private LayoutInflater inflater;

		public ScoreListAdapter(int layoutId, List<User> dataSource,
				Context context) {
			this.dataSource = dataSource;
			this.layoutId = layoutId;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return (dataSource != null) ? dataSource.size() : 0;
		}

		public Object getItem(int position) {
			return (dataSource != null) ? dataSource.get(position) : null;
		}

		public long getItemId(int position) {
			return (dataSource != null) ? dataSource.get(position).getId() : 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;

			if (convertView == null) {
				view = inflater.inflate(layoutId, parent, false);
				TextView scoreTextPosition = (TextView) view
						.findViewById(R.id.scoreTextPosition);
				TextView scoreTextPoints = (TextView) view
						.findViewById(R.id.scoreTextPoints);
				TextView scoreTextName = (TextView) view
						.findViewById(R.id.scoreTextName);
				TextView scoreTextDate = (TextView) view
						.findViewById(R.id.scoreTextDate);

				scoreTextPosition.setText(String.valueOf(position + 1));
				scoreTextPoints.setText(String.valueOf(dataSource.get(position)
						.getPoints()));
				scoreTextName.setText(dataSource.get(position).getUserName());
				scoreTextDate.setText("23.09");
			} else {
				view = convertView;
			}

			return view;
		}
	}
}
