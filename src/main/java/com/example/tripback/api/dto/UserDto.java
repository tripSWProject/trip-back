package com.example.tripback.api.dto;

public class UserDto {
    public static class simpleUser{
        private String userName;
        private String profileImageUrl;

        public simpleUser(String userName, String profileImageUrl){
            this.userName = userName;
            this.profileImageUrl = profileImageUrl;
        }
    }
}
