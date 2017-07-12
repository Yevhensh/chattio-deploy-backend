package com.chattio.util;

import com.chattio.constants.PatternConstants;
import com.chattio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebSocketUserRetriever {

    public static String parseUserEmailFromHeaders(MessageHeaders headers) {
        Map<String, List<String>> headersMap = (LinkedMultiValueMap) headers.get("nativeHeaders");
        String unparsedToken = headersMap.get("Authorization").get(0);
        String token = unparsedToken.split(PatternConstants.WHITESPACE)[1];

        String claims = JwtHelper.decode(token).getClaims();
        Matcher matcher = Pattern.compile(PatternConstants.EMAIL_RETRIEVER).matcher(claims);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
