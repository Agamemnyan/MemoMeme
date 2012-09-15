package am.tir.games.memomemefree.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class UserCreator implements Creator<User> {

	public User createFromParcel(Parcel source) {
		return new User(source);
	}

	public User[] newArray(int size) {
		return new User[size];
	}

}
