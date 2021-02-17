package com.digivalet.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.digivalet.data.dto.ArticleResponse;
import com.google.gson.Gson;

public class PreferenceManager {

    public static final String PREFS_NAME = "local_pref";
    public static final String ITEMS = "items";

    public PreferenceManager() {
        super();
    }

    public void saveList(Context context, List<ArticleResponse> list) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(ITEMS, json);
        editor.commit();
    }


    public ArrayList<ArticleResponse> getList(Context context) {
        SharedPreferences settings;
        List<ArticleResponse> list;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(ITEMS)) {
            String jsonFavorites = settings.getString(ITEMS, null);
            Gson gson = new Gson();
            ArticleResponse[] items = gson.fromJson(jsonFavorites,
                    ArticleResponse[].class);

            list = Arrays.asList(items);
            list = new ArrayList<ArticleResponse>(list);
        } else
            return null;

        return (ArrayList<ArticleResponse>) list;
    }
}
