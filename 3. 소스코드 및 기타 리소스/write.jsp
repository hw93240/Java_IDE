<%@ page import="java.sql.*"
contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>게시물 등록 완료</title>
</head>
<body>
<%
    String URL = "jdbc:mysql://localhost:3306/mytest";
    String USER = "root";
    String PASSWORD = "mysql12345";
    String uid = request.getParameter("uid");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String name = request.getParameter("name");
    String count = request.getParameter("count");
    String date = request.getParameter("date");
    Connection conn;
    PreparedStatement stmt;

    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "insert into board(title, uname, date, count, content) values(?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, title);
        stmt.setString(2, name);
        stmt.setString(3, date);
        stmt.setString(4, count);
        stmt.setString(5, content);
        stmt.executeUpdate();
        out.println("<br><a href='tip_board.html'>글 작성이 완료되었습니다.</a>");
    } catch(Exception e){
        out.println("<h2> DB 오류</h2>");
        out.println(e.getMessage());
    }
%>
</body>
</html>