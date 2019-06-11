//package com.bridgeit.fundoonotes.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.bridgeit.fundoonotes.service.Amazons3;
//
//@RestController
//@RequestMapping("/storage")
//public class S3controller {
//	
//	private Amazons3 amazon;
//	@Autowired
//	public S3controller(Amazons3 amazon) {
//		
//		this.amazon = amazon;
//	}
//
//	@PostMapping("/uploadfile")
//	public String uploadFile(@RequestPart (value="file") MultipartFile file)
//	{
//		return this.amazon.uploadFile(file); 
//	}
//
//	@DeleteMapping("/deletefile")
//	public String deletefile(@RequestPart(value="url") String fileurl)
//	{
//		return this.amazon.deleteFileFromS3Bucket(fileurl);
//	}
//}
