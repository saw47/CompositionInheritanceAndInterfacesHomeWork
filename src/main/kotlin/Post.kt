internal data class Post(
    val ownerId: Int,
    var fromId: Int,
    var createdBy: Int,
    var text: String,
    var replyOwnerId: Int,
    var replyPostId: Int,
    var friendsOnly: Boolean,
    var postType: String, //Type of the post, can be: post, copy, reply, postpone, suggest.
    var signerId: Int,
    var canPin: Boolean,
    var canDelete: Boolean,
    var canEdit: Boolean,
    var isPinned: Boolean,
    var markedAsAds: Boolean,
    var isFavorite: Boolean
) {
    var id: Int = -1
    private val date: Int = (System.currentTimeMillis() * 1000).toInt()
    var copyHistory = emptyArray<Post>()

    var comments = Comments()
    var likes = Likes()
    var reposts = Reposts()

    internal inner class Comments() {
        var count: UInt = 0u
        var canPost: Boolean = true
        var groupsCanPost: Boolean = true
    }

    internal inner class Likes() {
        var count: UInt = 0u
        var userLikes: Boolean = true
        var canLike: Boolean = true
        var canPublish: Boolean = true
    }

    internal inner class Reposts() {
        var count: UInt = 0u
        var userReposted: Boolean = true
    }

    internal inner class Geo(val type: String, val coordinates: String) {

        internal inner class Place(
            val latitude: Int,
            val longitude: Int,
            val icon: String,
            val country: String,
            val city: String
        ) {
            var id: Int? = null
            var title: String? = null
            var created: Int? = null

            //If place id added as a checkin, place object has additional fields:
            var type: Int? = null
            var groupId: Int? = null
            var groupPhoto: String? = null
            var checkIns: Int? = null
            var updated: Int? = null
            var address: Int? = null

        }
    }

    internal inner class PostSource(
        val type: String,
        val url: String
    ) {
        var platform: String? = null
        var data: String? = null
    }

    var attachments = emptyArray<Attachments>()
}

internal object WallService {
    var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        post.id = if (posts.isNotEmpty()) {
            (posts.lastIndex + 1)
        } else 0
        posts += post
        return post
    }

    internal fun update(post: Post): Boolean {
        if (post.id in posts.indices && post.id == posts[post.id].id) {
            posts[post.id].fromId = post.fromId
            posts[post.id].createdBy = post.createdBy
            posts[post.id].text = post.text
            posts[post.id].replyOwnerId = post.replyOwnerId
            posts[post.id].replyPostId = post.replyPostId
            posts[post.id].friendsOnly = post.friendsOnly
            posts[post.id].postType = post.postType
            posts[post.id].signerId = post.signerId
            posts[post.id].canPin = post.canPin
            posts[post.id].canDelete = post.canDelete
            posts[post.id].canEdit = post.canEdit
            posts[post.id].isPinned = post.isPinned
            posts[post.id].markedAsAds = post.markedAsAds
            posts[post.id].isFavorite = post.isFavorite

            posts[post.id].comments.count = post.comments.count
            posts[post.id].comments.canPost = post.comments.canPost
            posts[post.id].comments.groupsCanPost = post.comments.groupsCanPost

            posts[post.id].likes.count = post.likes.count
            posts[post.id].likes.userLikes = post.likes.userLikes
            posts[post.id].likes.canLike = post.likes.canLike
            posts[post.id].likes.canPublish = post.likes.canPublish

            posts[post.id].reposts.count = post.reposts.count
            posts[post.id].reposts.userReposted = post.reposts.userReposted
            return true
        } else {
            return false
        }
    }
}

