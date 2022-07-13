package com.github.dantonyuk.checkstyle.checks.coding;

import static java.util.Objects.*;

public class StarStaticImport {

    public void testObjectsNonNull() {
        nonNull("");
    }

    public void testObjectsIsNull() {
        isNull("");
    }
}
