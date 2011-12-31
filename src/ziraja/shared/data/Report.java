package ziraja.shared.data;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Report implements Serializable {
	private static final long serialVersionUID = -4179150374364680620L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private String question;
	@Persistent
	private Integer quality;
	@Persistent
	private String rawAnswer;
	@Persistent
	private String answer;
	@Persistent
	private String doesItMean;
	@Persistent
	private String comment;
	@Persistent
	private Date timeStamp;

	public Report() {
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDoesItMean() {
		return doesItMean;
	}

	public void setDoesItMean(String doesItMean) {
		this.doesItMean = doesItMean;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getId() {
		return id;
	}
	
	public String toString() {
		return question;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRawAnswer() {
		return rawAnswer;
	}

	public void setRawAnswer(String rawAnswer) {
		this.rawAnswer = rawAnswer;
	}
	
	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getQuality() {
		return quality;
	}

}
