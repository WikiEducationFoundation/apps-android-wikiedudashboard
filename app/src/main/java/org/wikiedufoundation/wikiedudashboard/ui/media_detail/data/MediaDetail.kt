package org.wikiedufoundation.wikiedudashboard.ui.media_detail.data

class MediaDetail (
    val pageid : Int,
    val ns : Int,
    val title : String,
    val globalusage : List<FileUsage>,
    val categories : List<MediaCategory>
//    ,
//    val imageinfo: ImageDetails
)
