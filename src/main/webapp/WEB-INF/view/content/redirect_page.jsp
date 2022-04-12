<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>bankQ 관리자</title>
</head>
<body>
<script>
window.onload = function(){
	var nextPage = "${nextPage}", msg = "${msg}";
	if(nextPage) {
		if(msg) alert(msg);
		location.href="${ctx}" + nextPage;
	} else {
		location.href="${ctx}/login";
	}
};
</script>
</body>
</html>