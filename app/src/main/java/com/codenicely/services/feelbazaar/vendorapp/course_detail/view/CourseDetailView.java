package com.codenicely.services.feelbazaar.vendorapp.course_detail.view;

import com.codenicely.services.feelbazaar.vendorapp.course_detail.data.CourseDetail;
import com.codenicely.services.feelbazaar.vendorapp.helper.Progressive;
import com.codenicely.services.feelbazaar.vendorapp.helper.Toaster;

public interface CourseDetailView extends Progressive, Toaster {
    void setData(CourseDetail data);
}
