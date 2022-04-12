<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>bankQ v2 API</title>

<link rel="shortcut icon" href="${ctx}/static/images/icon_googleplay.png">
<link rel="stylesheet" type="text/css" href="${ctx}/static/core/vendors/bootstrap-4.1.3-dist/css/bootstrap-4.1.3.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/core/vendors/font-awesome/css/font-awesome.min.css">
<style>
body {
    color: #687d95; background: #eef2f7; font-family: "Helvetica Neue", Roboto, Arial, "Droid Sans", sans-serif;
    font-size: 13px; font-weight: 400; line-height: 1.471;
}
.login_wrapper {
    right: 0px; margin: 0px auto; max-width: 350px; position: relative;
}
.form-control, .btn {
	padding: 6px 12px; font-size:14px; line-height: 1.5;
}
.form-control{
	border:1px solid #ccc; border-radius: 4px; box-shadow: 0 1px 1px rgba(0,0,0,.075);
}
.btn-block{
	color:white; display: block; width:100%; text-align:center; vertical-align:middle; touch-action:manipulation;
	font-weight:400; white-space:nowrap; user-select:none;
}

section.login_content2 {
	margin: 0 auto; padding: calc(50vh - 206px) 0 0; position: relative; text-align: center; text-shadow: 0 1px 0 #fff; min-width: 280px;
}
section.login_content2 h1 {
	letter-spacing: -0.05em; line-height: 20px; margin-bottom: 30px; font-size: 20px; font-weight: 600;
}

.separator {
    border-top: 1px solid #d8d8d8; margin-top: 10px; 
}

</style>

</head>

<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a> 
		<a class="hiddenanchor" id="signin"></a>

		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content2">
					<input type="hidden" value="${ mode }"/>
					<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}" />
	           		<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}" />
					<h1><img src="${ctx}/static/images/icon_googleplay.png" width="50" height="auto" alt="bankQ" id="btn_loginHome"></h1>
					<div>
						<input type="text" id="inputUsername" name="username" value="${ username }" class="form-control" placeholder="username" autocomplete="off" required/>
						<p></p>
					</div>
					<div>
						<input type="password" id="inputPassword" name="password" value="${ password }" class="form-control" placeholder="Password" autocomplete="off" required/>
						<p></p>
					</div>
					<p class="text-left">
						<span class="text-danger">
	<c:choose>
		<c:when test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
								<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
								<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>	
		</c:when>
		<c:otherwise>
								&nbsp;
		</c:otherwise>
	</c:choose>
						</span>
					</p>
					<div style="margin-bottom:30px;">
						<button type="button" class="btn btn-secondary btn-large btn-block" id="btn_login">로그인</button>
					</div>
	
					<div class="clearfix"></div>
	
					<div class="separator">
						<div class="clearfix"></div>
						<br />
						<div>
							<h1>
								bankQ v2 API
							</h1>
							<p>©2022 All Rights Reserved.</p>
							<p>v.2.0.2</p>
						</div>
					</div>
				</section>				
			</div>
			<form name="securedLoginFrm" method="post" action="<c:url var="logoutUrl" value="/login"/>">
				<input type="hidden" name="username" value="" />
           		<input type="hidden" name="password" value="" />
           		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</div>

<script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/core/vendors/popper/umd/popper.min.js"></script>
<script type="text/javascript" src="${ctx}/static/core/vendors/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>
<script>
$(document.body).delegate(":input", "keyup", function (e) {
    if (e.which == 13)
    	$("#btn_login").trigger("click");
});

$("#btn_login").on("click", function() {
	var username = $("#inputUsername").val(), password = $("#inputPassword").val();
	if (username == "") {
		alert("username 입력하세요");
		return false;
	} else if (password == "") {
		alert("비밀번호를 입력하세요");
		return false;
	}
	
  var securedLoginFrm = $("form[name='securedLoginFrm']");
	securedLoginFrm.find("input[name='username']").val(username);
	securedLoginFrm.find("input[name='password']").val(password);   
	securedLoginFrm.submit();
});
</script>
</body>
</html>