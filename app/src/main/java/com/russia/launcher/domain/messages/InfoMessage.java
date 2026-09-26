package com.russia.launcher.domain.messages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InfoMessage {
    CONNECTION_TO_LAST_SERVER("Connecting to the last selected server!"),
    CONNECTION_TO_SELECT_SERVER("Connecting to server: %s!"),
    SERVER_SELECTED("Server selected! Press the yellow button to start the game"),
    INSTALL_GAME_FIRST("Install the game first"),
    GAME_FILES_VALID("File contents are valid!"),
    SETTINGS_ALREADY_DEFAULT("Default settings are already set"),
    DOWNLOAD_SUCCESS_INPUT_YOUR_NICKNAME("Game successfully installed, enter your nickname"),
    DOWNLOAD_SUCCESS_SELECT_SERVER("Game successfully installed, choose a server"),
    REINSTALL_GAME_QUESTION("Reinstall the game?"),
    RESET_SETTINGS_QUESTION("Reset game settings?"),
    APPROVE_INSTALL("Confirm installation");

    private final String text;

    public static String createConnectToSelectServerMessage(String serverName) {
        return String.format(
                CONNECTION_TO_SELECT_SERVER.getText(),
                serverName
        );
    }
}
