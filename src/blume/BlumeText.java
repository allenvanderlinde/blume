/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various display attributes.
 * 
 * @file	BlumeText.java
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

public class BlumeText {
	/**
	 * CSI (Control Sequence Introducer) sequence
	 * which should begin every string intended
	 * to have colored, or in other ways altered,
	 * text.
	 */
	protected static final String _ANSI_ = ((char) 27 + "[");
	/**
	 * Character which denotes a separation between color
	 * and any other display attributes or options.
	 */
	protected static final char _DELIM_ = ';';
	/**
	 * ANSI escape sequence to reset the terminal
	 * text color and text effects.
	 */
	protected static final String _RESET_ = (_ANSI_ + "0m");
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
	
	/*
	 * Standard terminal 8-color palette options
	 * (foreground).
	 */
	/**
	 * Terminal foreground color black.
	 */
	public static final String Black = "30";
	/**
	 * Terminal foreground color red.
	 */
	public static final String Red = "31";
	/**
	 * Terminal foreground color green.
	 */
	public static final String Green = "32";
	/**
	 * Terminal foreground color yellow.
	 */
	public static final String Yellow = "33";
	/**
	 * Terminal foreground color blue.
	 */
	public static final String Blue = "34";
	/**
	 * Terminal foreground color magenta.
	 */
	public static final String Magenta = "35";
	/**
	 * Terminal foreground color cyan.
	 */
	public static final String Cyan = "36";
	/**
	 * Terminal foreground color white.
	 */
	public static final String White = "37";
	/**
	 * Terminal default foreground color.
	 */
	public static final String Default = "39";
	
	/*
	 * Standard terminal SGR (Select Graphic Rendition) display
	 * attributes.
	 */
	public static class Attribute {
		/**
		 * Bold display attribute for text.
		 */
		public static final String Bold = "1";
		/**
		 * Underline display attribute for text.
		 */
		public static final String Underline = "4";
		/**
		 * Inverse display attribute for text
		 * (switches foreground and background colors).
		 */
		public static final String Inverse = "7";
	}
	
	/*
	 * Standard terminal 8-color palette options
	 * (background).
	 */
	public static class Background {
		/**
		 * Terminal background color black.
		 */
		public static final String Black = "40";
		/**
		 * Terminal background color red.
		 */
		public static final String Red = "41";
		/**
		 * Terminal background color green.
		 */
		public static final String Green = "42";
		/**
		 * Terminal background color yellow.
		 */
		public static final String Yellow = "43";
		/**
		 * Terminal background color blue.
		 */
		public static final String Blue = "44";
		/**
		 * Terminal background color magenta.
		 */
		public static final String Magenta = "45";
		/**
		 * Terminal background color cyan.
		 */
		public static final String Cyan = "46";
		/**
		 * Terminal background color white.
		 */
		public static final String White = "47";
		/**
		 * Default terminal background color.
		 */
		public static final String Default = "49";
	}
}