# totuple


[![Maven Central](https://img.shields.io/maven-central/v/com.github.aoiroaoino/totuple_2.12.svg?style=flat-square)](https://search.maven.org/#search%7Cga%7C1%7Ccom.github.aoiroaoino.totuple)
![Travis](https://img.shields.io/travis/aoiroaoino/totuple.svg?style=flat-square)


A tiny compiler plugin that adds the toTuple method to all case classes.

Support scala versions: Scala 2.11 and 2.12

## Usage

Add following setting in your `build.sbt`.

```
addCompilerPlugin("com.github.aoiroaoino" %% "totuple" % "0.1.2")
```

The toTuple method is added to the case class with arguments 1 to 22.

```scala
case class User(id: Long, name: String)

val user = User(1, "John Doe")

assert(user.toTuple == (user.id, user.name))
```

```scala
case class Foo()
case class Bar(p1: Int, p2: Int, ..., p23: Int)

val foo = Foo()
val bar = Bar(1, 2, ..., 23)

// compile error
foo.toTuple
bar.toTuple
```

## License

MIT
