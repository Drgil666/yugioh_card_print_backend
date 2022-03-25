package project.yugioh.card_print.service;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Gilbert
 * @date 2022/2/24 16:04
 */
public interface GridFsService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 对应文件的mongoId
     * @throws IOException IO异常
     */
    String createFile(MultipartFile file) throws IOException;

    /**
     * 获取文件
     *
     * @param mongoId 文件的mongoId
     * @return 对应的文件
     * @throws IOException IO异常
     */
    GridFsResource getFile(String mongoId) throws IOException;

    /**
     * 删除文件
     *
     * @param mongoId 文件的mongoId
     */
    void deleteFile(String mongoId);
}
