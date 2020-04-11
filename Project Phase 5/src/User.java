
public class User {
	protected String userID, password;
    
    
    
    public User() {
        this.userID = "";
        this.password = "";
    }
    
    
    
    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

 /*
  *  The verifyLogin() Method to verify the login information from the database.
  */
    public boolean verifyLogin(String userID, String password) 
    {
        return true;
    }

}
