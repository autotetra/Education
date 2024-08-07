/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hibernateModel.Categories;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;

/**
 *
 * @author a
 */
public class ItemViewModel {
    private Storeitems item; 
    private User user;
    private Categories cat;
    private Store store;

    public Storeitems getItem() {
        return item;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setItem(Storeitems item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categories getCat() {
        return cat;
    }

    public void setCat(Categories cat) {
        this.cat = cat;
    }
}
