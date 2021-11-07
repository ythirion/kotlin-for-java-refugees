package demo.solution.blog

import io.vavr.control.Either
import io.vavr.control.Either.right
import io.vavr.kotlin.left
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
        return if (article.comments.contains(comment))
            left(listOf(ValidationError(existingCommentError)))
        else {
            val comments = article.comments + comment
            right(Article(article.name, article.content, comments))
        }
    }

    companion object ErrorMessage {
        const val existingCommentError: String = "Comment already in the article"
    }
}