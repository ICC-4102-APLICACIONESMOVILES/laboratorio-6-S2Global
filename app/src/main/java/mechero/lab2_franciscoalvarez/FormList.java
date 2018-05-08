package mechero.lab2_franciscoalvarez;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {

                forms = MainActivity.formDatabase.daoAccess().fetchAllPolls();
                Handler mainHandler = new Handler(getActivity().getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listView = getView().findViewById(R.id.list);
                        FormAdapter fa = new FormAdapter(getContext(), (ArrayList<Form>) forms);
                        listView.setAdapter(fa);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                Intent intent = new Intent(getContext(), FillForm.class);
                                Form item = (Form)parent.getAdapter().getItem(position);
                                intent.putExtra("id",item.getPollId());
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();
    }


}