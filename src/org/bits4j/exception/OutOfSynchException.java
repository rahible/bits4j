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

/**
 * This exception maps to the HRESULT and HTTP error code returned from the
 * Background Intelligent Transfer Service Server. The name matches the naming
 * of the HRESULT that this exception maps to. Below is the information per the
 * MS site. This is one of two possible E_ACCESSDENIED errors {see
 * AccessDeniedException} where the only differentiator is the HTTP CODE.
 * <p>
 * <table border="1">
 * <tr>
 * <td>HRESULT</td>
 * <td>HTTP status code</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>0 (That's right 0 nothing else)</td>
 * <td>416</td>
 * <td>The client and server are out of sync, and the server cannot proceed
 * further with the FRAGMENT message processing. The client should read the
 * correct offset from the Ack and send another FRAGMENT.</td>
 * </tr>
 * </table>
 * 
 */
public class OutOfSynchException extends BitsHttpException {

	private static final long serialVersionUID = -4969379352522639554L;

	public static final int HTTP_CODE = 416;
	public static final String HEX = "0";
	private static final String DESCRIPTION = "The client and server are out of sync, and the server cannot proceed further with the FRAGMENT message processing. The client should read the correct offset from the Ack and send another FRAGMENT.";
	private static final String MESSAGE = HEX + "-" + DESCRIPTION;

	public OutOfSynchException() {
		super(MESSAGE);
	}

}
