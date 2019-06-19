package it372.depaul.finalprojectnaqvirit372;


//Reem Naqvi, 6/9/19. All Rights Reserved.
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calls the databasehelper
        SQLiteOpenHelper dbh = new DatabaseHelper(this);
        try {
            //write to database
            db = dbh.getWritableDatabase();
        }
        //display error if no database
        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database not created.", Toast.LENGTH_LONG);
        }

    }

    protected void onClick(View view){
        //get name
        EditText editText = (EditText)findViewById(R.id.edtxt_name);
        String name = editText.getText().toString();
        //get email
        EditText editText1 = (EditText)findViewById(R.id.edtxt_email);
        String email = editText1.getText().toString();
        //get the hotel type
        Spinner spn = (Spinner) findViewById(R.id.spnr_hotel);
        String hotel = spn.getSelectedItem().toString();
        //get the room type
        Spinner spn1 = (Spinner) findViewById(R.id.spnr_room);
        String room = spn1.getSelectedItem().toString();
        //get the checkin date
        EditText editText2 = (EditText)findViewById(R.id.edtxt_date);
        String checkin = editText2.getText().toString();
        //get the checkout date
        EditText editText3 = (EditText)findViewById(R.id.edtxt_dates);
        String checkout = editText3.getText().toString();
        //asks if user wants breakfast
        RadioButton radYes = (RadioButton)findViewById(R.id.rdbtn_yes);
        RadioButton radNo = (RadioButton)findViewById(R.id.rdbtn_no);
        String breakfast = radYes.isChecked() ? "Yes" : "No";
        //gets the Guests Number:
        EditText editText4 = (EditText)findViewById(R.id.edtxt_guestNum);
        String guests = editText4.getText().toString();

        //Insert values into database
        ContentValues hotelValues = new ContentValues();
        hotelValues.put("name",name);
        hotelValues.put("email",email);
        hotelValues.put("hotel",hotel);
        hotelValues.put("room",room);
        hotelValues.put("checkin",checkin);
        hotelValues.put("checkout",checkout);
        hotelValues.put("breakfast",breakfast);
        hotelValues.put("guests", guests);
        db.insert("hoteldata", null, hotelValues);


        // Create intent for new activity
        Intent intent = new Intent(this, DisplayInput.class);

        // Add output as extra text to intent.
        //intent.putExtra(DisplayInput.EXTRA_MESSAGE, output);

        // Start new activity.
        startActivity(intent);
    }
}
