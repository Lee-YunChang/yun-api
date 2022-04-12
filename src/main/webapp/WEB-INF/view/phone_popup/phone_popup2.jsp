<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<title>KCB 휴대폰 본인확인 서비스 샘플 2</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form name="form1" method="post" action="${popupUrl}">
		<input type="hidden" name="tc" value="kcb.oknm.online.safehscert.popup.cmd.P931_CertChoiceCmd" />
		<input type="hidden" name="cp_cd" value="${CP_CD}">	<%--  회원사코드  --%>
		<input type="hidden" name="mdl_tkn" value="${MDL_TKN}">	<%-- 토큰 --%>
		<input type="hidden" name="target_id" value="">
	</form>
<script>
window.onload = function() {
	var success = "${success}";
	if(success) {
		document.form1.submit();
	} else {
		alert("${RSLT_CD}", "${RSLT_MSG}");
		self.close();
	}
};
</script>
</body>
</html>


