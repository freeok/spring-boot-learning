package work.pcdd.mybatis.mapper;

import work.pcdd.mybatis.entity.Article;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
public interface TagMapper {

    /**
     * 查询某标签的所有文章
     */
    List<Article> findByManyToMany(Integer tagId);

}
