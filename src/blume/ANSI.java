/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various display attributes.
 * 
 * @file	ANSI.java
 * @version	1.1.0
 * 
 * @author	Allen Vanderlinde
 * @date	6/27/2018
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
 * Class for ANSI-specific prefixes and modifiers. These are usable
 * by Unix-based (Linux) operating systems.
 */
public class ANSI {
	/**
	 * CSI (Control Sequence Introducer) sequence
	 * which should begin every string intended
	 * to have colored, or in other ways altered,
	 * text.
	 */
	protected static final String _PREFIX_ = ((char) 27 + "[");
	/**
	 * Character which denotes a separation between color
	 * and any other display attributes or options.
	 */
	protected static final char _DELIM_ = ';';
	/**
	 * ANSI escape sequence to reset the terminal
	 * text color and text effects.
	 */
	protected static final String _RESET_ = (_PREFIX_ + "0m");
	/**
	 * Character which denotes the end of a color or display
	 * attribute escape sequence.
	 */
	protected static final char _TERMINATOR_ = 'm';
	/**
	 * ANSI escape sequence to signal use of the system's
	 * 8-bit color palette (256 colors).
	 */
	protected static final String _8_BIT_FOREGROUND_ = "38;5;";
	/**
	 * ANSI escape sequence to signal use of the system's
	 * 8-bit color palette (256 colors) for background color.
	 */
	protected static final String _8_BIT_BACKGROUND_ = "48;5;";
	/**
	 * ANSI escape sequence to signal use of 24-bit true
	 * color.
	 */
	protected static final String _24_BIT_FOREGROUND_ = "38;2;";
	/**
	 * ANSI escape sequence to signal use of 24-bit true
	 * color for background color.
	 */
	protected static final String _24_BIT_BACKGROUND_ = "48;2;";
}
