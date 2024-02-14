# Atem
[![Kotlin](https://img.shields.io/badge/java-17-ED8B00.svg?logo=java)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.22-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.4.0-02303A.svg?logo=gradle)](https://gradle.org)
[![JitPack](https://img.shields.io/jitpack/v/io.github.NOOBNUBY/Atem)](https://search.maven.org/artifact/io.github.NOOBNUBY/Atem)
[![GitHub](https://img.shields.io/github/license/NOOBNUBY/Atem)](https://www.gnu.org/licenses/gpl-3.0.html)    
A Papermc Item Library.

* [Features](#Features)
* [Dokka](#Dokka)
* [License](#License)
* [Importing](#import-library)
* [Usage](#how-to-use-this-library)

## Features
* DSL style code
* Modular inventory GUI without the requirement of event declaration

## Dokka
You can see the Dokka document.
* Dokka: [Link](https://atem.noobnuby.com/)

## License
This library is licensed under the General Public License v3.0.
* License: [Atem License](LICENSE)

## Import Library

* Maven
```XML
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.noobnuby</groupId>
    <artifactId>atem</artifactId>
    <version>VERSION</version>
</dependency>
```

* Gradle (Groovy DSL)
```groovy
repositories {
  mavenCentral()
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.noobnuby:atem:VERSION'
}
```

* Gradle (Kotlin DSL)
```kotlin
repositories {
  mavenCentral()
  maven("https://jitpack.io")
}

dependencies {
  implementation("com.github.noobnuby:atem:VERSION")
}
```

### This config is not yet available
> If you are using spigot/paper 1.17+, you can use the library-loading feature instead of shading.
* plugin.yml (1.17+)
```
# ...
libraries:
  - com.noobnuby.lib:atem-api:VERSION
# ...
```

## How to use this Library
This library is kotlin-optimized. DSL pattern will break unless you use kotlin.
```Kotlin
class Test {
  fun giveItem(player: Player) {
      val item = Atem.register(Material.STICK,1,Component.text("막대기"), listOf(Component.empty())) {
          rightClickEvent {
              it.sendMessage("우클릭 함!")
          }
      }
      player.inventory.addItem(item)
  }
}
```