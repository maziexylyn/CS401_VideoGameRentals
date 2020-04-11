
public class regular {

	private float accountBalance;
	
	public regular()
	{
		this.accountBalance = 0;
	}
	public regular(float ab)
	{
		this.accountBalance = ab;
	}
	

	/**
	 * @return the accountBalance
	 */
	public float getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
	@Override
	public String toString() {
		return "regular [accountBalance=" + accountBalance + "]";
	}

	/*
	 * The searchGame() method will allow the user to search the inventory
	 * for games and select a game.
	 */
	public void searchGame()
	{
		
	}
	
}
