package com.fphoenixcorneae.recyclerview.snaphelper.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.fphoenixcorneae.recyclerview.snaphelper.R;
import com.fphoenixcorneae.recyclerview.snaphelper.adapter.RecyclerSnapAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wkz
 */
public class PagerSnapActivity extends AppCompatActivity {

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
        new PagerSnapHelper().attachToRecyclerView(mRvStartSnap);
    }
}
