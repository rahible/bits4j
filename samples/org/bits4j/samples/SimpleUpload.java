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
package org.bits4j.samples;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.bits4j.Constants;
import org.bits4j.header.AckHeaderHandlerImpl;
import org.bits4j.httpclient.BitsHttpClient;
import org.bits4j.methods.BitsPostMethod;

/**
 * Simple example demonstrating the basic functionality of the File Upload
 * through the BITS protocol. This example only sends one Fragment of a text
 * file with a little bit of content.
 */
public class SimpleUpload {

	public static final String localHost = "http://localhost/upload-dir";

	/**
	 * The argument is the URL for the upload location of the BITS server. If
	 * none is provided, the url http://localhost/upload-dir is used.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String url = localHost;
		if (args.length == 0) {
			System.out.println("URL is not provided using:" + url);
		} else {
			url = args[0];
		}
		// just making sure that there is an ending /
		url = StringUtils.removeEnd(url, "/") + "/";
		try {
			// BitsHttpClient extends apaches HttpClient
			BitsHttpClient client = new BitsHttpClient();
			client.setAckHeaderHandler(new AckHeaderHandlerImpl());

			/*
			 * Needed for Basic Authentication against http (not https).
			 */
			
			/*
			Credentials defaultcreds = new UsernamePasswordCredentials(
					userName, password);
			client.getState()
					.setCredentials(
							new AuthScope(serverDomainOrIpAddress, port80or443, "realm"),
							defaultcreds);
							
			//preemtive is required for the current bits4j code base.
			client.getParams().setAuthenticationPreemptive(true);
			*/
			
			// Create a BITS Session
			String bitsSessionId = null;
			String contentName = "text.txt";

			String contentUrl = url + contentName;

			BitsPostMethod bitsPost = new BitsPostMethod(contentUrl);
			bitsPost.setRequestHeader(Constants.BITS_PACKET_TYPE,
					Constants.CREATE_SESSION);
			bitsPost.setRequestHeader(Constants.BITS_SUPPORTED_PROTOCOLS,
					Constants.BITS_1_5_UPLOAD_PROTOCAL_GUID);
			bitsPost.setRequestHeader(Constants.USER_AGENT, "My BITS Client");
			bitsPost.setRequestHeader(Constants.CONTENT_NAME, contentName);
			bitsPost.setRequestHeader(Constants.CONTENT_LENGTH, "0");
			client.executeMethod(bitsPost);

			// get the BITS server assigned Session Id
			bitsSessionId = ((Header) bitsPost
					.getResponseHeader(Constants.BITS_SESSION_ID)).getValue();
			bitsPost.releaseConnection();

			// Send Fragment to the BITS Server.
			bitsPost = new BitsPostMethod(contentUrl);
			bitsPost.setRequestHeader(Constants.BITS_SESSION_ID, bitsSessionId);
			String bytesRead = "my very small content of the file";
			ByteArrayRequestEntity byteArrayRequestEntity = new ByteArrayRequestEntity(
					bytesRead.getBytes());
			bitsPost.setRequestHeader(Constants.BITS_PACKET_TYPE,
					Constants.FRAGMENT);
			bitsPost.setRequestHeader(Constants.USER_AGENT, "My BITS Client");
			bitsPost.setRequestHeader(Constants.CONTENT_NAME, contentName);
			bitsPost.setRequestHeader(Constants.CONTENT_LENGTH, String
					.valueOf(bytesRead.getBytes().length));

			// Content-Range: bytes start-(bytesReat-1)/totalFileSize
			StringBuilder contentRange = new StringBuilder("bytes ")
					.append("0").append("-").append(
							String.valueOf((bytesRead.getBytes().length) - 1))
					.append("/").append(bytesRead.getBytes().length);
			bitsPost.setRequestHeader(Constants.CONTENT_RANGE, contentRange
					.toString());
			bitsPost.setRequestEntity(byteArrayRequestEntity);
			client.executeMethod(bitsPost);

			// Close the session
			bitsPost = new BitsPostMethod(contentUrl);
			bitsPost.setRequestHeader(Constants.BITS_SESSION_ID, bitsSessionId);
			bitsPost.setRequestHeader(Constants.BITS_PACKET_TYPE,
					Constants.CLOSE_SESSION);
			bitsPost.setRequestHeader(Constants.USER_AGENT, "My BITS Client");
			bitsPost.setRequestHeader(Constants.CONTENT_NAME, contentName);
			bitsPost.setRequestHeader(Constants.CONTENT_LENGTH, "0");
			client.executeMethod(bitsPost);

			// There should now be a file called text.txt on the BITS server
			// with the content "my very small content of the file"
			// You should be able to down load it in a browser with the url
			// defined by content above.

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
