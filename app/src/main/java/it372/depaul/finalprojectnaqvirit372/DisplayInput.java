package it372.depaul.finalprojectnaqvirit372;

//Reem Naqvi, 6/9/19. All Rights Reserved.

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayInput extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayinput);

        // Get intent from old activity
        Intent intent = getIntent();

        TextView txtName = (TextView) findViewById(R.id.txt_name);
        TextView txtEmail = (TextView) findViewById(R.id.txt_email);
        TextView txtHotel = (TextView) findViewById(R.id.txt_hotel);
        TextView txtRoom = (TextView) findViewById(R.id.txt_room);
        TextView txtcheckIn = (TextView) findViewById(R.id.txt_checkin);
        TextView txtcheckOut = (TextView) findViewById(R.id.txt_checkout);
        TextView txtbreakfast = (TextView) findViewById(R.id.txt_breakfst);
        TextView txtguests = (TextView) findViewById(R.id.txt_guests);

        //calls the databasehelper
        SQLiteOpenHelper dbh = new DatabaseHelper(this);
        try {
            //read from the database
            db = dbh.getReadableDatabase();

            String output="";

            //naviagate the hoteldata table
            Cursor cursor = db.query("hoteldata",
                   new String[]{"name", "email", "hotel", "room", "checkin", "checkout", "breakfast", "guests"},
                    null, null, null, null, null);

                //if on lastvalue:
            if (cursor.moveToLast()) {
                        //get the values:
                    String name = cursor.getString(0);
                    String email = cursor.getString(1);
                    String hotel = cursor.getString(2);
                    String room = cursor.getString(3);
                    String checkin = cursor.getString(4);
                    String checkout = cursor.getString(5);
                    String breakfast = cursor.getString(6);
                    String guests = cursor.getString(7);


                    //Display the text using textview:
                   txtName.setText(name);
                   txtEmail.setText(email);
                   txtHotel.setText(hotel);
                   txtRoom.setText(room);
                   txtcheckIn.setText(checkin);
                   txtcheckOut.setText(checkout);
                   txtbreakfast.setText(breakfast);
                   txtguests.setText(guests);


            }
            //display error if no database
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database not created.", Toast.LENGTH_LONG);
        }


    }
    //a toast for the user to see their confirmation
    protected void onClick(View view) {
        //displays the text when layout is clicked
        CharSequence text = "Reservation placed! Thank you and Enjoy your stay!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }
}
