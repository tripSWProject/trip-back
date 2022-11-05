package com.example.tripback.member;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.member.MemberDto.responseMemberList;
import com.example.tripback.member.request.PostMemberReq;
import org.springframework.web.bind.annotation.*;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 방장이 멤버 초대
    @GetMapping("/visit")
    @ResponseBody
    public ApiResult<String> inviteMember(@RequestParam String email){
        return success(memberService.inviteMember(email));
    }

    // 멤버가 그룹 수락
    @PostMapping("/accept")
    public ApiResult<String> acceptMember(@RequestBody PostMemberReq postMemberReq){
        return success(memberService.acceptMember(postMemberReq));
    }

    @GetMapping
    public ApiResult<responseMemberList> getMemberList(@RequestParam Long teamId){
        return success(new responseMemberList(memberService.memberList(teamId)));
    }

    @DeleteMapping
    public ApiResult<Long> deleteMember(@RequestParam Long teamId, @RequestParam Long userId){
        return success(memberService.deleteMember(teamId, userId));
    }
}
