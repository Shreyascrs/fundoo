//package com.bridgeit.fundoonotes.service;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Date;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//
//@Service
//public class Amazons3 {
//
//	private AmazonS3 s3client;
//
////	@Value("${amazonProperties.accessKey}")
////	private String accessKey;
////	@Value("${amazonProperties.secretKey}")
////	private String secretKey;
////	@Value("${amazonProperties.endpointurl}")
////	private String endPointUrl;
////	@Value("${amazonProperties.bucketName}")
////	private String bucketName;
////
////	@SuppressWarnings("deprecation")
////	@PostConstruct
////	public void initializeAmazon() {
////		AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
////		this.s3client = new AmazonS3Client(awsCredentials);
////	}
//
//	private File convertMultiPartToFile(MultipartFile file) throws IOException {
//		File convFile = new File(file.getOriginalFilename());
//		FileOutputStream fileOutputStream = new FileOutputStream(convFile);
//		fileOutputStream.write(file.getBytes());
//		fileOutputStream.close();
//		return convFile;
//	}
//
//	public String generateFileName(MultipartFile multipartFile) {
//		return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ", "_");
//	}
//
//	private void uploadFileTOs3Bucket(String filename, File file) {
//		s3client.putObject(
//				new PutObjectRequest(bucketName, filename, file).withCannedAcl(CannedAccessControlList.PublicRead));
//	}
//
//	public String uploadFile(MultipartFile multipartFile) {
//		String fileurl = "";
//		try {
//			File file = convertMultiPartToFile(multipartFile);
//			String filename = generateFileName(multipartFile);
//			fileurl = endPointUrl + "/" + bucketName + "/" + filename;
//			uploadFileTOs3Bucket(filename, file);
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		return fileurl;
//
//	}
//
//	public String deleteFileFromS3Bucket(String fileUrl) {
//		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
//		return "Successfully deleted";
//	}
//
//}
