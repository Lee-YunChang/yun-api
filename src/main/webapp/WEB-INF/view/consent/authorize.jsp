<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>개별인증</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211115"></script>
<script src="${ctx}/static/js/consent.js?v=20220124"></script>
<script>
$(document).ready(function() {
	const orgCode = "${ org_code }";
	if(orgCode !== "") {
		CONSENT.fn_on_move(3, orgCode);
	}
});
</script>
</body>
</html>