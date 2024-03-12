package work.pcdd.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.mybatis.entity.Authors;
import work.pcdd.mybatis.mapper.ArticlesMapper;

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
public class ArticlesController {

    @Autowired
    ArticlesMapper articlesMapper;

    /**
     * 传入 id 表示单查，否则表示全查
     */
    @GetMapping
    public List<Authors> findById(@RequestParam(required = false) Integer id) {
        return articlesMapper.find(id);
    }

}
