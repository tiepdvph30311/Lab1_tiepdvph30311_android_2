package tiepdvph30311.fpoly.android2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 2; // Tăng phiên bản cơ sở dữ liệu

    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_AGE = "age"; // Thêm cột age

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ITEM_NAME + " TEXT NOT NULL, " +
                    COLUMN_ITEM_AGE + " INTEGER NOT NULL);"; // Thêm cột age vào câu lệnh tạo bảng

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_ITEMS + " ADD COLUMN " + COLUMN_ITEM_AGE + " INTEGER;");
        }
    }
}
