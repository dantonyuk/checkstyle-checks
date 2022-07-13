plugins {
    scala
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.scala-lang:scala3-library_3:3.1.3")
    api("com.puppycrawl.tools:checkstyle:10.3.1")

    testImplementation("com.puppycrawl.tools:checkstyle:10.3.1:tests")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("junit:junit:4.13.2")
}
