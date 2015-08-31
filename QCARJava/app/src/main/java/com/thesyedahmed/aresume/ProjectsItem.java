package com.thesyedahmed.aresume;

/**
 * Created by steakpizza on 8/29/2015.
 */
class ProjectsItem {
    String mName;
    String mDesc;
    int mPhotoID;
    String mLink;
    int mID;

    ProjectsItem(String name, String desc, int photoID, String link, int id){
        this.mName = name;
        this.mDesc = desc;
        this.mPhotoID = photoID;
        this.mLink = link;
        this.mID = id;
    }
}


