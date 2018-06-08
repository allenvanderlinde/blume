/**
 * Project: Blume
 * 
 * Blume is a simple interface for producing ANSI 8-bit and 24-bit colored text with
 * foreground and background color options and various text attributes.
 * 
 * @file	BlumeColor.java
 * @version	1.0.060818
 * 
 * @author	Allen Vanderlinde
 * @date	6/7/2018
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

public class BlumeColor {
	/**
	 * Integer from 0 to 255 that represents an 8-bit
	 * color palette option.
	 */
	protected int colorValue;
	/**
	 * Red color value for 24-bit color.
	 */
	protected int red;
	/**
	 * Green color value for 24-bit color.
	 */
	protected int green;
	/**
	 * Blue color value for 24-bit color.
	 */
	protected int blue;
	/**
	 * Flag to determine whether this is an 8-bit color instance.
	 */
	protected boolean is8Bit;
	/**
	 * Flag to determine whether this is a 24-bit color instance.
	 */
	protected boolean is24Bit;
	
	/**
	 * Default constructor which produces black ({@link blume.BlumeText#Black}) from the
	 * system's 8-bit color palette.
	 */
	public BlumeColor() {
		this.colorValue = 16;
		this.is8Bit = true;
	}
	
	/**
	 * Constructor for an 8-bit color palette option (0 - 255).
	 * 
	 * @param colorValue
	 */
	public BlumeColor( int colorValue ) {
		this.colorValue = colorValue;
		this.is8Bit = true;
	}
	
	/**
	 * Constructor for a 24-bit RGB color option.
	 * 
	 * 24-bit (true color) is comprised of three color
	 * channels (red, green, blue) with values 0 to 255
	 * each.
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public BlumeColor( int red, int green, int blue ) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.is24Bit = true;
	}
	
	/*
	 * Accessors.
	 */
	/**
	 * @return The 8-bit color palette value of this color.
	 */
	public int getColorValue() {
		return this.colorValue;
	}
	/**
	 * @return The red color value of this color.
	 */
	public int getRed() {
		return this.red;
	}
	/**
	 * @return The green color value of this color.
	 */
	public int getGreen() {
		return this.green;
	}
	/**
	 * @return The blue color value of this color.
	 */
	public int getBlue() {
		return this.blue;
	}
	/**
	 * @return Whether this color is an 8-bit color.
	 */
	public boolean getIs8Bit() {
		return this.is8Bit;
	}
	/**
	 * @return Whether this color is a 24-bit color.
	 */
	public boolean getIs24Bit() {
		return this.is24Bit;
	}
}
