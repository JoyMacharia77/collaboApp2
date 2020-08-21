package com.joymacharia.collaboapp.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.joymacharia.collaboapp.AuthActivity;
import com.joymacharia.collaboapp.R;
import com.joymacharia.collaboapp.UserInfoActivity;
;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class signupFragment extends Fragment {
    private View view;
    private TextInputLayout layoutEmail,layoutPassword,layoutConfirm;
    private TextInputEditText txtEmail,txtPassword,txtConfirm;
    private TextView txtSignIn;
    private Button btnSignUp;
    private ProgressDialog dialog;

    //    private static final String KEY_USERNAME ="username" ;
    private static final String KEY_PASSWORD ="password" ;
    private static final String KEY_EMAIL ="email" ;

    public signupFragment() {}
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable  Bundle savedInstance){
        view = inflater.inflate(R.layout.fragment_signup, container,false);
        init();
        return view;
    }
    private void init(){
        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignUp);
        layoutPassword =view.findViewById(R.id.txtLayoutPasswordSignUp);
        layoutConfirm = view.findViewById(R.id.txtLayoutConfirmPasswordSignUp);
        txtPassword = view.findViewById(R.id.txtPasswordSignUp);
        txtEmail = view.findViewById(R.id.txtEmailSignUp);
        txtConfirm =view.findViewById(R.id.txtConfirmPasswordSignUp);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        txtSignIn = view.findViewById(R.id.txtSignIn);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer,new SigninFragment()).commit();
            }
        });

//        txtSignIn.setOnClickListener(v->{
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer,new SigninFragment()).commit();
//        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    register();

                }
            }
        });
//        btnSignUp.setOnClickListener(v->{
//            if(validate()){
//
//            }
//        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtPassword.getText().toString().length()>7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
                    layoutConfirm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void register() {
        dialog.setMessage("Registering");
        dialog.show();
//        Toast.makeText(getContext(), "going to volley", Toast.LENGTH_SHORT).show();
        String mURL = "http://192.168.0.107/api/register";

        StringRequest request = new StringRequest(Request.Method.POST, mURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.w("res", "Response:" + response);
                dialog.dismiss();
                try {
//                Toast.makeText(getContext(), "after try", Toast.LENGTH_SHORT).show();

                    JSONObject object = new JSONObject(response);
//                Toast.makeText(getContext(), "in volley", Toast.LENGTH_SHORT).show();

                    if (object.getBoolean("success")) {
                        JSONObject user = object.getJSONObject("user");
                        //make shared preference user
                        SharedPreferences userPref = signupFragment.this.getActivity().getApplicationContext().getSharedPreferences("user", signupFragment.this.getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPref.edit();
//                        Toast.makeText(signupFragment.this.getContext(), "going to volley", Toast.LENGTH_SHORT).show();
                        editor.clear();
                        editor.putString("token", object.getString("token"));
//                        editor.putString("name", user.getString("name"));
//                        editor.putString("lastname", user.getString("lastame"));
//                        editor.putString("photo", user.getString("photo"));
                        editor.putBoolean("isLoggedIn",true);
                        editor.apply();
                        editor.commit();
                        //if succes
                        startActivity(new Intent(((AuthActivity)getContext()), UserInfoActivity.class));
                        ((AuthActivity)getContext()).finish();
                        Toast.makeText(signupFragment.this.getContext(), "Registration success", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                dialog.dismiss();
                Toast.makeText(signupFragment.this.getContext(), "Connection failed", Toast.LENGTH_SHORT).show();


            }
        }){
            protected Map<String, String> getParams(){
                HashMap<String, String> map = new HashMap<>();
                map.put("email", txtEmail.getText().toString());
                map.put("password",txtPassword.getText().toString());
//                map.put("Content-Type", "application/json; charset=utf-8");
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    private boolean validate() {
        if (txtEmail.getText().toString().isEmpty()) {
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is required");
            return false;
        }
        //remember to change this to 8
        if(txtPassword.getText().toString().length()<6){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 6 characters");
            return false;
        }
        if(!txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Password doesn't match");
            return false;
        }
        return true;

    }

}
