package com.github.dantonyuk.checkstyle.checks.coding;

import java.util.Objects;

public class SpecificImport {

    public void testObjectsNonNull() {
        Objects.nonNull("");
    }

    public void testSelfNonNull() {
        nonNull("");
    }

    public void testObjectsIsNull() {
        Objects.isNull("");
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
