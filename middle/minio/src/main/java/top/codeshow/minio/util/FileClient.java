package top.codeshow.minio.util;


import io.minio.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.codeshow.common.exception.ServiceException;
import top.codeshow.common.util.MyStrUtils;

import java.io.InputStream;

@Slf4j
@Component
public class FileClient {
    @Value("${minio.upload.bucket}")
    private String bucket;
    @Resource
    private MinioClient minioClient;

    public String upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ServiceException("上传的文件不能为空");
        }
        log.info("文件上传了{}", file.getName());
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
            InputStream inputStream = file.getInputStream();
            String fileName = MyStrUtils.getUuid();
            String originalName = MyStrUtils.removeBlank(file.getOriginalFilename());
            if (originalName != null && originalName.contains(".")) {
                fileName = fileName.concat(originalName.substring(originalName.lastIndexOf(".")));
            }
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(inputStream, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
            log.info("文件上传返回结果为{}", objectWriteResponse);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("文件上传失败");
        }
    }

    public InputStream download(String fileUrl) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileUrl)
                    .build();
            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("调用minio客户端时失败");
        }
    }
}
