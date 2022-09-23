package kr.co.teo.bizhomework.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="board")

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Board extends BaseEntity {

		//gno 값을 데이터베이스의 auto_increment 나 sequence를 이용해서 생성
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		//메모 번호
		private Long gno;
		
		@Column(length=100, nullable=false)
		//메모 제목
		private String title;
		
		@Column(length=1500, nullable=false)
		//메모 내용
		private String content;
		
		@Column(length=100, nullable=false)
		//메모 작성자
		private String writer;
		
		//title을 변경해주는 메서드
		public void changeTitle(String title) {
			this.title = title;
		}
		
		//content를 변경해주는 메서드
		public void changeContent(String content) {
			this.content = content;
		}
}
