package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.dto.StarredInfo;
import com.turing.onebox.common.model.result.FileItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(FileInfo record);

    FileInfo selectByPrimaryKey(Integer id);

    List<FileInfo> selectAll();

    int updateByPrimaryKey(FileInfo record);


    /**
     * 获取文件列表
     * @param dir 当前文件夹
     * @return
     */
    List<FileInfo> fileList(Integer dir);

    /**
     * 获取星标文件列表
     * @return
     */
    List<StarredInfo> starredList();

    /**
     * 新建文件夹
     * @param folder
     * @return
     */
    @Insert("insert into folder values(#{id}, #{name}, #{dir}, #{secret}, 0, #{password}, 0)")
    Integer newFolder(Folder folder);


    /**
     * 标记文件为已删除
     * @param id
     * @return
     */
    @Update("update file set recycled")
    boolean deleteFile(Integer id);

    /**
     * 标记文件夹为已删除
     * @param id
     * @return
     */
    @Delete("delete from folder where id=#{id}")
    boolean deleteFolder(Integer id);

    /**
     * 在回收站中创建记录
     */
    @Insert("insert into ")


    /**
     * 获取原先文件名，与重命名相关联
     * @param id
     * @return
     */
    @Select("select `name` from file where id=#{id}")
    String getFileNameById(Integer id);

    /**
     * 文件重命名
     * 如果文件名没变，不做任何操作
     * @param id
     * @param
     * @return
     */
    @Update("update file set name=#{newName} where id=#{id}")
    boolean renameFile(Integer id, String newName);

    /**
     * 获取原先 文件夹 名，与重命名相关联
     * @param id
     * @return
     */
    @Select("select `name` from folder where id=#{id}")
    String getFolderNameById(Integer id);

    /**
     * 重命名文件夹
     * @param newName
     * @return
     */
    @Update("update folder set name=#{newName} where id=#{id}")
    boolean renameFolder(Integer id, String newName);

    /**
     * 设置星标文件
     * @param id
     * @return
     */
    @Insert("insert into starred values(#{id}, #{name}, #{dir}, #{type}, #{ext}, #{size}, #{real_Path}, 0, 1)")
    @ResultMap("starredResultMap")
    boolean starredFile(Integer id);


    /**
     * @Author HuangYuhan
     * @param fileName
     * @param dir
     * @return
     */
    int selectFileByFileNameAndDir(@Param("fileName") String fileName,@Param("dir") Integer dir);

    List<FileInfo> getFileInfoByDirId(Integer id);

    int updateByFileId(Integer id);
}