package com.example.tripback.api.controller;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.dto.MemberDto.responseMemberList;
import com.example.tripback.api.service.MemberService;
import com.example.tripback.api.request.PostMemberReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "members", description = "멤버 API")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "방장이 멤버 초대")
    @GetMapping("/visit")
    @ResponseBody
    public ApiResult<String> inviteMember(@RequestParam String email){
        return success(memberService.inviteMember(email));
    }

    @Operation(summary = "멤버가 그룹 수락")
    @PostMapping("/accept")
    public ApiResult<String> acceptMember(@RequestBody PostMemberReq postMemberReq){
        return success(memberService.acceptMember(postMemberReq));
    }

    @Operation(summary = "그룹 멤버 리스트 조회")
    @GetMapping
    public ApiResult<responseMemberList> getMemberList(@RequestParam Long teamId){
        return success(new responseMemberList(memberService.memberList(teamId)));
    }

    @Operation(summary = "그룹 나가기")
    @DeleteMapping
    public ApiResult<Long> deleteMember(@RequestParam Long teamId, @RequestParam Long userId){
        return success(memberService.deleteMember(teamId, userId));
    }
}
