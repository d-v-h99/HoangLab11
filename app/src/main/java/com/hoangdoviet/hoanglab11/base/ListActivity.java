package com.hoangdoviet.hoanglab11.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoangdoviet.hoanglab11.edit.EditActivity;
import com.hoangdoviet.hoanglab11.R;
import com.hoangdoviet.hoanglab11.data.AppDatabase;
import com.hoangdoviet.hoanglab11.data.model.User;
import com.hoangdoviet.hoanglab11.utils.Constants;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListUser.View, ListUser.OnItemClickListener, ListUser.DeleteListener {

    private ListUser.Presenter mPresenter;
    private UserAdapter mUserAdapter;
    private TextView mEmptyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> mPresenter.addNewUser());
        mEmptyTextView = findViewById(R.id.emptyTextView);

        RecyclerView recyclerView = findViewById(R.id.listRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter = new UserAdapter(this);
        recyclerView.setAdapter(mUserAdapter);
        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new ListPresenter(this, db.userDAO());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.populateUser();
    }

    @Override
    public void setConfirm(boolean confirm, long personId) {
        if (confirm) {
            mPresenter.delete(personId);
            mUserAdapter.removeUser(personId);
        }

    }

    @Override
    public void clickItem(User person) {
        mPresenter.openEditScreen(person);
    }

    @Override
    public void clickLongItem(User person) {
        mPresenter.openConfirmDeleteDialog(person);
    }

    @Override
    public void showAddUser() {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUsers(List<User> userList) {
        mEmptyTextView.setVisibility(View.GONE);
        mUserAdapter.setValues(userList);
    }

    @Override
    public void showEditScreen(long userID) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(Constants.USER_ID, userID);
        startActivity(intent);
    }

    @Override
    public void showDeleteConfirmDialog(User user) {
        DeleteConfirmFragment fragment = new DeleteConfirmFragment();
        Bundle bundle = new Bundle();
        Log.d("Checkk", ""+user.id);
        bundle.putLong(Constants.USER_ID, user.id);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "confirmDialog");
    }

    @Override
    public void showEmptyMessage() {
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(ListUser.Presenter presenter) {
        mPresenter = presenter;
    }
}