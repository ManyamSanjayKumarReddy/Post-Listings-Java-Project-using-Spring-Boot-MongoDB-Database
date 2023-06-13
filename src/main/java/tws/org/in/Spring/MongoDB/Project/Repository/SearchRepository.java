package tws.org.in.Spring.MongoDB.Project.Repository;

import tws.org.in.Spring.MongoDB.Project.Model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);
}
