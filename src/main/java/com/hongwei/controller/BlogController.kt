package com.hongwei.controller

import com.google.gson.Gson
import com.hongwei.model.jpa.BlogEntry
import com.hongwei.model.jpa.BlogEntryRepository
import com.hongwei.model.soap.auth.AccessLevel
import com.hongwei.model.soap.common.Response
import com.hongwei.model.soap.common.SoapConstant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog")
@CrossOrigin
class BlogController {
    @Autowired
    private lateinit var blogEntryRepository: BlogEntryRepository

    @GetMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun getBlogEntry(@PathVariable("id") id: Long, owner: String, sign: String?): String {
        return Gson().toJson(Response.from(SoapConstant.CODE_SUCCESS, "get blog entry success", blogEntryRepository.findById(id).get()))
    }

    @GetMapping(path = ["/entry.do"])
    @ResponseBody
    fun getAllBlogEntries(owner: String, sign: String?): String {
        return Gson().toJson(Response.from(SoapConstant.CODE_SUCCESS, "get blog entry success", blogEntryRepository.findByOwner(owner)))
    }

    @PostMapping(path = ["/entry.do"])
    @ResponseBody
    fun addBlogEntry(owner: String, title: String, description: String?, content: String, delta: String,
                     categories: String?, tags: String?, sign: String?): String {

        val entry = blogEntryRepository.save(BlogEntry().apply {
            this.owner = owner
            this.title = title
            this.content = content
            this.delta = delta
            this.description = description
            this.categories = categories
            this.tags = tags
            this.accessLevel = AccessLevel.Public.value
            this.createDate = System.currentTimeMillis()
            this.modifyDate = System.currentTimeMillis()
        })
        return Gson().toJson(Response.from(SoapConstant.CODE_SUCCESS, "add blog entry success", entry))
    }

    @PutMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun updateBlogEntry(@PathVariable("id") id: Long, owner: String, title: String?, description: String?, content: String?,
                        delta: String, categories: String?, tags: String?, sign: String?): String {
        if (!blogEntryRepository.findById(id).isPresent) {
            return Gson().toJson(Response.from(SoapConstant.CODE_ERROR, "blog entry not exist"))
        }

        val entry = blogEntryRepository.findById(id).get().apply {
            title?.let { this.title = title }
            description?.let { this.description = description }
            content?.let { this.content = content }
            delta?.let { this.delta = delta }
            categories?.let { this.categories = categories }
            tags?.let { this.tags = tags }
        }
        blogEntryRepository.save(entry)
        return Gson().toJson(Response.from(SoapConstant.CODE_SUCCESS, "update blog entry success", entry))
    }

    @DeleteMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun deleteBlogEntry(@PathVariable("id") id: Long, owner: String, sign: String?): String {
        if (!blogEntryRepository.findById(id).isPresent) {
            return Gson().toJson(Response.from(SoapConstant.CODE_ERROR, "blog entry not exist"))
        }

        blogEntryRepository.deleteById(id)
        return Gson().toJson(Response.from(SoapConstant.CODE_SUCCESS, "delete blog entry success"))
    }

}