<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>KCB 휴대폰 본인확인 서비스 샘플 4</title>
</head>
<body>
<h3>인증결과</h3>
<ul>
  <li>고객사코드		: ${CP_CD} </li>
  <li>거래번호			: ${TX_SEQ_NO} </li>
  <li>결과코드			: ${RSLT_CD}</li>
  <li>결과메세지		: ${RSLT_MSG}</li>

  <li>성명				: ${RSLT_NAME}</li>
  <li>생년월일			: ${RSLT_BIRTHDAY}</li>
  <li>성별				: ${RSLT_SEX_CD}</li>
  <li>내외국인 구분		: ${RSLT_NTV_FRNR_CD}</li>
  
  <li>DI				: ${DI}</li>
  <li>CI				: ${CI}</li>
  <li>CI업데이트 횟수	: ${CI_UPDATE}</li>
  <li>통신사코드		: ${TEL_COM_CD}</li>
  <li>휴대폰번호		: ${TEL_NO}</li>
  
  <li>리턴메시지		: ${RETURN_MSG}</li>
  <li>리턴메시지		: ${RES_JSON}</li>
  <li>리턴메시지		: ${email}</li>
</ul>
<br/>
* 성별 - M:남, F:여
<br/>
* 내외국인구분 - L:내국인, F:외국인
<br/>
* 통신사 - 01:SKT, 02:KT, 03:LGU+, 04:SKT알뜰폰, 05:KT알뜰폰, 06:LGU+알뜰폰
</body>
</html>
