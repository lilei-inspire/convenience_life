package com.ls.dao;

import com.ls.pojo.circle.Comment;
import com.ls.pojo.circle.Dynamic;
import com.ls.pojo.circle.Like;

import java.util.List;

public interface CircleDao {
    /**
     * 动态列表
     */
    List<Dynamic> getDynamicList();

    List<Dynamic> getDynamicListByUserID(Integer id);
    /**
     * 评论列表
     */
    List<Comment> getComments(Integer id);

    /**
     * 点赞列表
     */
    List<Like> getLikes(Integer id);

    /**
     * 新增动态
     */
    void insertDynamic(Dynamic dynamic);

    /**
     * 新增评论
     */
    void insertComment(Comment comment);

    /**
     * 新增点赞
     */
    void insertLike(Like like);

    /**
     * 删除动态
     */
    void deleteDynamic(Integer id);

    void deleteLike(Integer id);

    void deleteComment(Integer id);

    /**
     * 用户注销动态
     */
    void deleteAllDynamic(Integer id);

    void deleteAllLike(int[] dyanamics);

    void deleteAllComment(int[] dyanamics);

}
