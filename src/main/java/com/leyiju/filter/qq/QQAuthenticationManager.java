package com.leyiju.filter.qq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leyiju.domain.QQUser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with com.leyiju.filter.qq.
 *
 * @author: Xavier
 * @time: 2019/6/6 14:57
 */
public class QQAuthenticationManager implements AuthenticationManager {

    private final static List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    private final static String userInfoUri = "https://graph.qq.com/user/get_user_info";

    private final static String USER_INFO_API = "%s?access_token=%s&oauth_consumer_key=%s&openid=%s";

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getName() != null && authentication.getCredentials() != null) {
            QQUser user = null;
            try {
                user = getUserInfo(authentication.getName(), (String) authentication.getCredentials());
            } catch (IOException e) {
                e.printStackTrace();
                throw new BadCredentialsException("Bad Credentials!");
            }
            return new UsernamePasswordAuthenticationToken(user, null, AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    private QQUser getUserInfo(String accessToken, String openId) throws IOException {
        String url = String.format(USER_INFO_API, userInfoUri, accessToken, openId);
        Document document = Jsoup.connect(url).get();
        String resultText = document.text();
        JSONObject json = JSON.parseObject(resultText);

        QQUser user = new QQUser();
        user.setNickname(json.getString("nickname"));
        user.setGender(json.getString("gender"));
        user.setProvince(json.getString("province"));
        user.setYear(json.getString("year"));
        user.setAvatar(json.getString("figureurl_qq_2"));
        return user;
    }
}
