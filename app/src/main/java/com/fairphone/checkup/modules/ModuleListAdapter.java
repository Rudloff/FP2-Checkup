package com.fairphone.checkup.modules;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fairphone.checkup.R;

import java.util.List;

/**
 * Created by dirk on 16-10-15.
 */
public class ModuleListAdapter extends ArrayAdapter<Module> {

    public interface OnClickListener {
        void onClick(Module test);
    }

    OnClickListener mOnClickListener;

    public ModuleListAdapter(Context context, List<Module> tests, OnClickListener onClickListener) {
        super(context,
                R.layout.module_chooser_list_item, R.id.list_item_title, tests);
        mOnClickListener = onClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        final Module module = getItem(position);
        if (module != null) {
            ((ImageView) view.findViewById(R.id.list_item_avatar)).setImageResource(module.getPictureResourceID());
            ((TextView) view.findViewById(R.id.list_item_title)).setText(getContext().getString(module.getModuleNameID()));
            ((TextView) view.findViewById(R.id.list_item_summary)).setText(getContext().getString(module.getDescriptionId()));
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onClick(module);
            }
        });

        return view;
    }


}
