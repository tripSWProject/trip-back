package com.example.tripback.api.controller;

import com.example.tripback.api.entity.User;
import com.example.tripback.common.utils.ApiResponse;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.service.UserService;
import com.example.tripback.api.request.PostImgReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "user", description = "사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "사용자 조회")
    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @Operation(summary = "이메일 수정")
    @PatchMapping
    public ApiResult<Long> modifyEmailUser(@RequestParam String email){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return success(userService.modifyEmail(email, user.getUsername()));
    }

    @Operation(summary = "프로필 사진 수정")
    @PatchMapping("/img")
    public ApiResult<Long> modifyImgUser(@ModelAttribute PostImgReq postImgReq){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return success(userService.modifyProfilePic(postImgReq, user.getUsername()));
    }


}