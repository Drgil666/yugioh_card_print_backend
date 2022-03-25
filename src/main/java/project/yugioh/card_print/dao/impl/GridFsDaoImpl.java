package project.yugioh.card_print.dao.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import project.yugioh.card_print.dao.GridFsDao;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:19
 */
@Component("GridFsDaoImpl")
public class GridFsDaoImpl implements GridFsDao {
    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * 上传文件
     *
     * @param file 要上传的文件
     * @return 文件id
     */
    @Override
    public String createFile(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ObjectId objectId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());
        return objectId.toHexString();
    }

    /**
     * 获取文件
     *
     * @param mongoId 要获取的文件名
     * @return 对应的文件
     */
    @Override
    public GridFsResource getFile(String mongoId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoId));
        GridFSFile gridFsFile = gridFsTemplate.findOne(query);
        if (gridFsFile != null) {
            return gridFsTemplate.getResource(gridFsFile);
        }
        return null;
    }

    /**
     * 批量删除文件
     *
     * @param mongoId 文件id列表
     */
    @Override
    public void deleteFile(String mongoId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoId));
        gridFsTemplate.delete(query);
    }
}
