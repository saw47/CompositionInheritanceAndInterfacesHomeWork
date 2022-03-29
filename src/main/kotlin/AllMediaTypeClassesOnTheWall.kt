internal class Photo(
    _id: Int,
    _albumId: Int,
    _ownerId: Int,
    _userId: Int,
    _text: String,
    _date: Int,
    _width: Int,
    _height: Int,
    _type: String,
    _url: String
) : Attachments {
    override val type: String = "photo"
    internal val internalPhoto: InternalPhoto = InternalPhoto(
        _id, _albumId, _ownerId, _userId, _text, _date, _width, _height, _type, _url
    )

    inner class InternalPhoto(
        private val id: Int,
        private val albumId: Int,
        private val ownerId: Int,
        private val userId: Int,
        private val text: String,
        private val date: Int,
        private val originalWidth: Int,
        private val originalHeight: Int,
        _type: String,
        _url: String
    ) {

        internal val sizes = arrayOf(PhotoSizes(_type, _url, originalWidth, originalHeight))

        inner class PhotoSizes(
            internal val type: String,
            internal val url: String,
            internal val width: Int,
            internal val height: Int
        )

    }
}

internal class PostedPhoto(
    _id: Int,
    _userId: Int,
    _photo130: String,
    _photo604: String,
) : Attachments {
    override val type: String = "posted_photo"
    internal val internalPostedPhoto = InternalPostedPhoto(_id, _userId, _photo130, _photo604)

    inner class InternalPostedPhoto(
        private val id: Int,
        private val userId: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

internal class Graffiti(
    _id: Int,
    _ownerId: Int,
    _photo130: String,
    _photo604: String,
) : Attachments {
    override val type: String = "graffiti"
    internal val internalGraffiti = InternalGraffiti(_id, _ownerId, _photo130, _photo604)

    inner class InternalGraffiti(
        private val id: Int,
        private val ownerId: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

internal class App(
    _id: Int,
    _name: Int,
    _photo130: String,
    _photo604: String,
) : Attachments {
    override val type: String = "app"
    internal val internalApp = InternalApp(_id, _name, _photo130, _photo604)

    inner class InternalApp(
        private val id: Int,
        private val name: Int,
        private val photo130: String,
        private val photo604: String,
    )
}

internal class PrettyCards(
    _cardId: String,
    _linkUrl: String,
    _title: String,
    _width: Int,
    _height: Int
) : Attachments {
    override val type: String = "pretty_cards"
    internal val postedPhoto = InternalPrettyCards(_cardId, _linkUrl, _title, _width, _height)

    inner class InternalPrettyCards(
        private val cardId: String,
        private val linkUrl: String,
        private val title: String,
        _width: Int,
        _height: Int
    ) {
        var images = arrayOf<Images>(Images(linkUrl, _width, _height))

        inner class Images(
            private val url: String,
            private val width: Int,
            private val height: Int
        )
    }

}

