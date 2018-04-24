package mechero.lab2_franciscoalvarez;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormList extends Fragment {

    ListView listView;
    List<Form> forms;

    public FormList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                forms = MainActivity.formDatabase.daoAccess().fetchAllPolls();
                listView = getView().findViewById(R.id.list);
                ArrayList<Form> forms = new ArrayList<>();
                FormAdapter fa = new FormAdapter(getContext(), forms);
                listView.setAdapter(fa);
            }
        }).start();
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

}
