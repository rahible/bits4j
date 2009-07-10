/*
 * Copyright 2009 Robert Aaron Hible
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.bits4j.exception;

import org.apache.commons.httpclient.Header;
import org.apache.commons.lang.StringUtils;

/**
 * The base exception for handling unknown Background Intelligent Transfer
 * Service BITS Exceptions.
 */
public class BitsHttpException extends Exception {

	private static final long serialVersionUID = 2637365286294262304L;

	private Header[] headers;

	public BitsHttpException() {
		super();
	}

	public BitsHttpException(String message) {
		super(message);
	}

	public BitsHttpException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Returns the message headers set on this exception. Useful for reporting,
	 * please don't use this for anything else, it would be wrong.
	 * 
	 * @return Header[]
	 */
	public String getHeadersAsString() {
		StringBuilder message = new StringBuilder();
		if (this.headers != null) {
			for (Header header : headers) {
				message.append(header.getName()).append(":");
				if (!StringUtils.isEmpty(header.getValue())) {
					message.append(header.getValue());
				}
				message.append(System.getProperty("line.separator"));
			}
		}
		return message.toString();
	}

	/**
	 * Sets the headers to be included in the exception message.
	 * 
	 * @param Header
	 *            [] headers
	 */
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

}
