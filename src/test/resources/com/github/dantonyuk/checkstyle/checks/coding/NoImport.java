package com.github.dantonyuk.checkstyle.checks.coding;

public class NoImport {
    public void testSelfNonNull() {
        nonNull("");
    }

    public void testSelfIsNull() {
        isNull("");
    }

    private boolean isNull(String s) {
        return s == null;
    }

    private boolean nonNull(String s) {
        return s != null;
    }
}
