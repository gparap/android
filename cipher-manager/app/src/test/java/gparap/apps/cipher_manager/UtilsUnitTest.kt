/*
 * Copyright 2024 gparap
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
package gparap.apps.cipher_manager

import gparap.apps.cipher_manager.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsUnitTest {
    @Test
    fun encryptTextWithAES_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="AAAAAAAAAAAAAAAAAAAAACGNaHWBhySF6MY9S4ZrsPU="
        val actualValue = Utils.encryptWithAES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithAES_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "AAAAAAAAAAAAAAAAAAAAACGNaHWBhySF6MY9S4ZrsPU="
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithAES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun encryptTextWithARC4_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="K44GpBlgqhRJ6qk+"
        val actualValue = Utils.encryptWithARC4(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithARC4_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "K44GpBlgqhRJ6qk+"
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithARC4(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun encryptTextWithBlowfish_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="AAAAAAAAAAC8WzxTJgVCHnjgelwyPhyf"
        val actualValue = Utils.encryptWithBlowfish(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithBlowfish_isCorrect() {
        val publicKey = "publicKey0123456".toByteArray()
        val textInput = "AAAAAAAAAAC8WzxTJgVCHnjgelwyPhyf"
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithBlowfish(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun encryptTextWithDES_isCorrect() {
        val publicKey = "key01234".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="AAAAAAAAAACO5wQ02XGl/DCMCrHwsgf8"
        val actualValue = Utils.encryptWithDES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithDES_isCorrect() {
        val publicKey = "key01234".toByteArray()
        val textInput = "AAAAAAAAAACO5wQ02XGl/DCMCrHwsgf8"
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithDES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun encryptTextWithTripleDES_isCorrect() {
        val publicKey = "publickey0123456789x3DES".toByteArray()
        val textInput = "Hello World!"
        val expectedValue ="AAAAAAAAAAAOdd2cwioy+eD9alRMBtO/"
        val actualValue = Utils.encryptWithTripleDES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun decryptTextWithTripleDES_isCorrect() {
        val publicKey = "publickey0123456789x3DES".toByteArray()
        val textInput = "AAAAAAAAAAAOdd2cwioy+eD9alRMBtO/"
        val expectedValue ="Hello World!"
        val actualValue = Utils.decryptWithTripleDES(publicKey, textInput)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun doCipherWithRSA_isCorrect() {
        val privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjiy/jw4nEHud6ej3NjOFGFJdovnu6LzyMheNmHAGyi2ZwpgJKgYVKQe7rjMBLAwQTKYOG80RUhGLHMJrj5lZJ+2sp2olnjzl6X0sGbgRw76Js5Am8eO3ZQEnNGsPSQecbyNnK3PTNTqK/UpWIBeUtfc3t9/aW2vjVX5JJXsoEcK0WLPcNj5EyL/cqbUsJsPsr4xE/zVwxCYdQDlzGwXOOF0VjYmKbhNndq2n78TOAZ9U21JB14BeyHZtytfyG0l3fIofc8se7W3NYGFVPoU+ip2Les3Xnt/Ck4Yab3MmmltXinSl4elgp6PPqd4U6VSAjcAWyS1JxfM523k/+Q20RAgMBAAECggEACVStASyUOQBu4kfkbAx2Q2B0IYWhq0Yb6SQP7PDa33AQ4os0LQJOA6Y2npGPnJAB3OKE1q+dNcm+VYFGSwumn2605uTJK2OKatsSPZ4MCpH/hUEu9YX8QwWGGGnHI6E/MXCAjZCU9nw+sJYCgknc0R9A/ytjMijw4sKixuUdt0pLlTpop6ArZCnJf6Q++OgHGc4d8JsQVoBCg2AbOkfzz1vY8YlZxloJs4u82ZnC2Ut/o3uwRUmXFYUatt1ip45W2nDGQ7s+bhQiMteVOw8+NbyXGYuDkW4PJ8dhxuE/wWYHYgci50eR5ELghJwkhyN3iclbElrS04qLG+tTdblJAQKBgQDEHKkmw8WMMHp2+NpQUjtGpFHSxax3E1OH7GuYY5GaeqHxGSppyjoJu/TRy/jcEpqdQO0mpiVj/5weFf7I8Opapm8t9Q/4zOyeZAkyI5fjuQJXVTfwh2Fd6HKcWQpTXMCD980PKTDSvMaij0Tu6jfLpjBg89KWlwqwOzuTVZJUgQKBgQDVfHSHOZVeX7DiqPpUD9b+aAqU9YUJrAsds5Pv9vceSBa3Hmxt4RPmeKaxJ24UWKXUeSniXPuJ6tteVRnBgDhqALrnDhSWrqc6a6PY7/o88Dj1T5H8qedlnrmxQR4FcpjpwIjIqYJRiF1kiTQrZwILCM4aiofi7jcoxUW31lmQkQKBgHpHIJUCFket5n4N9hmE3F7UkAwqorlwPHIQ0Jmo4519f0HUHiTuKbfuEN1LH/UgSHVqiTPC/qdWNmJv9ngV9xnkkogGOBo6tAKifzVbCha8QaBwizuXPfeOuv+PaSfHOmZOePZOCZosiHHNgP6y3W1GRv38qT6qGMj/dcsM3PuBAoGBAKOWgYiZRG8TrmyRBXlixeEegnVQicg2kDILSGJKAWSv4tAhPP8ZyPEDJOB9EWn/39qnmcO+cnVlhQGM/nZ7NT9tHcsty/MHFFe84cm7YipN/adkeL5+xzTkYvCDsGiGPsxn36i704N8lewTAun7PeKMtbo6yxPGbHgOanULiIgxAoGBAJfmMGJciVtiF8AazP4QLZ1PMRVJPZ9+15wW8pNJa6L+Uj9z8GoFWiM8iXfaEgMrU7xZEvZDdG9680L84o3QAqnwHJpcZAC2anvaqvGqWPT+w/4hvh/SMii/I84bVEEPhWrxFwIZTi49cdeFYpoFLUJZv/vADNSgxCaIjMIZeaJM"
        val publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo4sv48OJxB7neno9zYzhRhSXaL57ui88jIXjZhwBsotmcKYCSoGFSkHu64zASwMEEymDhvNEVIRixzCa4+ZWSftrKdqJZ485el9LBm4EcO+ibOQJvHjt2UBJzRrD0kHnG8jZytz0zU6iv1KViAXlLX3N7ff2ltr41V+SSV7KBHCtFiz3DY+RMi/3Km1LCbD7K+MRP81cMQmHUA5cxsFzjhdFY2Jim4TZ3atp+/EzgGfVNtSQdeAXsh2bcrX8htJd3yKH3PLHu1tzWBhVT6FPoqdi3rN157fwpOGGm9zJppbV4p0peHpYKejz6neFOlUgI3AFsktScXzOdt5P/kNtEQIDAQAB"
        val textInput = "Hello World!"
        val encryptedValue = Utils.encryptWithRSA(publicKey, textInput)
        val decryptedValue = Utils.decryptWithRSA(privateKey, encryptedValue)
        assertEquals(decryptedValue, textInput)
    }
}