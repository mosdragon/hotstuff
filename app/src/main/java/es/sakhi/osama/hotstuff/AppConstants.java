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

//    Config Keys

    String DEFROST_FRONT_WINDOW = "defrost_front_window";
    String DEFROST_BACK_WINDOW = "defrost_back_window";
    String AUTO_DEFROST = "auto_defrost";
    String SET_TEMP_TO = "set_temp_to";
    String AUTO_TEMP = "auto_temp";
    String FIND_CLOSE_GAS = "find_close_gas";
    String FIND_CHEAP_GAS = "find_cheap_gas";
    String FIND_NO_GAS = "find_no_gas";
    String ACTIVATE_HEATED_SEATS = "activate_heated_seats";
    String ACTIVATE_COOLED_SEATS = "activate_cooled_seats";



}
