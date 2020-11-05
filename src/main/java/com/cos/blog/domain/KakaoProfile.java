package com.cos.blog.domain;

import lombok.Getter;

@Getter
public class KakaoProfile {

    private Integer id;
    private String connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Getter
    public class Properties {
        private String nickname;
    }

    @Getter
    public class KakaoAccount {

        private Boolean profile_needs_agreement;
        private Profile profile;
        private Boolean has_email;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;

        @Getter
        public class Profile {
            private String nickname;
        }
    }
}







