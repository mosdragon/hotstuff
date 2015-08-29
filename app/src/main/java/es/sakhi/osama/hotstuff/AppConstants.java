package es.sakhi.osama.hotstuff;

/**
 * Created by osama on 8/29/15.
 */
public interface AppConstants {

//    This is for a Chevy Volt
    String DEFAULT_VIN = "1G1RD6E44CU000002";

    String TOKEN_ENDPOINT = "https://developer.gm.com/api/v1/oauth/access_token";
    String API_ENDPOINT = "https://developer.gm.com/api/v1/";

//    Must format with vin #
    String VEHICLE_COMMANDS = API_ENDPOINT + "account/vehicles/"+ DEFAULT_VIN +"/commands/";
    String COMMAND_START = VEHICLE_COMMANDS + "start";
    String COMMAND_STOP = VEHICLE_COMMANDS + "cancelStart";

    String CLIENT_ID = "l7xx9fef898c61c24924bc9be15af52fcb57";
    String CLIENT_SECRET = "ffe990f7b9914ac78d3825791910771f";
    String API_KEY = "l7xx9fef898c61c24924bc9be15af52fcb57";
    String BASIC = "Basic";
    String BEARER = "Bearer";

    String ACCESS_TOKEN = "access_token";
    String EXPIRES_IN = "expires_in";
    String EXPIRATION_DATE = "expiration_date";

    String COMMAND_RESPONSE = "commandResponse";
    String STATUS  = "status";
    String IN_PROGRESS = "inProgress";
}
