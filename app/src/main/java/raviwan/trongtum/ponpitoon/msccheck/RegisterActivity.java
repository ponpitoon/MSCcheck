package raviwan.trongtum.ponpitoon.msccheck;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
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
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class RegisterActivity extends AppCompatActivity {

    private ImageView backImageView, humanImageView;
    private EditText studentIDEditText, nameEditText, userEditText,
            passwordEditText, addressEditText, phoneEditText, eMailEditText;
    private Spinner titleSpinner, yearSpinner, majorSpinner, classSpinner;
    private Button button;
    private String studentIDString, nameString, userString,
            passwordString, addressString, phonString,
            eMailString, titleString, yearString,
            majorString, classString, pathImageString, nameImageString;
    private Uri uri;
    private boolean aBoolean = true;

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

                aBoolean = false;

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
        final String[] yearStrings = myConstant.getYearStrings();
        ArrayAdapter<String> yearStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, yearStrings);
        yearSpinner.setAdapter(yearStringArrayAdapter);


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearString = yearStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yearString = yearStrings[0];
            }
        });

        // for major
        final String[] majorStrings = myConstant.getMajorStrings();
        ArrayAdapter<String> majorStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, majorStrings);
        majorSpinner.setAdapter(majorStringArrayAdapter);

        majorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                majorString = majorStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                majorString = majorStrings[0];

            }
        });



        // for Class
        final String[] classStrings = myConstant.getClassStrings();
        ArrayAdapter<String> ClassStringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, classStrings);
        classSpinner.setAdapter(ClassStringArrayAdapter);

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classString = classStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                classString = classStrings[0];

            }
        });



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
                } else if (studentIDString.length() != 13) {
                    //Student ID False
                    MyAlert myAlert = new MyAlert(RegisterActivity.this);
                    myAlert.myDialog("Student ID False", "Please Try Again Student ID have 13 Digi");
                } else if (aBoolean) {
                    //Non Choose Image
                    MyAlert myAlert = new MyAlert(RegisterActivity.this);
                    myAlert.myDialog("Non Choose Imge", "please Choose Image Avata");
                } else {
                    //Comfirm Data
                    confirmData();
                }


            }   // onClick
        });
    }

    private void confirmData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
       // builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_profile);
        builder.setTitle("Cinfirm Data ?");
        builder.setMessage("Student = " + studentIDString + "\n" +
                "Title = " + titleString + "\n" +
                "Name = " + nameString + "\n" +
                "Year = " + yearString + "\n" +
                "User = " + userString + "\n" +
                "Password = " + passwordString + "\n" +
                "Major = " + majorString + "\n" +
                "CLass = " + classString + "\n" +
                "Address = " + addressString + "\n" +
                "Phone = " + phonString + "\n" +
                "E-mail = " + eMailString);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                uploadValueToSever();
                dialog.dismiss();
            }
        });


        builder.show();

    }

    private void uploadValueToSever() {

        try {

            String[] strings = new String[]{MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, strings, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                pathImageString = cursor.getString(i);
            } else {
                pathImageString = uri.getPath();
            }

            Log.d("2JuneV1", "pathImage ==>" + pathImageString);

            //Find Name Image Cheesed
            nameImageString = pathImageString.substring(pathImageString.lastIndexOf("/"));
            Log.d("2JuneV1", "nameImage ==>" + nameImageString);

            //Upload Image to Sever
            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                    .Builder().permitAll().build();
            StrictMode.setThreadPolicy(threadPolicy);

            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com", 21, "dom@swiftcodingthai.com", "Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("Image");
            simpleFTP.stor(new File(pathImageString));
            simpleFTP.disconnect();

            Toast.makeText(RegisterActivity.this,"Upload Image OK", Toast.LENGTH_SHORT).show();

            //Upload String Text to mySql
            PostNewUser postNewUser = new PostNewUser(RegisterActivity.this);
            MyConstant myConstant = new MyConstant();
            postNewUser.execute(myConstant.getUrlImageString() + nameImageString,
                    studentIDString,
                    titleString,
                    nameString,
                    yearString,
                    userString,
                    passwordString,
                    majorString,
                    classString,
                    phonString,
                    eMailString,
                    myConstant.getUrlAddUserFarString());

            String strResult = postNewUser.get();
            Log.d("3JunrV1", "Result ==> " + strResult);

            if (Boolean.parseBoolean(strResult)) {
                finish();
            } else {
                MyAlert myAlert = new MyAlert(RegisterActivity.this);
                myAlert.myDialog("Error Upload value", "Please Try Again Cannot Upload To Sever");
            }

        } catch (Exception e) {
            Log.d("2JuneV1", "e upload ==>" + e.toString());
        }

    }   // upload

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
