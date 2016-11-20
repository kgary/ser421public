package com.coreservlets.networking;

import java.io.IOException;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;

/** Defines some high-level utilities for getting the body of GET and POST requests.
 *  Uses the Apache HttpComponents suite, version 4.x. The exact version number is not specified
 *  by the Android documentation, unfortunately.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class HttpUtils { 
  /** Retrieve body of an address as a single large String, using HTTP GET. 
   *  Query parameters are not set separately, but should be URL-encoded and attached to
   *  the base address in standard form. For example, the address might be
   *  a single String like "http://example.com/blah?param1=foo+bar&param2=baz".
   *  The return value is a single String containing the entire body of
   *  the response, assuming the response was successful (status in the 200's).
   * 
   * @param address the URL as a String, with query string (if any) attached
   * @return the body of the response, with no headers or status line
   * @throws IOException if connection is refused or there is another problem
   * @throws ClientProtocolException if HTTP status is 300 or greater
   */
  public static String urlContent(String address) throws IOException, ClientProtocolException {
    HttpClient client = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet(address);
    ResponseHandler<String> handler = new BasicResponseHandler();
    return(client.execute(httpGet, handler));
  }
  
  /** Retrieve body of an address as a single large String, using HTTP POST. 
   *  Query parameters are supplied separately, as alternating names and values,
   *  and should NOT be URL-encoded because the utility encodes them for you.
   *  For example, a typical request might look like
   *  urlContentPost("http://example.com/blah", "param1", "foo bar", "param2", "baz").
   *  The return value is a single String containing the entire body of
   *  the response, assuming the response was successful (status in the 200's).
   * 
   * @param address the base URL as a String, with no query parameters attached
   * @param paramNamesAndValues alternating param names and param values, un-encoded
   * @return the body of the response, with no headers or status line
   * @throws IOException if connection is refused or there is another problem
   * @throws ClientProtocolException if HTTP status is 300 or greater
   */
  public static String urlContentPost(String address, String ... paramNamesAndValues) 
      throws IOException, ClientProtocolException {
    HttpClient client = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(address);
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    for(int i=0; i<paramNamesAndValues.length-1; i=i+2) {
      String paramName = paramNamesAndValues[i];
      String paramValue = paramNamesAndValues[i+1];  // NOT URL-Encoded
      params.add(new BasicNameValuePair(paramName, paramValue));
    }
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
    httpPost.setEntity(entity);
    ResponseHandler<String> handler = new BasicResponseHandler();
    return(client.execute(httpPost, handler));
  }
}
