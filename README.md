# My Library

A Java library for handling pagination with a simple and flexible `Pageable` and `Page` class implementation. Easily integrate pagination features into your Java or Spring projects. Built with Java 17.

## Features

- **Pageable Class**: Handles pagination details like current page, total pages, start and end pages of a block, etc.
- **Page Class**: Encapsulates the pageable object along with the data of the current page.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java 17
- Gradle
- Lombok

### Installation

To include this library in your project, add the following to your `build.gradle` file:

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/YOUR_USERNAME/YOUR_REPOSITORY")
    }
}

dependencies {
    implementation 'com.example:my-library:1.0-SNAPSHOT'
}
