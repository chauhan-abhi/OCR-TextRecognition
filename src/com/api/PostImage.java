package com.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.jdt.internal.compiler.ast.IfStatement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostImage {
	private static final String url = "https://api.ocr.space/parse/image";

	public String sendPost(String apiKey, boolean isOverlayRequired, String imageUrl, String language)
			throws Exception {
		URL obj = new URL(url); // OCR API Endpoints
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject postDataParams = new JSONObject();

		postDataParams.put("apikey", apiKey);// TODO Add your Registered API key
		postDataParams.put("isOverlayRequired", isOverlayRequired);
		postDataParams.put("url", imageUrl);
		postDataParams.put("language", language);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(getPostDataString(postDataParams));
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// return result
		return String.valueOf(response);

	}

	public String parseJSON(String url) throws JSONException {
		String parsedResult = "";
		JSONObject json = new JSONObject(url);
		JSONArray arr = json.getJSONArray("ParsedResults");
		for (int i = 0; i < arr.length(); i++)
			parsedResult = arr.getJSONObject(i).getString("ParsedText");
		return parsedResult;
	}

	public String blacklist(String parsedResult) {
		String permission="YES";
		Properties prop = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("blacklist.properties");

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s= parsedResult.toLowerCase().trim();
		String[] blacklisted = prop.getProperty("blacklisted_words").trim().split(",");
		for(String w:blacklisted){
			if (s.contains(w)) {
				permission="NO";
			}
		}
		

		return permission;
	}

	public String getPostDataString(JSONObject params) throws Exception {

		StringBuilder result = new StringBuilder();
		boolean first = true;

		Iterator<String> itr = params.keys();

		while (itr.hasNext()) {

			String key = itr.next();
			Object value = params.get(key);

			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value.toString(), "UTF-8"));

		}
		return result.toString();
	}
	

}
