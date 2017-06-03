package raviwan.trongtum.ponpitoon.msccheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class ShowTeacherActivity extends AppCompatActivity {

    //Explicit
    private ImageView imageView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teacher);

        //Initial view
        initialView();

        //Back Controller
        backController();

        //Create ListView
        createListView();


    }   // Main Method

    private void createListView() {
        try {

            MyConstant myConstant = new MyConstant();
            GetAllData getAllData = new GetAllData(ShowTeacherActivity.this);
            getAllData.execute(myConstant.getUrlGetTeacherString());
            String strJSON = getAllData.get();
            Log.d("3JuneV3", "strJSON ==> " + strJSON);

        } catch (Exception e) {
            Log.d("3Jumev3", "e create ==> " + e.toString());
        }
    }

    private void backController() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialView() {
        imageView = (ImageView) findViewById(R.id.imvBack);
        listView = (ListView) findViewById(R.id.livTeacher);
    }

}   // Main Class
