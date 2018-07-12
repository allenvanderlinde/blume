/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various text attributes.
 * 
 * @file	Blume.java
 * @version	1.1.0
 * 
 * @author	Allen Vanderlinde
 * @date	5/29/2018
 * 
 * @copyright
 * 
 * MIT License
 * 
 * Copyright (c) 2018 Allen Vanderlinde
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package blume;

/**
 * Blume implementation methods.
 */
public class Blume {
	/*
	 * Note: It is important to note that the order of ANSI escape sequences
	 * for color and display attributes are not significant, and therefore
	 * a display attribute (e.g., bold, italic) can be inserted in the string
	 * before a color option, and vice versa.
	 */
	
	/**
	 * Displays a 16x16 table of the 8-bit color palette values
	 * available for ANSI-supported terminals.
	 */
	public static void display8BitColors() {
		// If Win32 console, exit method
		if ( BlumeText.getIsWin32() ) {
			try {
				throw new OSIncompatibilityException();
			} catch ( OSIncompatibilityException e ) {
				e.printStackTrace( "This method is not compatibile with Win32 consoles." );
				
				return;
			}
		}
		
		BlumeColor white = new BlumeColor( 255 );
		BlumeColor bg = new BlumeColor( 0 );
		
		for ( int i = 0; i < 256; i++ ) {
			if ( i % 16 == 0 ) {
				System.out.println();
			}
			
			bg.setColorValue( i );
			
			Blume.print( String.format( " %03d ",  i ), white, bg );
		}
		
		System.out.println();
	}
	
	/**
	 * Prints out three vertical blocks of the possible shades of red, green, and blue
	 * using ANSI.
	 */
	public static void displayANSIGradients() {
		// If Win32 console, exit method
		if ( BlumeText.getIsWin32() ) {
			try {
				throw new OSIncompatibilityException();
			} catch ( OSIncompatibilityException e ) {
				e.printStackTrace( "This method is not compatibile with Win32 consoles." );
				
				return;
			}
		}
		
		BlumeColor nothing = new BlumeColor( 0 );
		
		// Red
		BlumeColor red = new BlumeColor( 255, 0, 0 );
		for ( int r = 0; r <= 255; r++ ) {
			if ( r % 16 == 0 ) {
				System.out.println();
			} else {
				red.setRed( r );
				
				print( "  ", nothing, red );
			}
		}
		
		// Green
		BlumeColor green = new BlumeColor( 0, 255, 0 );
		for ( int g = 0; g <= 255; g++ ) {
			if ( g % 16 == 0 ) {
				System.out.println();
			} else { 
				green.setGreen( g );
				
				print( "  ", nothing, green );
			}
		}
		
		// Blue
		BlumeColor blue = new BlumeColor( 0, 0, 255 );
		for ( int b = 0; b <= 255; b++ ) {
			if ( b % 16 == 0 ) {
				System.out.println();
			} else {
				blue.setBlue( b );
				
				print( "  ", nothing, blue );
			}
		}
	}
	
