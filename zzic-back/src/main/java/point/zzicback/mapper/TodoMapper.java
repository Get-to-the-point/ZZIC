package point.zzicback.mapper;

import org.apache.ibatis.annotations.*;
import point.zzicback.model.Todo;

import java.util.List;

@Mapper
public interface TodoMapper {
    /**
     * 아래는 보편적인 MyBatis Mapper 메소드들입니다.
     * 이미 기능이 구현되어 있으며, `TodoMapper.xml` 파일에 정의되어 있습니다.
     */
    int deleteByPrimaryKey(Long id); // JPA의 deleteById와 같은 역할

    int insert(Todo record); // JPA의 save와 같은 역할

    int insertSelective(Todo record); // JPA의 save와 같은 역할

    Todo selectByPrimaryKey(Long id);  // JPA의 findById와 같은 역할

    int updateByPrimaryKeySelective(Todo record);  // JPA의 save와 같은 역할

    int updateByPrimaryKey(Todo record); // JPA의 save와 같은 역할

    @Select("SELECT * FROM TODO")
    List<Todo> selectAll();

    @Select("SELECT * FROM TODO WHERE id = #{id}")
    Todo selectId(Long id);

    @Insert("INSERT INTO TODO (TITLE, DESCRIPTION, DONE) VALUES (#{title}, #{description}, #{done})")
    void createTodo(Todo todo);

    @Update("UPDATE TODO SET TITLE = #{title},DESCRIPTION = #{description},Done = #{done} WHERE id = #{id}")
    void updateTodo(Todo todo);

    @Delete("DELETE FROM TODO WHERE id = #{id}")
    void deleteTodo(Long id);

    @Select("SELECT * FROM TODO WHERE done = #{done}")
    List<Todo> findByDone(Boolean done);
}