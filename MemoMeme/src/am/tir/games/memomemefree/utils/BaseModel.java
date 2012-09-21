package am.tir.games.memomemefree.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * @author Artak.Gevorgyan
 *
 */
public class BaseModel implements BaseColumns {
	protected final String TABLE_NAME;

	protected final Context context;

	protected final SQLiteDatabase database;

	public BaseModel(final Context context, final String table) {
		this.context = context;
		TABLE_NAME = table;
		database = new ScoreDatabase(context).getWritableDatabase();
	}

	public Cursor getAll() {
		return database.query(TABLE_NAME, null, null, null, null, null, null);
	}

	public void remove(final int id) {
		database.delete(TABLE_NAME, BaseColumns._ID + " = " + id, null);
	}

	public Cursor get(final int id) {
		return database.query(TABLE_NAME, null, BaseColumns._ID + " = " + id,
				null, null, null, null);
	}

	public void close() {
		database.close();
	}
}
