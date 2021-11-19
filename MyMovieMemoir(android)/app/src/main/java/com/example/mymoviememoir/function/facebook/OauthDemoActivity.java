package com.example.mymoviememoir.function.facebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.example.mymoviememoir.R;
import com.squareup.picasso.Picasso;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OauthDemoActivity extends AppCompatActivity {

    private static final String SHARED_PREFERENCES_NAME = "AuthStatePreference";
    private static final String AUTH_STATE = "AUTH_STATE";
    private static final String USED_INTENT = "USED_INTENT";
    private static final String LOGIN_HINT = "login_hint";

    private static final String LOG_TAG = "Oauth_Tag";


    // state
    AuthState mAuthState;

    // views
    Button mAuthorize;
    Button mMakeApiCall;
    Button mSignOut;
    TextView mGivenName;
    TextView mFamilyName;
    TextView mFullName;
    ImageView mProfileView;

    // login hint
    protected String mLoginHint;

    // broadcast receiver for app restrictions changed broadcast
    BroadcastReceiver mRestrictionsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuthorize = findViewById(R.id.authorize);
        mMakeApiCall = findViewById(R.id.makeApiCall);
        mSignOut = findViewById(R.id.signOut);
        mGivenName = findViewById(R.id.givenName);
        mFamilyName = findViewById(R.id.familyName);
        mFullName = findViewById(R.id.fullName);
        mProfileView = findViewById(R.id.profileImage);

        enablePostAuthorizationFlows();

        // wire click listeners
        mAuthorize.setOnClickListener(new AuthorizeListener(this));

        // Retrieve app restrictions and take appropriate action
//        getAppRestrictions();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve app restrictions and take appropriate action
//        getAppRestrictions();

        // Register a receiver for app restrictions changed broadcast
//        registerRestrictionsReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Unregister receiver for app restrictions changed broadcast
//        unregisterReceiver(mRestrictionsReceiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkIntent(intent);
    }

    private void checkIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case "com.monash.m3application.ui.canlendar.HANDLE_AUTHORIZATION_RESPONSE":
                    if (!intent.hasExtra(USED_INTENT)) {
                        handleAuthorizationResponse(intent);
                        intent.putExtra(USED_INTENT, true);
                    }
                    break;
                default:
                    // do nothing
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIntent(getIntent());

        // Register a receiver for app restrictions changed broadcast
