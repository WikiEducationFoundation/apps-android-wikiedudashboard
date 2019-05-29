package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CourseUpload implements Serializable {
    private int id;
    private String uploaded_at;
    private String usage_count;
    private String url;
    @SerializedName("thumburl")
    private String thumbUrl;
    private boolean deleted;
    @SerializedName("thumbwidth")
    private String thumbWidth;
    @SerializedName("thumbheight")
    private String thumbHeight;
    private String file_name;
    private String uploader;
}
