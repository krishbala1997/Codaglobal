package com.api.framework

import grails.converters.JSON

class UserController {
    def userService
    def login() { 
        response.setHeader("Access-Control-Allow-Origin", "*")
        def userName = request.JSON.userName
        def password = request.JSON.password
        def userIns = User.findByUserNameAndPssword(userName,password)
        def resultMap = [:]   
        println "userIns---->${request.JSON}---${password}--->${request.JSON}"
        if(userIns){
            resultMap["status"] = true
            resultMap["userId"] = userIns?.id
            resultMap["role"] = userIns?.role?.name
        }   
        else{
             resultMap["status"] = false
        }
        render resultMap as JSON
    }
    
}
