"Mockito Instant" book repository
=====================================

## Where to get the book?

The book is available here - [link to the book](http://www.packtpub.com/how-to-create-stubs-mocks-spies-using-mockito/book)

## What is in this repo?

In this repo you can find code present in the book. It can be built by numerous build tools (check next sections for more details).
This codebase goes further then the book - any changes in approach or fixes will be applied much faster here than in the book so check this repo for changes.

## How is this repo structured?

```
src
    main
        java
            com.blogspot.toomuchcoding
                all necessary production classes
    test
        java
            com.blogspot.toomuchcoding
                quickStart
                    ordered examples from the "Quick start" chapter
                soWhatIsMockito
                    ordered examples from the "So what is Mockito" chapter
                topFeaturesYouNeedToKnowAbout
                    ordered examples from the "To features you need to know about" chapter
            org.mockito.configuration
                global Mockito configuration
```

## How to build it?

In comparison to the book the attached build files contain dependencies needed to run all tests from this repo. You *don't* need *catch-exception* library to use Mockito.

### Maven (config in pom.xml):

```
   mvn clean install
```

### Gradle (config in build.gradle):

```
   ./gradlew build
```

### Buildr (config in Buildr)

```
   buildr
```

### SBT (config in build.sbt)

```
   sbt test
```

### IVY (config in build.xml and ivy.xml)

```
   ant
```

### ANT (config in build_for_ant.xml)

```
    ant -buildfile build_for_ant.xml
```

## Anything to add / emphasise to the books content?

- It's much better to use annotations than to create mocks etc. by hand - you remove unnecessary code
- Before you start coding anything read the [Growing Object-Oriented Software Guided by Tests](http://www.growing-object-oriented-software.com/) book. It's an absolute must!
- Read [Mockito's wiki on how to write good tests](https://github.com/mockito/mockito/wiki/How-to-write-good-tests)
- Using BDDMockito allows you to use more user friendly syntax
- I prefer [AssertJ](http://joel-costigliola.github.io/assertj/assertj-core.html) than Hamcrest so I'd rewrite the tests using AssertJ. Read more about [AssertJ's features here](http://joel-costigliola.github.io/assertj/assertj-core-features-highlight.html)
- It's better to assert behaviour rather than implementation if possible (less Mockito.verify(...) more assertThat(...) ) - it will make your tests less brittle

## Any changes in comparison to the book?

- *Do not ever create a mock of a list* - even to show how to stub multiple method calls! Instead use a real list

# Any additional thoughts?

Read my new book "Mockito Cookbook" :) The book is out and you can buy it here [Mockito Cookbook at Packt Publishing](https://www.packtpub.com/mockito-cookbook/book). You can also take a look at the code - [Mockito Cookbook repository](https://github.com/marcingrzejszczak/mockito-cookbook)

# Contact
In case of any issues, doubts or if you need help with understanding sth just contact me either via email - marcin(at)grzejszczak.pl or via my blog at [http://toomuchcoding.blogspot.com](http://toomuchcoding.blogspot.com), or my homepage [http://www.marcin.grzejszczak.pl](http://www.marcin.grzejszczak.pl), or Twitter - [@MGrzejszczak](http://twitter.com/MGrzejszczak).

## Mockito Instant Google Group
There is also the [Mockito Instant Google Group](https://groups.google.com/forum/#!forum/mockito-instant) where you can share your thoughts on whatever is related to the book or any topic related to Mockito.

# Build status
[![Build Status](https://travis-ci.org/marcingrzejszczak/mockito-instant.svg?branch=master)](https://travis-ci.org/marcingrzejszczak/mockito-instant)
