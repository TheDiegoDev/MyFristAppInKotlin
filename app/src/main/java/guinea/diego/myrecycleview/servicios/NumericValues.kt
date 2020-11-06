package guinea.diego.myrecycleview.servicios

import guinea.diego.myrecycleview.modelo.InfoRM
import java.lang.Double

class NumericValues {

    fun getNumericValues(cadena: String): String {
        val sb = StringBuilder()
        for (i in cadena.indices) {
            var numeric = true
            try {
                val num = Double.parseDouble(cadena[i].toString())
            } catch (e: NumberFormatException) {
                numeric = false
            }
            if (numeric) {
                sb.append(cadena[i].toString())
            }
        }
        return sb.toString();
    }
}