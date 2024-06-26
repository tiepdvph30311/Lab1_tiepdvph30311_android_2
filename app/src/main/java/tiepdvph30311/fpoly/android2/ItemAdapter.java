package tiepdvph30311.fpoly.android2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }

        TextView itemTextView = convertView.findViewById(R.id.itemTextView);
        TextView ageTextView = convertView.findViewById(R.id.itemAge);

        itemTextView.setText(item.getItemName());
        ageTextView.setText(String.valueOf(item.getAge())); // Hiển thị age

        return convertView;
    }

    public void removeItem(Item item) {
        remove(item);
        notifyDataSetChanged();
    }
}
