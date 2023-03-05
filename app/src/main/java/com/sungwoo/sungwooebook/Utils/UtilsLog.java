package com.sungwoo.sungwooebook.Utils;

import android.util.Log;

public class UtilsLog {
    private static final int DEBUG = 0;
    private static final int RELEASE = 1;

    private static int version = DEBUG;

    public static void d(String log) {
        if (versionDebug()) {
            Log.d("Ebook", log);
        }
    }

    public static void e(String log) {
        Log.e("Ebook", log);
    }

    public static boolean versionDebug() {
        if (version == DEBUG) {
            return true;
        }
        return false;
    }
}
