package work.pcdd.mybatis.mapper;

import work.pcdd.mybatis.entity.Authors;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
public interface AuthorsMapper {

    List<Authors> findByInnerJoin(Integer authorId);

    List<Authors> findByLeftJoin(Integer authorId);

}
