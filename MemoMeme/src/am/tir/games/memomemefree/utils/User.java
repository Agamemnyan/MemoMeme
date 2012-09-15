package am.tir.games.memomemefree.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable {

	public static final Creator<User> CREATOR = new UserCreator();
	
	private int id;
	private String userName;
	private int lvl;
	private long points;

	public User() {
		super();
	}

	public User(Parcel source) {
		Log.v("MemoMeme",
				"ParcelData(Parcel source): time to put back parcel data");
		id = source.readInt();
		userName = source.readString();
		lvl = source.readInt();
		points = source.readLong();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
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
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
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
		dest.writeInt(id);
		dest.writeString(userName);
		dest.writeInt(lvl);
		dest.writeLong(points);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
