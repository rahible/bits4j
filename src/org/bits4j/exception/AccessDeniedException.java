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
 * * This exception maps to the HRESULT and HTTP error code returned from the
 * Background Intelligent Transfer Service Server. The name matches the naming
 * of the HRESULT that this exception maps to. Below is the information per the
 * MS site. This is one of two possible E_ACCESSDENIED errors {see
 * UploadsNotEnabledException} where the only differentiator is the HTTP CODE.
 * <p>
 * <table border="1">
 * <tr>
 * <td>HRESULT</td>
 * <td>HTTP status code</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>E_ACCESSDENIED (x80070005)</td>
 * <td>403</td>
 * <td>It can be one of the following:
 * <ul>
 * <li>The destination URL exists on the server and cannot be overwritten.</li>
 * <li>The client is not authorized to access the URL on the server.</li>
 * </ul>
 * </td>
 * </tr>
 * </table>
 * 
 */
public class AccessDeniedException extends BitsHttpException {
	private static final long serialVersionUID = -5647034449705933413L;
	public static final int HTTP_CODE = 403;
	public static final String HEX = "x80070005";
	private static final String HRESULT = "E_ACCESSDENIED";
	private static final String DESCRIPTION = "It can be one of the following:"
			+ System.getProperty("line.separator")
			+ "\t"
			+ "The destination URL exists on the server and cannot be overwritten."
			+ System.getProperty("line.separator") + "\t"
			+ "The client is not authorized to access the URL on the server.";

	private static final String MESSAGE = HRESULT + "(" + HEX + ")" + "-"
			+ DESCRIPTION;

	public AccessDeniedException() {
		super(MESSAGE);
	}

}
