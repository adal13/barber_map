package com.map.barbershop.ui.files

class Validaciones {
    companion object {
        fun validateEmail(correo: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            return correo.matches(emailPattern.toRegex())
        }

        fun validatePhone(phone: String): Boolean {
            val telefonoPattern = "^[0-9]{10}$"
            return phone.matches(telefonoPattern.toRegex())
        }

        fun validateText(textString: String): Boolean {
            // Expresión regular para solo letras
            val textoPattern = "^[a-zA-Z]+$"
            return textString.matches(textoPattern.toRegex())
        }
        fun validatePassword(password: String): Boolean {
            // Al menos 8 caracteres, una letra minúscula, una letra mayúscula, un número y un carácter especial
            val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$"

            return password.matches(regex.toRegex())
        }
    }

}
