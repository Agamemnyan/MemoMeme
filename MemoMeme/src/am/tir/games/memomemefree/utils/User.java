package am.tir.games.memomemefree.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class User implements Parcelable {

	public static final Creator<User> CREATOR = new UserCreator();

	private long id;
	private String userName;
	private long points;
	private int level;

	public User() {
		super();
	}

	public User(Parcel source) {
		Log.v("MemoMeme",
				"ParcelData(Parcel source): time to put back parcel data");
		id = source.readLong();
		userName = source.readString();
		points = source.readLong();
		level = source.readInt();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the points
	 */
	public long getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(long points) {
		this.points = points;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	public int describeContents() {
		return hashCode();
	}

	public void writeToParcel(Parcel dest, int flags) {
		Log.v("MemoMeme", "writeToParcel..." + flags);
		dest.writeLong(id);
		dest.writeString(userName);
		dest.writeLong(points);
		dest.writeInt(level);
	}
}
