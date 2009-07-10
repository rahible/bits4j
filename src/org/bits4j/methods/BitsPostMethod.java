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
package org.bits4j.methods;

import org.apache.commons.httpclient.methods.PutMethod;

/**
 * This class is used to support the verb BITS_POST in order to communicate with
 * a Background Intelligent Transfer Service (BITS) Server.
 * <p>
 * The BitsPostMethod method is similar to the HTTP PutMethod in that it allows
 * the body of the request to be sent to the server. However, the verb is
 * BITS_POST and there are some required headers.
 * <p>
 * For the upload, the URL MUST contain the file name for the upload. This file
 * name MUST not exist on the server, or an exception will occur at Session
 * Create time.
 * <p>
 * Note: As defined in the protocol specification MC-BUP Background Intelligent
 * Transfer Service (BITS) Upload Protocol Specification, the file down load is
 * a simple HTTP Get method.
 */
public class BitsPostMethod extends PutMethod {

	// The verb for the HTTP Request
	private static final String BITS_POST = "BITS_POST";

	/**
	 * Creates a basic BitsPostMethod for communicating with a BITS Server.
	 */
	public BitsPostMethod() {
		super();
	}

	/**
	 * Creates a basic BitsPostMethod for communicating with a Background
	 * Intelligent Transfer Service (BITS) Server and uses the URL for the
	 * <i>remote-URL</i> as defined in the protocol specification MC-BUP
	 * Background Intelligent Transfer Service (BITS) Upload Protocol
	 * Specification.
	 * <p>
	 * For the upload, the URL MUST contain the file name for the upload. This
	 * file name MUST not exist on the server, or an exception will occur at
	 * Session Create time.
	 * <p>
	 * Note: As defined in the protocol specification MC-BUP Background
	 * Intelligent Transfer Service (BITS) Upload Protocol Specification, the
	 * file down load is a simple HTTP Get method.
	 * 
	 * @param String
	 *            URL
	 */
	public BitsPostMethod(String url) {
		super(url);
	}

	/**
	 * Retrieves the HTTP Protocol verb.
	 * 
	 * @return
	 */
	@Override
	public String getName() {
		return BITS_POST;
	}
}
