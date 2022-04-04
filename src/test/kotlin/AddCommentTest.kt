import org.junit.Test
import java.time.Instant
import org.junit.Assert.*

class AddCommentTest {
    @Test(expected = PostNotFoundException::class)
    fun shouldPostNotFoundException() {
        var testPost = Post(
            ownerId = 106724,
            fromId = 205445,
            createdBy = 556933,
            text = "Test text post bla",
            replyOwnerId = 225666,
            replyPostId = 558455,
            friendsOnly = false,
            postType = "post",
            signerId = 447885,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true
        )

        var oneMoreTestPost = Post(
            ownerId = 111222,
            fromId = 223332,
            createdBy = 456975,
            text = "it is another post!",
            replyOwnerId = 334443,
            replyPostId = 332223,
            friendsOnly = false,
            postType = "post",
            signerId = 323232,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true
        )

        WallService.add(testPost)
        WallService.add(oneMoreTestPost)

        val commentToTestPost: Comment = Comment(
            _canPost = true,
            _showReplyButton = true,
            _groupsCanPost = true,
            _isDon = true,
            id = 554447,
            fromId = 558998,
            date = Instant.now().epochSecond.toInt()
        )
        commentToTestPost.text = "some text to test post"
        WallService.createComment(commentToTestPost, 2)
    }

    @Test
    fun successAddCommentToPost() {
        WallService.posts = emptyArray()
        WallService.comments = emptyArray()

        var testPost = Post(
            ownerId = 106724,
            fromId = 205445,
            createdBy = 556933,
            text = "Test text post bla",
            replyOwnerId = 225666,
            replyPostId = 558455,
            friendsOnly = false,
            postType = "post",
            signerId = 447885,
            canPin = false,
            canDelete = false,
            canEdit = true,
            isPinned = false,
            markedAsAds = false,
            isFavorite = true
        )

        WallService.add(testPost)

        val counterCommentBeforeAdd = testPost.comments.count

        val commentToTestPost = Comment(
            _canPost = true,
            _showReplyButton = true,
            _groupsCanPost = true,
            _isDon = true,
            id = 554447,
            fromId = 558998,
            date = Instant.now().epochSecond.toInt()
        )
        commentToTestPost.text = "some text to test post"
        WallService.createComment(commentToTestPost, 0)

        val counterCommentAfterAdd = testPost.comments.count
        assertEquals(counterCommentBeforeAdd,counterCommentAfterAdd - 1u)

        var checkCommentId: Boolean = false
        for (comment in WallService.comments) {
            if(comment.id == commentToTestPost.id) {
                checkCommentId = true
            }
        }
        assertEquals(true,checkCommentId)
    }
}