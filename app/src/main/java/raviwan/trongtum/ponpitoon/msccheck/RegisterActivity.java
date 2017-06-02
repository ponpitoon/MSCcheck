package raviwan.trongtum.ponpitoon.msccheck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    private ImageView backImageView, humanImageView;
    private EditText studentIDEditText, nameEditText, userEditText,
            passwordEditText, addressEditText, phoneEditText, eMailEditText;
    private Spinner titleSpinner, yearSpinner, majorSpinner, classSpinner;
    private Button button;
    private String studentIDString, nameString, userString,
            passwordString, addressString, phonString,
            eMailString, titleString, yearString,
            majorString, classString;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initial View
        initialView();

        //Create Spinner
        createSpinner();

        //Back Controller
        backController();

        //Human Controller
        humanController();

        //Save Controller
        saveController();


    }   // Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            try {

                uri = data.getData();

                Bitmap bitmap = BitmapFactory.
                        decodeStream(getContentResolver()
                                .openInputStream(uri));
                humanImageView.setImageBitmap(bitmap);



            } catch (Exception e) {
                Log.d("2Junev1", "e Result ==> " + e.toString());
            }

        } // if

    }

    private void humanController() {
        humanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App for Choose Image"), 1);

            }
        });
    }

    private void createSpinner() {
        MyConstant myConstant = new MyConstant();

        //for Title
        final String[] titleStrings = myConstant.getTitleStrings();
        ArrayAdapter<String> titleStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, titleStrings);
        titleSpinner.setAdapter(titleStringArrayAdapter);

        titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                titleString = titleStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                titleString = titleStrings[0];
            }
        });

        // for Year
        String[] yearStrings = myConstant.getYearStrings();
        ArrayAdapter<String> yearStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, yearStrings);
        yearSpinner.setAdapter(yearStringArrayAdapter);

        // for major
        String[] majorStrings = myConstant.getMajorStrings();
        ArrayAdapter<String> majorStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, majorStrings);
        majorSpinner.setAdapter(majorStringArrayAdapter);

        // for Class
        String[] classStrings = myConstant.getClassStrings();
        ArrayAdapter<String> ClassStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, classStrings);
        classSpinner.setAdapter(ClassStringArrayAdapter);

    }   // CreateSpinner

    private void saveController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Value From Edit Text
                studentIDString = studentIDEditText.getText().toString().trim();
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                addressString = addressEditText.getText().toString().trim();
                phonString = phoneEditText.getText().toString().trim();
                eMailString = eMailEditText.getText().toString().trim();

                //Check Space
                if (checkSpace()) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(RegisterActivity.this);
                    myAlert.myDialog("Have Space", "Pleass Fill All Every Blank");
                }


            }   // onClick
        });
    }

    private boolean checkSpace() {
        return studentIDString.equals("") || nameString.equals("") ||
                userString.equals("") || passwordString.equals("") ||
                addressString.equals("") || phonString.equals("") || eMailString.equals("");
    }

    private void backController() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialView() {
        backImageView = (ImageView) findViewById(R.id.imvBack);
        humanImageView = (ImageView) findViewById(R.id.imvHuman);
        studentIDEditText = (EditText) findViewById(R.id.edtStudentID);
        nameEditText = (EditText) findViewById(R.id.edtName);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        addressEditText = (EditText) findViewById(R.id.edtAddress);
        phoneEditText = (EditText) findViewById(R.id.edtPhone);
        eMailEditText = (EditText) findViewById(R.id.edtEmail);
        titleSpinner = (Spinner) findViewById(R.id.spnTitle);
        yearSpinner = (Spinner) findViewById(R.id.spnYear);
        majorSpinner = (Spinner) findViewById(R.id.spnMajor);
        classSpinner = (Spinner) findViewById(R.id.spnClass);
        button = (Button) findViewById(R.id.btnSave);
    }

}   //Main Class
