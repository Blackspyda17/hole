package com.motivaimagine.motivaimagine_trial.rest_client;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by gpaez on 9/21/2017.
 */

public class BaseService {

    protected JsonObjectRequest getDefaultRequest(int method, String url, JSONObject parameters, final boolean fromCache, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        if(!fromCache)
            url = url + (url.contains("?")? "&" : "?") +  "_cache=false";
        JsonObjectRequest request = new JsonObjectRequest(method,url,parameters,listener,errorListener){

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
            {
                try
                {
                    if(!fromCache)
                        return super.parseNetworkResponse(response);
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    return Response.success(new JSONObject(jsonString),
                            enforceClientCaching(HttpHeaderParser.parseCacheHeaders(response),
                                    response));
                } catch (Exception e)
                {
                    return super.parseNetworkResponse(response);
                }
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                1000 * 60, // 60 seconds
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return request;
    }

    protected JsonArrayRequest getDefaultRequest(int method, String url, JSONArray parameters, final boolean fromCache, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        if(!fromCache)
            url = url + (url.contains("?")? "&" : "?") +  "_cache=false";
        JsonArrayRequest request = new JsonArrayRequest(method,url,parameters,listener,errorListener){

            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response)
            {
                try
                {
                    if(!fromCache)
                        return super.parseNetworkResponse(response);
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    return Response.success(new JSONArray(jsonString),
                            enforceClientCaching(HttpHeaderParser.parseCacheHeaders(response),
                                    response));
                } catch (Exception e)
                {
                    return super.parseNetworkResponse(response);
                }
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                1000 * 60, // 60 seconds
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return request;
    }

    protected static final int DEFAULT_CLIENT_CACHE_EXPIRING = 1000 * 60 * 5; // 5 minutes;
    protected Cache.Entry enforceClientCaching(Cache.Entry entry, NetworkResponse response)
    {
        long now = System.currentTimeMillis();
        if (entry == null) {
            entry = new Cache.Entry();
            entry.data = response.data;
            entry.etag = response.headers.get("ETag");
            entry.softTtl = now + DEFAULT_CLIENT_CACHE_EXPIRING;
            entry.ttl = entry.softTtl;
            entry.serverDate = now;
            entry.responseHeaders = response.headers;
        } else if (entry.isExpired()) {
            entry.softTtl = now + DEFAULT_CLIENT_CACHE_EXPIRING;
            entry.ttl = entry.softTtl;
        }
        return entry;
    }

    protected RequestQueue getDefaultQueue(Context context){
        return Volley.newRequestQueue(context);
    }
}
