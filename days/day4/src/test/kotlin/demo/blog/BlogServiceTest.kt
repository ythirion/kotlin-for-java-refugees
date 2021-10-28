package demo.blog

import demo.blog.Article
import demo.blog.BlogService
import demo.blog.Comment
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class BlogServiceTest {

    @Test
    fun `returns a Right for valid comment`() {
        val blogService = BlogService()
        val article = Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        )
        val result = blogService.addComment(article, "Amazing article !!!", "Pablo Escobar")

        assert(result.isRight)
    }

    @Test
    fun `add a comment with the given text`() {
        val blogService = BlogService()
        val article = Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        )
        val text = "Amazing article !!!"
        val result = blogService.addComment(article, text, "Pablo Escobar")

        assert(result.get().comments.first().text == text)
    }

    @Test
    fun `add a comment with the given author`() {
        val blogService = BlogService()
        val article = Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        )
        val author = "Pablo Escobar"
        val result = blogService.addComment(article, "Amazing article !!!", author)

        assert(result.get().comments.first().author == author)
    }

    @Test
    fun `add a comment with the date of the day`() {
        val blogService = BlogService()
        val article = Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        )
        val author = "Pablo Escobar"
        val result = blogService.addComment(article, "Amazing article !!!", author)
    }

    @Test
    fun `returns a Left when adding existing comment`() {
        val blogService = BlogService()
        val article = Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
            listOf(Comment("Amazing article !!!", "Pablo Escobar", LocalDate.now()))
        )
        val result = blogService
            .addComment(article, "Amazing article !!!", "Pablo Escobar")

        assert(result.isLeft)
    }
}