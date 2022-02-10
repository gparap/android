package gparap.apps.wallpaper.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.DisplayMetrics
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.test.core.app.ActivityScenario
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.data.CreatorModel
import gparap.apps.wallpaper.data.TagModel
import gparap.apps.wallpaper.data.WallpaperModel
import gparap.apps.wallpaper.ui.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UtilsInstrumentedTest {
    private lateinit var context: Context
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    @SmallTest
    fun getWallpaperDetailsMessage_messageIsCorrect() {
        val expectedMessage = createWallpaperDetailsMessage(mockWallpaperObject(), context)
        val actualMessage = Utils.getWallpaperDetailsMessage(mockWallpaperObject(), context)
        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    @SmallTest
    fun getScreenWidth_screenWidthIsCorrect() {
        val expectedWidth = getScreenWidthInPixels()
        var actualWidth = -1

        //get the actual screen width in pixels
        activityScenario.onActivity {
            val displayMetrics = DisplayMetrics()
            it.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
            actualWidth = Utils.getScreenWidth(it)
        }
        assertEquals(expectedWidth, actualWidth)
    }

    @Test
    @SmallTest
    fun getScreenHeight_screenHeightIsCorrect() {
        val expectedHeight = getScreenHeightInPixels()
        var actualHeight = -1

        //get the actual screen height in pixels
        activityScenario.onActivity {
            val displayMetrics = DisplayMetrics()
            it.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
            actualHeight = Utils.getScreenHeight(it)
        }
        assertEquals(expectedHeight, actualHeight)
    }

    @Test
    @SmallTest
    fun createScaledBitmap() {
        val mockImage = context.resources.getDrawable(R.drawable.ic_wallpaper_24, null)

        //create expected bitmap
        val expectedBitmap = mockImage.toBitmap(
            getScreenWidthInPixels(),
            getScreenHeightInPixels(),
            Bitmap.Config.ARGB_8888
        )

        //create actual bitmap
        val actualBitmap = Utils.createScaledBitmap(
            expectedBitmap.toDrawable(context.resources),
            getScreenWidthInPixels(),
            getScreenHeightInPixels(),
        )

        assertEquals(expectedBitmap.width, actualBitmap.width)
        assertEquals(expectedBitmap.height, actualBitmap.height)
    }

    @Test
    @SmallTest
    fun createStorageDirectory_directoryPathIsCorrect() {
        val expectedDirectoryPath = "/storage/emulated/0/Pictures/wallpapers"
        val actualDirectoryPath = Utils.createStorageDirectory()
        assertEquals(expectedDirectoryPath, actualDirectoryPath.toString())
    }

    @Test
    @SmallTest
    fun createWallpaperFile_filePathIsCorrect() {
        val expectedFilePath = "/storage/emulated/0/Pictures/wallpapers/wallpaper_1.png"
        val actualFilePath = Utils.createWallpaperFile("1")
        assertEquals(expectedFilePath, actualFilePath.toString())
    }

    private fun getScreenWidthInPixels(): Int {
        var width = 0
        activityScenario.onActivity {
            val displayMetrics = DisplayMetrics()
            it.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
            width = displayMetrics.widthPixels
        }
        return width
    }

    private fun getScreenHeightInPixels(): Int {
        var height = 0
        activityScenario.onActivity {
            val displayMetrics = DisplayMetrics()
            it.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
            height = displayMetrics.heightPixels
        }
        return height
    }

    private fun createWallpaperDetailsMessage(wallpaper: WallpaperModel, context: Context): String {
        var message = ""
        message = message
            .plus(context.resources.getString(R.string.text_title))
            .plus(' ')
            .plus(wallpaper.title)
            .plus(context.resources.getString(R.string.text_new_line_double))
            .plus(context.resources.getString(R.string.text_category))
            .plus(' ')
            .plus(wallpaper.category)
            .plus(context.resources.getString(R.string.text_new_line_double))
            .plus(context.resources.getString(R.string.text_creator))
            .plus(' ')
            .plus(wallpaper.creator[0].name)
            .plus(context.resources.getString(R.string.text_new_line_double))
            .plus(context.resources.getString(R.string.text_link))
            .plus(' ')
            .plus(wallpaper.creator[0].website)
            .plus(context.resources.getString(R.string.text_new_line_double))
        return message
    }

    private fun mockWallpaperObject(): WallpaperModel {
        val tagModel = TagModel("base", "info", "extra")
        val tags = listOf(tagModel)
        val creatorModel = CreatorModel("creator1", "website1")
        val creators = listOf(creatorModel)
        return WallpaperModel(
            "1", "imageUrl", "title1", "sortBy1", "category1",
            tags, creators)
    }
}