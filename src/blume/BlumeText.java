/**
 * Project: Blume
 * 
 * @brief	Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * 			foreground and background color options and various display attributes.
 * 
 * @file	BlumeText.java
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

public class BlumeText {	
	/**
	 * @brief	CSI (Control Sequence Introducer) sequence
	 * 			which should begin every string intended
	 * 			to have ANSI colored, or in other ways altered,
	 * 			text.
	 */
	protected static final String _ANSI_		= ((char) 27 + "[");
	/**
	 * @brief	Character which denotes a separation between color
	 * 			and any other display attributes or options.
	 */
	protected static final char _DELIM_			= ';';
	/**
	 * @brief	Character which denotes the end of a color or display
	 * 			attribute escape sequence.
	 */
	protected static final char _TERMINATOR_	= 'm';
	/**
	 * @brief	The ANSI escape code to reset the terminal
	 * 			text color and text effects.
	 */
	protected static final String _RESET_		= (_ANSI_ + "0m");
	
	/*
	 * Terminal standard 8-color palette options
	 * (foreground).
	 */
	/**
	 * @brief	Terminal foreground color black.
	 */
	public static final String Black			= "30";
	/**
	 * @brief	Terminal foreground color red.
	 */
	public static final String Red				= "31";
	/**
	 * @brief	Terminal foreground color green.
	 */
	public static final String Green			= "32";
	/**
	 * @brief	Terminal foreground color yellow.
	 */
	public static final String Yellow			= "33";
	/**
	 * @brief	Terminal foreground color blue.
	 */
	public static final String Blue				= "34";
	/**
	 * @brief	Terminal foreground color magenta.
	 */
	public static final String Magenta			= "35";
	/**
	 * @brief	Terminal foreground color cyan.
	 */
	public static final String Cyan				= "36";
	/**
	 * @brief	Terminal foreground color white.
	 */
	public static final String White			= "37";
	/**
	 * @brief	Terminal default foreground color.
	 */
	public static final String Default			= "39";
	
	/*
	 * Terminal standard SGR (Select Graphic Rendition) text
	 * attributes.
	 */
	public static class Attribute {
		/**
		 * @brief	Bold display attribute for text.
		 */
		public static final String Bold				= "1";
		/**
		 * @brief	Underline display attribute for text.
		 */
		public static final String Underline		= "4";
	}
	
	/*
	 * Terminal standard 8-color palette options
	 * (background).
	 */
	public static class Background {
		/**
		 * @brief	Terminal background color black.
		 */
		public static final String Black		= "40";
		/**
		 * @brief	Terminal background color red.
		 */
		public static final String Red			= "41";
		/**
		 * @brief	Terminal background color green.
		 */
		public static final String Green		= "42";
		/**
		 * @brief	Terminal background color yellow.
		 */
		public static final String Yellow		= "43";
		/**
		 * @brief	Terminal background color blue.
		 */
		public static final String Blue			= "44";
		/**
		 * @brief	Terminal background color magenta.
		 */
		public static final String Magenta		= "45";
		/**
		 * @brief	Terminal background color cyan.
		 */
		public static final String Cyan		= "46";
		/**
		 * @brief	Terminal background color white.
		 */
		public static final String White		= "47";
		/**
		 * @brief	Default terminal background color.
		 */
		public static final String Default		= "49";
	}
}