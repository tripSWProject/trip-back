package com.example.tripback.users;

import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.common.utils.S3Uploader;
import com.example.tripback.users.request.PostImgReq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Transactional(readOnly = true)
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long modifyEmail(String email, String userId){
        User user = userRepository.findByUserId(userId);

        user.setUserEmail(email);
        return user.getUserSeq();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long modifyProfilePic(PostImgReq postImgReq, String userId){
        User user = userRepository.findByUserId(userId);
        String url = saveFile(postImgReq.getImg(), postImgReq.getFileName(), user.getUserSeq());
        user.setProfileImageUrl(url);
        return user.getUserSeq();
    }

    private String saveFile(MultipartFile img, String title, Long userIdx){
        String imgName = title + "_" + img.getOriginalFilename();
        String imgPath = "images/" + userIdx;
        try {
            String uploadName = s3Uploader.upload(img, imgPath, imgName);
            return uploadName;
        } catch (IOException e) {
            throw new NotFoundException("failed to save img");
        }
    }
}
