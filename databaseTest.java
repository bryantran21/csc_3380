import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class insertUser
{
	public static void main(String[] args) 
	{
		NewAccount obj1 = new NewAccount();
		
		String email = obj1.getemail();
		String username = obj1.getusername();
		String password = obj1.getpassword();
		String role = obj1.getrole();
		File database = new File("database.csv");

		insertToDatabase(database, email, username, password, role);
	}
  
    
  
  public static void insertToDatabase(File file, String email, String username, String password, String role)
  {
	  try (FileWriter writer = new FileWriter(file, true))
	  {

	      StringBuilder sb = new StringBuilder();
	      sb.append(email);
	      sb.append(',');
	      sb.append(username);
	      sb.append(',');
	      sb.append(password);
	      sb.append(',');
	      sb.append(role);
	      sb.append('\n');

	      writer.write(sb.toString());

	      System.out.println("done!");
	  } 
	  catch (IOException e) 
	  {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  } 
  }
}
