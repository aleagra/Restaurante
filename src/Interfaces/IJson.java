package Interfaces;
import org.json.JSONException;
import org.json.JSONObject;

public interface IJson {
        JSONObject toJson() throws JSONException;
        void fromJson(JSONObject json)throws JSONException;
    }



