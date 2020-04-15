package com.example.quizyou.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizyou.R;
import com.example.quizyou.User.Student;
import com.example.quizyou.User.StudentActivity;

import java.util.ArrayList;

public class student_home extends Fragment  {

    private Button mViewStudent;
    private Button mBack;
    private ArrayList<Student> students = new ArrayList<>();

    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.activity_student, container, false);
        Intent intent = new Intent(getActivity().getApplicationContext(), StudentActivity.class);
        startActivity(intent);
        return retView;
    }

}
