package com.example.kotlinpract1.UI.View

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlinpract1.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Fragment3 : Fragment() {

    // Метод onCreateView вызывается для создания макета фрагмента из XML.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Инфлейтинг (раздувание) макета fragment3.
        val view = inflater.inflate(R.layout.fragment3, container, false)

        // Находим элементы UI по их ID.
        val buttonLoadImage = view.findViewById<Button>(R.id.button_load_image)
        val editTextImageUrl = view.findViewById<EditText>(R.id.edit_text_image_url)

        // Обработчик клика на кнопку загрузки изображения.
        buttonLoadImage.setOnClickListener {
            val imageUrl = editTextImageUrl.text.toString()

            // Проверяем, не пустой ли URL.
            if (imageUrl.isNotEmpty()) {
                showToast("Загрузка изображения...")
                loadImageAndSave(imageUrl)
            } else {
                showToast("Введите URL изображения")
            }
        }

        return view
    }

    // Метод для асинхронной загрузки и сохранения изображения.
    private fun loadImageAndSave(imageUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Загружаем изображение с помощью библиотеки Picasso.
                val bitmap = Picasso.get().load(imageUrl).get()

                // Сохраняем изображение во внутреннем хранилище.
                saveImageToInternalStorage(bitmap, fileName = "image.jpg")
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Ошибка при загрузке изображения")
            }
        }
    }

    // Метод для сохранения изображения во внутреннем хранилище устройства.
    private fun saveImageToInternalStorage(bitmap: Bitmap, fileName: String) {
        try {
            // Открываем файловый поток для записи изображения.
            requireContext().openFileOutput(fileName, Context.MODE_PRIVATE).use { fileOutputStream ->
                // Сжимаем изображение и записываем в файл.
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            }
            showToast("Изображение успешно сохранено")
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("Ошибка при сохранении изображения")
        }
    }

    // Метод для отображения всплывающих уведомлений (Toast) в основном потоке.
    private fun showToast(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}
