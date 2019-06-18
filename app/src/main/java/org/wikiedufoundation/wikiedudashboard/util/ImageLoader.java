package org.wikiedufoundation.wikiedudashboard.util;

import android.widget.ImageView;
import android.widget.ProgressBar;

public interface ImageLoader {

    void loadImage(String url, ImageView imageView, ProgressBar progressBar);

}
