package com.yunapi.controller.api;

import com.yunapi.domain.request.UserJoinRequest;
import com.yunapi.domain.request.UserLoginRequest;
import com.yunapi.domain.request.UserLogoutRequest;
import com.yunapi.domain.response.BaseResponse;
import com.yunapi.domain.response.UserJoinResponse;
import com.yunapi.domain.response.UserResponse;
import com.yunapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "user", description = "회원 (사용자 및 로그인)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원 가입",
            description = "<ul><li>birthDate(생년월일): yyyyMMdd. 8자</li>"
                    + "<li>foreigner(외국인 여부): L(내국인), F(외국인)</li>"
                    + "<li>sex(성별): M(남자), F(여자)</li>"
                    + "<li>uuid: uuid가 없는 경우에는 32자 이상의 특정 값이라도 반드시 보내줘야합니다.</li></ul>")
    @PostMapping("")
    public UserJoinResponse save(@RequestBody UserJoinRequest value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userIp = request.getHeader("X-FORWARDED-FOR");
        if (userIp == null) {
            userIp = request.getRemoteAddr();
        } else {
            userIp = "";
        }
        value.setUserIp(userIp);
        return userService.save(value);
    }

    @Operation(summary = "회원 로그인",
            description="<h3>`email && securityCode` 또는 `id && token`을 이용하여 로그인합니다.</h3>"
                    + "<h4>uuid: uuid가 없는 경우에는 32자 이상의 특정 값이라도 반드시 보내줘야합니다.</h4>"
                    + "<h4>securityCode: 비밀번호 (<b>uuid를 이용하여 AES 암호화</b>한 값)</h4>"
                    + "<h4>만일, 다른기기로 로그인을 하고싶다면 방법3을 이용해야합니다. (방법 1을 이용할 경우, 기존에 저장된 uuid와 일치하지 않을 경우 로그인이 되지 않습니다.)</h4>"
                    + "<h4>※ 방법 2에서 loginType이 4가 아닌경우 tokenKey를 갱신합니다. 새로 저장하여 이용해주세요.</h4>"
                    + "<ul><li>방법 1: email, securityCode, uuid, device(iOS 또는 android)</li>"
                    + "<li>방법 2: id, token, device, loginType(1,2,3,4 중 하나)</li>"
                    + "<li>방법 3: email, securityCode, uuid, device, **forcedLogin(Y)**</li></ul>")
    @PostMapping("/login")
    private UserResponse login(@RequestBody UserLoginRequest value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userIp = request.getHeader("X-FORWARDED-FOR");
        if (userIp == null) {
            userIp = request.getRemoteAddr();
        }else {
            userIp = "";
        }
        value.setUserIp(userIp);
        return userService.login(value);
    }

    @Operation(summary="회원 로그아웃", description="value keys: id(user id)")
    @PostMapping(value ="/logout")
    private BaseResponse logout(@RequestBody UserLogoutRequest value) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userIp = request.getHeader("X-FORWARDED-FOR");
        if (userIp == null) {
            userIp = request.getRemoteAddr();
        } else {
            userIp = "";
        }
        value.setUserIp(userIp);
        return userService.logout(value);
    }
}
