package pl.rzemla.sharedprefstest;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class SharedPrefsUtils {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Gson gson;

    private String KEY_CUSTOM_CHARACTERISTIC_NAMES = "KEY_CUSTOM_CHARACTERISTIC_NAMES";


    public SharedPrefsUtils(Context context) {
        prefs = context.getSharedPreferences("pl.rzemla.sharedprefstest",Context.MODE_PRIVATE);
        editor = prefs.edit();
        gson = new Gson();
    }


    public HashMap<String,String> getCharacCustomNamesMap() {

        String defValue = new Gson().toJson(new HashMap<String,String>());
        String json = prefs.getString(KEY_CUSTOM_CHARACTERISTIC_NAMES,defValue);
        TypeToken<HashMap<String,String>> token = new TypeToken<HashMap<String, String>>(){};

        HashMap<String,String> map = gson.fromJson(json,token.getType());
        return map;
    }

    public void saveCharaCustomNamesMap(HashMap<String,String> map) {
        String json = gson.toJson(map);
        editor.putString(KEY_CUSTOM_CHARACTERISTIC_NAMES,json);
        editor.apply();
    }



}
