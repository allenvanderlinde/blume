/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various display attributes.
 * 
 * @file	Win32.java
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

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

/**
 * Class built on JNA objects for Win32 console coloring.
 */
public class Win32 {
	/**
	 * Console information required by Windows in order to change text color
	 * and background.
	 */
	private static CONSOLE_SCREEN_BUFFER_INFO consoleInfo	= null;	
	/**
	 * Standard output handle for Win32.
	 */
	public static final int STD_OUTPUT_HANDLE				= -11;
	
	/**
	 * Simple console coordinate points class.
	 */
	public static class COORD extends Structure {
		/**
		 * X position.
		 */
		public short X;
		/**
		 * Y position.
		 */
		public short Y;
		
		/**
		 * Overridden field order function required for Structure base class.
		 */
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList( new String[] { "X", "Y" } );
		}
	}
	
	/**
	 * Simple rectangle class.
	 */
	public static class SMALL_RECT extends Structure {
		/**
		 * Distance of the left of the rectangle from the left side of the console.
		 */
		public short Left;
		/**
		 * Distance of the top of the rectangle from the top side of the console.
		 */
		public short Top;
		/**
		 * Distance of the right of the rectangle from the right side of the console.
		 */
		public short Right;
		/**
		 * Distance of the bottom of the rectangle from the bottom side of the console.
		 */
		public short Bottom;
		
		/**
		 * Overridden field order function required for Structure base class.
		 */
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList( new String[] { "Left", "Top", "Right", "Bottom" } );
		}
	}
	
	/**
	 * Java version of the native Win32 CONSOLE_SCREEN_BUFFER_INFO struct.
	 */
	public static class CONSOLE_SCREEN_BUFFER_INFO extends Structure {
		public COORD dwSize;
		public COORD dwCursorPosition;
		public short wAttributes;
		public SMALL_RECT srWindow;
		public COORD dwMaximumWindowSize;
		
		/**
		 * Overridden field order function required for Structure base class.
		 */
		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList( new String[] { "dwSize", "dwCursorPosition", "wAttributes", "srWindow", "dwMaximumWindowSize" } );
		}
	}
	
	/**
	 * Java interface for Win32's kernel32 library (JNA).
	 */
	public interface Kernel32 extends Library {
		/**
		 * Kernel32 DLL object.
		 */
		Kernel32 DLL = (Kernel32) Native.loadLibrary( "kernel32", Kernel32.class );
		
		public int GetStdHandle( int nStdHandle );
		
		public boolean SetConsoleTextAttribute( int in_hConsoleOutput, short in_wAttributes );
		
		public boolean GetConsoleScreenBufferInfo( int in_hConsoleOutput, CONSOLE_SCREEN_BUFFER_INFO out_lpConsoleScreenBufferInfo );
		
		//public int GetLastError();
	}
	
	/**
	 * Get the current CONSOLE_SCREEN_BUFFER_INFO object.
	 * 
	 * @return Win32 console info object.
	 */
	public static CONSOLE_SCREEN_BUFFER_INFO getConsoleInfo() {
		return consoleInfo;
	}
	
	/**
	 * Initialize the CONSOLE_SCREEN_BUFFER_INFO object with native console data.
	 * 
	 * @throws Exception
	 */
	public static void initializeConsole() throws Exception {
		if ( consoleInfo == null ) {
			consoleInfo = new CONSOLE_SCREEN_BUFFER_INFO();
		}
		
		int handleStdOut = Kernel32.DLL.GetStdHandle( STD_OUTPUT_HANDLE );
			
		Kernel32.DLL.GetConsoleScreenBufferInfo( handleStdOut, consoleInfo );
	}
	
	/**
	 * Resets the console with standard console attributes.
	 */
	public static void resetConsole() {
		int handleStdOut = Kernel32.DLL.GetStdHandle( STD_OUTPUT_HANDLE );
		
		Kernel32.DLL.SetConsoleTextAttribute( handleStdOut, consoleInfo.wAttributes );
	}
	
	/**
	 * Sets the Windows console text attributes with the chosen
	 * hexadecimal (short) values.
	 * 
	 * @param hexes
	 */
	public static void setColor( short[] hexes ) {
		int handleStdOut = Kernel32.DLL.GetStdHandle( STD_OUTPUT_HANDLE );
		
		// Combine the hexadecimal (short) color options via bitwise OR
		short colors = 0x00;
		for ( int i = 0; i < hexes.length; i++ ) {
			colors |= hexes[i];
		}
		
		Kernel32.DLL.SetConsoleTextAttribute( handleStdOut, colors );
	}
	
	/**
	 * Sets the Windows console text attributes with the chosen color
	 * modifiers.
	 * 
	 * @param mods
	 */
	public static void setColor( String[] mods ) {
		int handleStdOut = Kernel32.DLL.GetStdHandle( STD_OUTPUT_HANDLE );
		
		// Parse the color modifier for Windows systems from string arguments into a base-16 short
		short colors = 0x00;
		for ( int i = 0; i < mods.length; i++ ) {
			colors |= (short) Integer.parseInt( mods[i], 16 );
		}
		
		Kernel32.DLL.SetConsoleTextAttribute( handleStdOut, colors );
	}
}
