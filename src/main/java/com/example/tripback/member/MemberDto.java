package com.example.tripback.member;

import lombok.Getter;
import java.util.List;

public class MemberDto {

    @Getter
    public static class responseMemberList{
        private final List<String> membersList;

        public responseMemberList(List<String> membersList) {
            this.membersList = membersList;
        }
    }

}
