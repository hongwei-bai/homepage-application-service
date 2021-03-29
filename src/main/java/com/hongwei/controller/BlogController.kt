package com.hongwei.controller

import com.hongwei.constants.NotFound
import com.hongwei.model.jpa.BlogEntry
import com.hongwei.model.jpa.BlogEntryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = ["*"])
class BlogController {
    @Autowired
    private lateinit var blogEntryRepository: BlogEntryRepository

    @GetMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun getBlogEntry(@PathVariable("id") id: Long, owner: String, sign: String?): ResponseEntity<*> =
            ResponseEntity.ok(blogEntryRepository.findById(id).get())

    @GetMapping(path = ["/entry.do"])
    @ResponseBody
    fun getAllBlogEntries(owner: String): ResponseEntity<*> =
            ResponseEntity.ok(blogEntryRepository.findByOwner(owner))

    @PostMapping(path = ["/entry.do"])
    @ResponseBody
    fun addBlogEntry(owner: String, title: String, description: String?, content: String, delta: String,
                     categories: String?, tags: String?): ResponseEntity<*> =
            ResponseEntity.ok(blogEntryRepository.save(BlogEntry().apply {
                this.owner = owner
                this.title = title
                this.content = content
                this.delta = delta
                this.description = description
                this.categories = categories
                this.tags = tags
                this.accessLevel = "public"
                this.createDate = System.currentTimeMillis()
                this.modifyDate = System.currentTimeMillis()
            }))

    @PutMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun updateBlogEntry(@PathVariable("id") id: Long, owner: String, title: String?, description: String?, content: String?,
                        delta: String, categories: String?, tags: String?): ResponseEntity<*> {
        if (!blogEntryRepository.findById(id).isPresent) {
            throw NotFound
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
        return ResponseEntity.ok(entry)
    }

    @DeleteMapping(path = ["/{id}/entry.do"])
    @ResponseBody
    fun deleteBlogEntry(@PathVariable("id") id: Long, owner: String): ResponseEntity<*> {
        if (!blogEntryRepository.findById(id).isPresent) {
            throw NotFound
        }

        blogEntryRepository.deleteById(id)
        return ResponseEntity.ok(null)
    }

}