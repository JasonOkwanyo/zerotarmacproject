package com.example.zerotarmacc.model

class SubmitApplication {
    var firstname:String=""
    var surname:String=""
    var location:String=""
    var jtitle:String=""
    var imageUrl:String=""
    var id:String=""

    constructor(firstname:String,surname:String,location:String,jtitle:String,imageUrl:String,id:String){
        this.firstname=firstname
        this.surname=surname
        this.location=location
        this.jtitle=jtitle
        this.imageUrl=imageUrl
        this.id=id

    }
    constructor()
}