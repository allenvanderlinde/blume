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

**Notes:**
1. The first use of `BlumeColor` above takes a single argument. This is an integer value from 0 to 255 which represents a color from the standard 8-bit color palette map: (https://en.wikipedia.org/wiki/8-bit_color).
2. The second use of `BlumeColor` takes three arguments, each representing the amount of color to use for each Red, Green, and Blue color channel. This produces a 24-bit "true color" combination.

**More information:**
You can also instantiate `BlumeColor` objects elsewhere and use their instances like any identifier.
```java
BlumeColor whiteFg = null;
BlumeColor hotPinkBg = null;

whiteFg = new BlumeColor( 255 );
hotPinkBg = new BlumeColor( 241, 66, 244 );

Blume.println( "Hello, again!", whiteFg, hotPinkBg, BlumeText.Attribute.Bold );
```

### Semantics
Semantically, _foreground_ `BlumeColor` arguments <u>always</u> preceed _background_ `BlumeColor` arguments, followed by a series of modifications (e.g., bold, inverse, underline). `BlumeText.Attribute` and `BlumeText.Background` are nested classes whose members are used as modifications. `BlumeText` also has 8 default "system" colors which are also technically considered modifications (e.g., cyan, red, yellow, magenta).

Modifications are a variable argument (varargs) array of type `String` which always come as the final arguments in any calls to `Blume.print()` or `Blume.println()`. Modifications can be placed in any order.

This means you won't be able to use an 8-bit or 24-bit background color with one of the 8 default terminal foreground colors.

```java
// This will not work!
Blume.println( "Blume is \"flower\" in German", BlumeText.Cyan, new BlumeColor( 255, 155, 231 ) );
```

```java
// This is ok
Blume.println( "Blume is \"flower\" in German", BlumeText.Yellow, BlumeText.Background.Red );
```
**Note:** Since modifications can be written in any order, this will also work.
```java
// This works too!
Blume.println( "Blume is \"flower\" in German", BlumeText.Background.Red, BlumeText.Yellow );
```

You can create a new `BlumeColor` object, however, with the necessary color combination to produce cyan and use that instead.
```java
Blume.println( "Blume is \"flower\" in German", new BlumeColor( 0, 255, 255 ), new BlumeColor( 255, 155, 231 ) );
```

#### Example 2
Print a yellow, bold word in a string with some text over a purple background.

```java
Blume.print( "This " );
Blume.print( "word", BlumeText.Yellow, BlumeText.Attribute.Bold );
Blume.print( " is yellow and bold " );
Blume.println( "and this has a purple background!", new BlumeColor( 255 ), new BlumeColor( 153, 0, 204 ) );
```
Output:

![Example 2](examples/example2.png)

## More Information
You can find the Javadocs for this project [here](https://github.com/allenvanderlinde/blume/tree/master/doc).
