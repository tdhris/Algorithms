package bg.unisofia.fmi.dsa.project;

public enum Balance {
	LEFT_HEAVY,
	RIGHT_HEAVY,
	BALANCED,
	LEFT_UNBALANCED,
	RIGHT_UNBALANCED;
	
	public static Balance getBalance(long left, long right) {
		long balance = left - right;
		if (balance == 0) {
			return Balance.BALANCED;
		} else if (balance == 1) {
			return Balance.LEFT_HEAVY;
		} else if (balance == -1) {
			return Balance.RIGHT_HEAVY;
		} else if (balance > 1) {
			return Balance.LEFT_UNBALANCED;
		} else {
			return Balance.RIGHT_UNBALANCED;
		}
	}
}
