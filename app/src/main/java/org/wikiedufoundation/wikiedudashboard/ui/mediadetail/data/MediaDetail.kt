package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data

import com.google.gson.annotations.SerializedName

/**
 * [MediaDetail] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class MediaDetail(
    @SerializedName("pageid")
    val pageId: Int,
    @SerializedName("ns")
    val ns: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("globalusage")
    val globalUsage: List<FileUsage>,
    @SerializedName("categories")
    val categories: List<MediaCategory>,
    @SerializedName("imageinfo")
    val imageInfo: List<ImageDetails>
)

/**
 * [ImageDetails] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class ImageDetails(
    @SerializedName("extmetadata")
    val extMetaData: ExtMetaData
)

/**
 * [ExtMetaData] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class ExtMetaData(
    @SerializedName("ImageDescription")
    val description: ImageDescription,

    @SerializedName("License")
    val license: MediaLicense,

    @SerializedName("LicenseUrl")
    val licenseUrl: MediaLicenseUrl

)

/**
 * [MediaLicense] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class MediaLicense(
    val value: String
)

/**
 * [MediaLicenseUrl] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class MediaLicenseUrl(
    val value: String
)

/**
 * [ImageDescription] model class
 * @constructor primary constructor to initialize properties and variables
 * ***/
data class ImageDescription(
    val value: String
)
