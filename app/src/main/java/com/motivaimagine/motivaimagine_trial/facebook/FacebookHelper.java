package com.motivaimagine.motivaimagine_trial.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FacebookHelper {
  private FacebookListener mListener;
  private CallbackManager mCallBackManager;
  public String FB_email;
  public FacebookHelper(@NonNull FacebookListener facebookListener) {
    mListener = facebookListener;
    mCallBackManager = CallbackManager.Factory.create();


    FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {


      @Override public void onSuccess(LoginResult loginResult) {
        requestEmail(loginResult.getAccessToken());
        Profile profile = Profile.getCurrentProfile();

        try {
          mListener.onFbSignInSuccess(loginResult.getAccessToken().getToken(),
              loginResult.getAccessToken().getUserId(),profile.getFirstName(),profile.getLastName(),profile.getProfilePictureUri(100,100).toString());
        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }



      @Override public void onCancel() {
        mListener.onFbSignInFail("User cancelled operation");
      }

      @Override public void onError(FacebookException e) {
        mListener.onFbSignInFail(e.getMessage());
      }
    };
    LoginManager.getInstance().registerCallback(mCallBackManager, mCallBack);
  }

  @NonNull @CheckResult public CallbackManager getCallbackManager() {
    return mCallBackManager;
  }

  public void performSignIn(Activity activity) {
    LoginManager.getInstance()
        .logInWithReadPermissions(activity,
            Arrays.asList("public_profile", "user_friends", "email"));
  }

  public void performSignIn(Fragment fragment) {
    LoginManager.getInstance()
        .logInWithReadPermissions(fragment,
            Arrays.asList("public_profile", "user_friends", "email"));
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    mCallBackManager.onActivityResult(requestCode, resultCode, data);
  }

  public void performSignOut() {
    LoginManager.getInstance().logOut();
    mListener.onFBSignOut();
  }


  private void requestEmail(AccessToken currentAccessToken) {
    GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
      @Override
      public void onCompleted(JSONObject object, GraphResponse response) {
        if (response.getError() != null) {
          Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
          return;
        }
        try {
          String email = object.getString("email");
          setEmail(email);
        } catch (JSONException e) {
          Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
      }
    });
    Bundle parameters = new Bundle();
    parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
    request.setParameters(parameters);
    request.executeAsync();
  }


  public void setEmail(String email) {
    this.FB_email=email;
  }

  public String getFB_email(){
    return FB_email;
  }



}
