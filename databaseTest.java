import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter 
{
  public static void main(String[] args) 
  {

    try (PrintWriter writer = new PrintWriter(new File("database.csv"))) 
    {

      StringBuilder sb = new StringBuilder();
      sb.append("Username");
      sb.append(',');
      sb.append("Password");
      sb.append('\n');

      sb.append("theblakelalonde");
      sb.append(',');
      sb.append("Present_2021");
      sb.append('\n');
      
      sb.append("trevor");
      sb.append(',');
      sb.append("huval");
      sb.append('\n');

      writer.write(sb.toString());

      System.out.println("done!");
    } 
    catch (FileNotFoundException e) 
    {
      System.out.println(e.getMessage());
    }
  }
}