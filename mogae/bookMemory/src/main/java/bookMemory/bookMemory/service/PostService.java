package bookMemory.bookMemory.service;

import bookMemory.bookMemory.controller.dto.request.AddPostRequest;
import bookMemory.bookMemory.controller.dto.request.UpdatePostRequest;
import bookMemory.bookMemory.model.Post;

public interface PostService {
    public Post createPost(AddPostRequest request);

    public Post updatePost(UpdatePostRequest request);

    public void deletePost(Long postId);

    public Post getPost(Long postId);
}
