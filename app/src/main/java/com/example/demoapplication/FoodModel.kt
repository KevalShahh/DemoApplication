package com.example.demoapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FoodModel {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("chef")
    @Expose
    var chef: String? = null

    @SerializedName("thumbnail")
    @Expose
    private var thumbnail: String? = null

    @SerializedName("timestamp")
    @Expose
    private var timestamp: String? = null

    @JvmName("getId1")
    fun getId(): Int? {
        return id
    }

    @JvmName("setId1")
    fun setId(id: Int?) {
        this.id = id
    }

    @JvmName("getName1")
    fun getName(): String? {
        return name
    }

    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name
    }

    @JvmName("getDescription1")
    fun getDescription(): String? {
        return description
    }

    @JvmName("setDescription1")
    fun setDescription(description: String?) {
        this.description = description
    }

    @JvmName("getPrice1")
    fun getPrice(): Int? {
        return price
    }

    @JvmName("setPrice1")
    fun setPrice(price: Int?) {
        this.price = price
    }

    @JvmName("getChef1")
    fun getChef(): String? {
        return chef
    }

    @JvmName("setChef1")
    fun setChef(chef: String?) {
        this.chef = chef
    }

    fun getThumbnail(): String? {
        return thumbnail
    }

    fun setThumbnail(thumbnail: String?) {
        this.thumbnail = thumbnail
    }

    fun getTimestamp(): String? {
        return timestamp
    }

    fun setTimestamp(timestamp: String?) {
        this.timestamp = timestamp
    }
}
