package mechero.lab2_franciscoalvarez;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FormAdapter extends ArrayAdapter<Form> {

    private Context mContext;
    private List<Form> formList = new ArrayList<>();

    public FormAdapter(Context context, ArrayList<Form> list) {
        super(context, 0 , list);
        mContext = context;
        formList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Form currentForm = formList.get(position);

        TextView cat = (TextView) listItem.findViewById(R.id.textView_cat);
        cat.setText(currentForm.getCategory());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentForm.getName());

        TextView date = (TextView) listItem.findViewById(R.id.textView_date);
        date.setText(currentForm.getDate());

        TextView comment = (TextView) listItem.findViewById(R.id.textView_comment);
        comment.setText(currentForm.getComment());

        return listItem;
    }
}