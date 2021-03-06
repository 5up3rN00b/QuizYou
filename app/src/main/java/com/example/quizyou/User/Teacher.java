package com.example.quizyou.User;

import com.example.quizyou.MainActivity;
import com.example.quizyou.Test.GradedTest;
import com.example.quizyou.Test.Test;
import com.example.quizyou.Test.TestResult;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher implements User {
    private ArrayList<String> studentIDs;
    private ArrayList<Test> madeTests, assignedTests;
    private ArrayList<TestResult> results;
    private ArrayList<GradedTest> gradedTests;
    private String name, email, password;
    private long ID;
    private static long staticID = 0;

    public Teacher(String name, String email, String password) {
        studentIDs = new ArrayList<>();
        madeTests = new ArrayList<>();
        assignedTests = new ArrayList<>();
        results = new ArrayList<>();
        gradedTests = new ArrayList<>();

        this.name = name;
        this.email = email;
        this.password = password;
        ID = staticID;
        staticID++;
    }

    public Teacher(String name, String email, String password, ArrayList<String> students, ArrayList<Test> madeTests, ArrayList<Test> assignedTests, ArrayList<TestResult> results, ArrayList<GradedTest> gradedTests, long id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.studentIDs = students;
        this.madeTests = madeTests;
        this.assignedTests = assignedTests;
        this.results = results;
        this.gradedTests = gradedTests;
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getStudentIDs() {
        return studentIDs;
    }

    public ArrayList<Test> getMadeTests() {
        return madeTests;
    }

    public ArrayList<Test> getAssignedTests() {
        return assignedTests;
    }

    public ArrayList<TestResult> getResults() {
        return results;
    }

    public ArrayList<GradedTest> getGradedTests() {
        return gradedTests;
    }

    public static void setStaticID(int staticID) {
        Teacher.staticID = staticID;
    }

    public void addMadeTest(Test t) {
        madeTests.add(t);
    }

    public void addAssignedTest(Test t) {
        //madeTests.remove(t);
        assignedTests.add(t);

        for (String id : studentIDs) {
            ((Student) StudentActivity.students.get(id)).addPending(t);

            MainActivity.mDb.collection("Students")
                .document(id)
                .update("pending", ((Student) StudentActivity.students.get(id)).getPending());

        }
    }

    public void addTestResults(TestResult r) {
        results.add(r);
    }

    public void removeTestResults(TestResult r) {
        System.out.println("Gay");
        for (int i = 0; i < results.size(); i++) {
            if (r.getStudentID() == results.get(i).getStudentID() && r.getTest().getName() == results.get(i).getTest().getName() && r.getExitedApp() == results.get(i).getExitedApp()) {
                results.remove(i);
                break;
            }
        }
    }

    public void addGradedTests(GradedTest g) {
        gradedTests.add(g);
    }

    public void addStudents(String id) {
        studentIDs.add(id);
    }

    public boolean equals(String email) {
        if (email.equals(this.email)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
//        if (results.size() > 0) {
//            return "Name: " + name + " Email: " + email + " Password: " + password + " Static ID: " + staticID;
//        } else {
            return "Name: " + name + " Email: " + email + " Password: " + password + " Static ID: " + staticID;
//        }
    }
}
