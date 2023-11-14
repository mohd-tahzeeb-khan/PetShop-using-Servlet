import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class sell extends HttpServlet{
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        response.setContentType("text/html");
        String animal=request.getParameter("animal");
        String breed=request.getParameter("breed");
        String no=request.getParameter("no");
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");
            String query_getting = "SELECT count FROM pets WHERE animal = ? AND breed = ?";
            String query_update="UPDATE pets SET count = ? WHERE animal = ? AND breed=?";
            PreparedStatement selectingstatement = con.prepareStatement(query_getting);
            PreparedStatement updatestatement=con.prepareStatement(query_update);

            selectingstatement.setString(1, animal);
            selectingstatement.setString(2, breed);
            ResultSet results = selectingstatement.executeQuery();
            results.next();
            int numAnimals = results.getInt("count");
            // out.println(numAnimals);
            results.close();
            // out.println(no);
            int count=numAnimals-Integer.parseInt(no);
            // out.println(count);
            updatestatement.setString(1, String.valueOf(count));
            updatestatement.setString(2, animal);
            updatestatement.setString(3, breed);
            int i=updatestatement.executeUpdate();
            // out.print(i);
             if(i>0){
                 out.println("Animal Sold");
             }
        } catch (Exception e) {
            out.print(e);
            // TODO: handle exception
        }
    }
}