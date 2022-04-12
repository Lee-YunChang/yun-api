<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
<head>
	<title>KCB 휴대폰 본인확인 서비스 샘플 1</title>

	<script>
		
		$(function(){
			window.open("", "auth_popup", "width=430,height=640,scrollbar=yes");
			var form1 = document.form1;
			form1.target = "auth_popup";
			var RQST_CAUS_CD = $('#RQST_CAUS_CD').val();
			if(RQST_CAUS_CD ==1){
				form1.action = "${ctx}/kcb/join";
			}else if(RQST_CAUS_CD ==2){
				form1.action = "${ctx}/kcb/modify";
			}else if(RQST_CAUS_CD ==3){
				form1.action = "${ctx}/kcb/find";
			}
			form1.submit();
		});

		function sendApp(RES_JSON){
			var os = navigator.userAgent.toLowerCase();
			var isMobile;
			if(os.indexOf("android") !== -1){
				isMobile = "android";
				// android 일 때
			}else if(os.indexOf("iphone")>-1||os.indexOf("ipad")>-1||os.indexOf("ipod")>-1){
				isMobile = "ios";
				// iphone 일 때
			}else{
				isMobile = "android";
			}

			if (isMobile == "android") {
				bankqKCB.onData(JSON.stringify(RES_JSON));
			} else if (isMobile == "ios") {
				webkit.messageHandlers.bankqKCB.postMessage(JSON.stringify(RES_JSON));
			}
		}	
	</script>
</head>
<body>
<div>
	<form name="form1" method="post" action="${ctx}/kcb/phone_popup2">
		<input type="hidden" name="RQST_CAUS_CD" id="RQST_CAUS_CD" value="${RQST_CAUS_CD}">
		<input type="hidden" name="email" id="email" value="${email}">
		<%--<input type="button" value="휴대폰 본인확인" onClick="jsSubmit();">--%>
	</form>
</div>

<!-- 휴대폰 본인확인 팝업 처리결과 정보 = phone_popup3 에서 값 입력 -->
<form name="kcbResultForm" method="post">
	<input type="hidden" name="CP_CD" />
	<input type="hidden" name="TX_SEQ_NO" />
	<input type="hidden" name="RSLT_CD" />
	<input type="hidden" name="RSLT_MSG" />

	<input type="hidden" name="RSLT_NAME" />
	<input type="hidden" name="RSLT_BIRTHDAY" />
	<input type="hidden" name="RSLT_SEX_CD" />
	<input type="hidden" name="RSLT_NTV_FRNR_CD" />

	<input type="hidden" name="DI" />
	<input type="hidden" name="CI" />
	<input type="hidden" name="CI_UPDATE" />
	<input type="hidden" name="TEL_COM_CD" />
	<input type="hidden" name="TEL_NO" />
	<input type="hidden" name="RES_JSON" />
	<input type="hidden" name="email" />

	<input type="hidden" name="RETURN_MSG" />
</form>
</body>
</html>
