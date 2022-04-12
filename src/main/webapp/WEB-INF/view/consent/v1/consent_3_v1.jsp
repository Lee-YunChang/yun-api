<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>전송요구서 3</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&amp;subset=korean" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consent.css?v=20211201"/>
<style>
@media screen and (min-height: 1100px) {
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
<form method="post" action="${ctx}/w/wizvera">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="type" value="${ type }"/>
	<input type="hidden" name="grant_type" value="${ grant_type }"/>
	<input type="hidden" name="request_type" value="0"/>
  <div class="d-summary-head">
    <div class="d-title">
      <span>개인(신용)정보 제공 동의서</span>
    </div>
    <div class="d-content">
      귀하는 개인(신용)정보 제공에 관한 동의를 거부하실 수 있습니다. 다만, 개인(신용) 정보의 제공에 관한 동의는 본인신용정보 통합조회, 데이터분석 서비스, 마이데이터 서비스 가입현황 안내 및 전송요구내역 통합조회 서비스 이용과 직접적으로 관련된 필수적 사항이므로 아래의 사항에 동의하셔야만 본인신용정보 통합조회, 데이터분석 서비스, 마이데이터서비스 가입현황 안내 및 전송요구내역 통합조회 서비스의 제공이 가능합니다.
    </div>
  </div>
  <div class="d-summary-main">
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        제공받는 자
      </div>
      <div class="d-content">
				<c:forEach var="org" items="${ orgs }" varStatus="status">
					<input type="hidden" name="org_codes[${ status.index }]" value="${ org.org_code }"/>
					<span class="s-org-name">${ org.org_name }</span>
				</c:forEach>
      </div>
    </div>
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        제공받는 자의 이용목적
      </div>
      <div class="d-content">
        본인확인 및 개인(신용)정보의 전송
      </div>
    </div>
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        보유 및 이용기간
      </div>
      <div class="d-content">
        본인확인 및 개인(신용)정보의 전송 목적 달성시 까지
      </div>
    </div>
  </div>
  <div class="d-summary-head">
    <div class="d-title">
      <span>제공 항목</span>
    </div>
  </div>
  <div class="d-summary d-summary-agree">
    <div class="d-flex d-title">
      <div>개인(신용)정보</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <table class="d-close d-table">
        <thead>
          <tr>
            <td width="30.48%"></td>
            <td></td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>개인(식별) 정보</td>
	            <c:choose>
								<c:when test="${grant_type eq 'authorization_code'}">
			           	<td>CI</td>
								</c:when>
								<c:otherwise>
			           	<td>전자서명, CI, 인증서, 전송요구서</td>
								</c:otherwise>
							</c:choose>
          </tr>
        </tbody>
      </table>
      <div class="d-radio">
        <div>위 개인(신용)정보 수집·이용에 동의하십니까?</div>
        <div>
            <input type="radio" value="Y" name="agree_3_1" id="agree_3_1_y"/>
            <label class="custom-control-label mr-32" for="agree_3_1_y">동의함</label>
            <input type="radio" value="N" name="agree_3_1" id="agree_3_1_n"/>
            <label class="custom-control-label" for="agree_3_1_n">동의하지 않음</label>
        </div>
      </div>
    </div>
  </div>

  <div class="d-summary-head">
    <div class="d-content">
    귀하는 개인(신용)정보 제공에 관한 동의를 거부하실 수 있습니다. 다만, 개인(신용) 정보의 제공에 관한 동의는 본인신용정보 통합조회, 데이터분석 서비스, 마이데이터 서비스 가입현황 안내 및 전송요구내역 통합조회 서비스 이용과 직접적으로 관련된 필수적 사항이므로 아래의 사항에 동의하셔야만 본인신용정보 통합조회, 데이터분석 서비스, 마이데이터서비스 가입현황 안내 및 전송요구내역 통합조회 서비스의 제공이 가능합니다.
    </div>
  </div>
  <div class="d-summary-main">
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        제공받는 자
      </div>
      <div class="d-content">
        한국신용정보원
      </div>
    </div>
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        제공받는 자의 이용목적
      </div>
      <div class="d-content">
        마이데이터서비스 가입현황 안내 및 전송요구내역 통합조회 서비스 제공
      </div>
    </div>
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        보유 및 이용기간
      </div>
      <div class="d-content">
        한국신용정보원의 마이데이터서비스 가입현황 안내 및 전송요구내역 통합조회 서비스 목적 달성시 까지
      </div>
    </div>
  </div>
  <div class="d-summary-head">
    <div class="d-title">
      <span>제공 항목</span>
    </div>
  </div>
  <div class="d-summary d-summary-agree">
    <div class="d-flex d-title">
      <div>개인(신용)정보</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <table class="d-close d-table">
        <thead>
          <tr>
            <td width="30.48%"></td>
            <td></td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>전송요구 내역정보</td>
           	<td>회원 가입여부, 서비스목록수, 서비스목록, 클라이언트ID, 전송요구내역수, 전송요구내역목록, 정보제공자 기관코드, 권한 범위, 전송요구일자, 전송요구종료시점</td>
          </tr>
        </tbody>
      </table>
      <div class="d-radio">
        <div>위 개인(신용)정보 제공에 동의하십니까?</div>
        <div>
            <input type="radio" value="Y" name="agree_3_2" id="agree_3_2_y"/>
            <label class="custom-control-label mr-32" for="agree_3_2_y">동의함</label>
            <input type="radio" value="N" name="agree_3_2" id="agree_3_2_n"/>
            <label class="custom-control-label" for="agree_3_2_n">동의하지 않음</label>
        </div>
      </div>
    </div>
  </div>
  <div class="d-summary-footer">
    <div id="btn-next" class="btn">다음</div>
  </div>
</form>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${ctx}/static/js/_.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211115"></script>
<script src="${ctx}/static/js/consent.js?v=20220120"></script>
<script>
$(document).ready(function() {
	const headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }'};
	
	$(document).on("click", "#btn-next.btn-enabled", function() {
		const $form = $("form:eq(0)");
		let data = $form.serializeObject();
		const grantType = '${ grant_type }';
		data["org_codes"] = [];
		$('[name^=org_codes]').each(function() { 
			data["org_codes"].push($(this).val()); 
		});
		axios({
			method: 'post',
			url: "${ctx}/w/consents/consent3",
			data: JSON.stringify(data),
			headers: headers
		}).then(res => {
			console.log('CONSENT_3 insert');
			if('authorization_code' === grantType) {	// 개별인증
// 				window.open("/v2/mydata/oauth2/authorize/popup/${ mydata_user_id }/" + data["org_codes"][0], "_blank"); // FIXME :: cross-origin 문제 해결 후  popup으로 교체
				location.href = "/v2/mydata/oauth2/authorize/testbed/${ mydata_user_id }/" + data["org_codes"][0];
			} else {	// 통합인증
				var $searchFrm = $("form:eq(0)");
				$searchFrm.submit();
			}
		}).catch(err => {
			console.log(err);
		});
	});
		
});
</script>
</body>
</html>