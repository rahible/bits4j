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
 * MS site.
 * <p>
 * <table border="1">
 * <tr>
 * <td>HRESULT</td>
 * <td>HTTP status code</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>E_INVALIDARG (0x80070057)</td>
 * <td>403</td>
 * <td>The client's request was invalid in some way, including:
 * <ul>
 * <li>The Content-Range format was invalid or the range information sent from
 * the client is incorrect.</li>
 * <li>The size of the header field value sent from the client is greater than 4
 * kilobytes.</li>
 * <li>The client sends a different request entity length as part of the
 * Content-Range header in subsequent fragments.</li>
 * <li>None of the GUIDs sent by the client as part of the
 * BITS-Supported-Protocols header can be recognized by the server.</li>
 * <li>The destination URL maps to a directory instead.</li>
 * <li>The Content-Length header is not sent from the client.</li>
 * <li>An Unknown BITS-Packet-Type value was received by the server.</li>
 * <li>The size of the reply URL received by the server from the server
 * application is greater than 2,200 characters.</li>
 * </ul>
 * </td>
 * </tr>
 * </table>
 * 
 */
public class InvalidArgException extends BitsHttpException {
	private static final long serialVersionUID = 3531829107494444657L;
	public static final int HTTP_CODE = 400;
	public static final String HEX = "0x80070057";
	private static final String HRESULT = "E_INVALIDARG";
	private static final String DESCRIPTION = "The client's request was invalid in some way, including:"
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The Content-Range format was invalid or the range information sent from the client is incorrect."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The size of the header field value sent from the client is greater than 4 kilobytes."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The client sends a different request entity length as part of the Content-Range header in subsequent fragments."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "None of the GUIDs sent by the client as part of the BITS-Supported-Protocols header can be recognized by the server."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The destination URL maps to a directory instead."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The Content-Length header is not sent from the client."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "An Unknown BITS-Packet-Type value was received by the server."
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The size of the reply URL received by the server from the server application is greater than 2,200 characters.";

	private static final String MESSAGE = HRESULT + "(" + HEX + ")"
			+ System.getProperty("line.separator") + DESCRIPTION;

	public InvalidArgException() {
		super(MESSAGE);
	}
}
