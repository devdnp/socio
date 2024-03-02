package com.devdnp.socio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devdnp.socio.models.Post;
import com.devdnp.socio.response.ApiResponse;
import com.devdnp.socio.services.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception{
		
		Post createdPost = postService.createdNewPost(post, userId);
		
		
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		String message = postService.deletePost(postId, userId);
		
		ApiResponse res = new ApiResponse(message, true);
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByPostId(@PathVariable Integer postId) throws Exception{
		
		Post post = postService.findPostByPostId(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userId){
		
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPosts(){
		
		List<Post> posts = postService.findAllPosts();
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/posts/save/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		Post post = postService.savedPost(postId, userId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likedPost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		
		Post post = postService.likePost(postId, userId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
}
