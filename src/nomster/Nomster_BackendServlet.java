package nomster;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.*;

import com.google.appengine.api.utils.SystemProperty;


@SuppressWarnings("serial")
public class Nomster_BackendServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String url = null;
	    try {
	      if (SystemProperty.environment.value() ==
	          SystemProperty.Environment.Value.Production) {
	        // Load the class that provides the new "jdbc:google:mysql://" prefix.
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        url = "jdbc:google:mysql://nomster-backend:nomster-sql-db/nomster?user=root";
	      } else {
	        // Local MySQL instance to use during development.
	        Class.forName("com.mysql.jdbc.Driver");
	        url = "jdbc:mysql://173.194.82.203:3306/nomster?user=root";

	        // Alternatively, connect to a Google Cloud SQL instance using:
	        // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return;
	    }
	    PrintWriter out = resp.getWriter();
	    resp.setContentType("text/plain");
	    try {
	      Connection conn = DriverManager.getConnection(url);
	      try {
	        String sql = "SELECT * FROM user";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        ResultSet rs=statement.executeQuery();
	        while(rs.next())
	        {
	        	out.println(rs.getString("account"));
	        }
	        
	      } finally {
	        conn.close();
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
		
		
	}
}
