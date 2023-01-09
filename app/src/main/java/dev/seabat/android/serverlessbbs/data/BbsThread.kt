package dev.seabat.android.serverlessbbs.data

data class BbsThread(
    val image_unread_visible: Boolean,
    val image_new_visible: Boolean,
    val image_follow_visible: Boolean,
    val text_title: String,
    val text_detail: String,
    val text_create_date: String,
    val text_edit_date: String
)