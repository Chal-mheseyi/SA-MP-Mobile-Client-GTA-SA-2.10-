package com.russia.launcher.domain.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    NICKNAME_IS_EMPTY("Enter nickname"),
    EMAIL_IS_EMPTY("Enter email"),
    DONATE_SUM_IS_EMPTY("Enter donation amount"),
    SERVER_NOT_SELECTED("Choose a server"),
    NICKNAME_CONTAINS_DIGITS("Nickname must not contain digits"),
    DONATE_SUM_CONTAINS_SYMBOLS_OTHER_DIGITS("Donation amount must contain only digits"),
    NICKNAME_IS_TOO_LONG("Nickname length must be less than 20 characters"),
    EMAIL_IS_TOO_LONG("Email length must be less than 100 characters"),
    NICKNAME_DOES_NOT_MATCH_PATTERN("Nickname doesn't match the format: \"Имя_Фамилия\""),
    EMAIL_DOES_NOT_MATCH_PATTERN("Email doesn't match the format: \"example@example.com\""),
    PASSWORD_IS_EMPTY("Enter password"),
    PASSWORD_IS_TOO_LONG("Password length must be less than 17 characters"),
    PASSWORD_IS_TOO_SHORT("Password length must be more than 5 characters"),
    TEST_SERVER_PASSWORD_NOT_INPUT("Password for the test server was not set"),

    INPUT_NICKNAME_BEFORE_SERVER_CONNECT("Enter your nickname before connecting to the server"),
    CAPTCHA_NOT_PASSED("Confirm you're not a robot before entering"),
    CAPTCHA_COMPLETING_PROBLEMS("Couldn't verify you're not a robot, please try again"),

    FIRSTLY_LOAD_GAME("Download the game first!"),

    FAILED_LOAD_LOADER_SLIDER_DATA("Failed to load data"),
    APK_UPDATE_FILE_NOT_FOUND("Installation error: file not found");


    private final String text;

}
