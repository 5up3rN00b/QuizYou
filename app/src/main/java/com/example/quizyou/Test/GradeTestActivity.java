package com.example.quizyou.Test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.quizyou.MainActivity;
import com.example.quizyou.R;
import com.example.quizyou.User.Student;
import com.example.quizyou.User.StudentActivity;
import com.example.quizyou.User.Teacher;
import com.example.quizyou.User.TeacherActivity;

import java.util.ArrayList;

public class GradeTestActivity extends AppCompatActivity {

    private SwipeRefreshLayout mPullRefresh;

    // TODO Display wrong errors

//    Spinner mSpinner;
    ImageButton mBack;

    private ArrayList<EditText> editTexts = new ArrayList<>();
    private final static String TAG = "GradeTestActivity";
    private ListView mListView;
    private TextView mNoTest;
    public static TestResult selectedTestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_test);
        mNoTest = findViewById(R.id.noTest);
        mBack = findViewById(R.id.back_button);
        mListView = findViewById(R.id.grade_list_view);

        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        Log.d(TAG, ((Teacher) MainActivity.u).getResults().toString());

        mPullRefresh = findViewById(R.id.pullToRefreshGradeTests);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        ArrayList<String> list = new ArrayList<>();
        Log.d(TAG, ((Teacher) MainActivity.u).getResults().toString());
        for (TestResult t : ((Teacher) MainActivity.u).getResults()) {
            Student s = (Student) StudentActivity.students.get(Long.toString(t.getStudentID()));
            list.add(s.getName() + "'s " + t.getTest().getName());
        }

        ArrayList<TestResult> testResults = new ArrayList<>();
        for(int i = 0; i<((Teacher)MainActivity.u).getResults().size(); i++){
            testResults.add(((Teacher)MainActivity.u).getResults().get(i));
        }

        TestResultAdapter adapter1 = new TestResultAdapter(this, R.layout.adapter_grade_view_layout, testResults);
        mListView.setAdapter(adapter1);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestResult result = (TestResult) parent.getItemAtPosition(position);
                selectedTestResult = result;

                startActivity(new Intent(getApplicationContext(), GradeStudentTestActivity.class));
                finish();
            }
        });

        if (((Teacher) MainActivity.u).getResults().size() == 0){
            mNoTest.setText("No tests to grade right now");
        } else {
            mNoTest.setText("");
        }

        mPullRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainActivity.load();
                Intent intent = new Intent(getApplicationContext(), GradeTestActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                mPullRefresh.setRefreshing(false);
            }
        });
    }
}
