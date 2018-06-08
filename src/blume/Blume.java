/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various text attributes.
 * 
 * @file	Blume.java
 * @version	1.0.060818
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

public class Blume {
	/*
	 * Note: It is important to note that the order of ANSI escape sequences
	 * for color and display attributes are not significant, and therefore
	 * a display attribute (e.g., bold, italic) can be inserted in the string
	 * before a color option, and vice versa.
	 */
	
	/**
	 * Prints a string with various modifiers for text color and
	 * display attributes.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param mods
	 */
	public static void print( String text, String... mods ) {
		// If there are no modification arguments, print like normal and return
		if ( mods.length == 0 ) {
			System.out.print( text );
			
			return;
		}
		
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// Build the ANSI sequences from the method arguments into an escaped string
		for ( String mod : mods ) {
			string.append( mod )
				.append( BlumeText._DELIM_ );
		}
		
		// Remove the trailing delimiter character appended from the previous loop logic
		string.setLength( string.length() - 1 );
		
		// Close the string and prepare for printing to the terminal
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.print( string );
	}
	
	/**
	 * Prints a string with an 8-bit or 24-bit color option represented
	 * by a {@link blume.BlumeColor} object. Background color palette options
	 * and other display attributes are also compatible mods.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param color
	 * @param mods
	 */
	public static void print( String text, BlumeColor color, String... mods ) {
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// If mods have been included, append them to the ANSI escape sequence
		if ( mods.length > 0 ) {
			for ( String mod : mods ) {
				string.append( mod )
					.append( BlumeText._DELIM_ );
			}
		}
		
		/*
		 * Close the string with the appropriate color option and value
		 * and prepare for printing to the terminal.
		 */
		if ( color.getIs8Bit() ) { // 8-bit color
			string.append( BlumeText._8_BIT_FOREGROUND_ )
				.append( color.getColorValue() );
		} else if ( color.getIs24Bit() ) { // 24-bit color
			string.append( BlumeText._24_BIT_FOREGROUND_ )
				.append( color.getRed() )
				.append( BlumeText._DELIM_ )
				.append( color.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( color.getBlue() );
		}
		
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.print( string );
	}
	
	/**
	 * Prints a string with 8-bit or 24-bit foreground and background
	 * color options represented by {@link blume.BlumeColor} objects.
	 * Display attributes (e.g., bold, inverse) can still be used
	 * and are compatible mods.
	 * 
	 * No new line or LF is produced.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param foregroundColor
	 * @param backgroundColor
	 * @param mods
	 */
	public static void print( String text, BlumeColor foregroundColor, BlumeColor backgroundColor, String... mods ) {
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// If mods have been included, append them to the ANSI escape sequence
		if ( mods.length > 0 ) {
			for ( String mod : mods ) {
				string.append( mod )
					.append( BlumeText._DELIM_ );
			}
		}
		
		/*
		 * For the foreground color, close the string with the
		 * appropriate color option and value and prepare
		 * for printing to the terminal.
		 */
		if ( foregroundColor.getIs8Bit() ) { // 8-bit color
			string.append( BlumeText._8_BIT_FOREGROUND_ )
				.append( foregroundColor.getColorValue() );
		} else if ( foregroundColor.getIs24Bit() ) { // 24-bit color
			string.append( BlumeText._24_BIT_FOREGROUND_ )
				.append( foregroundColor.getRed() )
				.append( BlumeText._DELIM_ )
				.append( foregroundColor.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( foregroundColor.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( BlumeText._DELIM_ );
		
		/*
		 * For the background color, close the string with the
		 * appropriate color option and value and prepare
		 * for printing to the terminal.
		 */
		if ( backgroundColor.getIs8Bit() ) {
			string.append( BlumeText._8_BIT_BACKGROUND_ )
				.append( backgroundColor.getColorValue() );
		} else if ( backgroundColor.getIs24Bit() ) {
			string.append( BlumeText._24_BIT_BACKGROUND_ )
				.append( backgroundColor.getRed() )
				.append( BlumeText._DELIM_ )
				.append( backgroundColor.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( backgroundColor.getBlue() );
		}
		
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.print( string );
	}
	
	/**
	 * Prints a string with various modifiers for text color and
	 * display attributes.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param mods
	 */
	public static void println( String text, String... mods ) {
		// If there are no modification arguments, print like normal and return
		if ( mods.length == 0 ) {
			System.out.println( text );
			
			return;
		}
		
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// Build the ANSI sequences from the method arguments into an escaped string
		for ( String mod : mods ) {
			string.append( mod )
				.append( BlumeText._DELIM_ );
		}
		
		// Remove the trailing delimiter character appended from the previous loop logic
		string.setLength( string.length() - 1 );
		
		// Close the string and prepare for printing to the terminal
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.println( string );
	}
	
	/**
	 * Prints a string with an 8-bit or 24-bit color option represented
	 * by a {@link blume.BlumeColor} object. Background color palette options
	 * and other display attributes are also compatible mods.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param color
	 * @param mods
	 */
	public static void println( String text, BlumeColor color, String... mods ) {
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// If mods have been included, append them to the ANSI escape sequence
		if ( mods.length > 0 ) {
			for ( String mod : mods ) {
				string.append( mod )
					.append( BlumeText._DELIM_ );
			}
		}
		
		/*
		 * Close the string with the appropriate color option and value
		 * and prepare for printing to the terminal.
		 */
		if ( color.getIs8Bit() ) { // 8-bit color
			string.append( BlumeText._8_BIT_FOREGROUND_ )
				.append( color.getColorValue() );
		} else if ( color.getIs24Bit() ) { // 24-bit color
			string.append( BlumeText._24_BIT_FOREGROUND_ )
				.append( color.getRed() )
				.append( BlumeText._DELIM_ )
				.append( color.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( color.getBlue() );
		}
		
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.println( string );
	}
	
	/**
	 * Prints a string with 8-bit or 24-bit foreground and background
	 * color options represented by {@link blume.BlumeColor} objects.
	 * Display attributes (e.g., bold, inverse) can still be used
	 * and are compatible mods.
	 * 
	 * This method produces a new line or LF.
	 * 
	 * Compatible modifiers can be found in the {@link blume.BlumeText} class
	 * and are essentially ANSI escape sequences.
	 * 
	 * @param text
	 * @param foregroundColor
	 * @param backgroundColor
	 * @param mods
	 */
	public static void println( String text, BlumeColor foregroundColor, BlumeColor backgroundColor, String... mods ) {
		StringBuilder string = new StringBuilder( BlumeText._ANSI_ );
		
		// If mods have been included, append them to the ANSI escape sequence
		if ( mods.length > 0 ) {
			for ( String mod : mods ) {
				string.append( mod )
					.append( BlumeText._DELIM_ );
			}
		}
		
		/*
		 * For the foreground color, close the string with the
		 * appropriate color option and value and prepare
		 * for printing to the terminal.
		 */
		if ( foregroundColor.getIs8Bit() ) { // 8-bit color
			string.append( BlumeText._8_BIT_FOREGROUND_ )
				.append( foregroundColor.getColorValue() );
		} else if ( foregroundColor.getIs24Bit() ) { // 24-bit color
			string.append( BlumeText._24_BIT_FOREGROUND_ )
				.append( foregroundColor.getRed() )
				.append( BlumeText._DELIM_ )
				.append( foregroundColor.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( foregroundColor.getBlue() );
		}
		
		// Append another delimiter before appending the background color
		string.append( BlumeText._DELIM_ );
		
		/*
		 * For the background color, close the string with the
		 * appropriate color option and value and prepare
		 * for printing to the terminal.
		 */
		if ( backgroundColor.getIs8Bit() ) {
			string.append( BlumeText._8_BIT_BACKGROUND_ )
				.append( backgroundColor.getColorValue() );
		} else if ( backgroundColor.getIs24Bit() ) {
			string.append( BlumeText._24_BIT_BACKGROUND_ )
				.append( backgroundColor.getRed() )
				.append( BlumeText._DELIM_ )
				.append( backgroundColor.getGreen() )
				.append( BlumeText._DELIM_ )
				.append( backgroundColor.getBlue() );
		}
		
		string.append( BlumeText._TERMINATOR_ )
			.append( text )
			.append( BlumeText._RESET_ );
		
		System.out.println( string );
	}
}
