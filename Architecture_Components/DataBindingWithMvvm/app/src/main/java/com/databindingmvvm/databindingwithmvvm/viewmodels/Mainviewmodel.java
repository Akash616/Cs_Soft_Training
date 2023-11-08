package com.databindingmvvm.databindingwithmvvm.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.databindingmvvm.databindingwithmvvm.models.Customer;

public class Mainviewmodel extends AndroidViewModel {
    //Provider Class
    Customer customer;
    public Mainviewmodel(@NonNull Application application) {
        super(application);
        /*ya provider class hai, jab hum Live pai kaam karanga, toh hama
        * ApI say data lana hoga or kisi other source say data milaga.
        * toh us case mai YAHI par DATA fill hoga. Yahi sara kam karaga DATA
        * ko lana ka.*/
        customer = new Customer("Akash Gupta");
    }

    public Customer getCustomer() {
        return customer;
    }
}
