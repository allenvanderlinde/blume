# Introduction
Blume (pronounced "bloom") is an experiment and project of mine written in Java. The goal was to create a simple API for printing 8-bit and 24-bit color characters to the terminal using ANSI escape codes with little to no hassle for the developer.

Blume is so far functional in Unix-like environments (e.g., Linux) and there are plans to expand its code to work in Windows-based environments as well. No testing has been done so far on macOS.

## Usage and Examples
To use Blume, simply add the JAR as one of your project's referenced libraries and include it in your project's build path.

Then import it:
```java
import blume.*;
```

If for whatever reason you'd like to only use individual classes of Blume (e.g., `BlumeColor`), you can import them like so:
```java
import blume.Blume;
import blume.BlumeColor;
import blume.BlumeText;
```

`BlumeColor` might be useful to you as a _very_ basic class that holds RGB color data.

**Note:** `Blume` is a static class that can be used anywhere `System.out.print()` and `System.out.println()` can.

### Examples
For the following examples, we're assuming the following:
```java
import blume.*;

public class Application {
	public static void main( String[] args ) {
		// Example code would go here
	}
}
```

#### Example 1
Print bold white text with a [hot pink background](https://www.w3schools.com/colors/colors_picker.asp?color=f142f4).
```java
Blume.println( "Hello, world!", new BlumeColor( 255 ), new BlumeColor( 241, 66, 244 ), BlumeText.Attribute.Bold );
```
Output:

![Example 1](examples/example1.png)

You can also instantiate `BlumeColor` objects elsewhere and use their instances like any identifier.
```java
```
