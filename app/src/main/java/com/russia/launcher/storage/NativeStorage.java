package com.russia.launcher.storage;

import static com.russia.launcher.config.Config.NATIVE_SETTINGS_FILE_PATH;

import android.content.Context;
import android.widget.Toast;

import com.russia.launcher.domain.messages.ErrorMessage;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class NativeStorage {

    private static final String CLIENT_SECTION_NAME = "client";

    private static File getSettingsFile(Context context) {
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            throw new IllegalStateException("External files directory is unavailable");
        }

        return new File(externalFilesDir, NATIVE_SETTINGS_FILE_PATH.substring(1));
    }

    public static void addClientProperty(String propertyName, String value, Context context) {
        try {
            File settingsFile = getSettingsFile(context);
            File parent = settingsFile.getParentFile();

            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Unable to create settings directory: " + parent);
            }

            Wini w;
			if (settingsFile.exists()) {
			    w = new Wini(settingsFile);
			} else {
			    w = new Wini();
			    w.setFile(settingsFile);
			}
			w.put(CLIENT_SECTION_NAME, propertyName, value == null ? "" : value);
			w.store();
        } catch (InvalidFileFormatException e) {
            throw new RuntimeException("Invalid settings.ini format", e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save settings.ini", e);
        }
    }

    public static String getClientProperty(String property, Context context) {
        try {
            File settingsFile = getSettingsFile(context);

            if (!settingsFile.exists()) {
                return "";
            }

            Wini w = new Wini(settingsFile);
            String value = w.get(CLIENT_SECTION_NAME, property);
            return value == null ? "" : value;
        } catch (IOException ignored) {
            return "";
        }
    }

    private static void showMessage(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show();
    }
}
