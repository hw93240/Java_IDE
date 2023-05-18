<%@ page import="java.sql.*"
contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>로그인</title>
</head>
<body>
<%
    String URL = "jdbc:mysql://localhost:3306/mytest";
    String USER = "root";
    String PASSWORD = "mysql12345";
    String uid = request.getParameter("uid");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String passwd = request.getParameter("passwd");
    Connection conn;
    PreparedStatement stmt;

    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "select uname from user where uid=? and passwd=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, uid);
        stmt.setString(2, passwd);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            name = rs.getString(1);
            break;
        }
        if(name != null) {
            session.setAttribute("uid", email);
            session.setAttribute("name", name);
            out.println(name + "님 로그인에 성공하였습니다.");
            out.println("<br><a href='work.jsp'>작업 영역</a>");
        }
    } catch(Exception e) {
        out.println("<h2> DB 오류</h2>");
        out.println(e.getMessage());
    }
%>
</body>
</html>