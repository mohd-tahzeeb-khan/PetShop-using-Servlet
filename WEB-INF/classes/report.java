import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.Result;

import java.io.*;
import java.sql.*;
public class report extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");
            Statement state=con.createStatement();
            String query_select="SELECT * FROM pets;";
        ResultSet data = state.executeQuery(query_select);
        response.setContentType("text/html");
        out.print("<h1>Report</h1>");
        out.print("<table>");
        out.print("<tr><th>Animal</th><th>Breed</th><th>Colour</th><th>Count</th><th>Price</th></tr>");
        while(data.next()){
            String animal=data.getString("animal");
            String breed=data.getString("breed");
            String colour=data.getString("colour");
            int count=data.getInt("count");
            int price=data.getInt("price");
            out.println("<tr>");
            out.println("<td>" + animal + "</td>");
            out.println("<td>" + breed + "</td>");
            out.println("<td>" + colour + "</td>");
            out.println("<td>" + count + "</td>");
            out.println("<td>" + price + "</td>");
            out.println("</tr>");
        }
        out.print("</table>");
    }catch(Exception e){
        out.println(e);
    }
    }
}