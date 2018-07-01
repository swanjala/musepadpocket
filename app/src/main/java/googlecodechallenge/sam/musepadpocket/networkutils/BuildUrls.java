package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

import googlecodechallenge.sam.musepadpocket.R;

/**
 * This class builds Urls used to make network calls
 */

public final class BuildUrls {

    private static String STATIC_MUSE_BASE_URL;
    private static String STATIC_SIGN_UP_URL;
    private static Context context;
    private static String STATIC_LOGIN_URL;
    public static String STATIC_MUSE_URL;

    public BuildUrls(Context context){
        this.context = context;
        STATIC_MUSE_BASE_URL = context.getResources().getString(R.string.muse_base_url);

    }

    public static URL buildUrlForUserSignUp() {
        STATIC_SIGN_UP_URL = context.getString(R.string.sign_up_url);
        Uri signUpUrI = Uri.parse(STATIC_MUSE_BASE_URL).buildUpon()
                .appendEncodedPath(STATIC_SIGN_UP_URL)
                .build();
        try {
            return new URL(signUpUrI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static URL buildUrlForUserLogin() {
        STATIC_LOGIN_URL = context.getString(R.string.login_base_url);
        Uri userLogInUrI = Uri.parse(STATIC_MUSE_BASE_URL).buildUpon()
                .appendEncodedPath(STATIC_LOGIN_URL)
                .build();
        try {
            return new URL(userLogInUrI.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static URL buildUrlForMuseActions() {
        STATIC_MUSE_URL= context.getString(R.string.static_muselist_url);
        Uri museActionsUri = Uri.parse(STATIC_MUSE_BASE_URL).buildUpon()
                .appendEncodedPath(STATIC_MUSE_URL)
                .build();
        try {
            return new URL(museActionsUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static URL buildUrlForItemActions(String museId) {
        STATIC_MUSE_URL= context.getString(R.string.static_muselist_url);
        Uri itemActionsUri = Uri.parse(STATIC_MUSE_BASE_URL).buildUpon()
                .appendEncodedPath(STATIC_MUSE_URL)
                .appendEncodedPath(museId+"/items")
                .build();
        try {
            return new URL(itemActionsUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
