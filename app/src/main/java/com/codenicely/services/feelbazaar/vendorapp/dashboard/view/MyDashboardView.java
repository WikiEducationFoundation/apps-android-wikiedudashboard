package com.codenicely.services.feelbazaar.vendorapp.dashboard.view;

import com.codenicely.services.feelbazaar.vendorapp.dashboard.data.MyDashboardResponse;
import com.codenicely.services.feelbazaar.vendorapp.helper.Progressive;
import com.codenicely.services.feelbazaar.vendorapp.helper.Toaster;

public interface MyDashboardView extends Progressive, Toaster {
    void setData(MyDashboardResponse data);
}
