package work.pcdd.mybatis.mapper;

import work.pcdd.mybatis.entity.Author;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
public interface AuthorMapper {

    List<Author> findByInnerJoin(Integer authorId);

    List<Author> findByLeftJoin(Integer authorId);

}
