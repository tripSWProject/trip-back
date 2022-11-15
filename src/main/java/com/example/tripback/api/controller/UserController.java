package com.example.tripback.api.controller;

import com.example.tripback.api.entity.User;
import com.example.tripback.common.utils.ApiResponse;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.service.UserService;
import com.example.tripback.api.request.PostImgReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }

    @PatchMapping
    public ApiResult<Long> modifyEmailUser(@RequestParam String email){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return success(userService.modifyEmail(email, user.getUsername()));
    }

    @PatchMapping("/img")
    public ApiResult<Long> modifyImgUser(@ModelAttribute PostImgReq postImgReq){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return success(userService.modifyProfilePic(postImgReq, user.getUsername()));
    }


}