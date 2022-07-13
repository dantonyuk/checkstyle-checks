package com.github.dantonyuk.checkstyle.checks;

public class FullNameCall {

  public void callNonNullByFullName() {
    java.util.Objects.nonNull("");
  }

  public void callIsNullByFullName() {
    java.util.Objects.isNull("");
  }
}
