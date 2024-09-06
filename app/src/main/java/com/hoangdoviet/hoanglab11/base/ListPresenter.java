package com.hoangdoviet.hoanglab11.base;

import com.hoangdoviet.hoanglab11.data.dao.UserDAO;
import com.hoangdoviet.hoanglab11.data.model.User;

import java.util.List;

public class ListPresenter implements ListUser.Presenter{
    private final ListUser.View mView;
    private final UserDAO userDAO;

    public ListPresenter(ListUser.View mView, UserDAO userDAO) {
        this.mView = mView;
        this.userDAO = userDAO;
        this.mView.setPresenter(this);
    }

    @Override
    public void addNewUser() {
        mView.showAddUser();
    }

    @Override
    public void populateUser() {
        List<User> users = userDAO.getAllUser();
        if (users != null && !users.isEmpty()) {
            mView.setUsers(users);
        } else {
            mView.showEmptyMessage();
        }


    }

    @Override
    public void openEditScreen(User user) {
        mView.showEditScreen(user.id);
    }

    @Override
    public void openConfirmDeleteDialog(User user) {
        mView.showDeleteConfirmDialog(user);
    }

    @Override
    public void delete(long userID) {
        User user = userDAO.findUser(userID);
        userDAO.deleteUser(user);
    }
}
