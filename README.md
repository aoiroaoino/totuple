# totuple

A tiny compiler plugin that adds the toTuple method to all case classes.

Support scala versions: Scala 2.11 and 2.12

## Usage

WIP: **First stable release not yet.**

```sh
$ git clone git@github.com:aoiroaoino/totuple.git

$ cd totuple && sbt publishLocal
```

Add following setting in your `build.sbt`.

```
addCompilerPlugin("com.github.aoiroaoino" %% "totuple" % "0.1.0-SNAPSHOT")
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
