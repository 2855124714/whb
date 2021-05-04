package com.fjsy.architecture.global.data.bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PaperDetailDao {
    @Query("SELECT * FROM paperDetailBean")
    List<PaperDetailBean> getAll();

    @Query("SELECT * FROM paperDetailBean WHERE id IN (:ids)")
    List<PaperDetailBean> loadAllByIds(String[] ids);

    @Query("SELECT * FROM paperDetailBean WHERE id IN (:ids)")
    List<PaperDetailBean> loadAllByIds(List<String> ids);


//    @Query("delete from paperDetailBean WHERE (select count(id) from paperDetailBean)>500 and id in (SELECT * FROM paperDetailBean WHERE id LIKE :id AND viewUid LIKE :viewUid limit (SELECT count(id) FROM paperDetailBean) offset 2)")
    @Query("SELECT * FROM paperDetailBean WHERE id LIKE :id AND viewUid LIKE :viewUid ")
    PaperDetailBean findById(String id,String viewUid);

    @Insert
    void insertAll(PaperDetailBean... users);

    @Delete
    void delete(PaperDetailBean user);

    @Update
    void update(PaperDetailBean user);

}
