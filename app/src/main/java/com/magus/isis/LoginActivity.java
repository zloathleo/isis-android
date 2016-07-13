package com.magus.isis;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.magus.isis.local.LocalCacheKey;
import com.magus.isis.net.res.FavoritesRes;
import com.magus.isis.net.res.MenuRes;
import com.magus.isis.net.res.base.BaseRes;
import com.magus.isis.net.res.domain.AuthorizationUser;
import com.magus.isis.net.NetInterface;
import com.magus.isis.net.res.AuthorizationUserRes;


import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(LoginActivity.class);
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mLoginNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        // Set up the login form.
        mLoginNameView = (AutoCompleteTextView) findViewById(R.id.loginName);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mLoginNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String loginName = mLoginNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(loginName)) {
            mLoginNameView.setError("请输入登录名");
            focusView = mLoginNameView;
            cancel = true;
        } else if (!isLoginNameValid(loginName)) {
            mLoginNameView.setError("登录名长度>2");
            focusView = mLoginNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(loginName, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isLoginNameValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 2;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Object> {

        private final String mLoginName;
        private final String mPassword;

        UserLoginTask(String loginName, String password) {
            mLoginName = loginName;
            mPassword = password;
        }

        @Override
        protected Object doInBackground(Void... params) {
            try {
                AuthorizationUserRes r =  NetInterface.putLogin(mLoginName,mPassword);
                AuthorizationUser auth = r.getData();
                if("000000".equals(r.getCode()) && auth != null){
                    SharedPreferences pref = getSharedPreferences(LocalCacheKey.USER, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(LocalCacheKey.ACCESSTOKEN, auth.getAccessToken());
                    editor.commit();

                    MenuRes menu = NetInterface.getMenu();
                    FavoritesRes favorites = NetInterface.getFavorites(auth.getAccessToken());
                    Logger.warn("登录后获得初始化数据",menu.toString() + "," + favorites.toString());
                    return new Object[]{menu,favorites};
                }else{
                    return r;
                }
            } catch (IOException e) {
                Logger.warn("登录异常",e);
                return e;
            }
        }

        @Override
        protected void onPostExecute(final Object success) {
            mAuthTask = null;
            showProgress(false);
            if(success instanceof IOException){
                AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("登录失败")
                        .setPositiveButton("确定", null)
                        .setMessage("网络异常").create();
                dialog.show();
                return;
            }
            if(success instanceof BaseRes){
                BaseRes r = (BaseRes)success;
                AlertDialog dialog = new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("登录失败")
                        .setPositiveButton("确定", null)
                        .setMessage(r.getMsg()).create();
                dialog.show();
                return;
            }
            Object[] data = (Object[]) success;
            MenuRes menu = (MenuRes) data[0];
            FavoritesRes favorites = (FavoritesRes) data[1];

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("menu",menu);
            intent.putExtra("favorites",favorites);
            startActivity(intent);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

