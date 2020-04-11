
public class game {
	
	private String title, status, genre;
	private float price;
	
	
	public game()
	{
		this.title = "";
		this.status = "";
		this.genre = "";
		this.price = 0;
	}
	
	public game(String t, String s, String g, float pr)
	{
		this.title = t;
		this.status = s;
		this.genre = g;
		this.price = pr;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}




	@Override
	public String toString() {
		return "game [title=" + title + ", status=" + status + ", genre=" + genre + ", price=" + price + "]";
	}

	/*
	 * The description() method displays the game descriptions
	 * such as the title, status, genre, and price.
	 */
	public void description()
	{
		
	}

}
