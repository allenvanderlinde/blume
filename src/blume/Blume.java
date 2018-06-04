/**
 * Project: Blume
 * 
 * @brief	Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * 			foreground and background color options and various text attributes.
 * 
 * @file	Blume.java
 * @version	1.0.0-beta
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
	 * 
	 * As long as the escape code, or codes, are properly delimited, the text
	 * should still be displayed correctly. This is why the various method
	 * parameters below are of the format "mod"{n}.
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
			string.append( mod ).append( BlumeText._DELIM_ );
		}
		
		// Remove the trailing delimiter character appended from the previous loop logic
		string.setLength( string.length() - 1 );
		
		// Close the string and prepare for printing to the terminal
		string.append( BlumeText._TERMINATOR_ ).append( text ).append( BlumeText._RESET_ );
		
		System.out.print( string );
	}
	
	public static void print( String text, BlumeText color ) {
		System.out.println( new StringBuilder( text ) );
	}
}
