package com.motivaimagine.motivaimagine_trial.rest_client.user;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.motivaimagine.motivaimagine_trial.rest_client.BaseService;
import com.motivaimagine.motivaimagine_trial.rest_client.BuildConfig;
import com.motivaimagine.motivaimagine_trial.rest_client.R;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.UserInfoListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gpaez on 9/21/2017.
 */

public class UserController extends BaseService {

    private static UserController INSTANCE = new UserController();
    private UserController(){}

    public static UserController getInstance(){
        return INSTANCE;
    }

    public void login(Context context, String username, String password, final LoginListener listener){
        if(listener==null)
            return;
        listener.onLoginStart();
        String url = BuildConfig.REST_URL.concat(String.format(context.getString(R.string.uri_login),""+username,password));

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("username",username);
            parameters.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = getDefaultRequest(Request.Method.POST, url, null, false, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int id = response.getInt("id");
                    String token = response.getString("token");

                    listener.onLoginCompleted(id,token);
                } catch (JSONException e) {
                    listener.onLoginError("Ocurrió un error al interpretar la información_!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onLoginError("ERROR 404");
            }
        });

        getDefaultQueue(context).add(request);
    }

    public void getUserInfo(Context context,int userId, String token , final UserInfoListener listener){
        if(listener==null)
            return;
        listener.onUserInfoStart();

        String url = BuildConfig.REST_URL.concat(String.format(context.getString(R.string.uri_user_info),""+userId,token));

        JsonObjectRequest request = getDefaultRequest(Request.Method.GET, url, null, true, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    User user = new Gson().fromJson(String.valueOf(response),User.class);
                    listener.onUserInfoCompleted(user);
                }catch (Exception e){
                    listener.onUserInfoError("Ocurrió un error al interpretar la información_2");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onUserInfoError("Ocurrió un error al realizar la petición_3");
            }
        });

        getDefaultQueue(context).add(request);
    }

}
