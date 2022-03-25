package project.yugioh.card_print.service.Impl;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.yugioh.card_print.dao.GridFsDao;
import project.yugioh.card_print.service.GridFsService;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Gilbert
 * @date 2022/2/24 16:11
 */
@Service
public class GridFsServiceImpl implements GridFsService {
    @Resource
    private GridFsDao gridFsDao;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 对应文件的mongoId
     * @throws IOException IO异常
     */
    @Override
    public String createFile(MultipartFile file) throws IOException {
        return gridFsDao.createFile(file);
    }

    /**
     * 获取文件
     *
     * @param mongoId 文件的mongoId
     * @return 对应的文件
     * @throws IOException IO异常
     */
    @Override
    public GridFsResource getFile(String mongoId) throws IOException {
        return gridFsDao.getFile(mongoId);
    }

    /**
     * 删除文件
     *
     * @param mongoId 文件的mongoId
     */
    @Override
    public void deleteFile(String mongoId) {
        gridFsDao.deleteFile(mongoId);
    }
}
