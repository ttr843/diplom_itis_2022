package ru.itis.websiteservice.security.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@RequiredArgsConstructor
@Getter
public enum Endpoints {

    ROOT("/"),
    SIGN_UP("/signUp"),
    SIGN_IN("/signIn"),
    SIGN_IN_ERROR("/signIn?error"),
    CONFIRM("/confirm/**"),
    LOGOUT("/logout"),
    MAIN("/main");

    private final String url;
}
