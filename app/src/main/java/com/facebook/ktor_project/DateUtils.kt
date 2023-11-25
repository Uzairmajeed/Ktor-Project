import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {

    fun extractYearFromDate(dateString: String): String {
        return try {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy - hh:mm a", Locale.getDefault())
            val date = dateFormat.parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.get(Calendar.YEAR).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            "" // Return an empty string or handle the error as needed
        }
    }
}
