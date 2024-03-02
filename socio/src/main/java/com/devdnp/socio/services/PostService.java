package com.devdnp.socio.services;

import java.util.List;

import com.devdnp.socio.models.Post;

public interface PostService {

	Post createdNewPost(Post post, Integer userId) throws Exception;
	
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostByPostId(Integer postId) throws Exception;
	
	List<Post> findAllPosts();
	
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likePost(Integer postId, Integer userId) throws Exception;
	
}
