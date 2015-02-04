package nomster;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.MyUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

@SuppressWarnings("serial")
public class Oauth2callbackServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
	    resp.setContentType("text/plain");
	    out.println("get");
	    out.println(req.getSession().getId());
	    if(req.getParameter("state")!=null)
	    {	   	
	    	if(req.getParameter("state").equals(req.getSession().getId()))
		    {
		    	out.println("valid login from session");
		    	String code=req.getParameter(MyConstants.RESPONSE_TYPE);
		    	out.println("code:"+code);
		    	String data ="code="+code
		    			+"&client_id="+MyConstants.CLIENT_ID
		    			+"&client_secret="+MyConstants.CLIENT_SECRET
		    			+"&redirect_uri="+MyConstants.REDIRECT_URI
		    			+"&grant_type="+MyConstants.GRANT_TYPE;
		    	try{
			    	String jsonStr=MyUtils.httpPostRequest("https://www.googleapis.com/oauth2/v3/token", data, null);
			    	if(MyUtils.getStringFromJSONString(jsonStr, "error")!=null)
			    	{
			    		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    	}
			    	else
			    	{			    		
				    	String accessToken=MyUtils.getStringFromJSONString(jsonStr, "access_token");
				    	out.println("access_token:"+accessToken);
				    	String idToken = MyUtils.getStringFromJSONString(jsonStr, "id_token");
				    	out.println("id_token:"+idToken);				    	
				    	String expiresIn=MyUtils.getStringFromJSONString(jsonStr, "expires_in");
				    	out.println("expires_in:"+expiresIn);
				    	String tokenType=MyUtils.getStringFromJSONString(jsonStr, "token_type");
				    	out.println("token_type:"+tokenType);
				    	String refreshToken=MyUtils.getStringFromJSONString(jsonStr, "refresh_token");	
				    	out.println("refresh_token:"+refreshToken);
				    	out.println("=====");
				    	//String decodedData =new String(DatatypeConverter.parseBase64Binary(idToken));
				    	GoogleIdToken.Payload p = tokenDecode(idToken);
				    	out.println("iss:"+p.get("iss"));//The Issuer Identifier for the Issuer of the response.
				    	out.println("sub:"+p.get("sub"));//An identifier for the user, unique among all Google accounts.
				    	out.println("email:"+p.get("email"));
				    	out.println("aud:"+p.get("aud"));//Identifies the audience that this ID token is intended for. It must be one of the OAuth 2.0 client IDs of your application.
				    	out.println("iat:"+p.get("iat"));
				    	out.println("exp:"+p.get("exp"));
			    	}
			    		
		    	}catch(Exception e)
		    	{
		    		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    		out.println(e.getLocalizedMessage());
		    	}
		    }
		    else
		    {
		    	out.println("invalid session id");
		    	resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    }
	    }	
	    else
	    {
	    	out.println("missing state");
	    	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();
	    resp.setContentType("text/plain");
	    out.println("post");
	}
	private GoogleIdToken.Payload tokenDecode(String idTokenValue) throws IOException
	{
		JsonFactory  mJFactory = new GsonFactory();
		GoogleIdToken token = GoogleIdToken.parse(mJFactory, idTokenValue);
		GoogleIdToken.Payload payload = token.getPayload();
		
		 return payload;
	}
}
