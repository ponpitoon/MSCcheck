package raviwan.trongtum.ponpitoon.msccheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private LinearLayout teacherLinearLayout;
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Initial View
        initialView();

        //Layout Controller
        layoutController();


    }   // Main Method

    private void layoutController() {
        teacherLinearLayout.setOnClickListener(this);
    }

    private void initialView() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        teacherLinearLayout = (LinearLayout) findViewById(R.id.linTeacher);
    }

    @Override
    public void onClick(View view) {

        Class<?> aClass = null;

        //For Teacher
        if (view == teacherLinearLayout) {
            aClass = ShowTeacherActivity.class;
        }


        //Intent to Activity
        Intent intent = new Intent(AdminActivity.this, aClass);
        intent.putExtra("Login", loginStrings);
        startActivity(intent);

    }
}   // Main Class
