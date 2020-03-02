package fr.geraud.mycplusplusmovies

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MoviesControllerWrapperTest {
    @Test
    fun testCreateAndDelete() {
        val controller = MoviesControllerWrapper()

        assertNotNull(controller)

        controller.dispose()
    }

    @Test
    fun testGetMovies() {
        val controller = MoviesControllerWrapper()

        val movies = controller.getMovies()

        assertNotNull(movies)
        assertEquals(10, movies.size)
        controller.dispose()
    }
}
