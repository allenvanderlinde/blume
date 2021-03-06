/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various display attributes.
 * 
 * @file	OSIncompatibilityException.java
 * @version	1.1.0
 * 
 * @author	Allen Vanderlinde
 * @date	6/28/2018
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
 * Blume exception thrown when a Blume operation is performed
 * on an incompatible operating system.
 */
@SuppressWarnings("serial")
public class OSIncompatibilityException extends Exception {
	/**
	 * Prints a basic stack trace with no special message.
	 */
	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	
	/**
	 * Prints a stack trace with a special message first about incompatibility.
	 * 
	 * @param msg
	 */
	public void printStackTrace( String msg ) {
		StackTraceElement[] trace = super.getStackTrace();
		
		System.out.println( this.toString() + ": " + msg );
		
		for ( int i = 0; i < trace.length; i++ ) {
			System.out.println( "\t" + trace[i] );
		}
	}
}
