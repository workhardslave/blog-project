package com.cos.blog.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return null; // 페북 계정에 이메일이 등록 안되어 있음 ㅠ
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}