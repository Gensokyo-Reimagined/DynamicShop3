plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "9.0.0-beta16"
}

group = "me.sat7"
version = "3.21.0"
description = "DynamicShop"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://jitpack.io")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }

    maven {
        url = uri("https://nexus.hc.to/content/repositories/pub_releases")
    }

    maven {
        url = uri("https://repo.codemc.org/repository/maven-public")
    }

    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    maven {
        url = uri("https://repo.rosewooddev.io/repository/public/")
    }

    maven {
        url = uri("https://maven.enginehub.org/repo/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.org.bstats.bstats.bukkit)
    api(libs.com.opencsv.opencsv)
    api(libs.commons.io.commons.io)
    api(libs.com.github.pikamug.localelib)
    testImplementation(libs.junit.junit)
//    compileOnly(libs.io.papermc.paper.paper.api)
//    testCompileOnly(libs.io.papermc.paper.paper.api)
    compileOnly(libs.org.spigotmc.spigot.api)
    testCompileOnly(libs.org.spigotmc.spigot.api)
    compileOnly(libs.com.github.milkbowl.vaultapi)
//    compileOnly(libs.net.milkbowl.vault.vaultunlockedapi)
    compileOnly(libs.me.clip.placeholderapi)
    compileOnly(libs.com.github.zrips.jobs)
    compileOnly(libs.org.projectlombok.lombok)
    annotationProcessor(libs.org.projectlombok.lombok)
    testCompileOnly(libs.org.projectlombok.lombok)
    testAnnotationProcessor(libs.org.projectlombok.lombok)
    compileOnly(libs.org.black.ixx.playerpoints)
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "private"
            artifact(tasks.shadowJar) {
                artifactId = "DynamicShop"
            }
        }
    }

    repositories {
        maven {
            name = "gensokyoReimagined"
            url = uri("https://repo.gensokyoreimagined.net")
            credentials {
                username = project.findProperty("gensokyoUser")?.toString() ?: System.getenv("GENSOKYOUSER")
                password = project.findProperty("gensokyoToken")?.toString() ?: System.getenv("GENSOKYOTOKEN")
            }
        }
    }
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        relocate("org.bstats", "me.sat7.dynamicshop.lib.bstats")
        relocate("com.opencsv", "me.sat7.dynamicshop.lib.opencsv")
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}