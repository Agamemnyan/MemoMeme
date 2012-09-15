package am.tir.games.memomemefree.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable {

	public static final Creator<User> CREATOR = new UserCreator();

	private String userName;
	private long points;

	public User() {
		super();
	}

	public User(Parcel source) {
		Log.v("MemoMeme",
				"ParcelData(Parcel source): time to put back parcel data");
		userName = source.readString();
		points = source.readLong();
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

	public int describeContents() {
		return hashCode();
	}

	public void writeToParcel(Parcel dest, int flags) {
		Log.v("MemoMeme", "writeToParcel..." + flags);
		dest.writeString(userName);
		dest.writeLong(points);
	}
}
