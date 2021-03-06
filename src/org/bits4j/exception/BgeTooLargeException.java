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
 * <td>BG_E_TOO_LARGE (x80200020)</td>
 * <td>500</td>
 * <td>The fragment size sent by the client cannot be handled by the server..</td>
 * </tr>
 * </table>
 * 
 */
public class BgeTooLargeException extends BitsHttpException {
	private static final long serialVersionUID = 8339130292777359400L;
	public static final int HTTP_CODE = 500;
	public static final String HEX = "x80200020";
	private static final String HRESULT = "BG_E_TOO_LARGE";
	private static final String DESCRIPTION = "The fragment size sent by the client cannot be handled by the server.";

	private static final String MESSAGE = HRESULT + "(" + HEX + ")" + "-"
			+ DESCRIPTION;

	public BgeTooLargeException() {
		super(MESSAGE);
	}

}
