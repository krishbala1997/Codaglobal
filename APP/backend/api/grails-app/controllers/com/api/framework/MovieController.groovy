package com.api.framework
import grails.converters.JSON
class MovieController {
    def movieService
    def movieList() {
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            def userId
            if(request.JSON.id){
                userId = Long.parseLong(request.JSON.id)
            }
            
            def movieList = movieService.obtainMovieList(userId)
            render movieList as JSON
        }
        else{
            render true
        }
    }
    def viewMovie(){
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            def movieIns = Movie.get(Long.parseLong(request.JSON.id))
            def resultMap = [:]
            if(movieIns){
                resultMap["title"] = movieIns?.title
                resultMap["plot"] = movieIns?.plot
                resultMap["crew"] = movieIns?.crew
                resultMap["genere"] = movieIns?.genere
                resultMap["relasedate"] = movieIns?.relasedate?.format("yyyy-MM-dd")
                resultMap["imageLink"] = movieIns?.imageLink
                resultMap["ratingAvg"] = movieIns?.ratingAvg
                resultMap["likeCount"] = movieIns?.likeCount
                resultMap["language"] = movieIns?.language
            }
            render resultMap as JSON
        }
        else{
            render true
        }
    }
    def updateMovie(){
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            def movieIns = Movie.get(Long.parseLong(request.JSON.id))
            movieIns?.title = request.JSON.title
            movieIns?.plot = request.JSON.plot
            movieIns?.crew = request.JSON.crew
            movieIns?.genere = request.JSON.genere
            movieIns?.relasedate = Date.parse("yyyy-MM-dd", request.JSON.relasedate)
            movieIns?.language = request.JSON.language
            movieIns?.imageLink = request.JSON.imageLink
            movieIns?.posterLink = request.JSON.posterLink
            println "movieIns---->${movieIns.validate()}---${movieIns?.errors}"
            if(movieIns?.validate()){
                movieIns?.save(flush: true)
                render true
            }else{
                render false
            }
            
            
        }
        else{
            render true
        }
    }
    def createMovie(){
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            def movieIns = new Movie()
            movieIns?.title = request.JSON.title
            movieIns?.plot = request.JSON.plot
            movieIns?.crew = request.JSON.crew
            movieIns?.genere = request.JSON.genere
            movieIns?.relasedate = Date.parse("yyyy-MM-dd", request.JSON.relasedate)
            movieIns?.language = request.JSON.language
            movieIns?.imageLink = request.JSON.imageLink
            movieIns?.posterLink = request.JSON.posterLink
            movieIns?.postedBy = User.get(Long.parseLong(request.JSON.id))
            
            println "movieIns?.validate()--->${movieIns?.validate()}---${movieIns?.errors}"
            if(movieIns?.validate()){
                movieIns?.save(flush: true)
                render true
            }else{
                render false
            }
            
            
        }
        else{
            render true
        }
    }
    def deleteMovie(){
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            def movieIns = Movie.get(Long.parseLong(request.JSON.id))
            movieIns?.isDeleted = true
            if(movieIns?.validate()){
                movieIns?.save(flush: true)
                render false
            }
            else{
                render true
            }
        }
        else{
            render true
        }
    }
    def updateRating(){
        response.setHeader("Access-Control-Allow-Origin", "*")
        if(request.JSON){
            println "json---->${request.JSON}"
            def movieIns = Movie.get(Long.parseLong(request.JSON.id))
            def userIns = User.get(Long.parseLong(request.JSON.userId))
            def userMovie = UserMovie.createCriteria().get(){
                eq("user.id", userIns?.id)
                eq("movie.id", movieIns?.id)
                maxResults(1)
            }
            if(!userMovie){
                userMovie = new UserMovie(user:userIns,movie: movieIns)
            }
            
            userMovie?.rating = request.JSON.rating
            println "userMovie---->${userMovie?.validate()}--->${userMovie?.errors}"
            if(userMovie?.validate()){
                userMovie?.save(flush: true)
                render false
            }
            else{
                render true
            }
        }
        else{
            render true
        }
    }
}
