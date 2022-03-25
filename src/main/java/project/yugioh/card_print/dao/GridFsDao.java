package project.yugioh.card_print.dao;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:13
 */
public interface GridFsDao {
    /**
     * 上传文件
     *
     * @param file 要上传的文件
     * @return 文件id
     * @throws IOException IO异常
     */
    String createFile(MultipartFile file) throws IOException;

    /**
     * 获取文件
     *
     * @param mongoId 要获取的文件名
     * @return 对应的文件
     * @throws IOException IO异常
     */
    GridFsResource getFile(String mongoId) throws IOException;

    /**
     * 批量删除文件
     *
     * @param mongoId 文件id列表
     */
    void deleteFile(String mongoId);
}
