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

import org.apache.commons.httpclient.Header;
import org.apache.commons.lang.StringUtils;
import org.bits4j.Constants;
import org.bits4j.exception.AccessDeniedException;
import org.bits4j.exception.AckNotFoundException;
import org.bits4j.exception.AuthenticationRequiredException;
import org.bits4j.exception.BgeSessionNotFoundException;
import org.bits4j.exception.BgeTooLargeException;
import org.bits4j.exception.BitsHttpException;
import org.bits4j.exception.InvalidArgException;
import org.bits4j.exception.OutOfSynchException;
import org.bits4j.exception.ServerDiskFullException;
import org.bits4j.exception.UploadsNotEnabledException;
import org.bits4j.methods.BitsPostMethod;

/**
 * Handles the appropriate exception based on the header values from the
 * BitsMethod.
 */
public class AckHeaderHandlerImpl implements AckHeaderHandler {
	/**
	 * Tests for a header value of Constants.BITS_ERROR_CODE and if there throws
	 * the appropriate BitsHttpException.
	 * 
	 * @param BitsPostMethod
	 *            bitsPostMetho
	 * @param int httpCode
	 */
	public void handle(BitsPostMethod bitsPostMethod, int httpCode)
			throws BitsHttpException {
		// should httpCode be tested to 200/201 here?...na probably not.
		inspectForAck(bitsPostMethod, httpCode);
		String errorCode = getErrorCode(bitsPostMethod);
		if (StringUtils.isEmpty(errorCode)) {
			return;
		}
		throwAccessException(bitsPostMethod, errorCode, httpCode);
		throwBgeSessionNotFoundException(bitsPostMethod, errorCode);
		throwBgTooLargeException(bitsPostMethod, errorCode);
		throwInvalidArgException(bitsPostMethod, errorCode);
		throwOutOfSynchException(bitsPostMethod, errorCode);
		throwServerDiskFullException(bitsPostMethod, errorCode);
	}

	/**
	 * Tests the value returned for the Error Code from the BITS server against
	 * the ServerDiskFullException value to determine whether that Exception
	 * needs to be thrown.
	 * 
	 * @param bitsPostMethod
	 * @param errorCode
	 * @throws ServerDiskFullException
	 */
	private void throwServerDiskFullException(BitsPostMethod bitsPostMethod,
			String errorCode) throws ServerDiskFullException {
		if (StringUtils.contains(errorCode, ServerDiskFullException.HEX)) {
			throw new ServerDiskFullException();
		}
	}

	/**
	 * Tests the value returned for the Error Code from the BITS server against
	 * the OutOfSynchException value to determine whether that Exception needs
	 * to be thrown.
	 * 
	 * @param bitsPostMethod
	 * @param errorCode
	 * @throws OutOfSynchException
	 */
	private void throwOutOfSynchException(BitsPostMethod bitsPostMethod,
			String errorCode) throws OutOfSynchException {
		if (StringUtils.contains(errorCode, OutOfSynchException.HEX)) {
			throw new OutOfSynchException();
		}
	}

	/**
	 * Tests the value returned for the Error Code from the BITS server against
	 * the InvalidArgException value to determine whether that Exception needs
	 * to be thrown.
	 * 
	 * @param bitsPostMethod
	 * @param errorCode
	 * @throws InvalidArgException
	 */
	private void throwInvalidArgException(BitsPostMethod bitsPostMethod,
			String errorCode) throws InvalidArgException {
		if (StringUtils.contains(errorCode, InvalidArgException.HEX)) {
			throw new InvalidArgException();
		}
	}

	/**
	 * Tests the value returned for the Error Code from the BITS server against
	 * the BgTooLargeException value to determine whether that Exception needs
	 * to be thrown.
	 * 
	 * @param bitsPostMethod
	 * @param errorCode
	 * @throws BgTooLargeException
	 */
	private void throwBgTooLargeException(BitsPostMethod bitsPostMethod,
			String errorCode) throws BgeTooLargeException {
		if (StringUtils.contains(errorCode, BgeTooLargeException.HEX)) {
			throw new BgeTooLargeException();
		}
	}

	/**
	 * Tests the value returned for the Error Code from the BITS server against
	 * the BgTooLargeException value to determine whether that Exception needs
	 * to be thrown.
	 * <p>
	 * Note: Since the Error Code could match the AccessDeniedException or
	 * UploadsNotEnabledException, the only differentiator is the value for the
	 * http return code form the server.
	 * 
	 * @param bitsPostMethod
	 * @param errorCode
	 * @param httpCode
	 * @throws UploadsNotEnabledException
	 * @throws AccessDeniedException
	 */
	private void throwAccessException(BitsPostMethod bitsPostMethod,
			String errorCode, int httpCode) throws UploadsNotEnabledException,
			AccessDeniedException {
		// since the hex could match AccessDeniedException
		// OR UploadsNotEnabledException they are only differentiated by
		// httpcode
		if (StringUtils.contains(errorCode, AccessDeniedException.HEX)) {
			if (UploadsNotEnabledException.HTTP_CODE == httpCode) {
				throw new UploadsNotEnabledException();
			}
			throw new AccessDeniedException();
		}
	}

