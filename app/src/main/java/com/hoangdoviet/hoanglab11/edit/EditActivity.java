package com.hoangdoviet.hoanglab11.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.hoangdoviet.hoanglab11.R;
import com.hoangdoviet.hoanglab11.data.AppDatabase;
import com.hoangdoviet.hoanglab11.data.model.User;
import com.hoangdoviet.hoanglab11.utils.Constants;
import com.hoangdoviet.hoanglab11.utils.Util;

import java.util.Date;

public class EditActivity extends AppCompatActivity implements EditUser.View, EditUser.DateListener {

    private EditUser.Presenter mPresenter;

    private EditText mNameEditText;
    private EditText mAddressEditText;
    private EditText mEmailEditText;
    private EditText mBirthdayEditText;
    private EditText mPhoneEditText;

    private TextInputLayout mNameTextInputLayout;
    private TextInputLayout mAddressInputLayout;
    private TextInputLayout mEmailInputLayout;
    private TextInputLayout mBirthdayInputLayout;
    private TextInputLayout mPhoneTextInputLayout;

    private FloatingActionButton mFab;

    private User user;
    private boolean mEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        user = new User();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        mPresenter = new EditPresenter(this, db.userDAO());

        initViews();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mEditMode) {
            mPresenter.getUserAndPopulate(user.id);
        }
    }

    private void initViews() {
        mNameEditText =  findViewById(R.id.nameEditText);
        mAddressEditText = findViewById(R.id.addressEditText);
        mEmailEditText = findViewById(R.id.emailEditText);
        mBirthdayEditText =  findViewById(R.id.birthdayEditText);
        mPhoneEditText =  findViewById(R.id.phoneEditText);

        mNameTextInputLayout =  findViewById(R.id.nameTextInputLayout);
        mAddressInputLayout =  findViewById(R.id.addressTextInputLayout);
        mEmailInputLayout =  findViewById(R.id.emailTextInputLayout);
        mBirthdayInputLayout =  findViewById(R.id.birthdayTextInputLayout);
        mPhoneTextInputLayout =  findViewById(R.id.phoneTextInputLayout);
        mBirthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showDateDialog();
            }
        });
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setImageResource(mEditMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.name = mNameEditText.getText().toString();
                user.address = mAddressEditText.getText().toString();
                user.email = mEmailEditText.getText().toString();
                user.phone = mPhoneEditText.getText().toString();

                boolean valid = mPresenter.validate(user);

                if (!valid) return;

                if (mEditMode) {
                    mPresenter.update(user);
                } else {
                    mPresenter.save(user);
                }
            }
        });
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            user.id = getIntent().getLongExtra(Constants.USER_ID, 0);
            mEditMode = true;
        }
    }

    @Override
    public void setSelectedDate(Date date) {
        user.birthday = date;
        mBirthdayEditText.setText(Util.format(date));
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            mNameTextInputLayout.setError("Invalid Name");
        } else if (field == Constants.FIELD_EMAIL) {
            mEmailInputLayout.setError("Invalid Email");
        } else if (field == Constants.FIELD_PHONE) {
            mPhoneTextInputLayout.setError("Invalid phone");
        } else if (field == Constants.FIELD_ADDRESS) {
            mAddressInputLayout.setError("Invalid address");
        } else if (field == Constants.FIELD_BIRTHDAY) {
            mBirthdayInputLayout.setError("Invalid birthday");
        }
    }

    @Override
    public void clearPreErrors() {
        mNameTextInputLayout.setErrorEnabled(false);
        mEmailInputLayout.setErrorEnabled(false);
        mPhoneTextInputLayout.setErrorEnabled(false);
        mAddressInputLayout.setErrorEnabled(false);
        mBirthdayInputLayout.setErrorEnabled(false);
    }

    @Override
    public void openDateDialog() {
        DateDialogFragment fragment = new DateDialogFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(User person) {
        this.user = person;
        mNameEditText.setText(person.name);
        mAddressEditText.setText(person.address);
        mEmailEditText.setText(person.email);
        mBirthdayEditText.setText(Util.format(person.birthday));
        mPhoneEditText.setText(person.phone);
    }

    @Override
    public void setPresenter(EditUser.Presenter presenter) {
        mPresenter = presenter;
    }
}