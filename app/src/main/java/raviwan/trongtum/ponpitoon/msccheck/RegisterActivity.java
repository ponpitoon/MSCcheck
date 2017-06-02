package raviwan.trongtum.ponpitoon.msccheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        //Save Controller
        saveController();


    }   // Main Method

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

            }   // onClick
        });
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
