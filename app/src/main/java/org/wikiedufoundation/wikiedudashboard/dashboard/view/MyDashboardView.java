package org.wikiedufoundation.wikiedudashboard.dashboard.view;

import org.wikiedufoundation.wikiedudashboard.dashboard.data.MyDashboardResponse;
import org.wikiedufoundation.wikiedudashboard.helper.Progressive;
import org.wikiedufoundation.wikiedudashboard.helper.Toaster;

public interface MyDashboardView extends Progressive, Toaster {
    void setData(MyDashboardResponse data);
}
