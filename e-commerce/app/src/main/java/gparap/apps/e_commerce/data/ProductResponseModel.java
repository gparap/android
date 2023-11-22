/*
 * Copyright 2023 gparap
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
package gparap.apps.e_commerce.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/** @noinspection unused*/
public class ProductResponseModel implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("category_id")
    private int categoryId;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("item_cost")
    private Float price;
    @SerializedName("item_discount")
    private int discount;
    @SerializedName("items_left")
    private int stock;
    @SerializedName("image_url")
    private String url;
    @SerializedName("keywords")
    private String keywords;

    public ProductResponseModel() {
    }

    public ProductResponseModel(int id, int categoryId, String name, String description, Float price, int discount, int stock, String url, String keywords) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.url = url;
        this.keywords = keywords;
    }

    protected ProductResponseModel(Parcel in) {
        id = in.readInt();
        categoryId = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readFloat();
        discount = in.readInt();
        stock = in.readInt();
        url = in.readString();
        keywords = in.readString();
    }

    public static final Creator<ProductResponseModel> CREATOR = new Creator<ProductResponseModel>() {
        @Override
        public ProductResponseModel createFromParcel(Parcel in) {
            return new ProductResponseModel(in);
        }

        @Override
        public ProductResponseModel[] newArray(int size) {
            return new ProductResponseModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(categoryId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeFloat(price);
        dest.writeInt(discount);
        dest.writeInt(stock);
        dest.writeString(url);
        dest.writeString(keywords);
    }
}
