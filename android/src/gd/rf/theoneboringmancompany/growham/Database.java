package gd.rf.theoneboringmancompany.growham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gd.rf.theoneboringmancompany.growham.tools.AndroidConnector;

import static gd.rf.theoneboringmancompany.growham.tools.Settings.SCORES_LIMIT;

public class Database implements AndroidConnector {
    private static final String DATABASE_NAME = "scores.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "scoreTable";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_TIME = "Time";

    private static final int NUM_COLUMN_NAME = 0;
    private static final int NUM_COLUMN_TIME = 1;

    private static final String ORDER_BY = COLUMN_TIME + " DESC";

    private SQLiteDatabase mDataBase;
    private DatabaseHelper databaseHelper;

    Database(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insert(String Name, int Time) {
        mDataBase = databaseHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, Name);
        cv.put(COLUMN_TIME, Time);

        mDataBase.insert(TABLE_NAME, null, cv);

        databaseHelper.close();
        mDataBase.close();
    }

    //Returns an array of objects in which even objects are names, and odd objects are lifetimes.
    public Object[] selectToScore() {
        mDataBase = databaseHelper.getReadableDatabase();

        String[] columns = {COLUMN_NAME, COLUMN_TIME};
        Object[] o = new Object[SCORES_LIMIT * 2];

        Cursor mCursor = mDataBase.query(TABLE_NAME, columns, null, null,
                null, null , ORDER_BY, String.valueOf(SCORES_LIMIT));

        int oNum = 0;
        if (mCursor.moveToFirst()) {
            int count = mCursor.getCount();
            for (int i = 0; i < 3 && i < count; i++) {

                String Name = mCursor.getString(NUM_COLUMN_NAME);
                o[oNum] = Name;
                oNum++;

                int Time = mCursor.getInt(NUM_COLUMN_TIME);
                o[oNum] = Time;
                oNum++;

                mCursor.moveToNext();
            }

        }

        mCursor.close();
        databaseHelper.close();
        mDataBase.close();

        return o;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT,"+
                    COLUMN_TIME +" INT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}