	private void throwBgeSessionNotFoundException(
			BitsPostMethod bitsPostMethod, String errorCode)
			throws BgeSessionNotFoundException {
		if (StringUtils.contains(errorCode, BgeSessionNotFoundException.HEX)) {
			throw new BgeSessionNotFoundException();
		}
	}

	/**
	 * Tests for the presence of the BITS_ERROR_CODE or BITS_ERROR in the
	 * response headers. If the values are there, it returns the first one it
	 * comes across. If there is a header, and no value, then throws a
	 * BitHttpException. If no header is found then returns null.
	 * <p>
	 * Note: The MS Specification states that the BITS_ERROR_CODE is required,
	 * but the implementation on Win 2003 server returns BITS_ERROR.
	 * <p>
	 * 
	 * @param bitsPostMethod
	 * @return String - value of the BITS_ERROR/BITS_ERROR_CODE header.
	 * @throws BitsHttpException
	 */
	private String getErrorCode(BitsPostMethod bitsPostMethod)
			throws BitsHttpException {
		// TODO: Simplify this as it's copy and paste for 2 exact if statements
		// testing for a different value.
		// if the error header is null or empty then everything is fine and get
		// out. We need to test for BITS_ERROR_CODE AND BITS_ERROR as MS
		// specification says BITS_ERROR_CODE, but the BITS Server has been know
		// to just return BITS_ERROR

		if ((bitsPostMethod.getResponseHeader(Constants.BITS_ERROR_CODE) != null)) {
			String bitsErrorCode = StringUtils.trimToEmpty((bitsPostMethod
					.getResponseHeader(Constants.BITS_ERROR_CODE)).getValue());
			if (!StringUtils.isEmpty(bitsErrorCode)) {
				return bitsErrorCode;
			}
			throw new BitsHttpException(
					"Bits Server returned a bits header for "
							+ Constants.BITS_ERROR_CODE
							+ " but no value was found.");
		}
		if ((bitsPostMethod.getResponseHeader(Constants.BITS_ERROR) != null)) {
			String bitsError = StringUtils.trimToEmpty((bitsPostMethod
					.getResponseHeader(Constants.BITS_ERROR)).getValue());
			if (!StringUtils.isEmpty(bitsError)) {
				return bitsError;
			}
			throw new BitsHttpException(
					"Bits Server returned a bits header for "
							+ Constants.BITS_ERROR + " but no value was found.");
		}
		return null;
	}

	/**
	 * Checks the responseHeaders from the BitsPostMethod for the content of
	 * Ack. If Ack is not found, this method exceptions and includes all headers
	 * in the Exception method.
	 * 
	 * @param bitsPostMethod
	 * @throws AckNotFoundException
	 */
	private void inspectForAck(BitsPostMethod bitsPostMethod, int httpCode)
			throws AckNotFoundException {
		// if the request header was for Close-session and the http response
		// code is 500-599, then we return and ignore everything else.
		if ((bitsPostMethod.getRequestHeader(Constants.BITS_PACKET_TYPE) != null && StringUtils
				.equalsIgnoreCase(Constants.CLOSE_SESSION, bitsPostMethod
						.getRequestHeader(Constants.BITS_PACKET_TYPE)
						.getValue()))
				&& (httpCode >= 500 && httpCode <= 599)) {
			return;
		}

		// checking for presence of ACK header.
		if (bitsPostMethod.getResponseHeader(Constants.BITS_PACKET_TYPE) == null
				|| !StringUtils.equalsIgnoreCase((bitsPostMethod
						.getResponseHeader(Constants.BITS_PACKET_TYPE))
						.getValue(), Constants.ACK)) {
			Header[] headers = bitsPostMethod.getResponseHeaders();
			StringBuilder headerString = new StringBuilder();
			for (Header header : headers) {
				headerString.append(header.getName()).append(": ").append(
						header.getValue()).append(
						System.getProperty("line.separator"));
			}

			// if there is no ack header and there is a WWW-Authenticate, then
			// it might
			// be an authentication issue.
			Header authenticate = bitsPostMethod
					.getResponseHeader(Constants.WWW_AUTHENTICATE);
			if (authenticate != null) {
				throw new AuthenticationRequiredException(
						"Server is expecting Authentication:"
								+ authenticate.getValue());
			}

			throw new AckNotFoundException("Ack not present in response:"
					+ headerString.toString());
		}
	}
}
