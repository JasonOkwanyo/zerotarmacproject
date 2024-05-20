package com.example.zerotarmacc.model

class SubmitJob {
    var compname:String=""
    var jobtitle:String=""
    var salary:String=""
    var city:String=""
    var industry:String=""
    var jobtype:String=""
    var jobreq:String=""
    var idealcand:String=""
    var id:String=""

    constructor(compname:String,jobtitle:String,salary:String,city:String,industry:String,jobtype:String,jobreq:String,idealcand:String,id:String){
        this.compname=compname
        this.jobtitle=jobtitle
        this.salary=salary
        this.city=city
        this.industry=industry
        this.jobtype=jobtype
        this.jobreq=jobreq
        this.idealcand=idealcand
        this.id=id

    }
    constructor()
}