package com.leyiju.filter.qq;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with com.leyiju.filter.qq.
 *
 * @author: Xavier
 * @time: 2019/6/6 14:27
 */
public class QQAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static String CODE = "code";
    private final static String accessTokenUri = "https://graph.qq.com/oauth2.0/token";
    private final static String grantType = "authorization_code";
    private final static String clientId = "101386962";
    private final static String clientSecret = "2a0f820407df400b84a854d054be8b6a";
    private final static String redirectUri = "http://www.ictgu.cn/login/qq";
    private final static String openIdUri = "https://graph.qq.com/oauth2.0/me?access_token=";



    private final static String TOKEN_ACCESS_API = "%s?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s";

    public QQAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.GET.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        String code = httpServletRequest.getParameter(CODE);
        System.out.println("Code : " + code);
        String  tokenAccessApi = String.format(TOKEN_ACCESS_API, accessTokenUri, grantType, clientId, clientSecret, code, redirectUri);
        QQToken qqToken = this.getToken(tokenAccessApi);
        System.out.println(JSON.toJSONString(qqToken));
        if(qqToken != null) {
            String openId = getOpenId(qqToken.getAccessToken());
            System.out.println(openId);
            if(openId != null) {
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(qqToken.getAccessToken(), openId);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return null;
    }

    private String getOpenId(String accessToken) throws IOException {
        String url  = openIdUri + accessToken;
        Document document = Jsoup.connect(url).get();
        String resultText = document.text();
        Matcher matcher = Pattern.compile("\"openid\":\"(.*?)\"").matcher(resultText);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private QQToken getToken(String tokenAccessApi) throws IOException {
        Document document = Jsoup.connect(tokenAccessApi).get();
        String tokenResult = document.text();
        String[] results = tokenResult.split("&");
        if(results.length == 3) {
            QQToken qqToken = new QQToken();
            String accessToken = results[0].replace("access_Token=", "");
            int expiresIn = Integer.valueOf(results[1].replace("expires_in=", ""));
            String refreshToken = results[2].replace("refresh_Token=", "");
            qqToken.setAccessToken(accessToken);
            qqToken.setExpiresIn(expiresIn);
            qqToken.setRefresh_token(refreshToken);
            return qqToken;
        }
        return null;
    }

    class QQToken {
        private String accessToken;
        private int expiresIn;
        private String refresh_token;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }
    }
}
