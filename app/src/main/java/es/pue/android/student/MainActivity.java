package es.pue.android.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import es.pue.android.student.model.Student;

public class MainActivity extends AppCompatActivity {

    private Student student = null;
    private Button btnSaveInSD = null;
    File fileData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildStudent();

        btnSaveInSD = findViewById(R.id.btnSaveInSD);
        // Disable button if SD is not enabled.
        if (!Util.isExternalStorageAvailable() || Util.isExternalStorageReadOnly()) {
            btnSaveInSD.setEnabled(false);
        } else {
            // getExternalFilesDir defines if we are in SD.
            fileData = new File(getExternalFilesDir(Constants.STUDENT_SD_FILEPATH), Constants.STUDENT_FILE);
            // TODO write as a normal file.
        }

        // Save student in shared preferences.
        writeStudentInSharedPreferences();

        // Save student in File
        writeStudentInFile();
    }

    private void buildStudent() {
        student = new Student("Elon", "Musk", 35);
    }

    public void seeStudent(View view) {
        Intent i = new Intent(this, DetailActivity.class);

        startActivity(i);
    }

    private String stringifyStudent(Student student) {
        return new Gson().toJson(student);
    }

    private void writeStudentInSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences(Constants.STUDENT_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.STUDENT, stringifyStudent(student));
        prefsEditor.apply();
    }

    private void writeStudentInFile() {
        try {
            FileOutputStream fos = openFileOutput(Constants.STUDENT_FILE, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fos);
            outputWriter.write(stringifyStudent(student));
            outputWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveInSD(View view) {

    }
}
