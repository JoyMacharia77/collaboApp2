package com.joymacharia.collaboapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.joymacharia.collaboapp.models.User;
import com.joymacharia.collaboapp.models.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */

public class AddTaskFragment extends Fragment {
    //initialize
    private RecyclerView taskRecyclerView;
    private TaskAdapter taskAdapter;
    private EditText deadline,task;
    private Button addbtn;
    private View view;
//    private EditText taskTitle;
    private ProgressDialog dialog;
    private ArrayList<Task> list;
    private SharedPreferences preferences;


    public AddTaskFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_task,container,false);
        init();
        return view;
    }

    private void init() {
        deadline = view.findViewById(R.id.deadline);
        task = view.findViewById(R.id.task);
        addbtn = view.findViewById(R.id.add_btn);
        dialog = new ProgressDialog(getContext());
//        taskAdapter = new TaskAdapter(getContext(), list);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!task.getText().toString().isEmpty()){
                    addTask();
//                    Toast.makeText(AddTaskFragment.this.getContext(), "am working", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(AddTaskFragment.this.getContext(), "Task to be added can't be empty", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void addTask(){
        final String  addTask = task.getText().toString();
        final String deadlin = deadline.getText().toString();
//        final String taskTitle =
        dialog.setMessage("adding new task");
        dialog.show();

//        if(task.length() >0){
            list = new ArrayList<>();

            StringRequest request = new StringRequest(Request.Method.POST, constants.ADD_TASK, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);

                        if (object.getBoolean("success")) {
                            Toast.makeText(AddTaskFragment.this.getContext(), "got here", Toast.LENGTH_SHORT).show();
                            JSONObject task = object.getJSONObject("task");
                            JSONObject user = task.getJSONObject("user");

                            Task t = new Task();
                            User u = new User();
                            u.setId(user.getInt("id"));
                            t.setUser(u);
                            t.setId(task.getInt("id"));
                            t.setTask(task.getString("task"));
                            t.setDate(task.getString("created_at"));
                            t.setTaskDeadline(task.getString("deadline"));


                            list.add(0, t);
                            taskRecyclerView.getAdapter().notifyItemInserted(0);
                            taskRecyclerView.getAdapter().notifyDataSetChanged();
//                      addTask.setText("");

                            Toast.makeText(AddTaskFragment.this.getContext(), "Task added successfully", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(AddTaskFragment.this.getContext(), "Sorry...task not added", Toast.LENGTH_LONG).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            },error -> {
                error.printStackTrace();
                dialog.dismiss();
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    String token = preferences.getString("token","");
                    HashMap<String,String> map = new HashMap<>();
//                    map.put("Authorization","Bearer "+token);
                    return map;
                }
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("task",addTask);
//                    map.put("taskTitle",taskTitle);
                    map.put("deadline",deadlin);
                    return map;
                }

            };
            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(request);
        }

    }

