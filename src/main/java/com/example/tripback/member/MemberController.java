package com.example.tripback.member;

import com.example.tripback.common.utils.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 방장이 멤버 초대
    @GetMapping
    @ResponseBody
    public ApiUtils.ApiResult<String> inviteMember(@RequestParam String email){
        return success(memberService.inviteMember(email));
    }

    // 멤버가 그룹 수락
    @PostMapping
    public ApiUtils.ApiResult<String> acceptMember(@RequestParam String code, @RequestParam String email){
        return success(memberService.acceptMember(code, email));
    }

}
