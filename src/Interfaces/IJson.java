package Interfaces;
import org.json.JSONException;
import org.json.JSONObject;

public interface IJson<T> {
        JSONObject toJson() throws JSONException;
        void fromJson(JSONObject json)throws JSONException;
    }



