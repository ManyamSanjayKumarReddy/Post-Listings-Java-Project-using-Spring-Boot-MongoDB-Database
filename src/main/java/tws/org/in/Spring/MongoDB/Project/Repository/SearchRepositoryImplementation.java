package tws.org.in.Spring.MongoDB.Project.Repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import tws.org.in.Spring.MongoDB.Project.Model.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepositoryImplementation implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Job_Listing");
        MongoCollection<Document> collection = database.getCollection("Job_Post");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                        new Document("$search", new Document("index", "default")
                                .append("text", new Document("query", text)
                                        .append("path", Arrays.asList("desc", "profile", "techs")))),
                        new Document("$sort", new Document("exp", 1L)),
                        new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
        return posts;
    }
}
