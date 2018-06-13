package es.pue.android.student;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import es.pue.android.student.model.Student;

public class DetailActivity extends AppCompatActivity {

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        student = getStudentFromFile();

        TextView tvStudentName = findViewById(R.id.tvStudentName);
        TextView tvStudentAge = findViewById(R.id.tvStudentAge);

        tvStudentName.setText(String.format("%s %s", student.getName(), student.getSurname()));
        tvStudentAge.setText(""+student.getAge());
    }

    private Student unstringifyStudent(String studentJson) {
        return new Gson().fromJson(studentJson, Student.class);
    }

    private void getStudentFromSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences(Constants.STUDENT_DATA, Context.MODE_PRIVATE);
        String studentJson = prefs.getString(Constants.STUDENT, "");
        student = unstringifyStudent(studentJson);
    }

    private Student getStudentFromFile() {
        Student student = null;
        try {
            FileInputStream fis = openFileInput(Constants.STUDENT_FILE);
            InputStreamReader inputReader = new InputStreamReader(fis);
            StringBuilder strBuilder = new StringBuilder();
            char[] buffer = new char[100];
            int charRead;

            while ((charRead = inputReader.read(buffer)) > 0) {
                String readString = String.copyValueOf(buffer, 0, charRead);
                strBuilder.append(readString);
            }
            inputReader.close();

            student = unstringifyStudent(strBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return student;
    }
}
