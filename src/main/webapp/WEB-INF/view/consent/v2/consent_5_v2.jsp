<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>전송요구서 5</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&amp;subset=korean" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consent_v2.css"/>
<style>
.mt-0 {
	margin-top: 0;
}
.d-checkbox .title {
    background-color: #fafafa;
    font-weight: 500;
}
.d-checkbox .d-check .d-img.bi-none {
	background-image: none !important;
}
.d-checkbox .d-check {
    padding-left: 32px;
    padding-top: 14.5px;
    padding-bottom: 14.5px;
}
.d-checkbox .title {
    padding-top: 10.5px;
    padding-bottom: 10.5px;
}
</style>
</head>
<body>
<div class="d-consent">
<form method="post" action="${ctx}/w/consents/select">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="type" value="${ type }"/>
	<input type="hidden" name="grant_type" value="${ grant_type }"/>
	<input type="hidden" name="request_type" value="1"/>
	<c:forEach var="org" items="${ orgs }" varStatus="status">
		<input type="hidden" name="org_codes[${ status.index }]" value="${ org.org_code }"/>
	</c:forEach>
  <div class="d-summary-head">
    <div class="d-title">
      <span>개인(신용)정보 수집·이용 동의서</span><br>
      <span>(상세정보 전송)</span>
    </div>
    <div class="d-content">
      주식회사 뱅큐는 「신용정보의 이용 및 보호에 관한 법률」, 「개인정보 보호법」 등 관련 법령에 따라 다음과 같이 귀하의 개인(신용)정보를 처리합니다.
    </div>
  </div>
  <div class="d-summary-main">
    <div class="d-summary">
      <div class="d-subtitle">
        수집・이용 목적
      </div>
      <div class="d-content">
        전송요구를 통한 본인신용정보 통합조회, 데이터분석 서비스의 이용
      </div>
    </div>
    <div class="d-summary fs-18">
      <div class="d-subtitle">
        보유 및 이용기간
      </div>
      <div class="d-content">
        서비스 이용 종료시 또는 삭제요구시 까지
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        거부 권리 및 불이익
      </div>
      <div class="d-content">
        귀하는 개인(신용)정보 수집･이용에 관한 동의를 거부하실 수 
