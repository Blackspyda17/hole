package com.motivaimagine.motivaimagine_trial.rest_client.user;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.motivaimagine.motivaimagine_trial.rest_client.BaseService;
import com.motivaimagine.motivaimagine_trial.rest_client.BuildConfig;
import com.motivaimagine.motivaimagine_trial.rest_client.R;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.DoctorListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.listeners.LoginListener;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Doctor;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.Error;
import com.motivaimagine.motivaimagine_trial.rest_client.user.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gpaez on 9/21/2017.
 */

public class Controller extends BaseService {

    private static Controller INSTANCE = new Controller();
    private Controller(){}

    public static Controller getInstance(){
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
                parameters.put("login",method);
                parameters.put("pass",password);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if(token!=null){
            try {
                parameters.put("email",username);
                parameters.put("login",method);
                parameters.put("token",token);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if(apptoken!=null){
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
                    User user=new User(id,name,lastname,country_id,app_token,type,email,0,null,null);
                    listener.onLoginCompleted(user);
                } catch (Exception e) {
                    try {
                        Error error =new Error(response.getBoolean("status"),response.getString("code"));
                        listener.onLoginError(error);
                    }catch (JSONException E){
                        Error error1= new Error(false,"Parsing Error");
                        listener.onLoginError(error1);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                Error error1= new Error(false,"Server Error");
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
                    User user=new User(id,name,lastname,country_id,app_token,type,email,0,null,null);
                    listener.onLoginCompleted(user);
                } catch (Exception e) {
                    Error error = null;
                    try {
                        error = new Error(response.getBoolean("status"),response.getString("code"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        error = new Error(false,"Parsing Error");
                    }
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




    public void getDoctorList(Context context,final DoctorListener listener){
        if(listener==null)
            return;
        listener.onDoc_ListStart();
        final ArrayList<Doctor> doctors=new ArrayList<>();

        String url = BuildConfig.REST_URL.concat(String.format(context.getString(R.string.uri_doctors)));


        JsonArrayRequest request = getDefaultRequest(Request.Method.GET, url, null, false, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{
                    for(int i=0;i<response.length();i++){
                        // Get current json object


                        Doctor doctor = new Gson().fromJson(String.valueOf(response.getJSONObject(i)),Doctor.class);
                        doctors.add(doctor);
                        // Get the current student (json object) data
//                        String firstName = student.getString("firstname");
//                        String lastName = student.getString("lastname");
//                        String age = student.getString("age");

                        // Display the formatted json data in text view
                    }

                    if(doctors.isEmpty()){
                        listener.onDoc_ListError(new Error(false,"Couldn't Complete the request"));
                    }else{
                        listener.onDoc_ListCompleted(doctors);
                    }

                }catch (Exception e){
                    listener.onDoc_ListError(new Error(false,"Couldn't Parsing data "+e.getMessage()));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onDoc_ListError(new Error(false,"Couldn't REQUESTING data "+error.getMessage()));
            }
        });

        getDefaultQueue(context).add(request);
    }

    public void Implant_Reg (Context context1,String user_id, String doctor_id,String doctor_name,String surgery_date,String registrant_id,int implantL,String validationL,String implantR,String validationR,String amount, final LoginListener listener){
        if(listener==null)
            return;
        listener.onLoginStart();
        String url = BuildConfig.REST_URL.concat(String.format(context1.getString(R.string.uri_register)));
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("user_id",user_id);
            parameters.put("doctor_id",doctor_id);
            parameters.put("doctor_name",doctor_name);
            parameters.put("surgery_date",surgery_date);
            parameters.put("registrant_id",registrant_id);
            parameters.put("implantL",implantL);
            parameters.put("validationL",validationL);
            parameters.put("implantR",implantR);
            parameters.put("validationR",validationR);
            parameters.put("amount",amount);

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
                    User user=new User(id,name,lastname,country_id,app_token,type,email,0,null,null);
                    listener.onLoginCompleted(user);
                } catch (Exception e) {
                    Error error = null;
                    try {
                        error = new Error(response.getBoolean("status"),response.getString("code"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        error = new Error(false,"Parsing Error");
                    }
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