	/**
	 * Prints out a semi-graphical display of the available colors from the
	 * operating system's standard color palette.
	 */
	public static void displayStandardColorTable() {
		if ( BlumeText.getIsANSI() ) { // ANSI
			int fgStart = Integer.parseInt( BlumeText.Black );
			int fgEnd = Integer.parseInt( BlumeText.White );
			int bgStart = Integer.parseInt( BlumeText.Background.Black );
			int bgEnd = Integer.parseInt( BlumeText.Background.White );
			
			for ( int i = fgStart; i <= fgEnd; i++ ) {
				for ( int j = bgStart; j <= bgEnd; j++ ) {
					if ( j % 8 == 0 ) {
						System.out.println();
					} else {
						print( "abc   ", String.valueOf( i ), String.valueOf( j ) );
					}
				}
			}
		} else if ( BlumeText.getIsWin32() ) { // Win32
			try {
				Win32.initializeConsole();
			} catch ( Exception e ) {
				e.printStackTrace();
				
				return;
			}
			
			for ( short hex = 0; hex <= 256; hex += 1 ) {				
				printFromHex( "abc   ", hex );
				
				if ( hex % 15 == 0 ) {
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * Prints a string with various modifiers for text color and
	 * display attributes.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class. 
	 * Different modifiers are used between Unix-based and Win32 consoles.	 *
	 * 
	 * @param text
	 * @param mods
	 */
	public static <T> void print( T text, String... mods ) {
		// If there are no modification arguments, print like normal and return
		if ( mods.length == 0 ) {
			System.out.print( text );
			
			return;
		}
		
		/*
		 * Print text with color options and formatting depending upon the
		 * operating system.
		 */
		if ( BlumeText.getIsANSI() ) {
			StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
			
			// Build the ANSI color sequence from the method arguments into a de		// ANSIliminated string
			for ( String mod : mods ) {
				string.append( mod )
					.append( ANSI._DELIM_ );
			}
			
			// Remove the trailing delimiter character appended from the previous loop logic
			string.setLength( string.length() - 1 );
			// ANSI
			// Close the string and prepare for printing to the terminal
			string.append( ANSI._TERMINATOR_ )
				.append( text )
				.append( ANSI._RESET_ );
			
			System.out.print( string );
		} else if ( BlumeText.getIsWin32() ) {			
			try {				
				Win32.initializeConsole();
			} catch ( Exception e ) {
				e.printStackTrace();
				
				return;
			}
			
			Win32.setColor( mods );
			
			System.out.print( text );
			
			Win32.resetConsole();
		} else {
			System.out.print( text );
		}
	}
	
	/**
	 * Prints a string with a foreground color described by a
	 * BlumeColor object for 8- and 24-bit coloring.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Note: This method is only compatible with consoles which support
	 * ANSI escape sequence coloring.
	 * 
	 * @param text
	 * @param fg
	 */
	public static <T> void print( T text, BlumeColor fg ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.print( string );
	}
	
	/**
	 * Prints a string with foreground and background colors described by
	 * BlumeColor objects for 8- and 24-bit coloring.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Note: This method is only compatible with consoles whichs upport
	 * ANSI escape sequence coloring.
	 * 
	 * @param text
	 * @param fg
	 * @param bg
	 */
	public static <T> void print( T text, BlumeColor fg, BlumeColor bg ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( ANSI._DELIM_ );
		
		// Set 8- or 24-bit background color
		if ( bg.getIs8Bit() ) {
			string.append( ANSI._8_BIT_BACKGROUND_ )
				.append( bg.getColorValue() );
		} else if ( bg.getIs24Bit() ) {
			string.append( ANSI._24_BIT_BACKGROUND_ )
				.append( bg.getRed() )
				.append( ANSI._DELIM_ )
				.append( bg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( bg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
		
		System.out.print( string );
	}
	
	/**
	 * Prints a string with a foreground color described by a
	 * BlumeColor object for 8- and 24-bit coloring. Certain ANSI
	 * display attributes can also be used (e.g., bold, italic).
	 * 
	 * No new line or LF is produced.
	 * 
	 * @param text
	 * @param fg
	 * @param attrs
	 */
	public static <T> void print( T text, BlumeColor fg, String... attrs ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Append ANSI display options to the string
		if ( attrs.length > 0 ) {
			for ( String attr : attrs ) {
				string.append( attr )
					.append( ANSI._DELIM_ );
			}
		}
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.print( string );
	}
	
	/**
	 * Prints a string with a foreground and background color
	 * described by BlumeColor objects for 8- and 24-bit coloring.
	 * Certain ANSI display attributes can also be used
	 * (e.g., bold, italic).
	 * 
	 * No new line or LF is produced.
	 * 
	 * @param text
	 * @param fg
	 * @param bg
	 * @param attrs
	 */
	public static <T> void print( T text, BlumeColor fg, BlumeColor bg, String... attrs ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Append ANSI display options to the string
		if ( attrs.length > 0 ) {
			for ( String attr : attrs ) {
				string.append( attr )
					.append( ANSI._DELIM_ );
			}
		}
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( ANSI._DELIM_ );
		
		// Set 8- or 24-bit background color
		if ( bg.getIs8Bit() ) {
			string.append( ANSI._8_BIT_BACKGROUND_ )
				.append( bg.getColorValue() );
		} else if ( bg.getIs24Bit() ) {
			string.append( ANSI._24_BIT_BACKGROUND_ )
				.append( bg.getRed() )
				.append( ANSI._DELIM_ )
				.append( bg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( bg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.print( string );
	}
	
	/**
	 * Prints a string with various hexadecimal modifiers for text
	 * color for Win32-based consoles.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Note: This is used primarily as an internal method for color table
	 * display and is not a cross-platform method.
	 * 
	 * @param text
	 * @param hexes
	 */
	public static <T> void printFromHex( T text, short... hexes ) {
		if ( BlumeText.getIsANSI() ) {
			try {
				throw new OSIncompatibilityException();
			} catch ( OSIncompatibilityException e ) {
				e.printStackTrace( "This method is not compatible with non-Windows operating systems." );
				
				return;
			}
		}
		try {				
			Win32.initializeConsole();
		} catch ( Exception e ) {
			e.printStackTrace();
			
			return;
		}
		
		Win32.setColor( hexes );
		
		System.out.print( text );
		
		Win32.resetConsole();
	}	
	
	/**
	 * Prints a string with various modifiers for text color and
	 * display attributes.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class. 
	 * Different modifiers are used between Unix-based and Win32 consoles.	 *
	 * 
	 * @param text
	 * @param mods
	 */
	public static <T> void println( T text, String... mods ) {
		// If there are no modification arguments, print like normal and return
		if ( mods.length == 0 ) {
			System.out.println( text );
			
			return;
		}
		
		/*
		 * Print text with color options and formatting depending upon the
		 * operating system.
		 */
		if ( BlumeText.getIsANSI() ) {
			StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
			
			// Build the ANSI color sequence from the method arguments into a deliminated string
			for ( String mod : mods ) {
				string.append( mod )
					.append( ANSI._DELIM_ );
			}
			
			// Remove the trailing delimiter character appended from the previous loop logic
			string.setLength( string.length() - 1 );
			
			// Close the string and prepare for printing to the terminal
			string.append( ANSI._TERMINATOR_ )
				.append( text )
				.append( ANSI._RESET_ );
			
			System.out.println( string );
		} else if ( BlumeText.getIsWin32() ) {			
			try {				
				Win32.initializeConsole();
			} catch ( Exception e ) {
				e.printStackTrace();
				
				return;
			}
			
			Win32.setColor( mods );
			
			System.out.println( text );
			
			Win32.resetConsole();
		} else {
			System.out.println( text );
		}
	}
	
	/**
	 * Prints a string with a foreground color described by a
	 * BlumeColor object for 8- and 24-bit coloring.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Note: This method is only compatible with consoles which support
	 * ANSI escape sequence coloring.
	 * 
	 * @param text
	 * @param fg
	 */
	public static <T> void println( T text, BlumeColor fg ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.println( string );
	}
	
	/**
	 * Prints a string with foreground and background colors described by
	 * BlumeColor objects for 8- and 24-bit coloring.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Note: This method is only compatible with consoles whichs upport
	 * ANSI escape sequence coloring.
	 * 
	 * @param text
	 * @param fg
	 * @param bg
	 */
	public static <T> void println( T text, BlumeColor fg, BlumeColor bg ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( ANSI._DELIM_ );
		
		// Set 8- or 24-bit background color
		if ( bg.getIs8Bit() ) {
			string.append( ANSI._8_BIT_BACKGROUND_ )
				.append( bg.getColorValue() );
		} else if ( bg.getIs24Bit() ) {
			string.append( ANSI._24_BIT_BACKGROUND_ )
				.append( bg.getRed() )
				.append( ANSI._DELIM_ )
				.append( bg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( bg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
		
		System.out.println( string );
	}
	
	/**
	 * Prints a string with a foreground color described by a
	 * BlumeColor object for 8- and 24-bit coloring. Certain ANSI
	 * display attributes can also be used (e.g., bold, italic).
	 * 
	 * This method produces a new line or LF.
	 * 
	 * @param text
	 * @param fg
	 * @param attrs
	 */
	public static <T> void println( T text, BlumeColor fg, String... attrs ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Append ANSI display options to the string
		if ( attrs.length > 0 ) {
			for ( String attr : attrs ) {
				string.append( attr )
					.append( ANSI._DELIM_ );
			}
		}
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.println( string );
	}
	
	/**
	 * Prints a string with a foreground and background color
	 * described by BlumeColor objects for 8- and 24-bit coloring.
	 * Certain ANSI display attributes can also be used
	 * (e.g., bold, italic).
	 * 
	 * This method produces a new line or LF.
	 * 
	 * @param text
	 * @param fg
	 * @param bg
	 * @param attrs
	 */
	public static <T> void println( T text, BlumeColor fg, BlumeColor bg, String... attrs ) {
		// Test if method is being executed from a Windows system
		if ( BlumeText.getIsWin32() ) {
			try {				
				throw new OSIncompatibilityException();
			} catch (OSIncompatibilityException e) {				
				e.printStackTrace( "BlumeColor is not compatible with Win32 consoles." );
				
				return;
			}
		}
		
		StringBuilder string = new StringBuilder( ANSI._PREFIX_ );
		
		// Append ANSI display options to the string
		if ( attrs.length > 0 ) {
			for ( String attr : attrs ) {
				string.append( attr )
					.append( ANSI._DELIM_ );
			}
		}
		
		// Set 8- or 24-bit foreground color
		if ( fg.getIs8Bit() ) { // 8-bit color
			string.append( ANSI._8_BIT_FOREGROUND_ )
				.append( fg.getColorValue() );
		} else if ( fg.getIs24Bit() ) { // 24-bit color
			string.append( ANSI._24_BIT_FOREGROUND_ )
				.append( fg.getRed() )
				.append( ANSI._DELIM_ )
				.append( fg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( fg.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( ANSI._DELIM_ );
		
		// Set 8- or 24-bit background color
		if ( bg.getIs8Bit() ) {
			string.append( ANSI._8_BIT_BACKGROUND_ )
				.append( bg.getColorValue() );
		} else if ( bg.getIs24Bit() ) {
			string.append( ANSI._24_BIT_BACKGROUND_ )
				.append( bg.getRed() )
				.append( ANSI._DELIM_ )
				.append( bg.getGreen() )
				.append( ANSI._DELIM_ )
				.append( bg.getBlue() );
		}
		
		// Terminate the escape sequence and print
		string.append( ANSI._TERMINATOR_ )
			.append( text )
			.append( ANSI._RESET_ );
	
		System.out.println( string );
	}
}