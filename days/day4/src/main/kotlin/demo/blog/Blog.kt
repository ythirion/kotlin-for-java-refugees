package demo.blog

import io.vavr.control.Either
import java.time.LocalDate

data class Comment(val text: String, val author: String, val creationDate: LocalDate)

data class ValidationError(val description: String)

data class Article(
    val name: String,
    val content: String,
    val comments: List<Comment> = emptyList()
)

class BlogService {

    fun addComment(article: Article, text: String, author: String)
            : Either<List<ValidationError>, Article> {
        return addComment(article, text, author, LocalDate.now())
    }

    private fun addComment(article: Article, text: String, author: String, creationDate: LocalDate)
            : Either<List<ValidationError>, Article> {
        val comment = Comment(text, author, creationDate)
        if (article.comments.contains(comment))
            return Either.left(listOf(ValidationError(existingCommenetError)))
        else {
            val comments = listOf(comment, *article.comments.toTypedArray())
            return Either.right(Article(article.name, article.content, comments))
        }

    }

    companion object ErrorMessage {
        val existingCommenetError: String = "Comment already in the article"
    }
}