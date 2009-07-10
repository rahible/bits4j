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
 * <td>E_ACCESSDENIED (x80070005)</td>
 * <td>501</td>
 * <td>Uploads are not enabled for the virtual directory.</td>
 * </tr>
 * </table>
 * 
 */
public class UploadsNotEnabledException extends BitsHttpException {

	private static final long serialVersionUID = 4916816131962068304L;
	public static final int HTTP_CODE = 501;
	public static final String HEX = "x80070005";
	private static final String HRESULT = "E_ACCESSDENIED";
	private static final String DESCRIPTION = "Uploads are not enabled for the virtual directory.";

	private static final String MESSAGE = HRESULT + "(" + HEX + ")" + "-"
			+ DESCRIPTION;

	public UploadsNotEnabledException() {
		super(MESSAGE);
	}

}
