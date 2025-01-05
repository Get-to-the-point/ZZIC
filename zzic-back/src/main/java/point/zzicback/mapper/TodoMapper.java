package point.zzicback.mapper;

import org.apache.ibatis.annotations.*;
import point.zzicback.model.Todo;

import java.util.List;

@Mapper
public interface TodoMapper {
    ///
    /// 마이바티스는 애너테이션을 이용해서 쿼리를 작성할 수 있습니다.
    /// 인서트는 @Insert, 셀렉트는 @Select, 딜리트는 @Delete 애너테이션을 사용합니다.
    ///
    @Update("UPDATE todo SET done = #{done} WHERE id = #{id}")
    int updateDone(Todo todo);

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

    @Select("SELECT * FROM TODO WHERE ID = #{id}")
    Todo selectById(Long id);

    @Insert("INSERT INTO TODO(title, description, done) VALUES(#{title}, #{description}, TRUE)")
    void createTodo(Todo todo);

    @Update("UPDATE TODO SET title=#{title}, description=#{description}, done=#{done} WHERE id = #{id}")
    void updateTodo(Todo todo);

    @Delete("DELETE FROM TODO WHERE id = #{id}")
    void deleteTodo(Long id);
}