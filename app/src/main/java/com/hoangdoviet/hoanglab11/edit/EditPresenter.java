package com.hoangdoviet.hoanglab11.edit;

import com.hoangdoviet.hoanglab11.data.dao.UserDAO;
import com.hoangdoviet.hoanglab11.data.model.User;
import com.hoangdoviet.hoanglab11.utils.Constants;
import com.hoangdoviet.hoanglab11.utils.Util;

public class EditPresenter implements EditUser.Presenter{
    private final EditUser.View mView;
    private final UserDAO userDAO;

    public EditPresenter(EditUser.View mView, UserDAO userDAO) {
        this.mView = mView;
        this.userDAO = userDAO;
        this.mView.setPresenter(this);
    }

    @Override
    public void save(User user) {
        long ids = this.userDAO.insertUser(user);
        mView.close();
    }

    @Override
    public boolean validate(User user) {
        mView.clearPreErrors();
        if (user.name.isEmpty() || !Util.isValidName(user.name)) {
            mView.showErrorMessage(Constants.FIELD_NAME);
            return false;
        }
        if (user.address.isEmpty()) {
            mView.showErrorMessage(Constants.FIELD_ADDRESS);
            return false;
        }
        if (user.phone.isEmpty() || !Util.isValidPhone(user.phone)) {
            mView.showErrorMessage(Constants.FIELD_PHONE);
            return false;
        }
        if (user.email.isEmpty() || !Util.isValidEmail(user.email)) {
            mView.showErrorMessage(Constants.FIELD_EMAIL);
            return false;
        }
        if (user.birthday == null) {
            mView.showErrorMessage(Constants.FIELD_BIRTHDAY);
            return false;
        }

        return true;
    }

    @Override
    public void showDateDialog() {
        mView.openDateDialog();
    }

    @Override
    public void getUserAndPopulate(long id) {
        User person = userDAO.findUser(id);
        if (person != null) {
            mView.populate(person);
        }
    }

    @Override
    public void update(User user) {
        int ids = this.userDAO.updateUser(user);
        mView.close();
    }
}
