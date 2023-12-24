package com.my.blog.common;

public class Utils {
    public static int isNotNull(int val) {
        return val != 0 ? 1 : 0;
    }

    public static boolean isNotNull(String str) {
        return str != null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
}
