package com.cos.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    // 스프링 시큐리티에서 권한 코드에 항상 ROLE_이 앞에 있어야 만 한다.
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    // 해당 value가 각각의 권한의 value와 대응
    private String value;
}
