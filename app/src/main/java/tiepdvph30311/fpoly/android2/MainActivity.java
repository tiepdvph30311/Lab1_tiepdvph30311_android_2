package tiepdvph30311.fpoly.android2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> items;
    private ItemAdapter itemsAdapter;
    private ListView listView;
    private ItemDAO itemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button button = findViewById(R.id.button);
        final EditText editTextItem = findViewById(R.id.editText);
        final EditText editTextAge = findViewById(R.id.editTextAge);

        itemDAO = new ItemDAO(this);
        items = itemDAO.getAllItems();
        itemsAdapter = new ItemAdapter(this, items);
        listView.setAdapter(itemsAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItemName = editTextItem.getText().toString();
                String ageText = editTextAge.getText().toString();
                if (!newItemName.isEmpty() && !ageText.isEmpty()) {
                    int age = Integer.parseInt(ageText);
                    itemDAO.addItem(newItemName, age);
                    items.clear();
                    items.addAll(itemDAO.getAllItems());
                    itemsAdapter.notifyDataSetChanged();
                    editTextItem.setText("");
                    editTextAge.setText("");
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Item item = items.get(position);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_edit_item, null);

                final EditText editItemName = dialogView.findViewById(R.id.editTextDialogItemName);
                final EditText editItemAge = dialogView.findViewById(R.id.editTextDialogItemAge);

                editItemName.setText(item.getItemName());
                editItemAge.setText(String.valueOf(item.getAge()));

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Sửa mục")
                        .setView(dialogView)
                        .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String newItemName = editItemName.getText().toString();
                                String newAgeText = editItemAge.getText().toString();
                                if (!newItemName.isEmpty() && !newAgeText.isEmpty()) {
                                    int newAge = Integer.parseInt(newAgeText);
                                    itemDAO.updateItem(item.getId(), newItemName, newAge);
                                    items.clear();
                                    items.addAll(itemDAO.getAllItems());
                                    itemsAdapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Item item = items.get(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xóa mục")
                        .setMessage("Bạn có chắc chắn muốn xóa mục này?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                itemDAO.deleteItem(item.getId());
                                items.remove(item);
                                itemsAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();

                return true;
            }
        });
    }
}
