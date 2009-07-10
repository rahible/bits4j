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
package org.bits4j;

/**
 * BITS-Host-Id
 * 
 * Optional. Include this header only if the BITS IIS extension property,
 * BITSHostId, is set. Replace PublicHostName with the server name or IP address
 * from the BITSHostId property.
 * 
 * The client must replace the server portion of the remote-URL on all
 * subsequent packets. If the client does not specify this host name on
 * subsequent packets, it is possible that the job will begin again on another
 * server in the farm, leaving a partial upload file on the previous server.
 * 
 * 
 * BITS-Host-Id-Fallback-Timeout
 * 
 * Optional. Include this header only if the BITS-Host-Id header is specified.
 * Replace Timeout with the time-out value from the BITSHostIdFallbackTimeout
 * property. The BITSHostIdFallbackTimeout property is one of the BITS IIS
 * extension properties.
 * 
 * The client uses the time-out period to determine how long it tries to
 * reconnect to the server name specified in the BITS-Host-Id header before
 * reverting to the host name specified in the remote file name of the job. The
 * timer begins when BITS is unable to connect to the BITS-Host-Id server. The
 * timer is reset when a connection to the server is restored. If a time-out
 * period is not specified, the client never reverts to the host name specified
 * in the remote file name.
 * 
 */
public class Constants {
	public static final String BITS_ERROR_CODE = "BITS-Error-Code";
	public static final String BITS_ERROR_CONTEXT = "BITS-Error-Context";
	public static final String BITS_PACKET_TYPE = "BITS-Packet-Type";
	public static final String BITS_SUPPORTED_PROTOCOLS = "BITS-Supported-Protocols";
	public static final String BITS_1_5_UPLOAD_PROTOCAL_GUID = "{7df0354d-249b-430f-820d-3d2a9bef4931}";
	public static final String BITS_SESSION_ID = "BITS-Session-Id";
	public static final String BITS_HOST_ID = "BITS-Host-Id";
	public static final String BITS_HOST_ID_FALLBACK_TIMEOUT = "BITS-Host-Id-Fallback-Timeout";
	public static final String BITS_RECEIVED_CONTENT_RANGE = "BITS-Received-Content-Range";
	public static final String BITS_REPLY_URL = "BITS-Reply-URL";
	public static final String CONTENT_RANGE = "Content-Range";
	/*
	 * Do not send the CONTENT_ENCODING header if the encoding type is Identity.
	 * The BITS server supports only Identity encoding.
	 */
	public static final String CONTENT_ENCODING = "Content-Encoding";
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_NAME = "Content-Name";
	public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
	public static final String USER_AGENT = "User-Agent";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String IDENTITY = "Identity";
	public static final String ACK = "ACK";
	public static final String BITS_ERROR = "BITS-Error";

	public static final String FRAGMENT = "FRAGMENT";
	public static final String PING = "PING";
	public static final String CREATE_SESSION = "CREATE-SESSION";
	public static final String CLOSE_SESSION = "CLOSE-SESSION";
	public static final String CANCEL_SESSION = "CANCEL-SESSION";
}
