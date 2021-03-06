package com.example.quizyou.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quizyou.R;
import com.example.quizyou.User.Student;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentList extends ArrayAdapter<Student> {

    private static final String TAG = "studentList";

    private Context mContext;
    int mResource;

    public StudentList(@NonNull Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTestName = (TextView) convertView.findViewById(R.id.textView1);
        tvTestName.setText(name);

        return convertView;


    }
}