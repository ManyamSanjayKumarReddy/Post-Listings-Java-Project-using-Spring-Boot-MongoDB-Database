package tws.org.in.Spring.MongoDB.Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tws.org.in.Spring.MongoDB.Project.Model.Post;
import tws.org.in.Spring.MongoDB.Project.Repository.PostRepository;
import tws.org.in.Spring.MongoDB.Project.Repository.SearchRepository;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository searchrepo;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return repo.findAll();
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){

        return searchrepo.findByText(text);
    }

}
