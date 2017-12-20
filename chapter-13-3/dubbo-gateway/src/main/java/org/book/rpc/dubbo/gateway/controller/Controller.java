package org.book.rpc.dubbo.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.book.rpc.dubbo.service.IHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
public class Controller {

    @Reference
    private IHello hello;

    @Autowired
    private RedisTemplate redisTemplate;

    @PreAuthorize("hasRole('ROLE_USER') or #oauth2.clientHasRole('ROLE_CLIENT')")
    @RequestMapping(value = "/private/{id}")
    public String privateIndex(@PathVariable String id, Principal principal) {

        //删除token示例
        TokenStore tokenStore = new RedisTokenStore(redisTemplate.getConnectionFactory());
        Collection<OAuth2AccessToken> oAuth2AccessTokens = tokenStore.findTokensByClientIdAndUserName("app", principal.getName());
        for (OAuth2AccessToken token : oAuth2AccessTokens) {
            tokenStore.removeAccessToken(token);
        }

        //调用远程dubbo服务
        hello.say("你好");
        return "success : " + id;
    }

    @RequestMapping(value = "/public/{id}")
    public String publicIndex(@PathVariable String id) {
        return "success : " + id;
    }

}
