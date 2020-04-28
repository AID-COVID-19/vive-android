package care.vive.android.util

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("latitude", "longitude", "placeName", "placeId", "id")
class Place : Parcelable {
    var latitude: Double?
    var longitude: Double?
    var placeName: String?
    var placeId: String?
    var id: String?

    constructor(
        latitude: Double?,
        longitude: Double?,
        placeName: String?,
        placeId: String?,
        id: String?
    ) {
        this.latitude = latitude
        this.longitude = longitude
        this.placeName = placeName
        this.placeId = placeId
        this.id = id
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(latitude)
        dest.writeValue(longitude)
        dest.writeString(placeName)
        dest.writeString(placeId)
        dest.writeString(id)
    }

    protected constructor(`in`: Parcel) {
        latitude = `in`.readValue(Double::class.java.classLoader) as Double?
        longitude =
            `in`.readValue(Double::class.java.classLoader) as Double?
        placeName = `in`.readString()
        placeId = `in`.readString()
        id = `in`.readString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<Place?> = object : Parcelable.Creator<Place?> {
            override fun createFromParcel(source: Parcel): Place? {
                return Place(source)
            }

            override fun newArray(size: Int): Array<Place?> {
                return arrayOfNulls(size)
            }
        }
    }
}