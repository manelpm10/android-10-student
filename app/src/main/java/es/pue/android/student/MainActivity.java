package es.pue.android.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.pue.android.student.model.Student;

public class MainActivity extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void buildStudent() {
        student = new Student("Elon", "Musk", 35);
    }

    private void saveStudent() {

    }

    public void seeStudent(View view) {
        Intent i = new Intent(this, DetailActivity.class);

        startActivity(i);
    }
}
