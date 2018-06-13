package es.pue.android.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import es.pue.android.student.model.Student;

public class MainActivity extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildStudent();
        saveStudent();
    }

    private void buildStudent() {
        student = new Student("Elon", "Musk", 35);
    }

    private void saveStudent() {
        String studentJSON = new Gson().toJson(student);

        SharedPreferences prefs = getSharedPreferences(Constants.STUDENT_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.STUDENT, studentJSON);
        prefsEditor.apply();
    }

    public void seeStudent(View view) {
        Intent i = new Intent(this, DetailActivity.class);

        startActivity(i);
    }
}
