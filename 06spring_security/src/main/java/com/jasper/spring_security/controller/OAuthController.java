package com.jasper.spring_security.controller;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Slf4j
public class OAuthController {
//    gitee的回调
    @GetMapping("notify")
    public HashMap<String, String> giteeCallback(@RequestParam String code) throws JsonProcessingException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",code);
        hashMap.put("grant_type","authorization_code");
        hashMap.put("client_id","feb31e7e0e84faf41a038ca503a44c744fac0b169e48e5a5defb680eab58ac64");
        hashMap.put("redirect_uri","http://localhost:8080/oauth/notify");
        hashMap.put("client_secret","ee89f74e0f51f079095b9483b4e536be0f726a5709e3d67ca8cca7e367557dc8");
//        https://gitee.com/oauth/token?grant_type=authorization_code&code={code}&client_id={client_id}&redirect_uri={redirect_uri}&client_secret={client_secret}
        String post = HttpUtil.post("https://gitee.com/oauth/token", hashMap);
        log.info("post = {}",post);
//        根据post中的accessToken获取用户信息
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> hashMap1 = objectMapper.readValue(post, new TypeReference<>() {});
        String accessToken =  hashMap1.get("access_token");
        String userInfo = HttpUtil.get("https://gitee.com/api/v5/user?access_token=" + accessToken);
        return objectMapper.<HashMap<String, String>>readValue(userInfo, new TypeReference<>() {});
    }

    @GetMapping("to_authorize")
    public String toAuthorize() {
        return "redirect:https://gitee.com/oauth/authorize?" +
                "client_id=feb31e7e0e84faf41a038ca503a44c744fac0b169e48e5a5defb680eab58ac64" +
                "&redirect_uri=http://localhost:8080/oauth/notify&response_type=code";
    }
}
