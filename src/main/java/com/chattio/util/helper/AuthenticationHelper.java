package com.chattio.util.helper;

import java.security.Principal;

public interface AuthenticationHelper {
    Principal setApplicationAuthentication(String username);
}
