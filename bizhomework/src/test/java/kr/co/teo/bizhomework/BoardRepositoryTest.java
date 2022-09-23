package kr.co.teo.bizhomework;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kr.co.teo.bizhomework.model.Board;

public interface BoardRepositoryTest extends JpaRepository<Board, Long>,
QuerydslPredicateExecutor<Board> {

//title이 일치하는 데이터를 조회 - 이름을 이용해서 생성한 쿼리
List<Board> findByTitle(String title);

//직접 쿼리를 작성하는 방법
@Transactional
@Modifying
@Query("update Board b set b.title = :title, b.content = :content where b.gno = :gno")
int updateBoard(@Param("title") String title, 
		@Param("content") String content, @Param("gno") Long gno);

}

