package es.pue.android.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import es.pue.android.student.model.Student;

public class DetailActivity extends AppCompatActivity {

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        loadStudent();

        TextView tvStudentName = findViewById(R.id.tvStudentName);
        TextView tvStudentAge = findViewById(R.id.tvStudentAge);

        tvStudentName.setText(String.format("%s %s", student.getName(), student.getSurname()));
        tvStudentAge.setText(""+student.getAge());
    }

    private void loadStudent() {
        SharedPreferences prefs = getSharedPreferences(Constants.STUDENT_DATA, Context.MODE_PRIVATE);
        String studentJson = prefs.getString(Constants.STUDENT, "");
        student = new Gson().fromJson(studentJson, Student.class);
    }
}
