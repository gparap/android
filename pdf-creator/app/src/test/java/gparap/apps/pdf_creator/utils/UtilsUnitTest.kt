/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.pdf_creator.utils

import org.junit.Assert.assertEquals
import org.junit.Test

internal class UtilsUnitTest {
    private val pdfInput = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n" +
            "Vestibulum fringilla vel magna scelerisque finibus. \n" +
            "Aliquam erat volutpat. In in quam aliquet, finibus leo a, rutrum enim.\n" +
            "Integer urna odio, suscipit at ligula eu,\n" +
            "condimentum ullamcorper nisl. \n" +
            "Pellentesque scelerisque consequat dui a posuere.\n" +
            "Phasellus iaculis aliquet quam, et facilisis elit viverra eu.\n" +
            "Mauris eget finibus est. Etiam porta, sapien eget vulputate pretium,\n" +
            "ipsum lacus venenatis diam, vitae interdum enim lectus dapibus ipsum.\n" +
            "Donec tincidunt vestibulum sodales. Sed non leo a dui gravida vestibulum.\n" +
            "Mauris tincidunt placerat sollicitudin. \n" +
            "Maecenas sit amet pharetra velit, venenatis mollis lorem. \n" +
            "Aliquam erat volutpat. Donec placerat nibh vitae augue gravida lacinia. \n" +
            "Donec et suscipit nibh.\n" +
            "\n" +
            "Vestibulum ut ullamcorper neque. Praesent viverra tincidunt tincidunt. Vivamus risus mauris, mollis et enim ut, pulvinar porta lorem. In quis orci ante. Maecenas tempor leo quis mattis feugiat. Suspendisse cursus libero eget venenatis porta. Fusce dictum molestie enim, at eleifend lacus euismod in. Ut maximus magna egestas, cursus tortor malesuada, pulvinar eros. Duis et magna et enim euismod vulputate. Aliquam pulvinar nibh tempus, ullamcorper dui ut, vehicula dui. Morbi congue commodo lectus at commodo. Quisque quis justo facilisis massa vehicula porta. Praesent dictum quam nec semper gravida.\n" +
            "\n" +
            "Fusce molestie auctor urna vel rutrum. Donec vitae arcu aliquet, scelerisque arcu non, consectetur sapien. Suspendisse mauris libero, tempus nec egestas eget, cursus id diam. Nulla tempor tortor a neque malesuada tempus. Vestibulum at nunc non purus tincidunt convallis. Mauris lobortis ex nisl, in cursus lectus molestie pharetra. Maecenas nulla tortor, vulputate at nisi aliquam, tincidunt porta dolor. Pellentesque quis sollicitudin risus. Morbi est diam, venenatis ac ipsum id, ultrices molestie metus. Ut tortor ligula, porttitor sed elementum non, faucibus id lacus.\n" +
            "\n" +
            "Duis feugiat lorem id mi lobortis luctus. Donec ornare nulla vel justo posuere, id sodales sem rutrum. Proin nec nisl aliquam, venenatis dolor non, sodales neque. Ut ac sapien in orci convallis mattis. Duis hendrerit, lacus vel finibus varius, nulla nunc iaculis diam, congue mollis enim tortor ac tortor. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Curabitur vel nisi mauris. Donec et ipsum ac nisl efficitur accumsan in fermentum elit. Curabitur arcu quam, tempus non quam semper, aliquet convallis odio.\n" +
            "\n" +
            "Nullam feugiat enim mi, id venenatis ex hendrerit nec. Morbi rutrum orci a rhoncus tempor. Mauris sed velit leo. Praesent tristique arcu ut lacus gravida, sed euismod dui fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam luctus fermentum est sed facilisis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean aliquam interdum libero, ut cursus tortor fringilla vel. Donec in tellus at ex tempor lacinia et at quam. Proin lobortis viverra finibus."

    @Test
    fun getPagesCount() {
        val expectedPages = 6
        val actualPages = Utils.getPagesCount(pdfInput)
        assertEquals("Pages count is wrong.", expectedPages, actualPages)
    }

    @Test
    fun getPages() {
        var expectedInput = ""
        val actualInput = pdfInput

        //get the pages and concatenate them
        val pages = Utils.getPages(pdfInput)
        pages.forEach { page ->
            expectedInput = expectedInput.plus(page)
        }

        assertEquals("Pages are wrong.", expectedInput, actualInput)
    }
}