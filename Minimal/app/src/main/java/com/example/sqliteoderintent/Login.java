package com.example.sqliteoderintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqliteoderintent.SQLiteHelper.SQLiteUserHelper;
import com.example.sqliteoderintent.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class Login extends AppCompatActivity implements View.OnClickListener{

    ImageView btnFb;
    CallbackManager callbackManager;
    SQLiteUserHelper sqLiteUserHelper;
    private EditText email, password;
    private Button btnDN, btnDK;
    private User user;
    private String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnDK.setOnClickListener(this);
        btnDN.setOnClickListener(this);


        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("public_profile", "email"));
            }
        });
        xulyLoginFb();

    }

    private void init() {
        btnDN = findViewById(R.id.btnDN);
        btnFb = findViewById(R.id.btnFB);
        btnDK = findViewById(R.id.btnDK);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private void getFbInfo() {
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(final JSONObject me, GraphResponse response) {
                            if (me != null) {
                                sqLiteUserHelper = new SQLiteUserHelper(getApplicationContext());
                                user = new User();
                                System.out.println(me.toString() + " data");
                                user.setName(me.optString("name"));
                                user.setEmail(me.optString("email"));
                                long id = sqLiteUserHelper.addUser(user);
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");
            request.setParameters(parameters);
            request.executeAsync();
             n=user.getName();
        }
    }
    private void xulyLoginFb() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Login Facebook success.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userlogin","Hằng kin");
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Facebook cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Login Facebook error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        if (v == btnDK) {
            Intent intent=new Intent(this,Signup_Activity.class);
            startActivity(intent);
        }
        else if(v==btnDN) {

            sqLiteUserHelper=new SQLiteUserHelper(this);
            User user=new User();
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString());
            User userLogin=sqLiteUserHelper.getUserByAccount(user);
            if(userLogin!=null){
                Intent intent=new Intent(Login.this,MainActivity.class);
                intent.putExtra("userlogin",userLogin.getName());
                startActivity(intent);


            }else {
                Toast.makeText(this,"Tài khoản hoặc mật khâu sai",Toast.LENGTH_SHORT).show();
            }
        }
    }
}