package com.api.framework

class Movie implements Serializable {
    String title
    String plot
    String crew
    String genere
    Date relasedate
    String language
    String imageLink
    String posterLink
    Double ratingAvg = 0
    Integer likeCount = 0
    Boolean isDeleted = false
    User postedBy
    Date dateCreated,lastUpdated
    
    static constraints = {
        plot(nullable:true)
        relasedate(nullable:true)
        posterLink(nullable:true)
        genere(nullable:true)
        ratingAvg(nullable:true)
        likeCount(nullable:true)
    }
}
