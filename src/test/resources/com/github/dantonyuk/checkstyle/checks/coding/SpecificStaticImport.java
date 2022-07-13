package com.github.dantonyuk.checkstyle.checks.coding;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SpecificStaticImport {

    public void testObjectsNonNull() {
        nonNull("");
    }

    public void testObjectsIsNull() {
        isNull("");
    }
}
