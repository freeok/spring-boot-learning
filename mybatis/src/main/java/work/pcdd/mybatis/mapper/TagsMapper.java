package work.pcdd.mybatis.mapper;

import work.pcdd.mybatis.entity.Articles;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
public interface TagsMapper {

    /**
     * 查询某标签的所有文章
     */
    List<Articles> findByManyToMany(Integer tagId);

}
