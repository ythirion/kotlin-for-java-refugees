---
layout: default
title: Koans
parent: Day 4 -  Testing
grand_parent: Kotlin for java refugees
nav_order: 1
---

## Koans

### [Scope: apply](https://play.kotlinlang.org/koans/Builders/The%20function%20apply/Task.kt)
* The context object is available as a receiver (this)
* The return value is the object itself
* Use apply for code blocks that don't return a value and mainly operate on the members of the receiver object. 

> The common case for apply is the object configuration. Such calls can be read as “ apply the following assignments to the object.”

```kotlin
val adam = Person("Adam").apply {
    age = 32
    city = "London"        
}
```
Exercise: Create your own apply method

### [Top level function](https://kotlinlang.org/docs/functions.html#function-scope)
* Top-level functions are functions inside a Kotlin package that are defined outside of any class, object, or interface. 
* This means that they are functions you call directly, without the need to create any object or call any class

Top-level functions in Kotlin can be used as a replacement for the static utility methods inside helper classes we code in Java. Let's look at how to define a top-level function in Kotlin.

```kotlin
package demo.utils

fun checkUserStatus(): String {
    return "online"
}
```

In the code above, we defined a `packagedemo.utils` inside a file called `UserUtils.kt`
and also defined a top-level utility function called `checkUserStatus()` inside this same package and file.

#### Java Interoperability
```java
package demo.utils

public class UserUtilsKt {

    public static String checkUserStatus() {
        return "online";
    }
}
```

This means that Java callers can simply call the method by referencing its generated class, just like for any other static method.

### [Static Methods](https://kotlinlang.org/docs/java-to-kotlin-interop.html#static-methods)
* As mentioned above, Kotlin represents package-level functions as static methods. 
* Kotlin can also generate static methods for functions defined in named objects or companion objects 
  * If you annotate those functions as `@JvmStatic`, the compiler will generate both :
    * a static method in the enclosing class of the object
    * and an instance method in the object itself 
 
For example:

```kotlin
class C {
    companion object {
        @JvmStatic fun callStatic() {}
        fun callNonStatic() {}
    }
}
```

Now, callStatic() is static in Java while callNonStatic() is not:
```java
    C.callStatic(); // works fine
    C.callNonStatic(); // error: not a static method
    C.Companion.callStatic(); // instance method remains
    C.Companion.callNonStatic(); // the only way it works
```

### [Late init](https://kotlinlang.org/docs/properties.html#late-initialized-properties-and-variables)

### [Equals Operators](https://kotlinlang.org/docs/equality.html#referential-equality)
#### Structural Equality (`==` => Object.equals())
* `==` operator is used to **compare the data** of two variables
* `==` operator in Kotlin only compares the data or variables, whereas in Java or other languages `==` is generally used to compare the references
* The negated counterpart of == in Kotlin is != which is used to compare if both the values are not equal to each other.

#### Referential equality (`===`)
* `===` operator is used to **compare the reference** of two variable or object
* It will only be true if both the objects or variables pointing to the same object.
* The negated counterpart of === in Kotlin is !== which is used to compare if both the values are not equal to each other. 

> For values which are represented as primitive types at runtime (for example, Int), the === equality check is equivalent to the == check

### [Inner Class](https://kotlinlang.org/docs/nested-classes.html#inner-classes)
* A nested class marked as inner can access the members of its outer class
* Inner classes carry a reference to an object of an outer class

```kotlin
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}
```

### [Type aliases](https://kotlinlang.org/docs/type-aliases.html)
* Type aliases provide alternative names for existing types
* If the type name is too long you can introduce a different shorter name and use the new one instead

It's useful :
* To shorten long generic types
* For instance, it's often tempting to shrink collection types:

```kotlin
    typealias NodeSet = Set<Network.Node>    
    typealias FileTable<K> = MutableMap<K, MutableList<File>>
```
* Different aliases for function types:

```kotlin
    typealias MyHandler = (Int, String, Any) -> Unit
    typealias Predicate<T> = (T) -> Boolean
```
* Have new names for inner and nested classes:

```kotlin
    class A {
        inner class Inner
    }
    class B {
        inner class Inner
    }
    
    typealias AInner = A.Inner
    typealias BInner = B.Inner
````

### [Nothing type](https://play.kotlinlang.org/koans/Introduction/Nothing%20type/Task.kt)
* This class has no instance, and it is used to represent a value which never exists
* This class is also used to represent a return type from a method that will never return
* If we use null to initialize a value of an inferred type and there is no other information that can be used to determine a more specific type, the compiler considers it as Nothing? type.

```kotlin
val variable = null

//compiler will read this as
// val variable : Nothing? = null

val list = listOf(null)

//compiler will read this as
//val list : List<Nothing?> = listOf(null)
```

* Nothing has no type and can also be used to mark code locations that can never be reached. 
* Let’s say we have a method which throws an exception. The return type of that method would be Nothing type.

```kotlin
 fun throwException(message: String): Nothing {
    throw IllegalArgumentException(message)
 }
```
throw is an expression in Kotlin and can be used as a part of Elvis operator also.
The above code can be replaced with : 

```kotlin
val personName = person.name ?: throwException("Name required")
```



