package com.motivaimagine.motivaimagine_trial.google;

public interface GoogleListener {
  void onGoogleAuthSignIn(String authToken, String userId,String email,String lastname,String name,String url);

  void onGoogleAuthSignInFailed(String errorMessage);

  void onGoogleAuthSignOut();
}
