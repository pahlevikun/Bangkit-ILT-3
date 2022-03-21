package id.pahlevikun.ilt3.sample

import org.json.JSONArray
import org.json.JSONObject


fun JSONObject.getStringOrEmpty(memberName: String): String {
    return if (has(memberName)) getString(memberName).orEmpty() else ""
}

fun JSONObject.getIntOrZero(memberName: String): Int {
    return if (has(memberName)) getInt(memberName) else 0
}

fun JSONObject.getJSONObjectOrEmpty(memberName: String): JSONObject {
    return if (has(memberName)) getJSONObject(memberName) ?: JSONObject() else JSONObject()
}

fun JSONObject.getJsonArrayOrEmpty(memberName: String): JSONArray {
    return if (has(memberName)) getJSONArray(memberName) ?: JSONArray() else JSONArray()
}