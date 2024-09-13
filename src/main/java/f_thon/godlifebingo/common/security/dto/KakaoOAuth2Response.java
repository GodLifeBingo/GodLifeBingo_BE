package f_thon.godlifebingo.common.security.dto;

import java.util.Map;

public class KakaoOAuth2Response implements OAuth2Response {

    private final Map<String, Object> attributes;

    public KakaoOAuth2Response(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        // 카카오의 사용자 ID는 최상위 레벨에 있음.
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        // 이메일은 kakao_account 객체 안에 있음.
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getName() {
        // 이름 정보는 profile 객체 안에 있음 (카카오에서는 name이 없고, 대신 nickname을 사용).
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return profile.get("nickname").toString();
    }

    public String getProfileImage() {
        // 프로필 이미지는 profile 객체 안에 있음.
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return profile.get("profile_image_url").toString();
    }

    public Boolean isEmailVerified() {
        // 이메일 인증 여부
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return (Boolean) kakaoAccount.get("is_email_verified");
    }

    public String getGender() {
        // 성별 정보 (선택적)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount.get("gender") != null ? kakaoAccount.get("gender").toString() : null;
    }

    public String getBirthday() {
        // 생일 정보 (선택적)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount.get("birthday") != null ? kakaoAccount.get("birthday").toString() : null;
    }

    public String getBirthyear() {
        // 출생년도 (선택적)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount.get("birthyear") != null ? kakaoAccount.get("birthyear").toString() : null;
    }
}