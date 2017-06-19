<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 가입 폼</h2>
	<form name='frm' method ='post' action='input.do'>
		<label>회원 아이디</label>
		<input type='text' name='userid'><br/>
		<label>회원 암호</label>
		<input type='password' name='userpwd'><p/>
		
		<input type='submit' value='가입하기'/>
	</form>
</body>
</html>