<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
#items {
	background-color: #568aa6;
   padding: 15px;
   box-sizing: border-box;
   box-shadow: 2px 2px 4px #aaaaaa;
   font-weight: bold;
   margin-bottom: 10px;
   font-size:15px;
   border-radius:3px;
	}
	
	#items>span, .list>span {
   display: inline-block;
}

.m_id {
   width: 200px;
   text-align: left;  
    margin-left:50px;
}

.m_pwd {
   width: 200px;
   text-align: left;  
    margin-left:150px;
}

.id{
   width: 160px;
   text-align: left;  
    margin-left:50px;
}

.pwd{
   width: 160px;
   text-align: left;  
    margin-left:50px;
}
	
</style>

<script>
function modify(id, pwd) {
	var frm = document.frm_hidden;
	var returnValue = prompt("비밀번호를 입력하세요.", "");
	
	if(returnValue== pwd) {
		var changeValue = prompt("수정할 비밀번호를 입력하세요.", "");
		frm.userid.value = id;
		frm.userpwd.value = changeValue;
		frm.action = 'modify.do';
		frm.submit();
	}else {
		alert("비밀번호가 틀렸습니다.");
		return;
	}
}
	
	function del(id,pwd){
		var url = 'delete.do';
		var ff = document.frm_hidden;
		ff.action = url;
		ff.userid.value=id; 
		ff.userpwd.value=pwd
		ff.submit();
		
	}

</script>

</head>
<body>

	<form name='frm' method ='post' action='list.do'>
		<label>검색어</label>
		<input type='text' name='userid'>
		<input type='submit' value='검색'/><p/>
	</form>
	<div class='items'>
		<span class='m_id'>아이디</span>
		<span class='m_pwd'>비밀번호</span>
	</div>
	
<form name='frm_list' method='post'>
	<c:forEach var = "list" items="${list}">
		<div class='list'>
			<input type='text' name='userid' id='userid' value='${list.userid}'>
			<input type='text' name='userpwd' id='userpwd' value='${list.userpwd}'>
			<input type='button' value='수정' onclick="modify('${list.userid}','${list.userpwd}')">
			<input type='button' value='삭제' onclick="del('${list.userid}','${list.userpwd}')">
		</div>
	</c:forEach>
</form>

	<form name='frm_hidden' method='post'>
			<input type='hidden' name='userid' id='userid' >
			<input type='hidden' name='userpwd' id='userpwd'>
	</form>

 
</body>
</html>