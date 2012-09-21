package am.tir.games.memomemefree.utils;

public enum Levels {
	LEVEL_1_1,
	LEVEL_2_1, LEVEL_2_2,
	LEVEL_3_1, LEVEL_3_2, LEVEL_3_3,
	LEVEL_4_1, LEVEL_4_2, LEVEL_4_3, LEVEL_4_4;

	public Levels getNext() {
		return this.ordinal() < Levels.values().length - 1 ? Levels.values()[this
				.ordinal() + 1] : null;
	}
}