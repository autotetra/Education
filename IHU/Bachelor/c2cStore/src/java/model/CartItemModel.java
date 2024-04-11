/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hibernateModel.Storeitems;

/**
 *
 * @author a
 */
public class CartItemModel {
    private Storeitems si;
    private int qua;

    public CartItemModel(Storeitems si, int qua) {
        this.si = si;
        this.qua = qua;
    }

    public CartItemModel() {
    }

    public Storeitems getSi() {
        return si;
    }

    public void setSi(Storeitems si) {
        this.si = si;
    }

    public int getQua() {
        return qua;
    }

    public void setQua(int qua) {
        this.qua = qua;
    }
}
