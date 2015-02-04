package nomster;

public class MyConstants {
public static final String CLIENT_ID="453077549340-0mbc5et33d267m2eg292sv19362ohosa.apps.googleusercontent.com";
public static final String CLIENT_SECRET="v7excrvIjKQvyAZORvgwdSE2";
public static final String SCOPE="openid%20email%20profile";
public static final String REDIRECT_URI="http://nomster-backend.appspot.com/oauth2callback";
public static final String RESPONSE_TYPE="code";
public static final String ACCESS_TYPE="offline";
public static final String GRANT_TYPE="authorization_code";
public static final String COOKIE_SUB="cookie_sub";
public static final String SQL_PRODUCTION_ADDRESS="jdbc:google:mysql://nomster-backend:nomster-sql-db/nomster?user=root";
public static final String SQL_DEVELOPMENT_ADDRESS="jdbc:mysql://173.194.82.203:3306/nomster?user=root"; // you must add your current ip to appengine console in order to access the dev sql
public static final String SQL_TABLE_USER="user";
public static final String SQL_TABLE_GROUP="group";
public static final String SQL_TABLE_GROUP_USER="group_user";
public static final String SQL_TABLE_GROUP_PUSH="push";
}
