package com.motivaimagine.motivaimagine_trial.rest_client.user;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.motivaimagine.motivaimagine_trial.rest_client.BaseService;
import com.motivaimagine.motivaimagine_trial.rest_client.BuildConfig;
import com.motivaimagine.motivaimagine_trial.rest_client.R;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
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

    public void login(final Context context, String username, String password,String method,String token,String apptoken, final LoginListener listener){

        if(listener==null)
            return;
        listener.onLoginStart();
        JSONObject parameters = new JSONObject();
        if(method.equals("E") && apptoken==null){

            try {
                parameters.put("email",username);
                parameters.put("pass",password);
                parameters.put("login",method);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        else if(method.equals("E")&& apptoken!=null){
            try {
                parameters.put("email",username);
                parameters.put("app_token",apptoken);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        String url = BuildConfig.REST_URL.concat(String.format(context.getString(R.string.uri_login)));

        JsonObjectRequest request = getDefaultRequest(Request.Method.POST, url, parameters, false, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int id = response.getInt("user_id");
                    String name =response.getString("first_name");
                    String lastname =response.getString("last_name");
                    int country_id =response.getInt("country_id");
                    String app_token =response.getString("app_token");
                    int type =response.getInt("type");
                    String email =response.getString("email");
                    User user=new User(id,name,lastname,country_id,app_token,type,email,0);
                    listener.onLoginCompleted(user);
                } catch (Exception e) {
                    try {
                        Error error =new Error(response.getBoolean("Status"),response.getString("Code"));
                        listener.onLoginError(error);
                    }catch (JSONException E){
                        Error error1= new Error(false,"Server Error");
                        listener.onLoginError(error1);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                Error error1= new Error(false,"Error Parseando");
                listener.onLoginError(error1);
            }
        });

        getDefaultQueue(context).add(request);

    }

    public void Register (Context context1,String name, String lastname,String email,String method,String platform,int country_id,String password,String token, final LoginListener listener){
        if(listener==null)
            return;
        listener.onLoginStart();
        String url = BuildConfig.REST_URL.concat(String.format(context1.getString(R.string.uri_register)));
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("email",email);
            parameters.put("first_name",name);
            parameters.put("last_name",lastname);
            parameters.put("method",method);
            parameters.put("platform",platform);
            parameters.put("country_id",country_id);
            parameters.put("pass",password);
            parameters.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = getDefaultRequest(Request.Method.POST, url, parameters, false, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   // User user = new Gson().fromJson(String.valueOf(response),User.class);

                    int id = response.getInt("user_id");
                    String name =response.getString("First_Name");
                    String lastname =response.getString("Last_Name");
                    int country_id =response.getInt("country_id");
                    String app_token =response.getString("app_token");
                    int type =response.getInt("type");
                    String email =response.getString("email");
                    User user=new User(id,name,lastname,country_id,app_token,type,email,0);
                    listener.onLoginCompleted(user);
                } catch (Exception e) {
                    Error error = new Gson().fromJson(String.valueOf(response),Error.class);
                    listener.onLoginError(error);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Error error1= new Error(false,"Error interpretando");
                listener.onLoginError(error1);
            }
        });

        getDefaultQueue(context1).add(request);
    }






}
