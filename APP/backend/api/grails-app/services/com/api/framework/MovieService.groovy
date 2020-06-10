package com.api.framework

import grails.transaction.Transactional

@Transactional
class MovieService {

    def obtainMovieList(userId) {
        def movieList = Movie.createCriteria().list(){
            if(userId){
                eq("postedBy.id", userId)
            }
            eq("isDeleted", false)
        }
        def resultMap = [:]
        def resultList = []
        movieList.each{movieIns->
            resultMap = [:]
            resultMap["id"] = movieIns?.id
            resultMap["title"] = movieIns?.title
            resultMap["plot"] = movieIns?.plot
            resultMap["crew"] = movieIns?.crew
            resultMap["genere"] = movieIns?.genere
            resultMap["relasedate"] = movieIns?.relasedate?.format("dd-MMM-yyyy")
            resultMap["imageLink"] = movieIns?.imageLink
            resultMap["ratingAvg"] = movieIns?.ratingAvg
            resultMap["likeCount"] = movieIns?.likeCount
            resultList << resultMap
        }
        return resultList
    }
}
