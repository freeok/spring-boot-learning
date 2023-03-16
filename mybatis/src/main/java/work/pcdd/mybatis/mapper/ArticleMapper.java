package work.pcdd.mybatis.mapper;

import org.springframework.stereotype.Repository;
import work.pcdd.mybatis.entity.Article;

import java.util.List;

/**
 * @author pcdd
 * @date 2021-05-13
 */
@Repository
public interface ArticleMapper {
    List<Article> list();
}
