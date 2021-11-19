package com.example.mymoviememoir.bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieBeanDao {

    @Query("SELECT * FROM subjectsbean WHERE id =:searchId ")
    MovieBean.SubjectsBean findById(String searchId);

    @Query("SELECT * FROM subjectsbean WHERE isGallery == 1")
    List<MovieBean.SubjectsBean> findGallery();

    @Query("SELECT * FROM subjectsbean WHERE isWatch == 1")
    List<MovieBean.SubjectsBean> findWatch();

    @Insert
    void insertAll(MovieBean.SubjectsBean... subjectsBeans);

    @Insert
    long insert(MovieBean.SubjectsBean subjectsBean);

    @Delete
    void delete(MovieBean.SubjectsBean customer);

    @Update(onConflict = REPLACE)
    void updateCustomers(MovieBean.SubjectsBean... customers);

    @Update(onConflict = REPLACE)
    void updateCustomer(MovieBean.SubjectsBean customers);

    @Query("DELETE FROM subjectsbean")
    void deleteAll();

}