있습니다. 다만, “필수 항목에 대한 수집･이용에 관한 동의”는 본인 신용정보 통합 조회, 데이터분석 서비스의 이용을 위한 필수적 사항이므로 동의를 거부하실 경우 본인신용정보 통합조회, 데이터분석 서비스의 이용이 제한될 수 있으며, “선택 항목에 대한 수집･이용에 관한 동의”는 거부하실 경우 해당 선택 항목에 대한 본인신용정보 통합조회, 데이터분석 서비스의 이용이 제한될 수 있습니다.
      </div>
    </div>
  </div>
  <div class="d-summary-head">
    <div class="d-title">
      <span>수집・이용 항목</span>
    </div>
  </div>
  <div class="d-summary d-summary-agree">
    <div class="d-flex d-title">
      <div>개인(신용)정보</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <div class="d-close d-table">
        <div>
          <div class="d-subtitle">개인(식별) 정보 (필수 항목)</div>
          <div class="d-content">전자서명, 접근토큰, 인증서, 전송요구서</div>
        </div>
        <div>
        	<div class="d-subtitle">신용거래정보 (필수 항목)</div>
          <div class="d-content">
          	<ul>
      				<c:if test="${ type eq 0 or type eq 2 }">
	              <li>
	                <div><b>은행</b></div>
	                <div>수신 계좌 정보 :</div>
	                <div>수신계좌 기본정보(통화코드, 저축방법, 계좌개설일자, 만기일, 약정액, 월 납입액), 수신계좌 추가정보(통화코드, 현재잔액, 출금 가능액, 적용금리, 최종납입회차), 수신계좌 거래내역(거래일시 또는 거래일자, 거래번호, 거래유형 (코드), 거래구분, 통화코드, 거래금액, 거래 후 잔액, 납입회차)</div>
	                <div>투자 상품 정보 :</div>
	                <div>투자상품계좌 기본정보(표준펀드코드, 납입유형 (코드), 개설일, 만기일), 투자상품계좌 추가정보(통화코드, 잔액, 평가금액, 투자원금, 보유좌수), 투자상품계좌 거래내역(거래일시 또는 거래일자, 거래번호, 거래유형 (코드), 통화코드, 기준가, 거래좌수, 거래금액, 거래 후 잔고평가금액)</div>
	                <div>대출 상품 정보 :</div>
	                <div>대출상품계좌 기본정보(대출일, 만기일, 최종적용금리, 월상환일, 상환방식 (코드), 자동이체 기관 (코드), 상환계좌번호 (자동이체)), 대출상품계좌 추가정보(통화코드, 대출잔액, 대출원금, 다음 이자 상환일), 대출상품계좌 거래내역(거래일시 또는 거래일자, 거래번호, 거래유형, 통화코드, 거래금액, 거래 후 대출잔액, 거래금액 중 원금, 거래금액 중 이자, 환출이자, 이자적용수, 이자적용목록, 이자적용시작 일, 이자적용종료 일, 적용이율, 이자금액, 이자종류 (코드)</div>
	                <div>개인형 IRP 정보 :</div>
	                <div>개인형 IRP 계좌 기본정보(계좌잔액, 계좌평가금액, 사용자부담금, 가입자부담금, 개설일, 최초입금일, 최초 제도 가입일, 연금개시 시작(예정)일), 개인형 IRP 계좌 추가정보(개별운용상품명, 상품가입번호, 상품유형 (코드), 평가금액, 납입(투자)원금, 보유좌수, 신규일, 만기일, 약정이자율), 개인형 IRP 계좌 거래내역(거래일시 또는 거래일자, 거래번호, 거래구분, 거래금액)</div>
	              </li>
      				</c:if>
      				<c:if test="${ type eq 1 or type eq 2 }">
	              <li>
	                <div><b>카드</b></div>
	                <div>카드 정보 :</div>
	                <div>카드기본정보(교통 기능(여부), 현금카드기능 (여부), 결제은행 (코드), 카드브랜드 (코드), 상품 연회비, 발급일자), 국내승인내역(승인번호, 승인일시, 결제상태 (코드), 사용구분 (신용/체크) (코드), 정정 또는 승인취소 일시, 이용금액, 정정후 금액, 전체 할부회차), 해외승인내역(승인번호, 승인일시, 결제상태 (코드), 사용구분 (신용/체크) (코드), 정정 또는 승인취소 일시, 이용금액, 정정후 금액, 결제(승인) 국가코드, 결제(승인) 시 통화코드, 원화)</div>
	                <div>청구 정보 :</div>
	                <div>청구기본정보(결제순번, 월별 청구금액, 결제일, 청구년월, 결제년월일), 청구추가정보(카드 식별자, 사용일시 또는 사용일자, 거래번호, 이용금액, 통화코드 (이용금액), 신용판매 수수료, 전체 할부회차, 현재 할부회차, 할부 결제 후 잔액, 상품구분 (코드)</div>
	              </li>
	             </c:if>
	          </ul>
          </div>
        </div>
      </div>
      <div class="d-radio">
        <div>위 개인(신용)정보 수집·이용에 동의하십니까?</div>
        <div>
        	<label class="r-green">동의함
            <input type="radio" value="Y" name="agree_5_1" id="agree_5_1_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">동의하지 않음
            <input type="radio" value="N" name="agree_5_1" id="agree_5_1_n"/>
  					<span class="checkmark"></span>
          </label>
        </div>
      </div>
    </div>
  </div>


  <div class="d-summary d-summary-agree readonly option">
    <div class="d-flex d-title">
      <div>가맹점명 및 사업자등록번호 정보(선택)</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <div class="d-close">
        <div class="d-checkbox mt-0 readonly">
          <div class="d-check title">
            <div class="fg-2">가맹점명 및 사업자등록번호 정보</div>
          </div>
          <div class="d-check-item">
						<c:forEach var="org" items="${ orgs }" varStatus="status">
							<c:if test="${fn:contains(agree_5_2_org_codes, org.org_code) }">
								<div><div class='d-check'>
									<input type="hidden" id="agree_5_2_org_codes[${ status.index }]" value="${ org.org_code }"/>
									${ org.org_name }
								</div></div>
							</c:if>
						</c:forEach>
					</div>
        </div>
      </div>
      <div class="d-radio">
        <div>위 가맹점명 정보 수집·이용에 동의하십니까?</div>
        <div>
        	<label class="r-green">동의함
            <input type="radio" value="Y" name="agree_5_2" id="agree_5_2_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">동의하지 않음
            <input type="radio" value="N" name="agree_5_2" id="agree_5_2_n"/>
  					<span class="checkmark"></span>
          </label>
        </div>
      </div>
    </div>
  </div>

  <div class="d-summary d-summary-agree readonly option">
    <div class="d-flex d-title">
      <div>적요 또는 거래메모 정보(선택)</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <div class="d-close">
        <div class="d-checkbox mt-0 readonly">
          <div class="d-check title">
            <div class="fg-2">적요 또는 거래메모 정보</div>
          </div>
          <div class="d-check-item">
						<c:forEach var="org" items="${ orgs }" varStatus="status">
							<c:if test="${fn:contains(agree_5_3_org_codes, org.org_code) }">
								<div><div class="d-check">
									<input type="hidden" id="agree_5_3_org_codes[${ status.index }]" value="${ org.org_code }"/>
									${ org.org_name }
								</div></div>
							</c:if>
						</c:forEach>
					</div>
        </div>
      </div>
      <div class="d-radio">
        <div>적요 또는 거래메모 정보 수집·이용에 동의하십니까?</div>
        <div>
        	<label class="r-green">동의함
            <input type="radio" value="Y" name="agree_5_3" id="agree_5_3_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">동의하지 않음
            <input type="radio" value="N" name="agree_5_3" id="agree_5_3_n"/>
  					<span class="checkmark"></span>
          </label>
        </div>
      </div>
    </div>
  </div>

  <div id="btn-next" class="btn">
  	<div class="arrow-next"></div>
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
		const $form = $("form");
		let data = $form.serializeObject();
		const grantType = '${ grant_type }';
		data["org_codes"] = [];
		$('[name^=org_codes]').each(function() { 
			data["org_codes"].push($(this).val()); 
		});
		
		if(data["agree_5_2"] == "N") {
			$("input[id^='agree_5_2_org_codes']").prop("disabled", true);
		} else {
			data['agree_5_2_org_codes'] = [];
			$("input[id^='agree_5_2_org_codes']").each(function(){ 
				data['agree_5_2_org_codes'].push($(this).val());
			});
		}
		if(data["agree_5_3"] == "N") {
			$("input[id^='agree_5_3_org_codes']").prop("disabled", true);
		} else {
			data['agree_5_3_org_codes'] = [];
			$("input[id^='agree_5_3_org_codes']").each(function(){ 
				data['agree_5_3_org_codes'].push($(this).val());
			});
		}
		
		axios({
			method: 'post',
			url: "${ctx}/w/consents/consent5",
			data: data,
			headers: headers
		}).then(res => {
			console.log('CONSENT_5 insert', res.data.result);
			var $searchFrm = $("form:eq(0)");
			$searchFrm.submit();
		}).catch(err => {
			console.log(err);
		});
	});
	
});
</script>
</body>
</html>