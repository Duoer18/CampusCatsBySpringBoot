package com.duoer.campus;

import com.duoer.campus.entity.User;

public class BaseContext {
    private static final ThreadLocal<User> USER = new ThreadLocal<>();

    public static User get() {
        return USER.get();
    }

    public static void set(User u) {
        USER.set(u);
    }

    public static void remove() {
        USER.remove();
    }
}
