package tws.org.in.Spring.MongoDB.Project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tws.org.in.Spring.MongoDB.Project.Model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
}
