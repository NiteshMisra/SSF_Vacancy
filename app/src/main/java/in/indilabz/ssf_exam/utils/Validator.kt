package `in`.indilabz.ssf_exam.utils

import `in`.indilabz.ssf_exam.utils.Toaster
import android.util.Patterns
import android.widget.EditText

object Validator {

    fun validateName(value: String): Boolean {

        if (value.length > 2) {

            return true
        } else {

            Toaster.longt("Invalid name")

            return false
        }
    }

    fun validatePhone(value: String): Boolean {

        if (value.length >= 10) {

            return true
        } else {

            Toaster.longt("Invalid phone number")

            return false
        }
    }

    fun validateEmail(value: String): Boolean {

        if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {

            return true
        } else {

            Toaster.longt("Invalid email")

            return false
        }
    }

    fun validateAddress(value: String): Boolean {

        if (value.length >= 1) {

            return true
        } else {

            Toaster.longt("Invalid address")

            return false
        }
    }

    fun validatePassword(value: String): Boolean {

        if (value.length in 6..14) {

            return true
        } else {

            Toaster.longt("Invalid password number")

            return false
        }
    }

    fun validateName(value: EditText): Boolean {

        if (value.text.toString().length > 2) {

            return true
        } else {

            Toaster.longt("Invalid name")

            return false
        }
    }

    fun validatePhone(value: EditText): Boolean {

        if (value.text.toString().length >= 10) {

            return true
        } else {

            Toaster.longt("Invalid phone number")

            return false
        }
    }

    fun validateEmail(value: EditText): Boolean {

        if (Patterns.EMAIL_ADDRESS.matcher(value.text.toString()).matches()) {

            return true
        } else {

            Toaster.longt("Invalid email")

            return false
        }
    }

    fun validateAddress(value: EditText): Boolean {

        if (value.text.toString().length >= 1) {

            return true
        } else {

            Toaster.longt("Invalid address")

            return false
        }
    }

    fun validatePassword(value: EditText): Boolean {

        if (value.text.toString().length in 6..14) {

            return true
        } else {

            Toaster.longt("Invalid password number")

            return false
        }
    }

    fun validateLength(value: EditText, len: Int): Boolean {

        if (value.text.toString().length == len) {

            return true
        } else {

            Toaster.longt("Invalid dob")

            return false
        }
    }
}
