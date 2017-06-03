package raviwan.trongtum.ponpitoon.msccheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit ภาษไทยแปลว่า ประกาศตัวแปร
    private EditText userEditText,passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial view
        Initialview();

        //TextView Controller
        textViewController();

        //Button Controller
        buttonController();

    }   //Main Method นี้คือเมททอดหลัก

    private void buttonController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Value From Edi Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("Heve Space", "Please Fill All Every Blank");

                } else {
                    //No Space
                    checkUserandPass();

                }

            }   //onClick
        });
    }

    private void checkUserandPass() {

        try {

            GetAllData getAllData = new GetAllData(MainActivity.this);
            MyConstant myConstant = new MyConstant();
            MyAlert myAlert = new MyAlert(MainActivity.this);
            boolean b = true;

            getAllData.execute(myConstant.getUrlGetUserString());
            String strJSON = getAllData.get();
            Log.d("3JuneV2", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] columnStrings = myConstant.getLoginStrings();
            String[] loginStrings = new String[columnStrings.length];

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString(columnStrings[6]))) {
                    // User True
                    for (int i1=0; i1<columnStrings.length; i1++) {

                        loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d("3JuneV2", "loginstring(" + i1 + ")" + loginStrings[i1]);
                        b = false;

                    }   //for
                }   //if
            }   // for

            if (b) {
                myAlert.myDialog("User False", "No This User in my Database");
            } else if (passwordString.equals(loginStrings[7])) {
                Toast.makeText(MainActivity.this, "Welcome" + loginStrings[4],
                        Toast.LENGTH_SHORT).show();
            } else {
                myAlert.myDialog("Password False", "Please Try Again Password False");
            }


        } catch (Exception e) {
            Log.d("3JuneV2", "e check ==>" + e.toString());
        }

    }

    private void textViewController() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent in RegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }   // onClick
        });
    }

    private void Initialview() {

        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewRegister);
        button = (Button) findViewById(R.id.btnLoing);

    }
}   //Main Class  นี้คือ คลาสหลัก
