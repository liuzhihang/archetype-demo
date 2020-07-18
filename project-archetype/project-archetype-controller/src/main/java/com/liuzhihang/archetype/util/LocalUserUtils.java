package com.liuzhihang.archetype.util;



/**
 * @author liuzhihang
 * @date 2019/12/28 16:43
 */
public class LocalUserUtils {

    private LocalUserUtils(){

    }

    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static UserInfo get() {

        return THREAD_LOCAL.get();
    }

    public static void set(UserInfo userInfo) {
        THREAD_LOCAL.set(userInfo);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
