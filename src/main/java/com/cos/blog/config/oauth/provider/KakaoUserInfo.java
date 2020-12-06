package com.cos.blog.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;
    private Map<String, Object> properties;
    private Map<String, Object> kakao_account;

    public KakaoUserInfo(Map<String, Object> attributes) {
        System.out.println("카카오 생성자" + attributes);
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        kakao_account = (Map)attributes.get("kakao_account");
        return kakao_account.get("email").toString();
    }

    @Override
    public String getName() {
        properties = (Map)attributes.get("properties");
        return properties.get("nickname").toString();
    }
}
