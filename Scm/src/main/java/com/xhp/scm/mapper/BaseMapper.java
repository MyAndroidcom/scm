package com.xhp.scm.mapper;

import com.xhp.scm.entity.Page;

import java.util.List;

public interface BaseMapper<T> {

    //添加单个对象

    public int insert(T entity) throws Exception;

    //修改单个对象
    public int update(T entity) throws Exception;

    //删除单个对象
    public int delete(T entity) throws Exception;

    //通过主键（数组）批量删除数据
    public int deleteList(String [] pks);

    //查询单个对象
    public T select(T entity) throws Exception;

    //通过关键字分页查询,返回数据集
    public List<T> selectPageList(Page<T> page);

    //通过关键字分页查询，返回记录数
    public Integer selectPageCount(Page<T> page);

    //通过关键字分页查询,多条件查询
    public List<T> selectPageListUseDyc(Page<T> page);

    //通过关键字分页查询，返回总记录数
    public Integer selectPageCountUseDyc(Page<T> page);
}
