/* write a program to justify the string is palindrome */
package com.javatechie.StringPalindrome;

public class App {

	public boolean isPalindrome(String input) {

		if (input == null) {
			throw new IllegalArgumentException("input shouldn't be null");
		}

		if (input.equals(reverse(input))) {
			return true;
		} else {
			return false;
		}
	}

	private String reverse(String input) {
		
	 /* write your correct code here */
		String rev = "";
		for (int i = input.length() - 1; i >= 0; i--) {
			rev = rev + input.charAt(i);
		}
		return rev;
		
	     
	}

}
