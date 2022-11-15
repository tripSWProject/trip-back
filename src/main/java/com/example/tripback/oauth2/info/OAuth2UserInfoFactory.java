package com.example.tripback.oauth2.info;

import com.example.tripback.oauth2.entity.ProviderType;
import com.example.tripback.oauth2.info.impl.KakaoOAuth2UserInfo;
import com.example.tripback.oauth2.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}