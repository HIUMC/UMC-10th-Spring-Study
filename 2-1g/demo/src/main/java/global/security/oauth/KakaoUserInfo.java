package global.security.oauth;

import java.util.Map;

public class KakaoUserInfo {

    private final Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getSocialId() {
        return String.valueOf(attributes.get("id"));
    }

    public String getNickname() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        if (properties != null && properties.get("nickname") != null) {
            return (String) properties.get("nickname");
        }

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return (String) profile.get("nickname");
    }
}
