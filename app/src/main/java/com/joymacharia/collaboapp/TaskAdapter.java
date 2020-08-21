package com.joymacharia.collaboapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.joymacharia.collaboapp.models.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskAdapter extends RecyclerView.Adapter <TaskAdapter.ViewHolder> {
    private ArrayList<Task> taskData;
    private ArrayList<Task> listAll;
    //private Context myContext;
    private Context context;
    private SharedPreferences preferences;

//    TaskAdapter(ArrayList<Task> taskData,Context context)
//    {
//        this.myContext = context;
//        this.taskData = taskData; }

    public TaskAdapter(ArrayList<Task> taskData, Context context)
    {
        this.context = context;
        this.taskData = taskData;
        this.listAll = new ArrayList<>(taskData);
        preferences = context.getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ///////////////////////////////
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent,false);
        return new TaskAdapter.ViewHolder(view);   */
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.task_list_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = taskData.get(position);
        ///////////////////////////
       // holder.myTaskDeadline.setText(task.getTaskDeadline());
        //holder.date.setText(task.getDate());
        //holder.task.setText(task.getTask());
        holder.myTaskTitle.setText(task.getTaskTitle());
//        holder.myTaskTitle.setText(task.getTaskTitle());


        holder.bindTo(task);

//        if (task.getUser().getId() == preferences.getInt("id", 0)) {
//            //something
//        }
    }


        //deleting a task
        private void deleteTask(int taskId, int position){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("confirm");
            builder.setMessage("delete task?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    StringRequest request = new StringRequest(Request.Method.POST, constants.DELETE_TASK,response -> {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getBoolean("success")) {
                                taskData.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                listAll.clear();
                                listAll.addAll(taskData);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },error -> {

                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            String token = preferences.getString("token","");
                            HashMap<String,String> map = new HashMap<>();
                            map.put("Authorization","Bearer "+token);
                            return map;
                        }
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> map = new HashMap<>();
                            map.put("id",taskId+"");
                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(request);
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }


        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                ArrayList<Task> filteredList = new ArrayList<>();
                if (constraint.toString().isEmpty()){
                    filteredList.addAll(listAll);
                } else {
                    for (Task task : listAll){
                        /////////////////////
                       // if(task.getTask().toLowerCase().contains(constraint.toString().toLowerCase())
                        if(task.getTaskTitle().toLowerCase().contains(constraint.toString().toLowerCase())
                                || task.getUser().getUserName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(task);
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return  results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                taskData.clear();
                taskData.addAll((Collection<? extends Task>) results.values);
                notifyDataSetChanged();
            }
        };

        public Filter getFilter() {
            return filter;
        }



    @Override
    public int getItemCount() {
        return taskData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView myTaskImage;
        private TextView myTaskTitle;
        private TextView myTaskDeadline;
        private TextView date;
        private TextView task;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            myTaskImage = itemView.findViewById(R.id.task_image);
            myTaskTitle = itemView.findViewById(R.id.task_title);
            myTaskDeadline = itemView.findViewById(R.id.task_deadline);
            //task = itemView.findViewById(R.id.task);
            date = itemView.findViewById(R.id.task_date_created);

            //onclick of items
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int taskPosition = getAdapterPosition();
                    Task currentTask = taskData.get(taskPosition);


                }
            });*/

        }

        public void bindTo(Task currentTask)
        {
           Glide.with(context).load(currentTask.getTaskImage()).into(myTaskImage);
            myTaskTitle.setText(currentTask.getTaskTitle());
            myTaskDeadline.setText(currentTask.getTaskDeadline());
            ///////////////////////////
            /*
            task.setText(currentTask.getTask());
            date.setText(currentTask.getDate());
            */

        }
    }
}
