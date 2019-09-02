##  maven settings.xml
1. 下载 [http://euler.primeton.com/download/attachments/10848287/settings.xml?version=1&modificationDate=1550822714000&api=v2]
1. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <profiles>
        <profile>
            <id>primeton</id>
            <repositories>
                <repository>
                    <id>primeton-public-repository</id>
                    <url>http://192.168.2.1:8081/nexus/content/groups/primeton_public_repositories/</url>
                </repository>
                <repository>
                    <id>spring-release</id>
                    <name>spring-release</name>
                    <url>https://repo.spring.io/release</url>
                </repository>
                <repository>
                    <!--
                        | 为了解决 spring-cloud-security-dependecies 2.1.0.RELEASE 里依赖了
                        | <spring-security-oauth2-autoconfigure.version>2.1.0.M4</spring-security-oauth2-autoconfigure.version>
                    -->
                    <id>spring-milestone</id>
                    <name>spring-milestone</name>
                    <url>https://repo.spring.io/milestone</url>
                </repository>
                <repository>
                    <id>jcenter-snapshots</id>
                    <name>jcenter</name>
                    <url>https://jcenter.bintray.com/</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>primeton-public-maven-plugin-repository</id>
                    <url>http://192.168.2.1:8081/nexus/content/groups/primeton_public_maven_repositories/</url>
                </pluginRepository>
            </pluginRepositories>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>primeton</activeProfile>
    </activeProfiles>
</settings>
```
