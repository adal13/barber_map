package com.map.barbershop.ui.files

class Validaciones {
    companion object {

        // Se hace la validacion de correo, para el mejor uso de la app
        fun validateEmail(correo: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            return correo.matches(emailPattern.toRegex())
        }

        // Se hace validacion de numero de telefono, para que la caja de texto solo acepte numero y 10 dijitos
        fun validatePhone(phone: String): Boolean {
            val telefonoPattern = "^[0-9]{10}$"
            return phone.matches(telefonoPattern.toRegex())
        }

        // Se hace la validacion de texto, esto para el registro de nombre y apellido, para no agregar algun dato inesesario
        fun validateText(textString: String): Boolean {
            // Expresión regular para solo letras
            val textoPattern = "^[a-zA-Z]+$"
            return textString.matches(textoPattern.toRegex())
        }

        // Validacion de Contraseña, acepta 8 caracteres como minimo, se tiene que incluir letra minúscula, letra mayúscula, número y caracteres especiales
        fun validatePassword(password: String): Boolean {
            val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$"

            return password.matches(regex.toRegex())
        }
    }

}
