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
package org.bits4j.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.bits4j.exception.BitsHttpException;
import org.bits4j.header.AckHeaderHandler;
import org.bits4j.methods.BitsPostMethod;

/**
 * The BitsHttpClient extends the HttpClient to wrap the execute calls and then
 * inspect the Return Headers to determine what, if any Exceptions should be
 * thrown from the handler.
 * <p>
 * For usage see the Apache Commons HttpClient class.
 * 
 */
public class BitsHttpClient extends HttpClient {
	private AckHeaderHandler ackHeaderHandler;

	/**
	 * Executes the HttpClient executeMethod, then utilizing the Handler,
	 * inspects the BitsPostMethod.
	 * 
	 * @param bitsPostMethod
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public int executeMethod(BitsPostMethod bitsPostMethod)
			throws BitsHttpException, HttpException, IOException {
		int httpCode = super.executeMethod(bitsPostMethod);
		this.getAckHeaderHandler().handle(bitsPostMethod, httpCode);
		return httpCode;
	}

	/**
	 * Executes the HttpClient executeMethod, then utilizing the Handler,
	 * inspects the BitsPostMethod.
	 * 
	 * @param hostConfig
	 * @param bitsPostMethod
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public int executeMethod(HostConfiguration hostConfig,
			BitsPostMethod bitsPostMethod) throws BitsHttpException,
			HttpException, IOException {
		int httpCode = super.executeMethod(hostConfig, bitsPostMethod);
		this.getAckHeaderHandler().handle(bitsPostMethod, httpCode);
		return httpCode;
	}

	/**
	 * Executes the HttpClient executeMethod, then utilizing the Handler,
	 * inspects the BitsPostMethod.
	 * 
	 * @param hostConfig
	 * @param bitsPostMethod
	 * @param state
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public int executeMethod(HostConfiguration hostconfig,
			BitsPostMethod bitsPostMethod, HttpState state)
			throws BitsHttpException, HttpException, IOException {
		int httpCode = super.executeMethod(hostconfig, bitsPostMethod, state);
		this.getAckHeaderHandler().handle(bitsPostMethod, httpCode);
		return httpCode;
	}

	public AckHeaderHandler getAckHeaderHandler() {
		return this.ackHeaderHandler;
	}

	public void setAckHeaderHandler(AckHeaderHandler ackHeaderHandler) {
		this.ackHeaderHandler = ackHeaderHandler;
	}
}
