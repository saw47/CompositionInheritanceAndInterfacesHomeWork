class Comment(
    _canPost: Boolean,
    _showReplyButton: Boolean,
    _groupsCanPost: Boolean,
    _isDon: Boolean,
    var id: Int,
    val fromId: Int,
    val date: Int,
    ) {
    var text: String? = null
    var replyToUser: Int? = null

    var attachments = emptyArray<AbleAttachToComment>()
    var parentsStack = emptyArray<Comment>()

    var donut:Donut = Donut(_isDon)
    var thread = Thread(_canPost, _showReplyButton, _groupsCanPost)
}

class Thread(_canPost: Boolean, _showReplyButton: Boolean, _groupsCanPost: Boolean) {
    var count: Int = 0
    var items = emptyArray<Comment>()
    var canPost = _canPost
    var showReplyButton = _showReplyButton
    var groupsCanPost = _groupsCanPost
}

class Donut(_isDon: Boolean) {
    var isDon = _isDon
    val placeholder: String = "Some text for users, who isn't have Donut subscribe "
}