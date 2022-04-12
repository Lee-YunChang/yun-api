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
  background-color: #5a5c5b;
}
.d-dim {
  position: relative;
  width: 360px;
  margin: 0 auto;
  height:100%;
  font-size: 16px;
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
.d-consent-type .d-type-1, .d-consent-type .d-type-2 {
  width: 40px; height: 40px;
  background-size: contain;
}
.d-consent-type .d-type-1 {
  background-image: url(/static/images/consent/consent-type-1.png);
}
.d-consent-type .d-type-2 {
  background-image: url(/static/images/consent/consent-type-2.png);
}
.d-consent-type .d-consent-name {
  font-size: 18px;
}
.d-consent-type .d-chevron-right {
  background-image: url(/static/images/consent/chevron-right.png);
  width: 24px;
  height: 24px;
  background-size: contain;
}
@media screen and (min-height: 1100px) {
  .d-consent {
    height: 100vh;
  }
}
</style>
</head>
<body>
	<div class="d-dim">
	  <div class="d-consent-select">
	    <div class="d-title">
	      인증 방법 선택
	    </div>
	    <div class="d-content">
	      <div class="d-consent-type" data-type="password">
	        <div class="d-type-1"></div>
	        <div class="d-consent-name">공동인증서</div>
	        <div class="d-chevron-right"></div>
	      </div>
	      <div class="d-consent-type" data-type="naver">
	        <div class="d-type-2"></div>
	        <div class="d-consent-name">네이버 인증서</div>
	        <div class="d-chevron-right"></div>
	      </div>
	    </div>
	  </div>
	</div>
<form method="post" action="${ctx}/w/wizvera">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="type" value="${ type }"/>
	<input type="hidden" name="request_type" value="${ request_type }"/>
	<c:forEach var="org_code" items="${ org_codes }" varStatus="status">
		<input type="hidden" name="org_codes[${ status.index }]" value="${ org_code }"/>
	</c:forEach>
</form>

<form method="post" action="${ctx}/w/consents/confirm">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="type"  id="type" value="${ type }"/>
	<input type="hidden" name="request_type" value="${ request_type }"/>
	<c:forEach var="org_code" items="${ org_codes }" varStatus="status">
		<input type="hidden" name="org_codes[${ status.index }]" value="${ org_code }"/>
	</c:forEach>
	<input type="hidden" id="n_token" name="n_token" value=""/>
	<input type="hidden" id="cert_tx_id" name="cert_tx_id" value=""/>
	<input type="hidden" id="sign_tx_id" name="sign_tx_id" value=""/>
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${ctx}/static/js/_.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211115"></script>
<script src="${ctx}/static/js/consent.js?v=20220120"></script>
<script>
$(document).ready(function() {
	const headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }'};
	$(document).on("click", ".d-consent-type", function() {
		const $form = $("form:eq(0)");
		let data = $form.serializeObject();
		const grantType = $(this).data("type");
		const turn = ${ request_type }+1;
		if('password' === grantType) {
			$form.submit();
		} else {	// 네이버 인증
			axios.get("${ctx}/v2/naver/oauth2/token").then(function (res) {
				const n_headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }','n-token':res.data.access_token};
				$('#n_token').val(res.data.access_token);
				axios.post("${ctx}/v2/naver/oauth2/sign_request/"+turn,{deviceBrowser:'WB'},{headers : n_headers})
				.then(function (res){
					if(res.data.rsp_code === '00000'){
						$('#cert_tx_id').val(res.data.cert_tx_id);
						$('#sign_tx_id').val(res.data.sign_tx_id);
						$("form:eq(1)").submit();
					}else{
						console.log(res.data.rsp_msg);
					}
				});
			});
		}
	});
		
});
</script>
</body>
</html>