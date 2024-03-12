package work.pcdd.mybatis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.mybatis.entity.Authors;
import work.pcdd.mybatis.mapper.AuthorsMapper;

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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {

    final AuthorsMapper authorsMapper;

    /**
     * 一对多查询 demo，内连接实现
     * 传入 id 表示单查，否则表示全查
     * 如果你想要列出所有作者及其对应的文章，没写过文章的作者则不显示，这时就可以使用内连接
     */
    @GetMapping("/inner-join")
    public List<Authors> findByOneToManyInnerJoin(@RequestParam(required = false) Integer authorId) {
        return authorsMapper.findByInnerJoin(authorId);
    }

    /**
     * 一对多查询 demo，左外连接实现
     * 如果你想要列出所有作者及其对应的文章，即使有些作者还没有写过文章，也需要显示出来，这时就可以使用左外连接
     */
    @GetMapping("/left-join")
    public List<Authors> findByOneToManyLeftJoin(@RequestParam(required = false) Integer authorId) {
        return authorsMapper.findByLeftJoin(authorId);
    }

}
