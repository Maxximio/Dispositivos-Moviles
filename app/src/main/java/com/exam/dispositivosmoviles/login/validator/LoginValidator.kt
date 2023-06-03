package com.exam.dispositivosmoviles.login.validator

import com.exam.dispositivosmoviles.data.entities.LoginUser

class LoginValidator {

    fun checkLogin(name: String, password: String) : Boolean{
//        var ret = false
        val admin = LoginUser()
         return (admin.name == name && admin.pass == password)
//        return ret
    }
}