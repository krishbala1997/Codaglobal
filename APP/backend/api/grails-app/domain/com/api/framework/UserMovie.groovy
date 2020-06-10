package com.api.framework

class UserMovie implements Serializable {
    Integer rating = 0
    Boolean isLiked = false
    String comment
    Date dateCreated,lastUpdated
    static belongsTo = [user: User,movie: Movie]
    static constraints = {
        rating(nullable:true)
        comment(nullable:true)
    }
}
