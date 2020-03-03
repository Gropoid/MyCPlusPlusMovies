package fr.geraud.mycplusplusmovies

data class MovieDetail(
    val name: String,
    val description: String,
    val score: Double,
    val actors: Array<Actor>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieDetail

        if (name != other.name) return false
        if (description != other.description) return false
        if (score != other.score) return false
        if (!actors.contentEquals(other.actors)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + actors.contentHashCode()
        return result
    }
}