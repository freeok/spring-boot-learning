package work.pcdd.mybatis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.mybatis.entity.Articles;
import work.pcdd.mybatis.mapper.ArticlesMapper;
import work.pcdd.mybatis.mapper.TagsMapper;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    final ArticlesMapper articlesMapper;
    final TagsMapper tagsMapper;

    /**
     * 多对一查询示例
     * 传入 id 表示单查，否则表示全查
     */
    @GetMapping("/right-join")
    public List<Articles> findByManyToOneRightJoin(@RequestParam(required = false) Integer authorId) {
        return articlesMapper.findByManyToOne(authorId);
    }

    /**
     * 多对多查询示例
     */
    @GetMapping("/m2m")
    public List<Articles> findByManyToMany(@RequestParam(required = false) Integer tagId) {
        return tagsMapper.findByManyToMany(tagId);
    }

}
