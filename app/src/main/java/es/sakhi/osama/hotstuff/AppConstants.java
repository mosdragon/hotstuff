package es.sakhi.osama.hotstuff;

/**
 * Created by osama on 8/29/15.
 */
public interface AppConstants {

//    This is for a Chevy Volt
    String DEFAULT_VIN = "1G1RD6E44CU000002";

    String VEHICLE_COMMANDS = "https://papi-plpl7g-sb.onstar.gm.com:20701/api/v1/";
    String TOKEN_ENDPOINT = "https://developer.gm.com/api/v1/oauth/access_token";
    String API_ENDPOINT = "https://developer.gm.com/api/v1/";
//    state, city
    String WEATHER_ENDPOINT_TEMPLATE = "http://api.wunderground.com/api/91a6fb7a08a58439/conditions/q/%s/%s.json";


    String CLIENT_ID = "l7xx9fef898c61c24924bc9be15af52fcb57";
    String CLIENT_SECRET = "ffe990f7b9914ac78d3825791910771f";
    String API_KEY = "l7xx9fef898c61c24924bc9be15af52fcb57";
    String BASIC = "Basic";
    String BEARER = "Bearer";

    String ACCESS_TOKEN = "access_token";
    String EXPIRES_IN = "expires_in";
    String EXPIRATION_DATE = "expiration_date";

}
