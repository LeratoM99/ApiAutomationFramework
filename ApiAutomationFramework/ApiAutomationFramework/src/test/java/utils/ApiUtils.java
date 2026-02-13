package utils;

import org.json.JSONObject;

public class ApiUtils {

    public static String createUserPayload(String name, String username, String email) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("username", username);
        obj.put("email", email);
        return obj.toString();
    }

    public static String updateUserPayload(String name, String email) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("email", email);
        return obj.toString();
    }
}
