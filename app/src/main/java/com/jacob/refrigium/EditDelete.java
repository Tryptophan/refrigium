package com.jacob.refrigium;

/**
 * Created by jacob on 8/17/15.
 */
public class EditDelete {
    private boolean isEditDelete;
    private int position;

    public EditDelete() {
        this.isEditDelete = false;
        this.position = 0;
    }

    public EditDelete(boolean isEditDelete, int position) {
        this.isEditDelete = isEditDelete;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isEditDelete() {
        return isEditDelete;
    }

    public void setIsEditDelete(boolean isEditDelete) {
        this.isEditDelete = isEditDelete;
    }
}
