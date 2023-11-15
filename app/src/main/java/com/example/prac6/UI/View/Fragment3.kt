package com.example.kotlinpract1.UI.View

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kotlinpract1.R
import com.squareup.picasso.Picasso


class Fragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment3, container, false)

        val buttonLoadImage = view.findViewById<Button>(R.id.button_load_image)
        val editTextImageUrl = view.findViewById<EditText>(R.id.edit_text_image_url)

        buttonLoadImage.setOnClickListener {
            val imageUrl = editTextImageUrl.text.toString()
            if (imageUrl.isNotEmpty()) {
                Toast.makeText(context, "гуд", Toast.LENGTH_SHORT).show()

                val networkThread = Thread {
                    try {
                        val bitmap = Picasso.get().load(imageUrl).get()

                        val diskThread = Thread {
                            saveImageToInternalStorage(bitmap, fileName = "image.jpg")
                        }
                        diskThread.start()
                    } catch (e: Exception) {

                        e.printStackTrace()
                    }
                }
                networkThread.start()
            } else {
                Toast.makeText(requireContext(), "Введите URL изображения", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap, fileName: String) {
        val context = requireContext()

        try {
            // Открываем или создаем файл для сохранения изображения во внутренней памяти
            val fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

            // Сохраняем изображение в формате JPEG с качеством 100 (максимальное качество)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)

            // Закрываем поток после записи
            fileOutputStream.close()

            Log.i("SafeImage","Изображение успешно сохранено")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("SafeImage","Ошибка при сохранении изображения")
        }
    }
}