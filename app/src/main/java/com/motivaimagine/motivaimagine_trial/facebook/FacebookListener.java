package com.motivaimagine.motivaimagine_trial.facebook;

import java.util.concurrent.ExecutionException;

public interface FacebookListener {
  void onFbSignInFail(String errorMessage);

  void onFbSignInSuccess(String authToken, String userId, String name, String Lastname, String picture) throws ExecutionException, InterruptedException;

  void onFBSignOut();
}
