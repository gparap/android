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
package gparap.apps.pdf_reader.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.pdf_reader.R
import gparap.apps.pdf_reader.model.Book

class BookAdapter() : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    var books = ArrayList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pdfImageLogo.setImageResource(R.drawable.ic_pdf_24)
        holder.pdfFileName.text = (books[position].title)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //get itemView's widgets
        val pdfImageLogo: ImageView = itemView.findViewById(R.id.imageViewPDF)
        val pdfFileName: TextView = itemView.findViewById(R.id.textViewFileNamePDF)
    }
}