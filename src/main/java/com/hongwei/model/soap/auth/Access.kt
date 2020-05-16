package com.hongwei.model.soap.auth

enum class Access(val value: String) {
    Dashboard("dashboard"),
    Blog("blog"),
    Gallery("gallery"),
    Simmer("simmer"),
    Resume("resume"),
    Project("project"),
    BlogOwner("blog_owner"),
    GalleryOwner("gallery_owner"),
}