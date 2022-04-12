<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>인증 방식 선택</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&amp;subset=korean" />
<style>
html, body {
  margin: 0;
  padding: 0;
  height:100%;
}
body {
  font-family:'Noto Sans KR','돋움', Dotum, Helvetica, Sans-serif; font-size:13px; font-weight: 400;
}
.d-dim {
  position: relative;
  width: 360px;
  margin: 0 auto;
  height:100%;
  font-size: 16px;
  background-color: #131514;
  opacity: .7;
}
.d-dim .d-consent-select {
  height: 334px;
  width: 100%;
  position: absolute;
  bottom: 0;
  background-color: #fff;
  border-radius: 10px 10px 0px 0px;
  font-weight: 700;
  color: #2B2D2C;
}
.d-dim .d-consent-select .d-title {
  margin: 32px auto 24px;
  text-align: center;
}
.d-content .d-consent-type {
	position: relative;
	display: flex;
	justify-content: space-between;
	align-items: center;
	height: 96px;
	background-color: #F8F8F8;
	margin: 16px;
	padding: 16px;
	box-sizing: border-box;
	cursor: pointer;
}

@media screen and (min-height: 1100px) {
  .d-consent {
    height: 100vh;
  }
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${ctx}/static/js/_.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211115"></script>
<script src="${ctx}/static/js/consent.js?v=20220120"></script>
</head>
<body>
	<div class="d-dim">
	  <div class="d-consent-select">
	    <div class="d-title">
	      인증을 완료 후 전자서명완료를 눌러주세요.
	    </div>
	    <div class="d-content">
	      <div class="d-consent-type" data-type="complete">
	        <div class="d-consent-name">전자서명완료</div>
	      </div>
	      <div class="d-consent-type" data-type="cancel">
	        <div class="d-consent-name">취 소</div>
	      </div>
	    </div>
	  </div>
	</div>
	<form method="post">
		<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
		<input type="hidden" name="request_type" value="${ request_type }"/>
		<input type="hidden" id="cert_tx_id" name="cert_tx_id" value="${ cert_tx_id }"/>
		<input type="hidden" id="sign_tx_id" name="sign_tx_id" value="${ sign_tx_id }"/>
	</form>

<script>
$(document).ready(function() {
	const headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }','n-token':'${ n_token }'};
	$(document).on("click", ".d-consent-type", function() {
		const $form = $("form:eq(0)");
		let data = $form.serializeObject();
		const grantType = $(this).data("type");
		var turn = ${ request_type }+1;
		var page = turn===1?3:5;
		var type = '${ type }' ;
		var orgCodes = '${ org_codes }' ;

		if('complete' === grantType) {
			axios.post("${ctx}/v2/naver/oauth2/sign_result",data,{headers:headers}).then(function (res){
				if(res.data.rsp_code === '00000'){
					CONSENT.fn_on_move_temp(page, type , orgCodes , "naver");
				}else{
					CONSENT.fn_msg_popup("네이버인증을 진행해 주세요.");
				}
			});
		} else {
			$form.attr("action","${ctx}/w/consents/select");
			$form.submit();
		}
	});
		
});
</script>
</body>
</html>