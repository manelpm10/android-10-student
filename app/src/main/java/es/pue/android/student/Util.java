package es.pue.android.student;

import android.os.Environment;

/**
 * Ouch! This class break my heart!
 */
public class Util {

    public static boolean isExternalStorageReadOnly() {
        String state = Environment.getExternalStorageState();

        return (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();

        return (Environment.MEDIA_MOUNTED.equals(state));
    }
}
