package com.wkz.snaphelper.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wkz.snaphelper.R;
import com.wkz.snaphelper.adapter.RecyclerSnapAdapter;
import com.wkz.snaphelper.widget.EndSnapHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wkz
 */
public class EndSnapActivity extends AppCompatActivity {

    @BindView(R.id.rvStartSnap)
    RecyclerView mRvStartSnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_snap);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRvStartSnap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvStartSnap.setHasFixedSize(true);
        mRvStartSnap.setAdapter(new RecyclerSnapAdapter());
        new EndSnapHelper().attachToRecyclerView(mRvStartSnap);
    }
}
