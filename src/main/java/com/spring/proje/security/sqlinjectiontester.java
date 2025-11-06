package com.spring.proje.security;

public class sqlinjectiontester {


    private static final String[] sql = new String[]{"select", "union", "insert", "update", "delete", "drop", "and", "'", ";", "--", "/*", "*/", "#", "or 1=1", "and 1=1"};

    public static boolean check(String text) {

        if (text == null || text.isEmpty()) {
            return false;
        }

        String lowertext = text.toLowerCase();


        for (String sqll : sql) {

            if (lowertext.contains(sqll)) {
                return true;
            }
        }

        return false;
    }
}