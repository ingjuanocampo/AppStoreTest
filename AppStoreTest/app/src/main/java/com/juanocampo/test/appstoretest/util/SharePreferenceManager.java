package com.juanocampo.test.appstoretest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by juanocampo on 6/19/16.
 */
//Singleton
public class SharePreferenceManager {

    private static SharePreferenceManager ourInstance;

    public static SharePreferenceManager getInstance(Context context) {
        return ourInstance == null ? ourInstance = new SharePreferenceManager(context) : ourInstance;
    }

    private Context mContext;
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public SharePreferenceManager(Context context){
        this.mContext=context;
        prefs = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        editor=prefs.edit();
    }

    public String getStringMemory(String Tag, String holder){
        return prefs.getString(Tag, holder);
    }
    public int getIntMemory(String Tag){
        return prefs.getInt(Tag,0);
    }
    public float getFloatMemory(String Tag){
        return prefs.getFloat(Tag,0);
    }
    public boolean getBooleanMemory(String Tag){
        return prefs.getBoolean(Tag,false);
    }
    public long getLongMemmory(String Tag){
        return prefs.getLong(Tag,0);
    }


    public void putInMemory(String Tag, Object object){
        try {
            if (object instanceof Integer){
                editor.putInt(Tag,(Integer)object);
            }else if (object instanceof Float){
                editor.putFloat(Tag,(Float)object);
            }else if (object instanceof String){
                editor.putString(Tag,(String)object);
            }else if (object instanceof Boolean){
                editor.putBoolean(Tag,(Boolean)object);
            }else if (object instanceof Long){
                editor.putLong(Tag,(Long)object);
            }
            editor.commit();

        }catch (Exception e){
            Log.e("SharePrefeceManager", "it can not write in memory");
        }
    }


    public void clearAllData() {
        editor.clear();
        editor.commit();

    }
}
