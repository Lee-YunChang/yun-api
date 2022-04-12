<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>전송요구서 4</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&amp;subset=korean" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/consent_v2.css"/>
<style>

</style>
</head>
<body>
<div class="d-consent">
<form method="post" action="${ctx}/w/consents/consent5">
	<input type="hidden" name="mydata_user_id" value="${ mydata_user_id }"/>
	<input type="hidden" name="type" value="${ type }"/>	
	<input type="hidden" name="grant_type" value="${ grant_type }"/>
  <div class="d-summary-head">
    <div class="d-title">
      <span>상세정보 전송요구서</span>
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
      	<ul>
      	<c:if test="${ type eq 0 or type eq 2 }">
        	<li>은행 : 수신 계좌 정보, 투자 상품 정보, 대출 상품 정보, 개인형 IRP 정보</li>
        </c:if>
      	<c:if test="${ type eq 1 or type eq 2 }">
        	<li>카드 : 카드 정보, 선불카드 정보, 포인트 정보, 청구･결제 정보 및 리볼빙 정보, 대출상품 정보</li>
        </c:if>
        </ul>
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
        전송요구를 통한 본인신용정보 통합조회, 데이터분석 서비스의 이용
      </div>
    </div>
    <div class="d-summary">
      <div class="d-subtitle">
        전송을 요구하는 개인신용정보의 보유기간
      </div>
      <div class="d-content">
        서비스 이용 종료시 또는 삭제요구시 까지
      </div>
    </div>
  </div>

  <div class="d-summary d-summary-agree option">
    <div class="d-title">
      <div>정기적 전송을 요구하는지 여부 및 요구하는 경우 그 주기</div>
      <p class="fs-12">
           정기적 전송을 선택하시면, 소비 리포트, 펀드 리포트를 매주 보내드립니다.
          </p>
    </div>
    <div class="d-content">
      <div>
      	<div class="d-radio">
        	<label class="r-green">예 (주 1회)
            <input type="radio" value="Y" name="agree_4_1" id="agree_4_1_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">아니오
            <input type="radio" value="N" name="agree_4_1" id="agree_4_1_n"/>
  					<span class="checkmark"></span>
          </label>
        </div>
        <div class="d-close d-checkbox">
          <div class="d-check all checked">
            <div class="d-img"></div>
            <div class="fg-2">전체 선택</div>
            <div class="d-arrow-2"></div>
          </div>
          <div class="d-check-item">
						<c:forEach var="org" items="${ orgs }" varStatus="status">
							<div><div class="d-check checked">
								<input type="hidden" name="agree_4_1_org_codes[${ status.index }]" value="${ org.org_code }"/>
								<div class="d-img"></div>${ org.org_name }
							</div></div>
						</c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="d-summary-agree option">
    <div class="d-flex d-title">
      <div>전송요구의 종료 시점 (전송요구서의 유효기간)</div>
    </div>
    <div class="d-content">
      <div class="d-radio">
        <div>
        	<label class="r-green">3개월
            <input type="radio" value="3" name="agree_4_2" id="agree_4_2-3"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">6개월
            <input type="radio" value="6" name="agree_4_2" id="agree_4_2-6" class="temp"/>
  					<span class="checkmark"></span>
          </label>
        </div>
        <div>
        	<label class="r-green">9개월
            <input type="radio" value="9" name="agree_4_2" id="agree_4_2-9"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">12개월
            <input type="radio" value="12" name="agree_4_2" id="agree_4_2-12" class="temp"/>
  					<span class="checkmark"></span>
          </label>
        </div>
      </div>
    </div>
  </div>

  <div class="d-summary d-summary-agree option">
    <div class="d-flex d-title">
      <div>가맹점명 및 사업자등록번호 정보(선택)</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <div class="d-close">
        <div class="d-checkbox-title">가맹점명 및 사업자동록번호 정보</div>
        <div class="d-close d-checkbox">
          <div class="d-check all checked">
            <div class="d-img"></div>
            <div class="fg-2">전체 선택</div>
            <div class="d-arrow-2"></div>
          </div>
          <div class="d-check-item">
						<c:forEach var="org" items="${ orgs }" varStatus="status">
							<div><div class="d-check checked">
								<input type="hidden" name="agree_4_3_org_codes[${ status.index }]" value="${ org.org_code }"/>
								<div class="d-img"></div>${ org.org_name }
							</div></div>
						</c:forEach>
          </div>
        </div>
      </div>
      <div class="d-radio">
        <div>위 가맹점명 정보 전송요구에 동의하십니까?</div>
        <div>
        	<label class="r-green">동의함
            <input type="radio" value="Y" name="agree_4_3" id="agree_4_3_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">동의하지 않음
            <input type="radio" value="N" name="agree_4_3" id="agree_4_3_n"/>
  					<span class="checkmark"></span>
          </label>
        </div>
      </div>
    </div>
  </div>

  <div class="d-summary d-summary-agree option">
    <div class="d-flex d-title">
      <div>적요 또는 거래메모 정보(선택)</div>
      <div class="d-arrow-1"></div>
    </div>
    <div class="d-content">
      <div class="d-close"> <!-- FIXME close 클래스 추가해야 함-->
        <div class="d-checkbox-title">적요 또는 거래메모 정보</div>
        <div class="d-close d-checkbox">
          <div class="d-check all checked">
            <div class="d-img"></div>
            <div class="fg-2">전체 선택</div>
            <div class="d-arrow-2"></div>
          </div>
          <div class="d-check-item">
						<c:forEach var="org" items="${ orgs }" varStatus="status">
							<div><div class="d-check checked">
								<input type="hidden" name="agree_4_4_org_codes[${ status.index }]" value="${ org.org_code }"/>
								<div class="d-img"></div>${ org.org_name }
							</div></div>
						</c:forEach>
          </div>
        </div>
      </div>
      <div class="d-radio">
        <div>적요 또는 거래메모 정보 전송요구에 동의하십니까?</div>
        <div>
        	<label class="r-green">동의함
            <input type="radio" value="Y" name="agree_4_4" id="agree_4_4_y"/>
  					<span class="checkmark"></span>
        	</label>
        	<label class="r-green">동의하지 않음
            <input type="radio" value="N" name="agree_4_4" id="agree_4_4_n"/>
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
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="${ctx}/static/js/_.js"></script>
<script src="${ctx}/static/js/namespace.js?v=20211114.22"></script>
<script src="${ctx}/static/js/consent.js?v=20211201"></script>
<script>
$(document).ready(function() {
	const headers = {'Content-Type': 'application/json', 'Accept': 'application/json', 'm-Id': '${ mydata_user_id }'};

	$(document).on("click", "#btn-next.btn-enabled", function() {
		const $form = $("form");
		const formData = $form.serializeObject();
		if(formData["agree_4_1"] == "N") {
			$("input[name^='agree_4_1_org_codes']").prop("disabled", true);
		}
		if(formData["agree_4_3"] == "N") {
			$("input[name^='agree_4_3_org_codes']").prop("disabled", true);
		}
		if(formData["agree_4_4"] == "N") {
			$("input[name^='agree_4_4_org_codes']").prop("disabled", true);
		}
		$("form")[0].submit();
	});
	
	
	$('.d-check').on('click', function() {
    var $target = $(this).closest('.d-check');
    var $parent = $target.closest('.d-checkbox');
    if($target.hasClass('all')) {
      if($target.hasClass('checked')) {
      	$parent.find('.d-check').removeClass('checked');
      	$parent.find('.d-check input').prop("disabled", true);
      } else {
      	$parent.find('.d-check').addClass('checked');
      	$parent.find('.d-check input').prop("disabled", false);
      }
    } else {
      $target.toggleClass('checked');
      $(this).prev().prop("disabled", !$(this).prev().prop("disabled"));
      if($parent.find('.d-check:not(.all)').length ==
      		$parent.find('.d-check.checked:not(.all)').length) {
      	$parent.find('.all').addClass('checked');
      } else {
      	$parent.find('.all').removeClass('checked');
      }
    }
  });
  
});
</script>
</html>