//        registerRestrictionsReceiver();
    }

    private void enablePostAuthorizationFlows() {
        mAuthState = restoreAuthState();
        if (mAuthState != null && mAuthState.isAuthorized()) {
            if (mMakeApiCall.getVisibility() == View.GONE) {
                mMakeApiCall.setVisibility(View.VISIBLE);
                mMakeApiCall.setOnClickListener(new MakeApiCallListener(this, mAuthState, new AuthorizationService(this)));
            }
            if (mSignOut.getVisibility() == View.GONE) {
                mSignOut.setVisibility(View.VISIBLE);
                mSignOut.setOnClickListener(new SignOutListener(this));
            }
        } else {
            mMakeApiCall.setVisibility(View.GONE);
            mSignOut.setVisibility(View.GONE);
        }
    }

    /**
     * Exchanges the code, for the {@link TokenResponse}.
     *
     * @param intent represents the {@link Intent} from the Custom Tabs or the System Browser.
     */
    private void handleAuthorizationResponse(@NonNull Intent intent) {
        AuthorizationResponse response = AuthorizationResponse.fromIntent(intent);
        AuthorizationException error = AuthorizationException.fromIntent(intent);
        final AuthState authState = new AuthState(response, error);
        if (response != null) {
            Log.i(LOG_TAG, String.format("Handled Authorization Response %s ", authState.toJsonString()));
            AuthorizationService service = new AuthorizationService(this);
            service.performTokenRequest(response.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException exception) {
                    if (exception != null) {
                        Log.w(LOG_TAG, "Token Exchange failed", exception);
                    } else {
                        if (tokenResponse != null) {
                            authState.update(tokenResponse, exception);
                            persistAuthState(authState);
                            Log.i(LOG_TAG, String.format("Token Response [ Access Token: %s, ID Token: %s ]",
                                    tokenResponse.accessToken, tokenResponse.idToken));
                        }
                    }
                }
            });
        }
    }

    private void persistAuthState(@NonNull AuthState authState) {
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
                .putString(AUTH_STATE, authState.toJsonString())
                .commit();
        enablePostAuthorizationFlows();
    }

    private void clearAuthState() {
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .edit()
                .remove(AUTH_STATE)
                .apply();
    }

    @Nullable
    private AuthState restoreAuthState() {
        String jsonString = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getString(AUTH_STATE, null);
        if (!TextUtils.isEmpty(jsonString)) {
            try {
                return AuthState.fromJson(jsonString);
            } catch (JSONException jsonException) {
                // should never happen
            }
        }
        return null;
    }

    /**
     * Kicks off the authorization flow.
     */
    public static class AuthorizeListener implements Button.OnClickListener {

        private final OauthDemoActivity mMainActivity;

        public AuthorizeListener(@NonNull OauthDemoActivity mainActivity) {
            mMainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {
            AuthorizationServiceConfiguration serviceConfiguration = new AuthorizationServiceConfiguration(
                    Uri.parse("https://accounts.google.com/o/oauth2/v2/auth") /* auth endpoint */,// get authorization code
                    //get api access token the same as api key in google map api key
                    Uri.parse("https://www.googleapis.com/oauth2/v4/token") /* token endpoint */
            );
            AuthorizationService authorizationService = new AuthorizationService(view.getContext());
            String clientId = "511828570984-fuprh0cm7665emlne3rnf9pk34kkn86s.apps.googleusercontent.com";
            Uri redirectUri = Uri.parse("com.google.codelabs.appauth:/oauth2callback");
            AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(
                    serviceConfiguration,
                    clientId,
                    AuthorizationRequest.RESPONSE_TYPE_CODE,
                    redirectUri
            );
            builder.setScopes("profile");

            if (mMainActivity.getLoginHint() != null) {
                Map loginHintMap = new HashMap<String, String>();
                loginHintMap.put(LOGIN_HINT, mMainActivity.getLoginHint());
                builder.setAdditionalParameters(loginHintMap);

                Log.i(LOG_TAG, String.format("login_hint: %s", mMainActivity.getLoginHint()));
            }

            AuthorizationRequest request = builder.build();
            String action = "com.google.codelabs.appauth.HANDLE_AUTHORIZATION_RESPONSE";
            // intent to invoke OauthDemoActivity
            Intent postAuthorizationIntent = new Intent(action);
            PendingIntent pendingIntent = PendingIntent.getActivity(view.getContext(), request.hashCode(), postAuthorizationIntent, 0);
            authorizationService.performAuthorizationRequest(request, pendingIntent);
        }
    }

    public static class SignOutListener implements Button.OnClickListener {

        private final OauthDemoActivity mMainActivity;

        public SignOutListener(@NonNull OauthDemoActivity mainActivity) {
            mMainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {
            mMainActivity.mAuthState = null;
            mMainActivity.clearAuthState();
            mMainActivity.enablePostAuthorizationFlows();
        }
    }

    public static class MakeApiCallListener implements Button.OnClickListener {

        private final OauthDemoActivity mMainActivity;
        private AuthState mAuthState;
        private AuthorizationService mAuthorizationService;

        public MakeApiCallListener(@NonNull OauthDemoActivity mainActivity, @NonNull AuthState authState, @NonNull AuthorizationService authorizationService) {
            mMainActivity = mainActivity;
            mAuthState = authState;
            mAuthorizationService = authorizationService;
        }

        @Override
        public void onClick(View view) {
            mAuthState.performActionWithFreshTokens(mAuthorizationService, new AuthState.AuthStateAction() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void execute(@Nullable String accessToken, @Nullable String idToken, @Nullable AuthorizationException exception) {
                    new AsyncTask<String, Void, JSONObject>() {
                        @Override
                        protected JSONObject doInBackground(String... tokens) {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("https://www.googleapis.com/oauth2/v3/userinfo")
                                    .addHeader("Authorization", String.format("Bearer %s", tokens[0]))
                                    .build();

                            try {
                                Response response = client.newCall(request).execute();
                                String jsonBody = response.body().string();
                                Log.i(LOG_TAG, String.format("User Info Response %s", jsonBody));
                                return new JSONObject(jsonBody);
                            } catch (Exception exception) {
                                Log.w(LOG_TAG, exception);
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(JSONObject userInfo) {
                            if (userInfo != null) {
                                String fullName = userInfo.optString("name", null);
                                String givenName = userInfo.optString("given_name", null);
                                String familyName = userInfo.optString("family_name", null);
                                String imageUrl = userInfo.optString("picture", null);
                                if (!TextUtils.isEmpty(imageUrl)) {
                                    Picasso.with(mMainActivity)
                                            .load(imageUrl)
                                            .placeholder(R.drawable.ic_account)
                                            .into(mMainActivity.mProfileView);
                                }
                                if (!TextUtils.isEmpty(fullName)) {
                                    mMainActivity.mFullName.setText(fullName);
                                }
                                if (!TextUtils.isEmpty(givenName)) {
                                    mMainActivity.mGivenName.setText(givenName);
                                }
                                if (!TextUtils.isEmpty(familyName)) {
                                    mMainActivity.mFamilyName.setText(familyName);
                                }

                                String message;
                                if (userInfo.has("error")) {
                                    message = String.format("%s [%s]", mMainActivity.getString(R.string.request_failed), userInfo.optString("error_description", "No description"));
                                } else {
                                    message = mMainActivity.getString(R.string.request_complete);
                                }
                                Snackbar.make(mMainActivity.mProfileView, message, Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    }.execute(accessToken);
                }
            });
        }
    }

//    private void getAppRestrictions(){
//        RestrictionsManager restrictionsManager =
//                (RestrictionsManager) this
//                        .getSystemService(Context.RESTRICTIONS_SERVICE);
//
//        Bundle appRestrictions = restrictionsManager.getApplicationRestrictions();
//
//        // Block user if KEY_RESTRICTIONS_PENDING is true, and save login hint if available
//        if(!appRestrictions.isEmpty()){
//            if(appRestrictions.getBoolean(UserManager.
//                    KEY_RESTRICTIONS_PENDING)!=true){
//                mLoginHint = appRestrictions.getString(LOGIN_HINT);
//            }
//            else {
//                Toast.makeText(this,R.string.restrictions_pending_block_user,
//                        Toast.LENGTH_LONG).show();
//                finish();
//            }
//        }
//    }

//    private void registerRestrictionsReceiver(){
//        IntentFilter restrictionsFilter =
//                new IntentFilter(Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED);
//
//        mRestrictionsReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                getAppRestrictions();
//            }
//        };
//
//        registerReceiver(mRestrictionsReceiver, restrictionsFilter);
//    }

    public String getLoginHint() {
        return mLoginHint;
    }
}