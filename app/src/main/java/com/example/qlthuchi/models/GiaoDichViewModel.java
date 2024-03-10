package com.example.qlthuchi.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class GiaoDichViewModel extends ViewModel {
    private MutableLiveData<List<GiaoDich>> liveGiaoDichList = new MutableLiveData<>();

    public LiveData<List<GiaoDich>> getLiveGiaoDichList() {
        return liveGiaoDichList;
    }

    public void setGiaoDichList(List<GiaoDich> giaoDichList) {
        liveGiaoDichList.setValue(giaoDichList);
    }
    public void addGiaoDich(GiaoDich giaoDich){
        List<GiaoDich> currentList = liveGiaoDichList.getValue();
        if (currentList != null) {
            currentList.add(giaoDich);
            liveGiaoDichList.setValue(currentList);
        }
    }
    public void removeGiaoDich(GiaoDich giaoDich){
        List<GiaoDich> currentList = liveGiaoDichList.getValue();
        if (currentList != null) {
            currentList.remove(giaoDich);
            liveGiaoDichList.setValue(currentList);
        }
    }
    public void editGiaoDich(GiaoDich updatedGiaoDich) {
        List<GiaoDich> currentList = liveGiaoDichList.getValue();
        if (currentList != null) {
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getId() == updatedGiaoDich.getId()) {
                    currentList.set(i, updatedGiaoDich);
                    liveGiaoDichList.setValue(currentList);
                    break;
                }
            }
        }
    }

}