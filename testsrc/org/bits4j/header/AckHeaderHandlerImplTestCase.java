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
package org.bits4j.header;

import org.bits4j.Constants;
import org.bits4j.exception.AckNotFoundException;
import org.bits4j.exception.AuthenticationRequiredException;
import org.bits4j.test.util.BitsPostMethodTest;
import org.junit.Before;
import org.junit.Test;

public class AckHeaderHandlerImplTestCase {
	private AckHeaderHandlerImpl ackHeaderHandler;

	@Before
	public void setUp() {
		ackHeaderHandler = new AckHeaderHandlerImpl();
	}

	@Test(expected = AuthenticationRequiredException.class)
	public void testAuthenticationRequiredException() throws Exception {
		int httpCode = 403;
		BitsPostMethodTest bitsPostMethod = new BitsPostMethodTest();
		bitsPostMethod.setResponseHeader(Constants.WWW_AUTHENTICATE,
				"BASIC relm:blah");
		ackHeaderHandler.handle(bitsPostMethod, httpCode);

	}

	@Test(expected = AckNotFoundException.class)
	public void test500ErrorFragmentNoAck() throws Exception {
		int httpCode = 501;
		BitsPostMethodTest bitsPostMethod = new BitsPostMethodTest();
		bitsPostMethod.setResponseHeader(Constants.BITS_PACKET_TYPE, "");
		bitsPostMethod.setRequestHeader(Constants.BITS_PACKET_TYPE,
				Constants.FRAGMENT);
		ackHeaderHandler.handle(bitsPostMethod, httpCode);
	}

	@Test
	public void test500ErrorCloseSession() throws Exception {
		int httpCode = 501;
		BitsPostMethodTest bitsPostMethod = new BitsPostMethodTest();
		bitsPostMethod.setResponseHeader(Constants.BITS_PACKET_TYPE,
				Constants.ACK);
		bitsPostMethod.setRequestHeader(Constants.BITS_PACKET_TYPE,
				Constants.CLOSE_SESSION);
		// Ack with at 500 for a request header of CLOSE session should not
		// throw an exception
		ackHeaderHandler.handle(bitsPostMethod, httpCode);
	}

}
