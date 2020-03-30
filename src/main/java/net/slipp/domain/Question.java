package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
  private User writer;

  @OneToMany(mappedBy = "question")
  @OrderBy("id ASC")
  private List<Answer> answers;

  private String title;
  @Lob
  private String contents;
  private LocalDateTime createDate;


  public Question() {
  }

  public Question(User writer, String title, String contents) {
    super();
    this.title = title;
    this.contents = contents;
    this.writer = writer;
    this.createDate = LocalDateTime.now();
  }

  private String getFormattedCreateDate() {
    return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public void update(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public boolean isSameWriter(User loginUser) {
    return this.writer.isSameWriter(loginUser);
  }
}
