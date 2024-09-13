package f_thon.godlifebingo.common.security.dto;

import java.util.Map;

public class NaverOAuth2Response implements OAuth2Response {

    private final Map<String, Object> attributes;

    public NaverOAuth2Response(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        // Naver의 사용자 ID는 response 객체 안에 있으므로, 해당 부분에 접근해야 함.
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("id").toString();
    }

    @Override
    public String getEmail() {
        // 이메일도 response 객체 안에 있음
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("email").toString();
    }

    @Override
    public String getName() {
        // 이름도 response 객체 안에 있음
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("name").toString();
    }

    public String getNickname() {
        // 닉네임을 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("nickname").toString();
    }

    public String getProfileImage() {
        // 프로필 이미지를 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("profile_image").toString();
    }

    public String getAge() {
        // 연령대를 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("age").toString();
    }

    public String getGender() {
        // 성별을 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("gender").toString();
    }

    public String getBirthday() {
        // 생일을 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("birthday").toString();
    }

    public String getBirthyear() {
        // 출생년도를 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("birthyear").toString();
    }

    public String getMobile() {
        // 전화번호를 반환
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("mobile").toString();
    }
}