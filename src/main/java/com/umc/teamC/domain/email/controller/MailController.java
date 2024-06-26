package com.umc.teamC.domain.email.controller;


import com.umc.teamC.domain.email.service.EmailService;
import com.umc.teamC.domain.email.dto.EmailRequestDTO;
import com.umc.teamC.domain.email.service.EmailServiceImpl;
import com.umc.teamC.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
public class MailController {

    private final EmailService emailService;

    // 인증번호 전송
    @Operation(summary = "인증번호 전송",
            description = "학번을 입력하면 해당 샘물 이메일 주소로 인증번호가 전송된다.")
    @PostMapping("/mail/check")
    public BaseResponse<Object> emailConfirm(@RequestBody EmailRequestDTO.EmailCheckRequest emailCheckRequest) throws Exception {
        String email = emailCheckRequest.getStudentId()+"@sangmyung.kr";
        String confirm;
        confirm = emailService.sendSimpleMessage(email);
        if (confirm.isEmpty()) {
            return BaseResponse.onFailure("인증번호 전송 실패");
        } else {
            return BaseResponse.onSuccess("인증번호 전송 성공");
        }
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인",
            description = "이메일로 받은 인증번호를 입력한다.")
    @PostMapping("/mail/authentication")
    public BaseResponse<Object> emailAuthentication(@RequestBody EmailRequestDTO.EmailAuthRequest emailAuthRequest) {
        if (emailAuthRequest.getCertificationNum().equals(EmailServiceImpl.ePw)){
            return BaseResponse.onSuccess("인증번호가 일치합니다.");
        } else {
            return BaseResponse.onFailure("인증번호가 일치하지 않습니다.");
        }
    }
}
