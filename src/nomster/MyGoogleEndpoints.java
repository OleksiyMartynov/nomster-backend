package nomster;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MyGoogleEndpoints 
{
private String page;
public static final String ISSUER="issuer";
public static final String AUTHORIZATION_ENDPOINT="authorization_endpoint";
public static final String TOKEN_ENDPOINT="token_endpoint";
public static final String USERINFO_ENDPOINT="userinfo_endpoint";
public static final String REVOCATION_ENDPOINT="revocation_endpoint";
public static final String JWKS_URI="jwks_uri";
public static final String RESPONSE_TYPES_SUPPORTED="response_types_supported";
public static final String SUBJECT_TYPES_SUPPORTED="subject_types_supported";
public static final String ID_TOKEN_ALG_VALUES_SUPPORTED="id_token_alg_values_supported";
public MyGoogleEndpoints() throws IOException
{
	// build a URL
    String s = "https://accounts.google.com/.well-known/openid-configuration";
    URL url = new URL(s);
 
    // read from the URL
    Scanner scan = new Scanner(url.openStream());
    page = new String();
    while (scan.hasNext())
        page += scan.nextLine();
    scan.close();
}
public String getValue(String key) throws JSONException
{
	JSONObject jObj = new JSONObject(page);
	return jObj.getString(key);
}
}
