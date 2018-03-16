package hit.scarlet.sun.daily.HttpAround

import android.net.Uri
import android.util.Log
import hit.scarlet.sun.daily.bean.Question
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Project HealthFlowers.
 * Created by 旭 on 2017/5/15.
 */

object JsonParse {
    lateinit var responseDate: String
    private val TAG = "JsonParse"

    fun QuestionListParse(response: String): MutableList<Question> {

        val parseResult = ArrayList<Question>()

        try {
            val result = JSONObject(response)
            responseDate = result.getString("date")
            val stories = result.getJSONArray("stories")

            for (i in 0 until stories.length()) {
                val question = Question()
                question.id = stories.getJSONObject(i).getInt("id")
                question.title = stories.getJSONObject(i).getString("title")
                question.uri = stories.getJSONObject(i).getJSONArray("images").get(0).toString()
                parseResult.add(question)
            }

        } catch (e: JSONException) {
            Log.d(TAG, "QuestionListParse: " + e)
        }

        return parseResult
    }

    fun  ThemeListParse(response: String): MutableList<Question> {
        val parseResult = ArrayList<Question>()

        try {
            val result = JSONObject(response)
            val stories = result.getJSONArray("stories")

            for (i in 0 until stories.length()) {
                val question = Question()
                question.id = stories.getJSONObject(i).getInt("id")
                question.title = stories.getJSONObject(i).getString("title")
                parseResult.add(question)
            }

        } catch (e: JSONException) {
            Log.d(TAG, "QuestionListParse: " + e)
        }

        return parseResult
    }
}
