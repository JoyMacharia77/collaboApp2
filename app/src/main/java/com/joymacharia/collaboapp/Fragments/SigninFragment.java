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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.joymacharia.collaboapp.AuthActivity;
import com.joymacharia.collaboapp.HomeActivity;
import com.joymacharia.collaboapp.R;
import com.joymacharia.collaboapp.constants;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninFragment extends Fragment  implements  View.OnClickListener {
    private View view;
    private TextInputLayout layoutEmail, layoutPassword;
    private TextInputEditText txtEmail, txtPassword;
    private TextView txtSignUp;
    private Button btnSignIn;
    private ProgressDialog dialog;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        init();
        return view;

    }

    private void init() {
        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignIn);
        layoutPassword = view.findViewById(R.id.txtLayoutPasswordSignIn);
        txtPassword = view.findViewById(R.id.txtPasswordSignIn);
        txtEmail = view.findViewById(R.id.txtEmailSignIn);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        txtSignUp = view.findViewById(R.id.txtSignUp);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer, new signupFragment()).commit();
            }
        });

//     txtSignUp.setOnClickListener(view -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer,new signupFragment()).commit());

//        txtSignUp.setOnClickListener(v->{
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FrameAuthContainer,new signupFragment()).commit();
//        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
//do something
                    login();
                }
            }
        });

//        btnSignIn.setOnClickListener(v->{
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
                if (txtEmail.getText().toString().isEmpty()) {
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
                if (txtPassword.getText().toString().length() > 7) {
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void login() {
        dialog.setMessage("Logging in");
        dialog.show();
//        Toast.makeText(getContext(), "going to volley", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, constants.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //if connection succeeds
                try {
//               Toast.makeText(getContext(), "in volley", Toast.LENGTH_SHORT).show();

                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
//                   Toast.makeText(getContext(), "in volley if", Toast.LENGTH_SHORT).show();

                        JSONObject user = object.getJSONObject("user");
                        SharedPreferences userPref = SigninFragment.this.getActivity().getApplicationContext().getSharedPreferences("user", SigninFragment.this.getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPref.edit();
                        editor.putString("token", object.getString("token"));
//                        editor.putString("name", user.getString("name"));
//                        editor.putString("lastname", user.getString("lastame"));
//                        editor.putString("photo", user.getString("photo"));
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
//
                        //if login is succesful
                        startActivity(new Intent(((AuthActivity) getContext()), HomeActivity.class));
                        ((AuthActivity) getContext()).finish();
                    } else {
                        Toast.makeText(SigninFragment.this.getContext(), "No login ...failure", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //if connection doesnt succeed
                Toast.makeText(SigninFragment.this.getContext(), "Connection failed", Toast.LENGTH_SHORT).show();

                error.printStackTrace();
                dialog.dismiss();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", txtEmail.getText().toString().trim());
                map.put("password", txtPassword.getText().toString());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

    private boolean validate() {
        if (txtEmail.getText().toString().isEmpty()) {
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is required");
            return false;
        }
        //remember to change this to 8
        if (txtPassword.getText().toString().length() < 6) {
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}

