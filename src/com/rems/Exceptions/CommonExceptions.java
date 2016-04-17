package com.rems.Exceptions;

import java.lang.Exception;

/**
 * @name: CommonException
 * @author: Team Amrita Virdi
 * @description: This is an Exception class for the REMS Project which contains
 *               predefined methods of Exception Absract class.
 **/
public class CommonExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommonExceptions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonExceptions(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CommonExceptions(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CommonExceptions(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CommonExceptions(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
