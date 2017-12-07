package com.sourcey.shuttlestopper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by afrahm on 12/5/2017.
 */

public class DriverLogin extends AppCompatActivity {
    private static final String TAG = "DriverLogin";
    private static final int REQUEST_DRIVER_LOGIN = 1;
    private static final int REQUEST_STUDENT_LOGIN=2;

    @Bind(R.id.driver_email) EditText _nameText;
    @Bind(R.id.driver_password) EditText _passwordText;
    @Bind(R.id.btn_driver_login) Button _driverLoginButton;
    @Bind(R.id.link_login)TextView _loginLink;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        ButterKnife.bind(this);

        /*_driverSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverSignup();
            }
        });*/
        //when student login link is clicked, return to student login page
        _loginLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivityForResult(intent, REQUEST_STUDENT_LOGIN);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        //when driver login button is clicked, try to login the user
        _driverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { login();}
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _driverLoginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(DriverLogin.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_DRIVER_LOGIN) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _driverLoginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _driverLoginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _nameText.setError("enter a valid email address");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
