package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

import com.google.gson.annotations.SerializedName

class MediaDetail(
    val pageid: Int,
    val ns: Int,
    val title: String,
    val globalusage: List<FileUsage>,
    val categories: List<MediaCategory>,
    val imageinfo: List<ImageDetails>
)

class ImageDetails(
    @SerializedName("extmetadata")
    val extMetaData: ExtMetaData
)

class ExtMetaData(
    @SerializedName("ImageDescription")
    val description: ImageDescription,

    @SerializedName("License")
    val license: MediaLicense,

    @SerializedName("LicenseUrl")
    val licenseUrl: MediaLicenseUrl

)

class MediaLicense(
    val value: String
)

class MediaLicenseUrl(
    val value: String
)

class ImageDescription(
    val value: String
)
