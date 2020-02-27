plugins {
    id("java")
    id("groovy")
}

version = "0.1"
group = "com.virtuslab"
description = "Functional Java Workshop"

repositories {
    mavenCentral()
}

//sourceSets {
//    create("solutions") {
//        compileClasspath += sourceSets["main"].output
//    }
//}

//val solutionsImplementation by configurations.getting {
//    extendsFrom(configurations.implementation.get())
//}

dependencies {
    compile(group = "io.vavr", name = "vavr", version = "0.10.2")
    
    compileOnly("org.projectlombok:lombok:1.18.8")
    annotationProcessor("org.projectlombok:lombok:1.18.8")

    testCompile("org.codehaus.groovy:groovy-all:2.5.6")
    testCompile("org.spockframework:spock-core:1.2-groovy-2.5") {
        exclude(group = "org.codehaus.groovy", module = "groovy-all")
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.0")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.3.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    
    //solutionsImplementation("org.projectlombok:lombok:1.18.8")
}

val test: Test by tasks
test.useJUnitPlatform()


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
