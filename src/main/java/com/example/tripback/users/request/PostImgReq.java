package com.example.tripback.users.request;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class PostImgReq {
    private MultipartFile img;
    private String fileName;
}
