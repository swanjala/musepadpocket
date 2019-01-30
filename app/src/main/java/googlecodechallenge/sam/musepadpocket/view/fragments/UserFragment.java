package googlecodechallenge.sam.musepadpocket.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import googlecodechallenge.sam.musepadpocket.R;
import googlecodechallenge.sam.musepadpocket.viewmodel.UserViewModel;

public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";

    public UserFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = layoutInflater.inflate(R.layout.fragment_activity_login,
                container, false);

        final TextView tv_user_name = rootView.findViewById(R.id.et_user_name);
        final TextView tv_password = rootView.findViewById(R.id.et_password);
        Button bt_login = rootView.findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginUser(tv_user_name.getText().toString(),
                        tv_password.getText().toString());

            }
        });
        Button bt_signup = rootView.findViewById(R.id.bt_signup);

        final View promptView = LayoutInflater
                .from(getContext())
                .inflate(R.layout.input_dialog, null);


        bt_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(getContext());
                alerDialogBuilder.setView(promptView);

                alerDialogBuilder.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getContext(), "Added successfully",
                                        Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = alerDialogBuilder.create();
                alert.show();
            }
        });

        return rootView;

    }

    public void loginUser(String username, String password) {
        UserViewModel userViewModel = ViewModelProviders.of(this)
                .get(UserViewModel.class);
        userViewModel.loginUser(username, password);
    }
}
