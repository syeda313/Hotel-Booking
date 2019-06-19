package it372.depaul.finalprojectnaqvirit372;

//Reem Naqvi, 6/9/19. All Rights Reserved.

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Entryformdata.db";
    private static final int DB_VERSION = 1;
    //name of database and its version
    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    // This method is only called if the database
    // does not exist.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table hoteldata(" +
                "name text, email text, hotel text, room text, checkin text, checkout text, breakfast text, guests text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

        // This method is required, but not used
        // in this example
    }

}


