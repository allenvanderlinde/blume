# Introduction
Blume (pronounced "bloom" in English; German for <i>flower</i>) is an experiment and project of mine written in Java. The goal was to create a simple API for printing 8-bit and 24-bit color characters to the terminal using ANSI escape codes with little to no hassle for the developer.

With the release of version 1.1.0, Blume now supports Win32 consoles! Unfortunately, due to the native color limitations of the Win32 console, there is no 8- or 24-bit true color capability whereby Blume can only utilize the Win32 basic color palette. (<i>But hey, that's not bad!</i>)

Java Native Access was leveraged to gain access to the Windows Kernel32 library and is an incredible API. Visit JNA [here](https://github.com/java-native-access/jna). The JNA libraries are used and included in Blume release JARs (see [here](#changes)).

No testing has been done so far on macOS.

## <a name="changes"></a>Changes in v1.1.0
1. Win32 console coloring now supported.
2. New native exception for operations performed on incompatible operating systems `OSIncompatibilityException`.
3. Added demonstration methods to show color capabilities: `displayStandardColorTable()`, `displayANSIGradients()`, and `display8BitColors()`.
4. Java Native Access libraries with minimum version 4.5.1 are required and can be downloaded below from the JNA GitHub page for further exploration. They are already included in the Blume release JAR for v1.1.0 and shouldn't need to be manually included in your project's build path.
	* [JNA](http://repo1.maven.org/maven2/net/java/dev/jna/jna/4.5.1/jna-4.5.1.jar)
	* [JNA Platform](http://repo1.maven.org/maven2/net/java/dev/jna/jna-platform/4.5.1/jna-platform-4.5.1.jar)

## Possible Improvements
1. Smarter methods that handle incompatible colors with ANSI operations (e.g., <i>Aqua</i>, <i>LightBlue</i>, etc.).

## Usage and Examples
To use Blume, simply add the JAR as one of your project's referenced libraries and include it in your project's build path.

Then import like so:
```java
import blume.*;
```

Blume's full Javadoc documentation can be found [here](https://github.com/allenvanderlinde/blume/tree/master/doc) on GitHub. This readme will cover a few basics of usage as opposed to being a comprehensive technical overview.

**Important Note:** `Blume` is essentially a static class that can be used anywhere `System.out.print()` and `System.out.println()` would be found.

**Important Note:** Currently ANSI coloring in Blume will allow the use of Win32 console colors and will not warn you of this. You will most likely run into unexpected behavior or find that your chosen colors are <u>not</u> printed.

### Simple Examples and Notes
#### Example 1
```java
import blume.*;

public class Application {
	public static void main( String[] args ) {
		Blume.println( "Hello, world!", BlumeText.Green, BlumeText.Background.White );
	}
}
```
The above example uses what are called <i>modifiers</i> (`BlumeText.Green`, `BlumeText.Background.White`). Most modifiers are cross-platform and compatible with Unix/Linux terminals and Windows command prompts, although some colors are only able to be used by one or the other operating system (e.g., <i>Aqua</i> is Win32 only). 

Modifications are a variable argument (varargs) array of type `String` which always come as the final argument in any calls to `Blume.print()` or `Blume.println()`. **Note:** Modifications can be placed in any order since Blume distinguishes between foreground and background colors.

#### 8- and 24-bit Coloring
**Note:** Standard color palette options <u>cannot</u> be used in conjunction with `BlumeColor` objects in order to maintain cross-platform support. `BlumeColor` 8- and 24-bit objects are only able to be used by terminals which have ANSI support (i.e., in Linux).

This means you won't be able to use an 8-bit or 24-bit background color with one of the 8 default terminal foreground colors.

```java
// This will not work!
Blume.println( "Blume is \"flower\" in German",
	BlumeText.Cyan,
	new BlumeColor( 255, 155, 231 ) );
```
When using `BlumeColor` objects, <i>foreground</i> colors should always preceed <i>background</i> colors. Display attributes (ANSI only) like bold and underlined text are considered <i>modifiers</i> and will follow any `BlumeColor` arguments.

#### Example 2
Print bold white text with a [hot pink background](https://www.w3schools.com/colors/colors_picker.asp?color=f142f4).
```java
Blume.println( "Hello, world!",
	new BlumeColor( 255 ),
	new BlumeColor( 241, 66, 244 ),
	BlumeText.Attribute.Bold );
```
Output:

![Example](examples/example.png)

**Notes:**
1. The first use of `BlumeColor` above takes a single argument. This is an integer value from 0 to 255 which represents a color from the standard 8-bit color palette map: (https://en.wikipedia.org/wiki/8-bit_color).
2. The second use of `BlumeColor` takes three arguments, each representing the amount of color to use for each Red, Green, and Blue color channel. This produces a 24-bit "true color" combination.

You can also instantiate `BlumeColor` objects elsewhere and use their instances like any identifier.
```java
BlumeColor whiteFg = null;
BlumeColor hotPinkBg = null;

whiteFg = new BlumeColor( 255 );
hotPinkBg = new BlumeColor( 241, 66, 244 );

Blume.println( "Hello, again!", whiteFg, hotPinkBg, BlumeText.Attribute.Bold );
```

Individual red, green, and blue color values can be set for 24-bit `BlumeColor` objects and an 8-bit color value from the operating system's basic 256-color palette can be as well.

```java
BlumeColor foreground = new BlumeColor( 255 );
BlumeColor hotPink = new BlumeColor( 0, 66, 244 );

foreground.setColorValue( 144 );
hotPink.setRed( 241 );

Blume.println( "Hello, again!", foreground, hotPink );
```

## More Information
You can find the Javadocs for this project [here](https://github.com/allenvanderlinde/blume/tree/master/doc).
