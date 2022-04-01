package com.yunapi.service;

import com.yunapi.domain.request.UserJoinRequest;
import com.yunapi.domain.request.UserLoginRequest;
import com.yunapi.domain.response.UserJoinResponse;
import com.yunapi.domain.response.UserResponse;
import com.yunapi.entity.ExceptionHistory;
import com.yunapi.entity.User;
import com.yunapi.exception.InvalidInputException;
import com.yunapi.repository.ExceptionHistoryRepository;
import com.yunapi.repository.UserRepository;
import com.yunapi.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ExceptionHistoryRepository exceptionHistoryRepository;

    @Transactional
    public UserJoinResponse save(UserJoinRequest value){
        UserJoinResponse response = new UserJoinResponse();	// default fail
        String device = StringUtils.isBlank(value.getDevice()) ? "android" : value.getDevice();

        if(StringUtils.isBlank(value.getName()) || StringUtils.isBlank(value.getCi()) || StringUtils.isBlank(value.getPhoneNumber()) ||
                StringUtils.isBlank(value.getBirthDate())) {
            response.setReason("필수 입력값을 모두 입력하지 않았습니다");
            exceptionHistoryRepository.save(new ExceptionHistory(value.getUuid(), device, value.getAppVersion(), value.getAppVersionCode(), "Q006"));
            return response;
        } else if(!Arrays.asList("L", "F").contains(value.getForeigner()) || !Arrays.asList("M", "F").contains(value.getSex())) {
            response.setReason("foreigner 또는 sex 값이 유효하지 않습니다.");
            exceptionHistoryRepository.save(new ExceptionHistory(value.getUuid(), device, value.getAppVersion(), value.getAppVersionCode(), "Q007"));
            return response;
        } else if(value.getBirthDate().length() != 8) {
            response.setReason("birthDate는 8자여야 합니다.");
            exceptionHistoryRepository.save(new ExceptionHistory(value.getUuid(), device, value.getAppVersion(), value.getAppVersionCode(), "Q008"));
            return response;
        }

        User user = userRepository.findTopByEmail(value.getEmail()).orElse(null);
        if (user == null) {
            user = userRepository.findTopByCi(value.getCi()).orElse(new User());
        }

        if (user.getId() != null && user.getStatus() != null && user.getStatus().compareTo("Y") == 0) {
            ExceptionHistory exception = new ExceptionHistory(value.getUuid(), device, value.getAppVersion(), value.getAppVersionCode(), "Q000");
            if (user.getCi().compareTo(value.getCi()) == 0) {
                response.setReason(MessageUtils.DUPLICATE_USER_ACCOUNT);
                exception.setResultCode("Q001");
            } else if (user.getEmail().compareTo(value.getEmail()) == 0) {
                response.setReason(MessageUtils.MAIL_DUPLICATE);
                exception.setResultCode("Q004");
            }
            exceptionHistoryRepository.save(exception);
            return response;
        }

        if ("L".equals(value.getForeigner())) {  //KCB 본인인증 정보 내국인
            if ("M".equals(value.getSex())) {
                if (value.getBirthDate().charAt(0) == '1') {
                    user.setSex("1");
                } else {
                    user.setSex("3");
                }
            } else {
                if (value.getBirthDate().charAt(0) == '1') {
                    user.setSex("2");
                } else {
                    user.setSex("4");
                }
            }
        } else {   //KCB 본인인증 정보 외국인
            if ("M".equals(value.getSex())) {
                if (value.getBirthDate().charAt(0) == '1') {
                    user.setSex("5");
                } else {
                    user.setSex("7");
                }
            } else {
                if (value.getBirthDate().charAt(0) == '1') {
                    user.setSex("6");
                } else {
                    user.setSex("8");
                }
            }
        }

        user.setBirthDate(value.getBirthDate());
        user.setName(Pattern.compile("[가-힣]+").matcher(value.getName()).find()
                ? value.getName().replaceAll("[^a-zA-Z0-9가-힣]", "") : value.getName().trim());
        user.setPhoneNumber(value.getPhoneNumber());
        user.setEmail(value.getEmail());
        user.setUuid(value.getUuid());
        user.setCi(value.getCi());
        user.setPushYn(value.getPushYn());
        user.setRecomm2(value.getRecomm2());
        user.setAppVersion(value.getAppVersion());
        user.setDeviceOs(device);

        try {
            user.setSecurityCode(CutEncryption.getEncSHA256(value.getPw()));
            user.setTokenKey(CutEncryption.getEncSHA256(value.getEmail() + AuthCode.SecurityCode()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GenerateRecomm recommGenerator = new GenerateRecomm();
        String recomm = recommGenerator.excuteGenerateWithUserId(""+user.getId());
        response.setRecomm(recomm);
        user.setRecomm(recomm);
        userRepository.save(user);

        response.setId(user.getId());
        response.setTokenKey(user.getTokenKey());
        response.setEmail(value.getEmail());
        response.setCi(user.getCi());
        response.setResult(MessageUtils.SUCCESS);
        return response;
    }

    @Transactional
    public UserResponse login(UserLoginRequest value){

        UserResponse response = new UserResponse();
        response.setResult(MessageUtils.FAIL);
        response.setReason(MessageUtils.INCORRECT_USERID_OR_PASSWORD);

        String device = StringUtils.isNotBlank(value.getDevice()) ? value.getDevice() : "android";
        User user = null;
        if (StringUtils.isNotBlank(value.getEmail()) && StringUtils.isNotBlank(value.getSecurityCode()) && StringUtils.isNotBlank(value.getUuid())) {
            // email로 로그인
            String securityCode = value.getSecurityCode();
            user = userRepository.findTopBySecurityCodeAndEmail(CutEncryption.getEncSHA256(securityCode), value.getEmail())
                    .orElseThrow(() -> new InvalidInputException("email 또는 비밀번호가 일치하지 않습니다"));
            user.setDeviceOs(device);

            if (!"Y".equals(value.getForcedLogin()) && StringUtils.isNotBlank(user.getUuid()) && !user.getUuid().equals(value.getUuid())) {	// uuid가 null일 경우, 로그아웃된 경우 입니다.
                // 다른기기에서 로그인 되었습니다....
                response.setResult(MessageUtils.FAIL);
                response.setReason(MessageUtils.UNMATCHED_UUID);
            } else {
                user.setUuid(value.getUuid());
                response.setReason("");
                response.setResult(MessageUtils.SUCCESS);
                response.setUser(user);
            }
            userRepository.save(user);
        } else if (value.getId() != null && value.getId() > 0) {
            // token으로 로그인 하는 경우.
            if(StringUtils.isBlank(value.getToken())) {
                throw new InvalidInputException(MessageUtils.INCORRECT_TOKENKEY);
            }
            user = userRepository.findByIdAndTokenKey(value.getId(), value.getToken()).orElseThrow(() -> new InvalidInputException(MessageUtils.INVALID_USER));
            user.setDeviceOs(device);


            response.setReason("");
            response.setResult(MessageUtils.SUCCESS);
            response.setUser(user);
            if (StringUtils.isNotBlank(value.getUuid())) {
                user.setUuid(value.getUuid());
            }
            userRepository.save(user);
        }
        return response;
    }
}
