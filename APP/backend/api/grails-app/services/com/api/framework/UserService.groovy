package com.api.framework

import grails.transaction.Transactional

@Transactional
class UserService {

    def obtainStudentList(teacherId) {
        def studentList = TeacherStudent.createCriteria().list(){
            eq("teacher.id",teacherId)
            student{
                eq("isDeleted", false)
            }           
            projections{
                property("student")
            }
        }
        return studentList
    }
    def obtainStudentMarkMap(studentId){
        def markList = StudentSubjectMark.createCriteria().list(){
            eq("student.id", studentId)
        }
        println "markList--->${markList}"
        def resultList = []
        markList?.each{markIns->
            def dataMap = [:]
            dataMap["id"] = markIns?.id
            dataMap["sname"] = markIns?.subject?.name
            dataMap["mark"] = markIns?.mark
            resultList << dataMap
        }
         println "resultList---->${resultList}"
        return resultList
    }
}
