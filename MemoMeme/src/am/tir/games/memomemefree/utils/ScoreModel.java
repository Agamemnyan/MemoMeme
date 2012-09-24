package am.tir.games.memomemefree.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class ScoreModel extends BaseModel {

	public final static String TABLE_NAME = "Score";
	public static final String KEY_VALUE = "value";
	public static final String KEY_NAME = "name";
	public static final String KEY_LEVEL = "level";

	public ScoreModel(final Context context) {
		super(context, TABLE_NAME);
	}

	public Cursor getAllOrdered() {
		return database.query(TABLE_NAME, null, null, null, null, null,
				KEY_VALUE + " DESC");
	}

	public Cursor getNonZeroOrdered() {
		return database.query(TABLE_NAME, null, KEY_VALUE + ">" + "0", null,
				null, null, KEY_VALUE + " DESC");
	}

	public List<User> getAllFormated() {

		Cursor cursor = getNonZeroOrdered();

		List<User> result = new ArrayList<User>(cursor.getCount());

		for (int i = 0; i < cursor.getCount(); i++) {
			if (!cursor.moveToPosition(i)) {
				break;
			}
			User user = new User();
			user.setId(cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)));
			user.setPoints(cursor.getLong(cursor
					.getColumnIndex(ScoreModel.KEY_VALUE)));
			user.setUserName(cursor.getString(cursor
					.getColumnIndex(ScoreModel.KEY_NAME)));
			user.setLevel(cursor.getInt(cursor
					.getColumnIndex(ScoreModel.KEY_LEVEL)));
			result.add(user);
		}

		cursor.close();
		return result;
	}

	public long getBestScore() {
		long result = 0;
		Cursor cursor = getNonZeroOrdered();
		if (cursor.moveToPosition(0)) {
			result = cursor
					.getLong(cursor.getColumnIndex(ScoreModel.KEY_VALUE));
		}

		cursor.close();
		return result;
	}

	public boolean getIsEmpty() {
		Cursor cursor = getAllOrdered();
		boolean result = false;

		if (cursor.getCount() == 0) {
			result = true;
		}

		cursor.close();
		return result;
	}

	public User add(String name) {
		final ContentValues values = new ContentValues();
		values.put(KEY_VALUE, 0);
		values.put(KEY_NAME, name);
		values.put(KEY_LEVEL, 0);

		ContentValues valuesTemp = new ContentValues();
		valuesTemp.put(KEY_LEVEL, 0);
		String whereClause = KEY_LEVEL + " > 0";
		database.update(TABLE_NAME, values, whereClause, null);

		long id = database.insert(TABLE_NAME, null, values);

		User result = new User();
		result.setId(id);
		result.setUserName(name);

		return result;
	}

	public User getContinueUserIfAny() {
		User user = null;
		Cursor cursor = database.query(TABLE_NAME, null, KEY_LEVEL + ">" + "0",
				null, null, null, KEY_VALUE + " DESC");
		if (cursor.moveToPosition(0)) {
			user = new User();
			user.setId(cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)));
			user.setPoints(cursor.getLong(cursor
					.getColumnIndex(ScoreModel.KEY_VALUE)));
			user.setUserName(cursor.getString(cursor
					.getColumnIndex(ScoreModel.KEY_NAME)));
			user.setLevel(cursor.getInt(cursor
					.getColumnIndex(ScoreModel.KEY_LEVEL)));
		}

		cursor.close();

		return user;
	}

	public void update(User user) {
		final ContentValues values = new ContentValues();
		values.put(KEY_VALUE, user.getPoints());
		values.put(KEY_NAME, user.getUserName());
		values.put(KEY_LEVEL, user.getLevel());

		String whereClause = BaseColumns._ID + " = " + user.getId();

		database.update(TABLE_NAME, values, whereClause, null);
	}

	@Override
	public void remove(final int id) {
		database.delete(TABLE_NAME, BaseColumns._ID + " = " + id, null);
	}
}