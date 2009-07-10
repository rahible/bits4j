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
package org.bits4j.test.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.bits4j.methods.BitsPostMethod;

public class BitsPostMethodTest extends BitsPostMethod {
	private Map<String, Header> headers = new HashMap<String, Header>();

	public Header getResponseHeader(String name) {
		return headers.get(name);
	}

	public void setResponseHeader(String name, String value) {
		Header header = new Header(name, value);
		headers.put(name, header);
	}
}