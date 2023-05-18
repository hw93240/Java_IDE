<%@ page import="java.sql.*"
contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
</head>
<body>
<%
    String URL = "jdbc:mysql://localhost:3306/mytest";
    String USER = "root";
    String PASSWORD = "mysql12345";
    String uid = request.getParameter("uid");
    String name = request.getParameter("name");
    String name2 = request.getParameter("name2");
    String email = request.getParameter("email");
    String passwd = request.getParameter("passwd");
    Connection conn;
    PreparedStatement stmt;

    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "insert into user(uid, passwd, uname, uname2, email) values(?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, uid);
        stmt.setString(2, passwd);
        stmt.setString(3, name);
        stmt.setString(4, name2);
        stmt.setString(5, email);
        stmt.executeUpdate();
        out.println(name + "님 등록이 성공적으로 완료되었습니다.");
    } catch(Exception e){
        out.println("<h2> DB 오류</h2>");
        out.println(e.getMessage());
    }
%>
</body>
</html>