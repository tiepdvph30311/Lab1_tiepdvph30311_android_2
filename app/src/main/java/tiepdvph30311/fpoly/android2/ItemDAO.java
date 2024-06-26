package tiepdvph30311.fpoly.android2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ItemDAO {
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public ItemDAO(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void addItem(String itemName, int age) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ITEM_NAME, itemName);
        values.put(DBHelper.COLUMN_ITEM_AGE, age); // Thêm age vào ContentValues
        database.insert(DBHelper.TABLE_ITEMS, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_ITEMS, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(cursor.getColumnIndex(DBHelper.COLUMN_ID));
            String itemName = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ITEM_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ITEM_AGE)); // Truy xuất age
            items.add(new Item(id, itemName, age));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public void deleteItem(long id) {
        database.delete(DBHelper.TABLE_ITEMS, DBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void updateItem(long id, String itemName, int age) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ITEM_NAME, itemName);
        values.put(DBHelper.COLUMN_ITEM_AGE, age);
        database.update(DBHelper.TABLE_ITEMS, values, DBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
