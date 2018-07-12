/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various display attributes.
 * 
 * @file	BlumeText.java
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
 * Primary Blume class for Win32 and ANSI color options and attributes.
 */
public class BlumeText {
	/**
	 * Operating system currently being used.
	 */
	protected static String _OperatingSystem;
	/**
	 * Identifies whether ANSI code should be used. 
	 */
	protected static boolean _isANSI;
	/**
	 * Identifies whether Win32 code should be used.
	 */
	protected static boolean _isWin32;
	
	/*
	 * Determine the operating system being used in order to ensure
	 * that the correct ANSI or Win32-based code is executed when using
	 * Blume.
	 */
	static {
		_OperatingSystem = System.getProperty( "os.name" ).toLowerCase();
		
		if ( _OperatingSystem.indexOf( "nux" ) >= 0
				|| _OperatingSystem.indexOf( "nix" ) >= 0 ) {
			_isANSI = true;
		} else if ( _OperatingSystem.indexOf( "win" ) >= 0 ) {
			_isWin32 = true;
		}
	}

	/*
	 * Basic color palette foreground color options. The value of
	 * these are determined when the application starts and the
	 * operating system is determined.
	 */
	/**
	 * Aqua is only applicable to Windows systems (Win32).
	 */
	public static final String Aqua = "03";
	public static String Black;
	public static String Blue;
	/**
	 * Cyan is only applicable to Unix-based systems (ANSI).
	 */
	public static final String Cyan = "36";
	/**
	 * Default color is only applicable to Unix-based systems (ANSI).
	 */
	public static final String Default = "39";
	/**
	 * Gray is only applicable to Windows systems (Win32).
	 */
	public static final String Gray = "08";
	public static String Green;
	/**
	 * Light aqua is only applicable to Windows systems (Win32).
	 */
	public static final String LightAqua = "0B";
	/**
	 * Light blue is only applicable to Windows systems (Win32).
	 */
	public static final String LightBlue = "09";
	/**
	 * Light green is only applicable to Windows systems (Win32).
	 */
	public static final String LightGreen = "0A";
	/**
	 * Light purple is only applicable to Windows systems (Win32).
	 */
	public static final String LightPurple = "0D";
	/**
	 * Light red is only applicable to Windows systems (Win32).
	 */
	public static final String LightRed = "0C";
	/**
	 * Bright white is only applicable to Windows systems (Win32).
	 */
	public static final String BrightWhite = "0F";
	/**
	 * Light yellow is only applicable to Windows systems (Win32).
	 */
	public static final String LightYellow = "0E";
	/**
	 * Magenta is only applicable to Unix-based systems (ANSI).
	 */
	public static final String Magenta = "35";
	/**
	 * Purple is only applicable to Windows systems (Win32).
	 */
	public static final String Purple = "05";
	public static String Red;
	public static String White;
	public static String Yellow;
	
	static {
		if ( getIsANSI() ) {
			Black = "30";
			Red = "31";
			Green = "32";
			Yellow = "33";
			Blue = "34";
			White = "37";
		} else if ( getIsWin32() ) {
			Black = "00";
			Red = "04";
			Green = "02";
			Yellow = "06";
			Blue = "01";
		}
	}
	
	/**
	 * Standard terminal SGR (Select Graphic Rendition) display
	 * attributes. These attributes only work with Unix-based
	 * systems.
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
	
	/**
	 * Basic color palette background color options. The value of
	 * these are determined when the application starts and the
	 * operating system is determined.
	 */
	public static class Background {
		/**
		 * Aqua is only applicable to Windows systems (Win32).
		 */
		public static final String Aqua = "30";
		public static String Black;
		public static String Blue;
		/**
		 * Cyan is only applicable to Unix-based systems (ANSI).
		 */
		public static final String Cyan = "46";
		/**
		 * Default color is only applicable to Unix-based systems (ANSI).
		 */
		public static final String Default = "49";
		/**
		 * Gray is only applicable to Windows systems (Win32).
		 */
		public static final String Gray = "80";
		public static String Green;
		/**
		 * Light aqua is only applicable to Windows systems (Win32).
		 */
		public static final String LightAqua = "B0";
		/**
		 * Light blue is only applicable to Windows systems (Win32).
		 */
		public static final String LightBlue = "90";
		/**
		 * Light green is only applicable to Windows systems (Win32).
		 */
		public static final String LightGreen = "A0";
		/**
		 * Light purple is only applicable to Windows systems (Win32).
		 */
		public static final String LightPurple = "D0";
		/**
		 * Light red is only applicable to Windows systems (Win32).
		 */
		public static final String LightRed = "C0";
		/**
		 * Bright white is only applicable to Windows systems (Win32).
		 */
		public static final String BrightWhite = "F0";
		/**
		 * Light yellow is only applicable to Windows systems (Win32).
		 */
		public static final String LightYellow = "E0";
		/**
		 * Magenta is only applicable to Unix-based systems (ANSI).
		 */
		public static final String Magenta = "45";
		/**
		 * Purple is only applicable to Windows systems (Win32).
		 */
		public static final String Purple = "50";
		public static String Red;
		public static String White;
		public static String Yellow;
		
		static { 
			if ( getIsANSI() ) {
				Black = "40";
				Blue = "44";
				Green = "42";
				Red = "41";
				White = "47";
				Yellow = "43";
			} else if ( getIsWin32() ) {
				Black = "00";
				Blue = "10";
				Green = "20";
				Red = "40";
				White = "70";
				Yellow = "60";
			}
		}
	}
	
	/**
	 * Returns whether ANSI code is being used based upon the operating system.
	 * 
	 * @return True if ANSI code is being used.
	 */
	public static boolean getIsANSI() {
		return _isANSI;
	}
	
	/**
	 * Returns whether Win32 code is being used based upon the operating system.
	 * 
	 * @return True if Win32 code is being used.
	 */
	public static boolean getIsWin32() {
		return _isWin32;
	}
	
	/**
	 * Gets a basic string of what operating system is currently being used.
	 * 
	 * @return Operating system string in lower case.
	 */
	public static String getOS() {
		return _OperatingSystem;
	}
}