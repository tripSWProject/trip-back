package com.example.tripback.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @GetMapping("/oauth/kakao")
    public void kakaoCallback(@RequestParam String code){
        userService.getKakaoAccessToken(code);
        System.out.println("code = " + code);
    }

    @ResponseBody
    @GetMapping("/oauth/naver")
    public void naverCallback(@RequestParam String code){
        userService.getKakaoAccessToken(code);
        System.out.println("code = " + code);
    }
}
