<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>전송요구서 1</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&amp;subset=korean" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consent.css?v=20211201"/>
<style>
@media screen and (min-height: 993px) {
  .d-consent {
    height: 100vh;
  }
  .d-summary-footer {
    position: absolute;
    bottom: 0;
    width: 100%;
  }
}
</style>
</head>
<body>
<div class="d-consent">
<form method="post" action="${ctx}/w/consents/consent2">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="user_name" value="${ user_name }"/>
	<input type="hidden" name="type" value="${ type }"/>
	<input type="hidden" name="grant_type" value="${ grant_type }"/>
  <div class="d-summary-head">
    <div class="d-title">
    	<span>가입상품 목록 전송 요구서</span>
    </div>
  </div>
  <div class="d-summary-main">
    <div class="d-summary">
      <div class="d-subtitle">
        정보제공자 (전송 요구를 받는 자)
      </div>
      <div class="d-content">
				<c:forEach var="org" items="${ orgs }" varStatus="status">
					<input type="hidden" name="org_codes[${ status.index }]" value="${ org.org_code }"/>
					<span class="s-org-name">${ org.org_name }</span>
				</c:forEach>
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        전송을 요구하는 개인신용정보
      </div>
      <div class="d-content">
      	<c:if test="${ type eq 0 or type eq 2 }">
	        <div>- 은행: 계좌(수신/투자상품/대출상품) 목록 및 개인형 IRP 계좌 목록</div>
      	</c:if>
      	<c:if test="${ type eq 1 or type eq 2 }">
        	<div>- 카드: 카드 목록 및 선불 카드 목록</div>
        </c:if>
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        개인신용정보를 제공받는 자
      </div>
      <div class="d-content">
        주식회사 뱅큐
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        전송을 요구하는 목적
      </div>
      <div class="d-content">
        상세정보 전송요구를 위한 가입상품목록 조회
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        전송을 요구하는 개인신용정보의 보유기간
      </div>
      <div class="d-content">
        상세정보 전송요구시까지 또는 7일 중 짧은 기간
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        정기적 전송을 요구하는지 여부 및 요구하는 경우 그 주기
      </div>
      <div class="d-content">
        아니오
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        전송요구의 종료시점 (전송요구서의 유효기간)
      </div>
      <div class="d-content">
        상세정보 전송요구시까지 또는 7일 중 짧은 기간
      </div>
    </div>
  </div>
  <div class="d-summary-footer">
    <div class="d-sign">
      <span class="username">
      	<c:choose><c:when test="${user_name eq null}">사용자이름</c:when><c:otherwise>${ user_name }</c:otherwise></c:choose>
      </span>은/는<br/>
      다음과 같이 전송을 요구합니다.
    </div>
    <div id="btn-next" class="btn btn-enabled">다음</div>
  </div>
</form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${ctx}/static/js/_.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211115"></script>
<script src="${ctx}/static/js/consent.js?v=20211201"></script>
<script>
$(document).ready(function() {
	const headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }'};
			
	$(document).on("click", "#btn-next.btn-enabled", function() {
		const $form = $("form");
		$("form")[0].submit();
	});
	
});
</script>
</body>
</html>