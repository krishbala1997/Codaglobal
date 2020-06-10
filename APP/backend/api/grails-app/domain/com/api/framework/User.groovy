package com.api.framework

class User implements Serializable{
    String name
    String userName
    String pssword
    String email
    Role role
    Boolean isDeleted = false
    Date dateCreated,lastUpdated
    static constraints = {
        name(nullable: false,blank: false)
        userName(nullable: false,blank: false)
        pssword(nullable: false,blank: false)
        email(nullable: true)
    }
}
