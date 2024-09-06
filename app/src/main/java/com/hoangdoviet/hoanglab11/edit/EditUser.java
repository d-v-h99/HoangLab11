package com.hoangdoviet.hoanglab11.edit;

import com.hoangdoviet.hoanglab11.BasePresenter;
import com.hoangdoviet.hoanglab11.BaseView;
import com.hoangdoviet.hoanglab11.data.model.User;

import java.util.Date;

public interface EditUser {
    interface Presenter extends BasePresenter {
        void save(User user);
        boolean validate(User user);
        void showDateDialog();
        void getUserAndPopulate(long id);
        void update(User user);
    }
    interface View extends BaseView<EditUser.Presenter>{
        void showErrorMessage(int field);

        void clearPreErrors();

        void openDateDialog();

        void close();

        void populate(User user);
    }
    interface DateListener {

        void setSelectedDate(Date date);

    }
}
